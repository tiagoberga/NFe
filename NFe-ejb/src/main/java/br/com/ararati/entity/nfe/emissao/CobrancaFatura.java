/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.nfe.emissao;

import br.com.ararati.entity.cadastros.*;
import br.com.ararati.entity.AbstractEntity;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * Dados da Cobrança
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "cobranca_fatura")
public class CobrancaFatura extends AbstractEntity {

    @ManyToOne
    @NotNull(message = "Empresa Emitente é obrigatório")
    @JoinColumn(name = "emitente_id", nullable = true)
    private Emitente emitente;

    @OneToOne
    @JoinColumn(name = "dados_nfe_id", nullable = false)
    private DadosNFe dadosNFe;

    // Número da Fatura
    @Length(min = 1, max = 15, message = "Número dos Lacres deve conter entre {min} e {max} caracteres")
    @Column(length = 60, nullable = true)
    private String nfat;
    // Valor Original da Fatura
    @DecimalMin(value = "0.00")
    @Column(precision = 13, scale = 2, nullable = true)
    private BigDecimal vorig;
    // Valor do desconto
    @DecimalMin(value = "0.00")
    @Column(precision = 13, scale = 2, nullable = true)
    private BigDecimal vdesc;
    // Valor Líquido da Fatura`
    @DecimalMin(value = "0.00")
    @Column(precision = 13, scale = 2, nullable = true)
    private BigDecimal vliq;

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

    public String getNfat() {
        return nfat;
    }

    public void setNfat(String nfat) {
        this.nfat = nfat;
    }

    public BigDecimal getVorig() {
        return vorig;
    }

    public void setVorig(BigDecimal vorig) {
        this.vorig = vorig;
    }

    public BigDecimal getVdesc() {
        return vdesc;
    }

    public void setVdesc(BigDecimal vdesc) {
        this.vdesc = vdesc;
    }

    public BigDecimal getVliq() {
        return vliq;
    }

    public void setVliq(BigDecimal vliq) {
        this.vliq = vliq;
    }

}
