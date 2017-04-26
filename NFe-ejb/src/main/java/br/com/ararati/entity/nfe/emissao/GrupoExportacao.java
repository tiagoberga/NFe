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
import javax.validation.constraints.NotNull;

/**
 * I50 - Grupo de informações de exportação para o item.
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "grupo_exportacao")
public class GrupoExportacao extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "detalhamento_produto_servico_id", nullable = false)
    private DetalhamentoProdutoServico detalhamentoProdutoServico;

    /* Número do ato concessório de Drawback  - O número do Ato Concessório de 
    Suspensão deve ser preenchido com 11  dígitos(AAAANNNNNND) e o número do 
    Ato Concessório de Drawback Isenção deve ser preenchido com 9 dígitos 
    (AANNNNNND). (Observação incluída na NT 2013/005 v. 1.10) */
    @Column(length = 11, nullable = true)
    private String ndraw;

    // Número do Registro de Exportação 
    @NotNull(message = "Número do Registro de Exportação é obrigatório")
    @Column(length = 12, nullable = false)
    private String nre;

    /**
     * Chave de Acesso da NF-e recebida para exportação, NF-e recebida com fim
     * específico de exportação. No caso de operação com CFOP 3.503, informar a
     * chave de acesso da NF-e que efetivou a exportação
     */
    @NotNull(message = "Chave de Acesso é obrigatório")
    @Column(length = 44, nullable = false)
    private String chnfe;

    /**
     * Quantidade do item realmente exportado, A unidade de medida desta
     * quantidade é a unidade de comercialização deste item. No caso de operação
     * com CFOP 3.503, informar a quantidade de mercadoria devolvida
     */
    @NotNull(message = "Quantidade Exportado é obrigatório")
    @Column(precision = 15, scale = 4, nullable = false)
    private BigDecimal qexport;

    public DetalhamentoProdutoServico getDetalhamentoProdutoServico() {
        return detalhamentoProdutoServico;
    }

    public void setDetalhamentoProdutoServico(DetalhamentoProdutoServico detalhamentoProdutoServico) {
        this.detalhamentoProdutoServico = detalhamentoProdutoServico;
    }

    public String getNdraw() {
        return ndraw;
    }

    public void setNdraw(String ndraw) {
        this.ndraw = ndraw;
    }

    public String getNre() {
        return nre;
    }

    public void setNre(String nre) {
        this.nre = nre;
    }

    public String getChnfe() {
        return chnfe;
    }

    public void setChnfe(String chnfe) {
        this.chnfe = chnfe;
    }

    public BigDecimal getQexport() {
        return qexport;
    }

    public void setQexport(BigDecimal qexport) {
        this.qexport = qexport;
    }

}
