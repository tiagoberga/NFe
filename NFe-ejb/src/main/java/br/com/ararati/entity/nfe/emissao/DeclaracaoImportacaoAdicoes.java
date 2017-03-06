/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.nfe.emissao;

import br.com.ararati.entity.cadastros.*;
import br.com.ararati.entity.AbstractEntity;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * Identificação do Destinatário da Nota Fiscal eletrônica
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "declaracao_importacao_adicoes")
public class DeclaracaoImportacaoAdicoes extends AbstractEntity {

    @ManyToOne
    @NotNull(message = "Empresa Emitente é obrigatório")
    @JoinColumn(name = "emitente_id", nullable = false)
    private Emitente emitente;

    @ManyToOne
    @JoinColumn(name = "declaracao_importacao_id", nullable = false)
    private DeclaracaoImportacao declaracaoImportacao;

    // Numero da Adição 
    @Column(length = 3, nullable = false)
    private String nadicao;
    // Numero sequencial do item dentro da Adição
    @Column(length = 3, nullable = false)
    private String nseqadic;
    // Código do fabricante estrangeiro
    @Column(length = 60, nullable = false)
    private String cfabricante;
    // Valor do desconto do item da DI – Adição
    @DecimalMin(value = "0.00")
    @Column(precision = 13, scale = 2, nullable = true)
    private BigDecimal vdescdi;
    // Número do ato concessório de Drawback 
    @Column(length = 11, nullable = true)
    private String ndraw;

    public Emitente getEmitente() {
        return emitente;
    }

    public void setEmitente(Emitente emitente) {
        this.emitente = emitente;
    }

    public DeclaracaoImportacao getDeclaracaoImportacao() {
        return declaracaoImportacao;
    }

    public void setDeclaracaoImportacao(DeclaracaoImportacao declaracaoImportacao) {
        this.declaracaoImportacao = declaracaoImportacao;
    }

    public String getNadicao() {
        return nadicao;
    }

    public void setNadicao(String nadicao) {
        this.nadicao = nadicao;
    }

    public String getNseqadic() {
        return nseqadic;
    }

    public void setNseqadic(String nseqadic) {
        this.nseqadic = nseqadic;
    }

    public String getCfabricante() {
        return cfabricante;
    }

    public void setCfabricante(String cfabricante) {
        this.cfabricante = cfabricante;
    }

    public BigDecimal getVdescdi() {
        return vdescdi;
    }

    public void setVdescdi(BigDecimal vdescdi) {
        this.vdescdi = vdescdi;
    }

    public String getNdraw() {
        return ndraw;
    }

    public void setNdraw(String ndraw) {
        this.ndraw = ndraw;
    }

}
