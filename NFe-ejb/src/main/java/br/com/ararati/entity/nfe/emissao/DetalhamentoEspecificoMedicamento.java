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
import org.hibernate.validator.constraints.Length;

/**
 * Grupo K. Detalhamento Específico de Medicamento e de matérias-primas
 * farmacêuticas.
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "detalhamento_especifico_medicamento")
public class DetalhamentoEspecificoMedicamento extends AbstractEntity {

    @ManyToOne
    @NotNull(message = "Empresa Emitente é obrigatório")
    @JoinColumn(name = "emitente_id", nullable = false)
    private Emitente emitente;

    @ManyToOne
    @JoinColumn(name = "detalhamento_produto_servico_id", nullable = false)
    private DetalhamentoProdutoServico detalhamentoProdutoServico;

    /**
     * Código de Produto da ANVISA - Utilizar o número do registro do produto da
     * Câmara de Regulação do Mercado de Medicamento – CMED
     */
    @NotNull(message = "Código da ANVISA é obrigatório")
    @Length(min = 1, max = 13, message = "Código da ANVISA deve conter entre {min} e {max} caracteres")
    @Column(length = 13, nullable = false)
    private String cprodanvisa;

    /**
     * Preço máximo consumidor
     */
    @NotNull(message = "Preço Máximo Consumidor é obrigatório")
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal vpmc;

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

    public String getCprodanvisa() {
        return cprodanvisa;
    }

    public void setCprodanvisa(String cprodanvisa) {
        this.cprodanvisa = cprodanvisa;
    }

    public BigDecimal getVpmc() {
        return vpmc;
    }

    public void setVpmc(BigDecimal vpmc) {
        this.vpmc = vpmc;
    }

}
