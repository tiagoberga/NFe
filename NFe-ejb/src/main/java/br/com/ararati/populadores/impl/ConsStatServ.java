/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.populadores.impl;

import consStatServ_v400.TConsStatServ;
import consStatServ_v400.ObjectFactory;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

/**
 *
 * @author tiago
 */
public class ConsStatServ {

    private String jaxbToXml(TConsStatServ stat) throws PropertyException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(TConsStatServ.class);
        JAXBElement<TConsStatServ> element = new ObjectFactory().createConsStatServ(stat);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

        StringWriter sw = new StringWriter();
        marshaller.marshal(element, sw);

        String xml = sw.toString();
        xml = xml.replaceAll("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\" ", "");
        return xml;
    }

    /**
     * Método responsável por retornar xml montado de TConsStatServ.
     *
     * @param cuf
     * @param tpAmb
     * @param versao
     * @return
     * @throws javax.xml.bind.JAXBException
     */
    public String fillTConsStatServ(String cuf, String tpAmb, String versao) throws JAXBException {
        TConsStatServ stat = new TConsStatServ();
        stat.setCUF(cuf);
        stat.setTpAmb(tpAmb);
        stat.setVersao(versao);
        stat.setXServ("STATUS");
        return jaxbToXml(stat);
    }
}
