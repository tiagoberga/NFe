/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.nfe.emissao;

import br.com.ararati.entity.AbstractEntity;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * Grupo I80 - Rastreabilidade de produto. Obrigatório o preenchimento deste
 * grupo no caso de medicamentos e produtos farmacêuticos.
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "nfe_rastreabilidade_produto")
public class RastreabilidadeProduto extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "detalhamento_produto_servico_id", nullable = false)
    private DetalhamentoProdutoServico detalhamentoProdutoServico;

    // Número do Lote do produto
    @Length(min = 1, max = 20, message = "Número do Lote deve conter entre 1 - 20 caracteres")
    @NotNull(message = "Número do Lote é obrigatório")
    @Column(length = 20, nullable = false)
    private String nlote;

    // Quantidade de produto no Lote
    @NotNull(message = "Quantidade é obrigatório")
    @Column(precision = 11, scale = 3, nullable = false)
    private BigDecimal qlote;

    // Data de fabricação/ Produção
    @NotNull(message = "Data de Fabricação é obrigatório")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dfab;

    // Data de validade - Informar o último dia do mês caso a validade não especifique o dia
    @NotNull(message = "Data de Validade é obrigatório")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dval;

    public DetalhamentoProdutoServico getDetalhamentoProdutoServico() {
        return detalhamentoProdutoServico;
    }

    public void setDetalhamentoProdutoServico(DetalhamentoProdutoServico detalhamentoProdutoServico) {
        this.detalhamentoProdutoServico = detalhamentoProdutoServico;
    }

    public String getNlote() {
        return nlote;
    }

    public void setNlote(String nlote) {
        this.nlote = nlote;
    }

    public BigDecimal getQlote() {
        return qlote;
    }

    public void setQlote(BigDecimal qlote) {
        this.qlote = qlote;
    }

    public Date getDfab() {
        return dfab;
    }

    public void setDfab(Date dfab) {
        this.dfab = dfab;
    }

    public Date getDval() {
        return dval;
    }

    public void setDval(Date dval) {
        this.dval = dval;
    }

}
