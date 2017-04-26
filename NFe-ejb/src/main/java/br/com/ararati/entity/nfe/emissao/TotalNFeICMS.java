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
 * W. Grupo Totais referentes ao ICMS
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "total_nfe_icms")
public class TotalNFeICMS extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "total_nfe_id", nullable = false)
    private TotalNFe totalNFe;

    // Base de Cálculo do ICMS 
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = true)
    private BigDecimal vbc;
    // Valor Total do ICMS 
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = true)
    private BigDecimal vicms;
    // Valor Total do ICMS desonerado 
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = true)
    private BigDecimal vicmsdeson;
    // Valor Total do FCP (Fundo de Combate à Pobreza)
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = true)
    private BigDecimal vfcp;
    // Base de Cálculo do ICMS ST 
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = true)
    private BigDecimal vbcst;
    // Valor Total do ICMS ST 
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = true)
    private BigDecimal vst;
    // Valor Total do FCP (Fundo de Combate à Pobreza) retido por substituição tributária
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = true)
    private BigDecimal vfcpst;
    // Valor Total do FCP retido anteriormente por Substituição Tributária
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = true)
    private BigDecimal vfcpstret;
    // Valor total do ICMS relativo Fundo de Combate à Pobreza (FCP) da UF de destino
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = true)
    private BigDecimal vfcpufdest;
    // Valor total do ICMS Interestadual para a UF de destino
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = true)
    private BigDecimal vicmsufdest;
    // Valor total do ICMS Interestadual para a UF do remetente
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = true)
    private BigDecimal vicmsufremet;
    // Valor Total dos produtos e serviços
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = true)
    private BigDecimal vprod;
    // Valor Total do Frete 
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = true)
    private BigDecimal vfrete;
    // Valor Total do Seguro 
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = true)
    private BigDecimal vseg;
    // Valor Total do Desconto 
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = true)
    private BigDecimal vdesc;
    // Valor Total do II 
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = true)
    private BigDecimal vii;
    // Valor Total do IPI 
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = true)
    private BigDecimal vipi;
    // Valor Total do IPI devolvido
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = true)
    private BigDecimal vipidevol;
    // Valor do PIS 
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = true)
    private BigDecimal vpis;
    // Valor da COFINS 
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = true)
    private BigDecimal vcofins;
    // Outras Despesas acessórias 
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = true)
    private BigDecimal voutro;
    // Valor Total da NF-e
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = true)
    private BigDecimal vnf;
    // Valor aproximado total de tributos federais, estaduais e municipais.
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = true)
    private BigDecimal vtottrib;

    public TotalNFe getTotalNFe() {
        return totalNFe;
    }

    public void setTotalNFe(TotalNFe totalNFe) {
        this.totalNFe = totalNFe;
    }

    public BigDecimal getVbc() {
        return vbc;
    }

    public void setVbc(BigDecimal vbc) {
        this.vbc = vbc;
    }

    public BigDecimal getVicms() {
        return vicms;
    }

    public void setVicms(BigDecimal vicms) {
        this.vicms = vicms;
    }

    public BigDecimal getVicmsdeson() {
        return vicmsdeson;
    }

    public void setVicmsdeson(BigDecimal vicmsdeson) {
        this.vicmsdeson = vicmsdeson;
    }

    public BigDecimal getVfcp() {
        return vfcp;
    }

    public void setVfcp(BigDecimal vfcp) {
        this.vfcp = vfcp;
    }

    public BigDecimal getVbcst() {
        return vbcst;
    }

    public void setVbcst(BigDecimal vbcst) {
        this.vbcst = vbcst;
    }

    public BigDecimal getVst() {
        return vst;
    }

    public void setVst(BigDecimal vst) {
        this.vst = vst;
    }

    public BigDecimal getVfcpst() {
        return vfcpst;
    }

    public void setVfcpst(BigDecimal vfcpst) {
        this.vfcpst = vfcpst;
    }

    public BigDecimal getVfcpstret() {
        return vfcpstret;
    }

    public void setVfcpstret(BigDecimal vfcpstret) {
        this.vfcpstret = vfcpstret;
    }

    public BigDecimal getVfcpufdest() {
        return vfcpufdest;
    }

    public void setVfcpufdest(BigDecimal vfcpufdest) {
        this.vfcpufdest = vfcpufdest;
    }

    public BigDecimal getVicmsufdest() {
        return vicmsufdest;
    }

    public void setVicmsufdest(BigDecimal vicmsufdest) {
        this.vicmsufdest = vicmsufdest;
    }

    public BigDecimal getVicmsufremet() {
        return vicmsufremet;
    }

    public void setVicmsufremet(BigDecimal vicmsufremet) {
        this.vicmsufremet = vicmsufremet;
    }

    public BigDecimal getVprod() {
        return vprod;
    }

    public void setVprod(BigDecimal vprod) {
        this.vprod = vprod;
    }

    public BigDecimal getVfrete() {
        return vfrete;
    }

    public void setVfrete(BigDecimal vfrete) {
        this.vfrete = vfrete;
    }

    public BigDecimal getVseg() {
        return vseg;
    }

    public void setVseg(BigDecimal vseg) {
        this.vseg = vseg;
    }

    public BigDecimal getVdesc() {
        return vdesc;
    }

    public void setVdesc(BigDecimal vdesc) {
        this.vdesc = vdesc;
    }

    public BigDecimal getVii() {
        return vii;
    }

    public void setVii(BigDecimal vii) {
        this.vii = vii;
    }

    public BigDecimal getVipi() {
        return vipi;
    }

    public void setVipi(BigDecimal vipi) {
        this.vipi = vipi;
    }

    public BigDecimal getVipidevol() {
        return vipidevol;
    }

    public void setVipidevol(BigDecimal vipidevol) {
        this.vipidevol = vipidevol;
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

    public BigDecimal getVoutro() {
        return voutro;
    }

    public void setVoutro(BigDecimal voutro) {
        this.voutro = voutro;
    }

    public BigDecimal getVnf() {
        return vnf;
    }

    public void setVnf(BigDecimal vnf) {
        this.vnf = vnf;
    }

    public BigDecimal getVtottrib() {
        return vtottrib;
    }

    public void setVtottrib(BigDecimal vtottrib) {
        this.vtottrib = vtottrib;
    }

    
}
