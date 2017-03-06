/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.operacoes;

import br.com.ararati.exception.NFeException;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import javax.ejb.Stateless;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * @author tiago
 */
@Stateless
public class Tools {

    /**
     * Método responsável por converter um InputStream em String.
     *
     * @param InputStream entra: inputStream
     * @return String sai: string
     */
    public static String convertToStringFromInputStream(InputStream is) {
        BufferedReader in = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }

    public static String convertToStringFromDocument(Document doc) {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();
            // below code to remove XML declaration
            // transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            String output = writer.getBuffer().toString();
            return output;
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Document convertToDocumentFromString(String xml) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(xml.getBytes()));
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Document convertToDocumentFromInputStream(InputStream xml) {
        DocumentBuilderFactory factory = null;
        DocumentBuilder builder = null;
        Document ret = null;

        try {
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        try {
            ret = builder.parse(new InputSource(xml));
        } catch (SAXParseException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static String obtemVersaoXmlProcNFe(byte[] arquivoXml) throws NFeException {
        String versaoXml = null;
        String conteudoXml;
        Document doc;
        NodeList nodeList;
        Node node;
        Element element;

        // OBTEM ARQUIVO XML
        conteudoXml = convertToStringFromInputStream(new ByteArrayInputStream(arquivoXml));
        // TRANFORMA O CONTEUDO XML EM DOM-W3
        doc = convertToDocumentFromInputStream(new ByteArrayInputStream(conteudoXml.getBytes()));
        doc.getDocumentElement().normalize();
        // OBTEM A TAG "nfeProc"
        nodeList = doc.getElementsByTagName("nfeProc");

        if (nodeList != null) {
            node = nodeList.item(0);
            // OBTEM O VALOR DO ATRIBUTO "versao" DA TAG "nfeProc"
            if (node != null) {
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    element = (Element) node;
                    versaoXml = element.getAttribute("versao");
                }
            } else {
                throw new NFeException("Arquivo XML esperado é o Autorizado (procNFe.xml)");
            }
        } else {
            throw new NFeException("Arquivo XML esperado é o Autorizado (procNFe.xml)");
        }

        return versaoXml;
    }
}
