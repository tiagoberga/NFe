/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.filter;

import br.com.ararati.entity.cadastros.Emitente;
import java.io.Serializable;

/**
 * @author tiago
 */
public class AbstractFilter implements Serializable {

    private Emitente emitente;

    public Emitente getEmitente() {
        return emitente;
    }

    public void setEmitente(Emitente emitente) {
        this.emitente = emitente;
    }

}
