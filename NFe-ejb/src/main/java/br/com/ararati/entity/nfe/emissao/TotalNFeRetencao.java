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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * W02. Grupo Totais referentes ao Retenção de Tributos
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "total_nfe_retencao")
public class TotalNFeRetencao extends AbstractEntity {

    @ManyToOne
    @NotNull(message = "Empresa Emitente é obrigatório")
    @JoinColumn(name = "emitente_id", nullable = true)
    private Emitente emitente;

    @OneToOne
    @JoinColumn(name = "total_nfe_id", nullable = false)
    private TotalNFe totalNFe;

    // Valor Retido de PIS 
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = true)
    private BigDecimal vretpis;
    // Valor Retido de COFINS
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = true)
    private BigDecimal vretcofins;
    // Valor Retido de CSLL
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = true)
    private BigDecimal vretcsll;
    // Base de Cálculo do IRRF
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = true)
    private BigDecimal vbcirrf;
    // Valor Retido do IRRF 
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = true)
    private BigDecimal virrf;
    // Base de Cálculo da Retenção da Previdência Socia
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = true)
    private BigDecimal vbcretprev;
    // Valor da Retenção da Previdência Social
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = true)
    private BigDecimal vretprev;

    public Emitente getEmitente() {
        return emitente;
    }

    public void setEmitente(Emitente emitente) {
        this.emitente = emitente;
    }

    public BigDecimal getVretpis() {
        return vretpis;
    }

    public void setVretpis(BigDecimal vretpis) {
        this.vretpis = vretpis;
    }

    public BigDecimal getVretcofins() {
        return vretcofins;
    }

    public void setVretcofins(BigDecimal vretcofins) {
        this.vretcofins = vretcofins;
    }

    public BigDecimal getVretcsll() {
        return vretcsll;
    }

    public void setVretcsll(BigDecimal vretcsll) {
        this.vretcsll = vretcsll;
    }

    public BigDecimal getVbcirrf() {
        return vbcirrf;
    }

    public void setVbcirrf(BigDecimal vbcirrf) {
        this.vbcirrf = vbcirrf;
    }

    public BigDecimal getVirrf() {
        return virrf;
    }

    public void setVirrf(BigDecimal virrf) {
        this.virrf = virrf;
    }

    public BigDecimal getVbcretprev() {
        return vbcretprev;
    }

    public void setVbcretprev(BigDecimal vbcretprev) {
        this.vbcretprev = vbcretprev;
    }

    public BigDecimal getVretprev() {
        return vretprev;
    }

    public void setVretprev(BigDecimal vretprev) {
        this.vretprev = vretprev;
    }

    public TotalNFe getTotalNFe() {
        return totalNFe;
    }

    public void setTotalNFe(TotalNFe totalNFe) {
        this.totalNFe = totalNFe;
    }

    
}
