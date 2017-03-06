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

/**
 * W01. Grupo Totais referentes ao ISSQN
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "total_nfe_issqn")
public class TotalNFeISSQN extends AbstractEntity {

    @ManyToOne
    @NotNull(message = "Empresa Emitente é obrigatório")
    @JoinColumn(name = "emitente_id", nullable = true)
    private Emitente emitente;
    
    @OneToOne
    @JoinColumn(name = "total_nfe_id", nullable = false)
    private TotalNFe totalNFe;

    // Valor total dos Serviços sob não-incidência ou não tributados pelo ICMS
    @DecimalMin(value = "0.00")
    @Column(precision = 13, scale = 2, nullable = true)
    private BigDecimal vserv;
    // Valor total Base de Cálculo do ISS
    @DecimalMin(value = "0.00")
    @Column(precision = 13, scale = 2, nullable = true)
    private BigDecimal vbc;
    // Valor total do ISS 
    @DecimalMin(value = "0.00")
    @Column(precision = 13, scale = 2, nullable = true)
    private BigDecimal viss;
    // Valor total do PIS sobre serviços 
    @DecimalMin(value = "0.00")
    @Column(precision = 13, scale = 2, nullable = true)
    private BigDecimal vpis;
    // Valor total da COFINS sobre serviços
    @DecimalMin(value = "0.00")
    @Column(precision = 13, scale = 2, nullable = true)
    private BigDecimal vcofins;
    // Data da prestação do serviço 
    @Temporal(TemporalType.DATE)
    private Date dcompet;
    // Valor total dedução para redução da Base de Cálculo
    @DecimalMin(value = "0.00")
    @Column(precision = 13, scale = 2, nullable = true)
    private BigDecimal vdeducao;
    // Valor total outras retenções
    @DecimalMin(value = "0.00")
    @Column(precision = 13, scale = 2, nullable = true)
    private BigDecimal voutro;
    // Valor total desconto incondicionado
    @DecimalMin(value = "0.00")
    @Column(precision = 13, scale = 2, nullable = true)
    private BigDecimal vdescincond;
    // Valor total desconto condicionado 
    @DecimalMin(value = "0.00")
    @Column(precision = 13, scale = 2, nullable = true)
    private BigDecimal vdesccond;
    // Valor total retenção ISS 
    @DecimalMin(value = "0.00")
    @Column(precision = 13, scale = 2, nullable = true)
    private BigDecimal vissret;
    // Código do Regime Especial de Tributação
    @Column(length = 2, nullable = true)
    private String cregtrib;

    public Emitente getEmitente() {
        return emitente;
    }

    public void setEmitente(Emitente emitente) {
        this.emitente = emitente;
    }

    public BigDecimal getVserv() {
        return vserv;
    }

    public void setVserv(BigDecimal vserv) {
        this.vserv = vserv;
    }

    public BigDecimal getVbc() {
        return vbc;
    }

    public void setVbc(BigDecimal vbc) {
        this.vbc = vbc;
    }

    public BigDecimal getViss() {
        return viss;
    }

    public void setViss(BigDecimal viss) {
        this.viss = viss;
    }

    public BigDecimal getVpis() {
        return vpis;
    }

    public void setVpis(BigDecimal vpis) {
        this.vpis = vpis;
    }

    public BigDecimal getVcofins() {
        return vcofins;
    }

    public void setVcofins(BigDecimal vcofins) {
        this.vcofins = vcofins;
    }

    public Date getDcompet() {
        return dcompet;
    }

    public void setDcompet(Date dcompet) {
        this.dcompet = dcompet;
    }

    public BigDecimal getVdeducao() {
        return vdeducao;
    }

    public void setVdeducao(BigDecimal vdeducao) {
        this.vdeducao = vdeducao;
    }

    public BigDecimal getVoutro() {
        return voutro;
    }

    public void setVoutro(BigDecimal voutro) {
        this.voutro = voutro;
    }

    public BigDecimal getVdescincond() {
        return vdescincond;
    }

    public void setVdescincond(BigDecimal vdescincond) {
        this.vdescincond = vdescincond;
    }

    public BigDecimal getVdesccond() {
        return vdesccond;
    }

    public void setVdesccond(BigDecimal vdesccond) {
        this.vdesccond = vdesccond;
    }

    public BigDecimal getVissret() {
        return vissret;
    }

    public void setVissret(BigDecimal vissret) {
        this.vissret = vissret;
    }

    public String getCregtrib() {
        return cregtrib;
    }

    public void setCregtrib(String cregtrib) {
        this.cregtrib = cregtrib;
    }

    public TotalNFe getTotalNFe() {
        return totalNFe;
    }

    public void setTotalNFe(TotalNFe totalNFe) {
        this.totalNFe = totalNFe;
    }

    
}
