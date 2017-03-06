package br.com.ararati.operacoes;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.SAXException;

public class Valida{
    private SAXException saxException;

    public boolean valida(String xmlFile, String xsdDirectory){
        boolean validado = false;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        factory.setNamespaceAware(true);
        factory.setValidating(true);
        factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
        factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaSource",   xsdDirectory);
        DocumentBuilder builder = null;

        try{
            builder = factory.newDocumentBuilder();
            builder.setErrorHandler(new SimpleErrorHandler());
            builder.parse(xmlFile);
            validado = true;
        }catch(Exception e){
            if(e instanceof SAXException){
                saxException = (SAXException)e;
            }
        }

        return validado;
    }

    public SAXException getSaxException(){
        return saxException;
    }
}