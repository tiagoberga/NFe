/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.nfe.emissao;

import br.com.ararati.entity.AbstractEntity;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

/**
 * Total da NF-e
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "pagamento")
public class Pagamento extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "dados_nfe_id", nullable = false)
    private DadosNFe dadosNFe;

    @Valid
    @Size(max = 100)
    @OneToMany(mappedBy = "pagamento", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<PagamentoDetalhe> detalhesPagamento;

    // Valor do troco
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = true)
    private BigDecimal vtroco;

    public DadosNFe getDadosNFe() {
        return dadosNFe;
    }

    public void setDadosNFe(DadosNFe dadosNFe) {
        this.dadosNFe = dadosNFe;
    }

    public List<PagamentoDetalhe> getDetalhesPagamento() {
        return detalhesPagamento;
    }

    public void setDetalhesPagamento(List<PagamentoDetalhe> detalhesPagamento) {
        this.detalhesPagamento = detalhesPagamento;
    }

    public BigDecimal getVtroco() {
        return vtroco;
    }

    public void setVtroco(BigDecimal vtroco) {
        this.vtroco = vtroco;
    }

}
