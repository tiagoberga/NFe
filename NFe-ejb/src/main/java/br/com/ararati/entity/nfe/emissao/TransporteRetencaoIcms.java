/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.nfe.emissao;

import br.com.ararati.entity.AbstractEntity;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;

/**
 * Informações do Transporte da NF-e
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "transporte_retencao_icms")
public class TransporteRetencaoIcms extends AbstractEntity {


    @OneToOne
    @JoinColumn(name = "transporte_nfe_id", nullable = false)
    private TransporteNFe transporteNFe;

    // Valor do Serviço 
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = true)
    private BigDecimal vserv;
    // BC da Retenção do ICMS 
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = true)
    private BigDecimal vbcret;
    // Alíquota da Retenção
    @DecimalMin(value = "0.0000")
    @Column(precision = 7, scale = 4, nullable = true)
    private BigDecimal picmsret;
    // Valor do ICMS Retido
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = true)
    private BigDecimal vicmsret;
    // CFOP
    @Column(length = 2, nullable = true)
    private String cfop;
    // Código do município de ocorrência do fato gerador do ICMS do transporte
    @Column(length = 7, nullable = true)
    private String cmunfg;

    public TransporteNFe getTransporteNFe() {
        return transporteNFe;
    }

    public void setTransporteNFe(TransporteNFe transporteNFe) {
        this.transporteNFe = transporteNFe;
    }

    public BigDecimal getVserv() {
        return vserv;
    }

    public void setVserv(BigDecimal vserv) {
        this.vserv = vserv;
    }

    public BigDecimal getVbcret() {
        return vbcret;
    }

    public void setVbcret(BigDecimal vbcret) {
        this.vbcret = vbcret;
    }

    public BigDecimal getPicmsret() {
        return picmsret;
    }

    public void setPicmsret(BigDecimal picmsret) {
        this.picmsret = picmsret;
    }

    public BigDecimal getVicmsret() {
        return vicmsret;
    }

    public void setVicmsret(BigDecimal vicmsret) {
        this.vicmsret = vicmsret;
    }

    public String getCfop() {
        return cfop;
    }

    public void setCfop(String cfop) {
        this.cfop = cfop;
    }

    public String getCmunfg() {
        return cmunfg;
    }

    public void setCmunfg(String cmunfg) {
        this.cmunfg = cmunfg;
    }

}
