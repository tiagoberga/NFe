package br.com.ararati.operacoes;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class SimpleErrorHandler implements ErrorHandler{
     
    public void error(SAXParseException exception)
            throws SAXException{
         if (!exception.getMessage().equals("cvc-elt.1: Cannot find the declaration of element 'enviNFe'.")){
             throw exception;
         }
     }

    public void fatalError(SAXParseException exception)
            throws SAXException {
         throw exception;
     }

    public void warning(SAXParseException exception)
            throws SAXException{
         throw exception;
     }
} 