/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.nfe.emissao;

import br.com.ararati.entity.cadastros.*;
import br.com.ararati.entity.AbstractEntity;
import br.com.ararati.enums.Y.NFeTipoBandeiraOperadoraCartaoCredito;
import br.com.ararati.enums.Y.NFeTipoFormaPagamento;
import br.com.ararati.enums.Y.NFeTipoIntegracaoPagamento;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * Total da NF-e
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "total_nfe")
public class Pagamento extends AbstractEntity {

    @ManyToOne
    @NotNull(message = "Empresa Emitente é obrigatório")
    @JoinColumn(name = "emitente_id", nullable = true)
    private Emitente emitente;

    @OneToOne
    @JoinColumn(name = "dados_nfe_id", nullable = false)
    private DadosNFe dadosNFe;

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

    // Valor do troco
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = true)
    private BigDecimal vtroco;

    public Emitente getEmitente() {
        return emitente;
    }

    public void setEmitente(Emitente emitente) {
        this.emitente = emitente;
    }

    public DadosNFe getDadosNFe() {
        return dadosNFe;
    }

    public void setDadosNFe(DadosNFe dadosNFe) {
        this.dadosNFe = dadosNFe;
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

    public BigDecimal getVtroco() {
        return vtroco;
    }

    public void setVtroco(BigDecimal vtroco) {
        this.vtroco = vtroco;
    }
    
    

}
