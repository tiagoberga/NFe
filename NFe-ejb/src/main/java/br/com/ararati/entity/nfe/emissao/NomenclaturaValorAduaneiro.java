/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.nfe.emissao;

import br.com.ararati.entity.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * I05a - Codificação NVE - Nomenclatura de Valor Aduaneiro e Estatística.
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "nomenclatura_valor_aduaneiro")
public class NomenclaturaValorAduaneiro extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "detalhamento_produto_servico_id", nullable = false)
    private DetalhamentoProdutoServico detalhamentoProdutoServico;

    /**
     * Codificação NVE - Nomenclatura de Valor Aduaneiro e Estatística.
     * Codificação opcional que detalha alguns NCM. Formato: duas letras
     * maiúsculas e 4 algarismos. Se a mercadoria se enquadrar em mais de uma
     * codificação, informar até 8 codificações principais. Vide: Anexo XII.03 -
     * Identificador NVE.
     */
    @Column(length = 6, nullable = true)
    private String nve;

    public DetalhamentoProdutoServico getDetalhamentoProdutoServico() {
        return detalhamentoProdutoServico;
    }

    public void setDetalhamentoProdutoServico(DetalhamentoProdutoServico detalhamentoProdutoServico) {
        this.detalhamentoProdutoServico = detalhamentoProdutoServico;
    }

    public String getNve() {
        return nve;
    }

    public void setNve(String nve) {
        this.nve = nve;
    }

}
