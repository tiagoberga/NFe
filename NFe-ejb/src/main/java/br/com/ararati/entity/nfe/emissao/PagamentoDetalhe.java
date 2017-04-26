/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.nfe.emissao;

import br.com.ararati.entity.AbstractEntity;
import br.com.ararati.enums.Y.NFeTipoFormaPagamento;
import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * Total da NF-e
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "pagamento_detalhe")
public class PagamentoDetalhe extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "dados_nfe_id", nullable = false)
    private DadosNFe dadosNFe;

    @ManyToOne
    @JoinColumn(name = "pagamento_id", nullable = false)
    private Pagamento pagamento;

    // Forma de pagamento
    @NotNull(message = "Forma de pagamento é obrigatório")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NFeTipoFormaPagamento tpag;

    // Valor do Pagamento
    @NotNull(message = "Valor do Pagamento é obrigatório")
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal vpag;

    @Valid
    @OneToOne(mappedBy = "pagamento", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private PagamentoDetalheGrupoCartoes card;

    public DadosNFe getDadosNFe() {
        return dadosNFe;
    }

    public void setDadosNFe(DadosNFe dadosNFe) {
        this.dadosNFe = dadosNFe;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public NFeTipoFormaPagamento getTpag() {
        return tpag;
    }

    public void setTpag(NFeTipoFormaPagamento tpag) {
        this.tpag = tpag;
    }

    public BigDecimal getVpag() {
        return vpag;
    }

    public void setVpag(BigDecimal vpag) {
        this.vpag = vpag;
    }

    public PagamentoDetalheGrupoCartoes getCard() {
        return card;
    }

    public void setCard(PagamentoDetalheGrupoCartoes card) {
        this.card = card;
    }

}
