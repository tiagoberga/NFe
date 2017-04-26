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
 * Grupo de informações da CIDE.
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "detalhamento_especifico_combustivel_cide")
public class DetalhamentoEspecificoCombustivelCIDE extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "detalhamento_especifico_combustivel_id", nullable = false)
    private DetalhamentoEspecificoCombustivel detalhamentoEspecificoCombustivel;

    /**
     * Informar a BC da CIDE em quantidade
     */
    @DecimalMin(value = "0.0000")
    @Column(precision = 16, scale = 4, nullable = false)
    private BigDecimal qbcprod;

    /**
     * Valor da alíquota da CIDE, Informar o valor da alíquota em reais da CIDE
     */
    @DecimalMin(value = "0.0000")
    @Column(precision = 15, scale = 4, nullable = false)
    private BigDecimal valiqprod;

    /**
     * Valor da CIDE , Informar o valor da CIDE
     */
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal vcide;

    public DetalhamentoEspecificoCombustivel getDetalhamentoEspecificoCombustivel() {
        return detalhamentoEspecificoCombustivel;
    }

    public void setDetalhamentoEspecificoCombustivel(DetalhamentoEspecificoCombustivel detalhamentoEspecificoCombustivel) {
        this.detalhamentoEspecificoCombustivel = detalhamentoEspecificoCombustivel;
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
