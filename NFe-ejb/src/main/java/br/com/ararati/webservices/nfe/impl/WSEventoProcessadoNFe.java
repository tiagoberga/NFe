/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.webservices.nfe.impl;

import br.com.ararati.operacoes.Tools;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author tiago
 */
public class WSEventoProcessadoNFe {

    /**
     * Método responsável por montar xml procEvento
     *
     * @param xmlEnvEventoAssinado String
     * @param xmlRetEnvEventoNFe String
     * @param versaoXml String
     * @return String
     */
    public String getRetornoEventoProcessado(String xmlEnvEventoAssinado, String xmlRetEnvEventoNFe, String versaoXml) throws SAXException, IOException, ParserConfigurationException, TransformerException {
        StringBuilder xmlProcEnvEvento = new StringBuilder();

        Document doc = Tools.convertToDocumentFromInputStream(new ByteArrayInputStream(xmlEnvEventoAssinado.getBytes()));
        doc.getDocumentElement().normalize();

        NodeList nodeListNfe = doc.getDocumentElement().getElementsByTagName("evento");
        NodeList nodeListInfNfe = doc.getElementsByTagName("infEvento");
        // ENQUANTO HOUVER ELEMENTOS COM TAG "<evento>
        for (int i = 0; i < nodeListNfe.getLength(); i++) {
            Element el = (Element) nodeListInfNfe.item(i);
            String chaveNFe = el.getAttribute("Id");

            String xmlEvento = outputXML(nodeListNfe.item(i));
            String xmlRetEvento = getRetEvento(xmlRetEnvEventoNFe, chaveNFe);

            xmlProcEnvEvento.append(buildProcEventoNFe(xmlEvento, xmlRetEvento, versaoXml));
        }

        return xmlProcEnvEvento.toString();

//        try {
//            return GenericJAXBTools.unmarshallFromXMLToObject(procEventoNFe_v100.TProcEvento.class, xmlProcEnvEvento.toString());
//        } catch (JAXBException ex) {
//            Logger.getLogger(WSEventoProcessadoNFe.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
    }

    private String buildProcEventoNFe(String xmlEvento, String xmlRetEvento, String versaoXml) {
        StringBuilder nfeProc = new StringBuilder();
        nfeProc.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
                .append("<procEventoNFe versao=\"").append(versaoXml).append("\" xmlns=\"http://www.portalfiscal.inf.br/nfe\">")
                .append(xmlEvento)
                .append(xmlRetEvento)
                .append("</procEventoNFe>");
        return nfeProc.toString();
    }

    private String getRetEvento(String xmlRetEnvEventoNFe, String chaveNFe) throws SAXException, IOException, ParserConfigurationException, TransformerException {
        Document document = Tools.convertToDocumentFromString(xmlRetEnvEventoNFe);
        NodeList nodeListProtNFe = document.getDocumentElement().getElementsByTagName("retEvento");
        NodeList nodeListChNFe = document.getElementsByTagName("chNFe");
        for (int i = 0; i < nodeListProtNFe.getLength(); i++) {
            Element el = (Element) nodeListChNFe.item(i);
            String chaveProtNFe = el.getTextContent();
            if (chaveNFe.contains(chaveProtNFe)) {
                return outputXML(nodeListProtNFe.item(i));
            }
        }
        return "";
    }

    private String outputXML(Node node) throws TransformerException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer trans = tf.newTransformer();
        trans.transform(new DOMSource(node), new StreamResult(os));

        String xml = os.toString();
        if ((xml != null) && (!"".equals(xml))) {
            xml = xml.replaceAll("<\\?xml version=\"1.0\" encoding=\"UTF-8\"\\?>", "");
        }

        return xml;
    }

    public static String lerXML(String fileXML) throws IOException {
        String linha = "";
        StringBuilder xml = new StringBuilder();

        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileXML)));
        while ((linha = in.readLine()) != null) {
            xml.append(linha);
        }

        in.close();
        return xml.toString();
    }
}
