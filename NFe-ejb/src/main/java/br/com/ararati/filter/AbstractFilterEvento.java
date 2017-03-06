/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.filter;

import br.com.ararati.filter.AbstractFilter;

/**
 *
 * @author tiago
 */
public abstract class AbstractFilterEvento extends AbstractFilter {

    public String getDetVersao() {
        return "1.00";
    }

    public String getInfEventoVersao() {
        return "1.00";
    }

    public String getEventoVersao() {
        return "1.00";
    }

    public String getEnvEventoVersao() {
        return "1.00";
    }

}
