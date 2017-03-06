/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.operacoes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author tiago
 */
public class AssinadorSchemaA1 {

    private static final String INFINUT = "infInut";
    private static final String EVENTO = "evento";
    private static final String NFE = "NFe";

    private PrivateKey privateKey;
    private KeyInfo keyInfo;

    /**
     * Assinando o XML de EnvNFe da NF-e.
     *
     * @param xml
     * @param certificadoArquivo
     * @param certificadoSenha
     * @return
     * @throws java.lang.Exception
     */
    public String assinaXmlEnviNFe(String xml, byte[] certificadoArquivo, String certificadoSenha) throws Exception {
        AssinadorSchemaA1 assinaXml = new AssinadorSchemaA1();
        return assinaXml.assinaEnviNFe(xml, certificadoArquivo, certificadoSenha);
    }

    /**
     * Assinando o XML de InutNFe da NF-e.
     *
     * @param xml
     * @param certificadoArquivo
     * @param certificadoSenha
     * @return
     * @throws java.lang.Exception
     */
    public String assinaXmlInutNFe(String xml, byte[] certificadoArquivo, String certificadoSenha) throws Exception {
        AssinadorSchemaA1 assinaXml = new AssinadorSchemaA1();
        return assinaXml.assinaInutNFe(xml, certificadoArquivo, certificadoSenha);
    }

    /**
     * Assinando o XML de EnvEvento da NF-e.
     *
     * @param xml
     * @param certificadoArquivo
     * @param certificadoSenha
     * @return
     * @throws java.lang.Exception
     */
    public String assinaXmlEnvEvento(String xml, byte[] certificadoArquivo, String certificadoSenha) throws Exception {
        AssinadorSchemaA1 assinaXml = new AssinadorSchemaA1();
        return assinaXml.assinaEnvEvento(xml, certificadoArquivo, certificadoSenha);
    }

    /**
     * Assinatura do XML de Envio de Lote da NF-e utilizando Certificado Digital
     * A1.
     *
     * @param xml
     * @param certificadoArquivo
     * @param certificadoSenha
     * @return
     * @throws Exception
     */
    private String assinaEnviNFe(String xml, byte[] certificadoArquivo, String certificadoSenha) throws Exception {
        Document document = documentFactory(xml);
        XMLSignatureFactory signatureFactory = XMLSignatureFactory.getInstance("DOM");
        ArrayList<Transform> transformList = signatureFactory(signatureFactory);
        loadCertificates(certificadoArquivo, certificadoSenha, signatureFactory);

        for (int i = 0; i < document.getDocumentElement().getElementsByTagName(NFE).getLength(); i++) {
            assinar(NFE, signatureFactory, transformList, privateKey, keyInfo, document, i);
        }

        return outputXML(document);
    }

    /**
     * Assintaruda do XML de Eventos da NF-e utilizando Certificado Digital A1.
     *
     * @param InputStream
     * @param certificadoArquivo
     * @param certificadoSenha
     * @return
     * @throws Exception
     */
    private String assinaEnvEvento(String xml, byte[] certificadoArquivo, String certificadoSenha) throws Exception {
        Document document = documentFactory(xml);
        XMLSignatureFactory signatureFactory = XMLSignatureFactory.getInstance("DOM");
        ArrayList<Transform> transformList = signatureFactory(signatureFactory);
        loadCertificates(certificadoArquivo, certificadoSenha, signatureFactory);

        for (int i = 0; i < document.getDocumentElement().getElementsByTagName(EVENTO).getLength(); i++) {
            assinar(EVENTO, signatureFactory, transformList, privateKey, keyInfo, document, i);
        }

        return outputXML(document);
    }

    /**
     * Assinatura do XML de INUTILIZACAO da NF-e utilizando Certificado Digital
     * A1.
     *
     * @param xml
     * @param certificadoArquivo
     * @param certificadoSenha
     * @return
     * @throws Exception
     */
    private String assinaInutNFe(String xml, byte[] certificadoArquivo, String certificadoSenha) throws Exception {
        Document document = documentFactory(xml);
        XMLSignatureFactory signatureFactory = XMLSignatureFactory.getInstance("DOM");
        ArrayList<Transform> transformList = signatureFactory(signatureFactory);
        loadCertificates(certificadoArquivo, certificadoSenha, signatureFactory);
        assinarInutilizacao(INFINUT, signatureFactory, transformList, privateKey, document, 0);

        return outputXML(document);
    }

    /**
     * Método responsável por assinar XMLs de TNFe e Eventos
     *
     * @param tipo String: variáveis estaticas EVENTO ou NFE
     * @param fac XMLSignatureFactory
     * @param transformList ArrayList<Transform>
     * @param privateKey PrivateKey
     * @param ki KeyInfo
     * @param document Document
     * @param index int
     */
    private void assinar(String tipo, XMLSignatureFactory fac, ArrayList<Transform> transformList, PrivateKey privateKey, KeyInfo ki, Document document, int index) throws Exception {
        NodeList elements = null;

        if (EVENTO.equals(tipo)) {
            elements = document.getElementsByTagName("infEvento");
        } else if (NFE.equals(tipo)) {
            elements = document.getElementsByTagName("infNFe");
        } 

        org.w3c.dom.Element el = (org.w3c.dom.Element) elements.item(index);
        String id = el.getAttribute("Id");
        el.setIdAttribute("Id", true);

        Reference ref = fac.newReference("#" + id,
                fac.newDigestMethod(DigestMethod.SHA1, null),
                transformList,
                null, null);

        SignedInfo si = fac.newSignedInfo(
                fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE, (C14NMethodParameterSpec) null),
                fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
                Collections.singletonList(ref));

        XMLSignature signature = fac.newXMLSignature(si, ki);

        DOMSignContext dsc = new DOMSignContext(privateKey, document.getDocumentElement().getElementsByTagName(tipo).item(index));
        signature.sign(dsc);
    }

    /**
     * Método responsável por assinar XMLs de TInutNFe
     *
     * @param tipo String: variáveis estaticas EVENTO ou NFE
     * @param fac XMLSignatureFactory
     * @param transformList ArrayList<Transform>
     * @param privateKey PrivateKey
     * @param ki KeyInfo
     * @param document Document
     * @param index int
     */
    private String assinarInutilizacao(String tipo, XMLSignatureFactory fac, ArrayList<Transform> transformList, PrivateKey privateKey, Document document, int index) throws Exception {
        NodeList elements = document.getElementsByTagName(tipo);
        org.w3c.dom.Element el = (org.w3c.dom.Element) elements.item(index);
        String id = el.getAttribute("Id");
        el.setIdAttribute("Id", true);

        Reference ref = fac.newReference("#" + id, fac.newDigestMethod(DigestMethod.SHA1, null), transformList, null, null);

        SignedInfo si = fac.newSignedInfo(
                fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE, (C14NMethodParameterSpec) null),
                fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
                Collections.singletonList(ref));

        XMLSignature signature = fac.newXMLSignature(si, keyInfo);

        DOMSignContext dsc = new DOMSignContext(privateKey, document.getFirstChild());
        signature.sign(dsc);

        return outputXML(document);
    }

    private ArrayList<Transform> signatureFactory(XMLSignatureFactory signatureFactory) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        ArrayList<Transform> transformList = new ArrayList<Transform>();
        TransformParameterSpec tps = null;
        Transform envelopedTransform = signatureFactory.newTransform(Transform.ENVELOPED, tps);
        Transform c14NTransform = signatureFactory.newTransform("http://www.w3.org/TR/2001/REC-xml-c14n-20010315", tps);

        transformList.add(envelopedTransform);
        transformList.add(c14NTransform);
        return transformList;
    }

    private Document documentFactory(String xml) throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        Document document = factory.newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes()));
        return document;
    }

    private void loadCertificates(byte[] certificadoArquivo, String certificadoSenha, XMLSignatureFactory signatureFactory) throws Exception {
        InputStream isCertificado = new ByteArrayInputStream(certificadoArquivo);
        KeyStore ks = KeyStore.getInstance("pkcs12");
        try {
            ks.load(isCertificado, certificadoSenha.toCharArray());
        } catch (IOException e) {
            throw new Exception("Senha do Certificado Digital incorreta ou Certificado inválido.");
        }

        KeyStore.PrivateKeyEntry pkEntry = null;
        Enumeration<String> aliasesEnum = ks.aliases();
        while (aliasesEnum.hasMoreElements()) {
            String alias = (String) aliasesEnum.nextElement();
            if (ks.isKeyEntry(alias)) {
                pkEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(alias, new KeyStore.PasswordProtection(certificadoSenha.toCharArray()));
                privateKey = pkEntry.getPrivateKey();
                break;
            }
        }

        X509Certificate cert = (X509Certificate) pkEntry.getCertificate();

        KeyInfoFactory keyInfoFactory = signatureFactory.getKeyInfoFactory();
        List<X509Certificate> x509Content = new ArrayList<X509Certificate>();

        x509Content.add(cert);
        X509Data x509Data = keyInfoFactory.newX509Data(x509Content);
        keyInfo = keyInfoFactory.newKeyInfo(Collections.singletonList(x509Data));
    }

    private String outputXML(Document doc) throws TransformerException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer trans = tf.newTransformer();
        trans.transform(new DOMSource(doc), new StreamResult(os));
        String xml = os.toString();
        if ((xml != null) && (!"".equals(xml))) {
            xml = xml.replaceAll("\\r\\n", "");
            xml = xml.replaceAll(" standalone=\"no\"", "");
        }
        return xml;
    }
}
