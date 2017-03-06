/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.cadastros;

import br.com.ararati.entity.AbstractEntity;
import br.com.ararati.enums.I.NFeTipoIndicadorVProd;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * Produtos e Serviços da NF-e
 *
 * @author tiago 12345
 */
@Entity
@Table(schema = "public", name = "produto")
public class Produto extends AbstractEntity {

    public static final int P_EMITENTE = 1;
    public static final int P_CPROD = 2;
    public static final int P_XPROD = 3;
    public static final int P_CEAN = 4;
    public static final int P_NCM = 5;
    public static final int P_CEST = 6;
    public static final int P_CFOP = 7;
    
    @ManyToOne
    @NotNull(message = "Empresa Emitente é obrigatório")
    @JoinColumn(name = "emitente_id", nullable = false)
    private Emitente emitente;

    // Código do produto ou serviço
    @NotNull(message = "Código é obrigatório")
    @Length(min = 1, max = 60, message = "Código deve conter entre {min} e {max} caracteres")
    @Column(length = 60, nullable = false)
    private String cprod;
    // GTIN (Global Trade Item Number) do produto, antigo código EAN ou código de barras
    @NotNull(message = "EAN é obrigatório")
    @Column(length = 14, nullable = false)
    private String cean;
    // Descrição do produto ou serviço
    @NotNull(message = "Descrição é obrigatório")
    @Length(min = 1, max = 120, message = "Descrição deve conter entre {min} e {max} caracteres")
    @Column(length = 120, nullable = false)
    private String xprod;
    // Código NCM com 8 dígitos
    @NotNull(message = "NCM é obrigatório")
    @Column(length = 8, nullable = false)
    private String ncm;
    // Código CEST
    @Column(length = 7, nullable = true)
    private String cest;
    // Codificação NVE - Nomenclatura de Valor Aduaneiro e Estatística.
    @Column(length = 8, nullable = true)
    private String nve;
    // EX_TIPI
    @Length(min = 2, max = 3, message = "EX TIPI deve conter entre {min} e {max} caracteres")
    @Column(length = 3, nullable = true)
    private String extipi;
    // Código Fiscal de Operações e Prestações
    @NotNull(message = "CFOP é obrigatório")
    @Column(length = 4, nullable = false)
    private String cfop;
    // Unidade Comercial
    @NotNull(message = "UN Comercial é obrigatório")
    @Length(min = 1, max = 6, message = "UN Comercial deve conter entre {min} e {max} caracteres")
    @Column(length = 6, nullable = false)
    private String ucom;
    // Quantidade Comercial
    @NotNull(message = "Quantidade Comercial é obrigatório")
    @DecimalMin(value = "0.0000")
    @Column(precision = 10, scale = 4, nullable = false)
    private BigDecimal qcom;
    // Valor Unitário de Comercialização
    @NotNull(message = "Valor Unitário de Comercialização é obrigatório")
    @DecimalMin(value = "0.0000")
    @Column(precision = 10, scale = 4, nullable = false)
    private BigDecimal vuncom;
    // Valor Total Bruto dos Produtos ou Serviços
    @NotNull(message = "Valor Total Bruto é obrigatório")
    @DecimalMin(value = "0.00")
    @Column(precision = 13, scale = 2, nullable = false)
    private BigDecimal vprod;
    // GTIN (Global Trade Item Number) da unidade tributável, antigo código EAN ou código de barras
    @NotNull(message = "EAN Tributável é obrigatório")
    @Column(length = 14, nullable = false)
    private String ceantrib;
    // Unidade Tributável
    @NotNull(message = "Unidade Tributável é obrigatório")
    @Column(length = 6, nullable = false)
    private String utrib;
    // Quantidade Tributável
    @NotNull(message = "Quantidade Tributável é obrigatório")
    @DecimalMin(value = "0.0000")
    @Column(precision = 11, scale = 4, nullable = false)
    private BigDecimal qtrib;
    // Valor Unitário de tributação
    @NotNull(message = "Valor Unitário Tributável é obrigatório")
    @DecimalMin(value = "0.0000")
    @Column(precision = 11, scale = 4, nullable = false)
    private BigDecimal vuntrib;
    // Valor Total do Frete
    @DecimalMin(value = "0.00")
    @Column(precision = 13, scale = 2, nullable = true)
    private BigDecimal vfrete;
    // Valor Total do Seguro
    @DecimalMin(value = "0.00")
    @Column(precision = 13, scale = 2, nullable = true)
    private BigDecimal vseg;
    // Valor do Desconto
    @DecimalMin(value = "0.00")
    @Column(precision = 13, scale = 2, nullable = true)
    private BigDecimal vdesc;
    // Outras despesas acessórias
    @DecimalMin(value = "0.00")
    @Column(precision = 13, scale = 2, nullable = true)
    private BigDecimal voutro;
    // Indica se valor do Item (vProd) entra no valor total da NF-e (vProd)
    @NotNull(message = "Indicador vProd é obrigatório")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NFeTipoIndicadorVProd indTot;

    public Emitente getEmitente() {
        return emitente;
    }

    public void setEmitente(Emitente emitente) {
        this.emitente = emitente;
    }

    public String getCprod() {
        return cprod;
    }

    public void setCprod(String cprod) {
        this.cprod = cprod;
    }

    public String getCean() {
        return cean;
    }

    public void setCean(String cean) {
        this.cean = cean;
    }

    public String getXprod() {
        return xprod;
    }

    public void setXprod(String xprod) {
        this.xprod = xprod;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    public String getCest() {
        return cest;
    }

    public void setCest(String cest) {
        this.cest = cest;
    }

    public String getNve() {
        return nve;
    }

    public void setNve(String nve) {
        this.nve = nve;
    }

    public String getExtipi() {
        return extipi;
    }

    public void setExtipi(String extipi) {
        this.extipi = extipi;
    }

    public String getCfop() {
        return cfop;
    }

    public void setCfop(String cfop) {
        this.cfop = cfop;
    }

    public String getUcom() {
        return ucom;
    }

    public void setUcom(String ucom) {
        this.ucom = ucom;
    }

    public BigDecimal getQcom() {
        return qcom;
    }

    public void setQcom(BigDecimal qcom) {
        this.qcom = qcom;
    }

    public BigDecimal getVuncom() {
        return vuncom;
    }

    public void setVuncom(BigDecimal vuncom) {
        this.vuncom = vuncom;
    }

    public BigDecimal getVprod() {
        return vprod;
    }

    public void setVprod(BigDecimal vprod) {
        this.vprod = vprod;
    }

    public String getCeantrib() {
        return ceantrib;
    }

    public void setCeantrib(String ceantrib) {
        this.ceantrib = ceantrib;
    }

    public String getUtrib() {
        return utrib;
    }

    public void setUtrib(String utrib) {
        this.utrib = utrib;
    }

    public BigDecimal getQtrib() {
        return qtrib;
    }

    public void setQtrib(BigDecimal qtrib) {
        this.qtrib = qtrib;
    }

    public BigDecimal getVuntrib() {
        return vuntrib;
    }

    public void setVuntrib(BigDecimal vuntrib) {
        this.vuntrib = vuntrib;
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

    public BigDecimal getVoutro() {
        return voutro;
    }

    public void setVoutro(BigDecimal voutro) {
        this.voutro = voutro;
    }

    public NFeTipoIndicadorVProd getIndTot() {
        return indTot;
    }

    public void setIndTot(NFeTipoIndicadorVProd indTot) {
        this.indTot = indTot;
    }

    
    
}


