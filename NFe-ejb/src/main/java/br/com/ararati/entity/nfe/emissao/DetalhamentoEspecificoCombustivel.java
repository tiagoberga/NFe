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
import org.hibernate.validator.constraints.Length;

/**
 * Identificação do Destinatário da Nota Fiscal eletrônica
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "detalhamento_especifico_combustivel")
public class DetalhamentoEspecificoCombustivel extends AbstractEntity {

    @ManyToOne
    @NotNull(message = "Empresa Emitente é obrigatório")
    @JoinColumn(name = "emitente_id", nullable = false)
    private Emitente emitente;

    @OneToOne
    @JoinColumn(name = "detalhamento_produto_servico_id", nullable = false)
    private DetalhamentoProdutoServico detalhamentoProdutoServico;

    // Código de produto da ANP
    @NotNull(message = "Código de produto da ANP é obrigatório")
    @Column(length = 9, nullable = false)
    private String cprodanp;
    // Percentual de Gás Natural para o produto GLP (cProdANP = 210203001)
    @DecimalMin(value = "0.0000")
    @Column(precision = 11, scale = 4, nullable = true)
    private BigDecimal pmixgn;
    // Código de autorização / registro do CODIF 
    @Length(min = 1, max = 21, message = "CODIF deve conter entre {min} e {max} caracteres")
    @Column(length = 21, nullable = true)
    private String codif;
    // Quantidade de combustível faturada à temperatura ambiente.
    @DecimalMin(value = "0.0000")
    @Column(precision = 11, scale = 4, nullable = true)
    private BigDecimal qtemp;
    // Sigla da UF de consumo
    @Column(length = 2, nullable = false)
    private String ufcons;
    // BC da CIDE
    @DecimalMin(value = "0.0000")
    @Column(precision = 11, scale = 4, nullable = false)
    private BigDecimal qbcprod;
    // Valor da alíquota da CIDE
    @DecimalMin(value = "0.0000")
    @Column(precision = 11, scale = 4, nullable = false)
    private BigDecimal valiqprod;
    // Valor da CIDE 
    @DecimalMin(value = "0.00")
    @Column(precision = 13, scale = 2, nullable = false)
    private BigDecimal vcide;

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

    public String getCprodanp() {
        return cprodanp;
    }

    public void setCprodanp(String cprodanp) {
        this.cprodanp = cprodanp;
    }

    public BigDecimal getPmixgn() {
        return pmixgn;
    }

    public void setPmixgn(BigDecimal pmixgn) {
        this.pmixgn = pmixgn;
    }

    public String getCodif() {
        return codif;
    }

    public void setCodif(String codif) {
        this.codif = codif;
    }

    public BigDecimal getQtemp() {
        return qtemp;
    }

    public void setQtemp(BigDecimal qtemp) {
        this.qtemp = qtemp;
    }

    public String getUfcons() {
        return ufcons;
    }

    public void setUfcons(String ufcons) {
        this.ufcons = ufcons;
    }

    public BigDecimal getQbcprod() {
        return qbcprod;
    }

    public void setQbcprod(BigDecimal qbcprod) {
        this.qbcprod = qbcprod;
    }

    public BigDecimal getValiqprod() {
        return valiqprod;
    }

    public void setValiqprod(BigDecimal valiqprod) {
        this.valiqprod = valiqprod;
    }

    public BigDecimal getVcide() {
        return vcide;
    }

    public void setVcide(BigDecimal vcide) {
        this.vcide = vcide;
    }

}
