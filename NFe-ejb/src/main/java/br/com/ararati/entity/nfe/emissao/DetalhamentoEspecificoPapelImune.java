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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Identificação do Destinatário da Nota Fiscal eletrônica
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "detalhamento_especifico_papel_imune")
public class DetalhamentoEspecificoPapelImune extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "detalhamento_produto_servico_id", nullable = false)
    private DetalhamentoProdutoServico detalhamentoProdutoServico;

    // Número do RECOPI 
    @NotNull(message = "Número do RECOPI é obrigatório")
    @Column(length = 20, nullable = false)
    private String nrecopi;

    public DetalhamentoProdutoServico getDetalhamentoProdutoServico() {
        return detalhamentoProdutoServico;
    }

    public void setDetalhamentoProdutoServico(DetalhamentoProdutoServico detalhamentoProdutoServico) {
        this.detalhamentoProdutoServico = detalhamentoProdutoServico;
    }

    public String getNrecopi() {
        return nrecopi;
    }

    public void setNrecopi(String nrecopi) {
        this.nrecopi = nrecopi;
    }

}
