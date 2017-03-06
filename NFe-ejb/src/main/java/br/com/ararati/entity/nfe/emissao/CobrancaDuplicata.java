/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.nfe.emissao;

import br.com.ararati.entity.cadastros.*;
import br.com.ararati.entity.AbstractEntity;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * Dados da Cobrança
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "cobranca_duplicata")
public class CobrancaDuplicata extends AbstractEntity {

    @ManyToOne
    @NotNull(message = "Empresa Emitente é obrigatório")
    @JoinColumn(name = "emitente_id", nullable = true)
    private Emitente emitente;

    @OneToOne
    @JoinColumn(name = "dados_nfe_id", nullable = false)
    private DadosNFe dadosNFe;

    // Número da Duplicata
    @Length(min = 1, max = 15, message = "Número da Duplicata deve conter entre {min} e {max} caracteres")
    @Column(length = 60, nullable = true)
    private String ndup;
    // Data de vencimento
    @Temporal(TemporalType.DATE)
    private Date dvenc;
    // Valor da duplicata
    @DecimalMin(value = "0.00")
    @Column(precision = 13, scale = 2, nullable = true)
    private BigDecimal vdup;

    public Emitente getEmitente() {
        return emitente;
    }

    public void setEmitente(Emitente emitente) {
        this.emitente = emitente;
    }

    public DadosNFe getDadosNFe() {
        return dadosNFe;
    }

    public void setDadosNFe(DadosNFe dadosNFe) {
        this.dadosNFe = dadosNFe;
    }

    public String getNdup() {
        return ndup;
    }

    public void setNdup(String ndup) {
        this.ndup = ndup;
    }

    public Date getDvenc() {
        return dvenc;
    }

    public void setDvenc(Date dvenc) {
        this.dvenc = dvenc;
    }

    public BigDecimal getVdup() {
        return vdup;
    }

    public void setVdup(BigDecimal vdup) {
        this.vdup = vdup;
    }

}
