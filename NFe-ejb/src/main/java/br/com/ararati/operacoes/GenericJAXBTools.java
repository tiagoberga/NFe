/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.operacoes;

import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Element;

/**
 *
 * @author tiago
 */
public class GenericJAXBTools implements Serializable {

    public static <T> Class marshalFromElementToObject(Element element, Class<T> classReference) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(classReference.getClass());
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return unmarshaller.unmarshal(element, classReference.getClass()).getValue();
    }

    /**
     * Converte qualquer xml em schema mapeado com '@XmlRootElement'.
     *
     * @param <T> Tipo de classe a ser instanciada pelo JAXB
     * @param classReference
     * @param xmlToUnmarshall
     * @return
     * @throws JAXBException
     */
    public static <T> T unmarshallFromXMLToObject(Class<T> classReference, String xmlToUnmarshall) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(classReference);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return unmarshaller.unmarshal(new StreamSource(new StringReader(xmlToUnmarshall)), classReference).getValue();
    }

    /**
     * Converte qualquer schema JAXB @XmlRootElement em xml.
     *
     * @param <T> Tipo de classe a ser instanciada pelo JAXB
     * @param object
     * @return
     * @throws JAXBException
     */
    public static <T> String marshalFromObjectToXML(T object) throws JAXBException {
        return GenericJAXBTools.marshalFromObjectToXML(object, null);
    }

    /**
     * Converte qualquer schema JAXB @XmlRootElement em xml.
     *
     * @param <T> Tipo de classe a ser instanciada pelo JAXB
     * @param object
     * @param properties propriedades à serem adicionadas ao Marshaller.
     * @return
     * @throws JAXBException
     */
    public static <T> String marshalFromObjectToXML(T object, Map<String, Object> properties) throws JAXBException {
        StringWriter sw = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = context.createMarshaller();

        // ADICIONA PROPRIEDADES DO MARSHALLER
        if (properties != null && !properties.isEmpty()) {
            for (Map.Entry<String, Object> entry : properties.entrySet()) {
                marshaller.setProperty(entry.getKey(), entry.getValue());
            }
        }

        marshaller.marshal(object, sw);

        return sw.toString();
    }

    public static <T> String marshalFromObjectToXML(T object, JAXBElement element, Map<String, Object> properties) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = context.createMarshaller();

        // ADICIONA PROPRIEDADES DO MARSHALLER
        if (properties != null && !properties.isEmpty()) {
            for (Map.Entry<String, Object> entry : properties.entrySet()) {
                marshaller.setProperty(entry.getKey(), entry.getValue());
            }
        }

        StringWriter sw = new StringWriter();
        marshaller.marshal(element, sw);

        return sw.toString();
    }

    public static <T> String objToXml(JAXBElement<T> element, T object) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

        StringWriter sw = new StringWriter();
        marshaller.marshal(element, sw);
        return sw.toString();
    }

    public static <T> T xmlToObj(String xml, T object) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Unmarshaller unmarshaller = context.createUnmarshaller();
        JAXBElement<T> element = (JAXBElement<T>) unmarshaller.unmarshal(new StringReader(xml));
        return element.getValue();
    }
}
