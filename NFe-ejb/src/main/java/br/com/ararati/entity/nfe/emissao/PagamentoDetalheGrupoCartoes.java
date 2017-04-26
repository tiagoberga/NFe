/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.nfe.emissao;

import br.com.ararati.entity.AbstractEntity;
import br.com.ararati.enums.Y.NFeTipoBandeiraOperadoraCartaoCredito;
import br.com.ararati.enums.Y.NFeTipoIntegracaoPagamento;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * Total da NF-e
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "pagamento_detalhe_grupocartoes")
public class PagamentoDetalheGrupoCartoes extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "pagamento_id", nullable = true)
    private Pagamento pagamento;

    // Tipo de Integração para pagamento
    @NotNull(message = "Tipo de Integração de pagamento é obrigatório")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NFeTipoIntegracaoPagamento tpintegra;

    // CNPJ da Credenciadora de cartão de crédito e/ou débito
    @Column(length = 14, nullable = true)
    private String cnpj;

    // Bandeira da operadora de cartão de crédito e/ou débito
    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private NFeTipoBandeiraOperadoraCartaoCredito tband;

    // Número de autorização da operação cartão de crédito e/ou débito
    @Length(min = 1, max = 20, message = "Número de autorização deve conter entre {min} e {max} caracteres")
    @Column(length = 20, nullable = true)
    private String caut;

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public NFeTipoIntegracaoPagamento getTpintegra() {
        return tpintegra;
    }

    public void setTpintegra(NFeTipoIntegracaoPagamento tpintegra) {
        this.tpintegra = tpintegra;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public NFeTipoBandeiraOperadoraCartaoCredito getTband() {
        return tband;
    }

    public void setTband(NFeTipoBandeiraOperadoraCartaoCredito tband) {
        this.tband = tband;
    }

    public String getCaut() {
        return caut;
    }

    public void setCaut(String caut) {
        this.caut = caut;
    }

}
