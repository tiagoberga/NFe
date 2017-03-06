/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.webservices.cep;

import br.com.ararati.Cep;
import br.com.ararati.Consulta;
import br.com.ararati.ConsultaCepException;

/**
 *
 * @author tiago
 */
public class WSCepFacade {

    public Cep getCepFromXml(String cep) throws ConsultaCepException{
        return new Consulta().getCepFromXml(cep);
    }

    public Cep getCepFromJson(String cep) throws ConsultaCepException {
        return new Consulta().getCepFromJson(cep);
    }

}
