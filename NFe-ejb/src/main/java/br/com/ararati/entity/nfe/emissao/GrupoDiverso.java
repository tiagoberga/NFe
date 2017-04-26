/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.nfe.emissao;

import br.com.ararati.entity.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Identificação do Destinatário da Nota Fiscal eletrônica
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "grupo_diversos")
public class GrupoDiverso extends AbstractEntity {

    @Column(length = 36, nullable = true)
    private String nfci;

    public String getNfci() {
        return nfci;
    }

    public void setNfci(String nfci) {
        this.nfci = nfci;
    }

}
