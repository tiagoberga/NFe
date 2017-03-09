/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.nfe.emissao;

import br.com.ararati.entity.cadastros.*;
import br.com.ararati.entity.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * Identificação do Destinatário da Nota Fiscal eletrônica
 *
 * @author tiago viado
 */
@Entity
@Table(schema = "nfe", name = "detalhamento_especifico_armamento")
public class DetalhamentoEspecificoArmamento extends AbstractEntity {

    @ManyToOne
    @NotNull(message = "Empresa Emitente é obrigatório")
    @JoinColumn(name = "emitente_id", nullable = false)
    private Emitente emitente;

    @ManyToOne
    @JoinColumn(name = "detalhamento_produto_servico_id", nullable = false)
    private DetalhamentoProdutoServico detalhamentoProdutoServico;

    // Indicador do tipo de arma de fogo 
    @NotNull(message = "Tipo da Arma é obrigatório")
    @Column(length = 1, nullable = false)
    private String tparma;
    // Número de série da arma 
    @NotNull(message = "Número de Série da Arma é obrigatório")
    @Length(min = 1, max = 15, message = "Número de Série da Arma deve conter entre {min} e {max} caracteres")
    @Column(length = 15, nullable = false)
    private String nserie;
    // Número de série do cano
    @NotNull(message = "Número de Série do Cano é obrigatório")
    @Length(min = 1, max = 15, message = "Número de Série da Cano deve conter entre {min} e {max} caracteres")
    @Column(length = 15, nullable = false)
    private String ncano;
    @NotNull(message = "Descrição Completa é obrigatório")
    @Length(min = 1, max = 256, message = "Descrição Completa deve conter entre {min} e {max} caracteres")
    @Column(length = 256, nullable = false)
    private String descr;

    public Emitente getEmitente() {
        return emitente;
    }

    public void setEmitente(Emitente emitente) {
        this.emitente = emitente;
    }

    public DetalhamentoProdutoServico getDetalhamentoProdutoServico() {
        return detalhamentoProdutoServico;
    }

    public void setDetalhamentoProdutoServico(DetalhamentoProdutoServico detalhamentoProdutoServico) {
        this.detalhamentoProdutoServico = detalhamentoProdutoServico;
    }

    public String getTparma() {
        return tparma;
    }

    public void setTparma(String tparma) {
        this.tparma = tparma;
    }

    public String getNserie() {
        return nserie;
    }

    public void setNserie(String nserie) {
        this.nserie = nserie;
    }

    public String getNcano() {
        return ncano;
    }

    public void setNcano(String ncano) {
        this.ncano = ncano;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

}
