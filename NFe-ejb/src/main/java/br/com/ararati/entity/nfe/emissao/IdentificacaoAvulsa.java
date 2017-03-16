/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.nfe.emissao;

import br.com.ararati.entity.cadastros.*;
import br.com.ararati.entity.AbstractEntity;
import enviNFe_v310.TUfEmi;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * Identificação do Destinatário da Nota Fiscal eletrônica
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "identificacao_avulsa")
public class IdentificacaoAvulsa extends AbstractEntity {

    @ManyToOne
    @NotNull(message = "Empresa Emitente é obrigatório")
    @JoinColumn(name = "emitente_id", nullable = false)
    private Emitente emitente;

    @OneToOne
    @JoinColumn(name = "dados_nfe_id", nullable = false)
    private DadosNFe dadosNFe;

    // CNPJ do órgão emitente
    @NotNull(message = "CNPJ do órgão emitente é obrigatório")
    @Length(min = 1, max = 60, message = "CNPJ do órgão emitente deve conter entre {min} e {max} caracteres")
    @Column(length = 14, nullable = false)
    private String cnpj;
    // Órgão emitente
    @NotNull(message = "Órgão emitente é obrigatório")
    @Length(min = 1, max = 60, message = "Órgão emitente deve conter entre {min} e {max} caracteres")
    @Column(length = 60, nullable = false)
    private String xorgao;
    // Matrícula do agente do Fisco
    @NotNull(message = "Matrícula do agente do Fisco é obrigatório")
    @Length(min = 1, max = 60, message = "Matrícula do agente do Fisco deve conter entre {min} e {max} caracteres")
    @Column(length = 60, nullable = false)
    private String matr;
    // Nome do agente do Fisco
    @NotNull(message = "Nome do agente do Fisco é obrigatório")
    @Length(min = 1, max = 60, message = "Nome do agente do Fisco deve conter entre {min} e {max} caracteres")
    @Column(length = 60, nullable = false)
    private String xagente;
    // Telfone
    @Length(min = 6, max = 14, message = "Telefone deve conter entre {min} e {max} caracteres")
    @Column(length = 14, nullable = true)
    private String fone;
    // UF Sigla
    @NotNull(message = "UF Sigla é obrigatório")
    @Column(length = 2, nullable = false)
    @Enumerated(EnumType.STRING)
    private TUfEmi uf;
    // Número do Documento de Arrecadação de Receita
    @Length(min = 1, max = 60, message = "Número do Documento de Arrecadação do Fisco deve conter entre {min} e {max} caracteres")
    @Column(length = 60, nullable = false)
    private String ndar;
    // Data de emissão do Documento de Arrecadação
    @Temporal(TemporalType.DATE)
    @Column(length = 60, nullable = true)
    private Date demi;
    // Valor Total constante no Documento de arrecadação de Receita
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = true)
    private BigDecimal vdar;
    // Repartição Fiscal emitente
    @Column(length = 60, nullable = false)
    private String repemi;
    // Data de pagamento do Documento de Arrecadação
    @Temporal(TemporalType.DATE)
    @Column(length = 60, nullable = true)
    private Date dpag;

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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getXorgao() {
        return xorgao;
    }

    public void setXorgao(String xorgao) {
        this.xorgao = xorgao;
    }

    public String getMatr() {
        return matr;
    }

    public void setMatr(String matr) {
        this.matr = matr;
    }

    public String getXagente() {
        return xagente;
    }

    public void setXagente(String xagente) {
        this.xagente = xagente;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public TUfEmi getUf() {
        return uf;
    }

    public void setUf(TUfEmi uf) {
        this.uf = uf;
    }

    public String getNdar() {
        return ndar;
    }

    public void setNdar(String ndar) {
        this.ndar = ndar;
    }

    public Date getDemi() {
        return demi;
    }

    public void setDemi(Date demi) {
        this.demi = demi;
    }

    public BigDecimal getVdar() {
        return vdar;
    }

    public void setVdar(BigDecimal vdar) {
        this.vdar = vdar;
    }

    public String getRepemi() {
        return repemi;
    }

    public void setRepemi(String repemi) {
        this.repemi = repemi;
    }

    public Date getDpag() {
        return dpag;
    }

    public void setDpag(Date dpag) {
        this.dpag = dpag;
    }
    
    
}
