/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.populadores;

import br.com.ararati.populadores.impl.ConsStatServ;
import javax.xml.bind.JAXBException;

/**
 *
 * @author tiago
 */
public class GeradorXmlFacade {

    public String fillConsultaStatusServico(String cuf, String tpAmb, String versao) throws JAXBException {
        return new ConsStatServ().fillTConsStatServ(cuf, tpAmb, versao);
    }

}
