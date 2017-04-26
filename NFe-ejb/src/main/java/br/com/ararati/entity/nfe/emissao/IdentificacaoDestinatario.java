/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.nfe.emissao;

import br.com.ararati.entity.cadastros.*;
import br.com.ararati.entity.AbstractEntity;
import br.com.ararati.enums.E.NFeTipoIndicadorIEDestinatario;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * Identificação do Destinatário da Nota Fiscal eletrônica
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "identificacao_destinatario")
public class IdentificacaoDestinatario extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "dados_nfe_id", nullable = false)
    private DadosNFe dadosNFe;

    // Documento
    @NotNull(message = "Documento é obrigatório")
    @Column(length = 20, nullable = false)
    @Length(min = 1, max = 20, message = "Documento deve conter entre {min} e {max} caracteres")
    private String documento;
    // Razão Social
    @NotNull(message = "Razão Social é obrigatório")
    @Column(length = 60, nullable = false)
    @Length(min = 2, max = 60, message = "Razão Social deve conter entre {min} e {max} caracteres")
    private String xnome;
    // Indicador da IE do Destinatário
    @NotNull(message = "Indicador de IE é obrigatório")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NFeTipoIndicadorIEDestinatario indiedest;
    // Inscrição Estadual do Destinatário 
    @Column(length = 14, nullable = true)
    @Length(min = 2, max = 14, message = "Inscrição Estadual deve conter entre {min} e {max} caracteres")
    private String ie;
    // Inscrição na SUFRAMA
    @Column(length = 9, nullable = true)
    @Length(min = 8, max = 9, message = "Inscrição na SUFRAMA deve conter entre {min} e {max} caracteres")
    private String isuf;
    // Inscrição Municipal do Tomador do Serviço
    @Column(length = 15, nullable = true)
    @Length(min = 1, max = 15, message = "Inscrição Municipal deve conter entre {min} e {max} caracteres")
    private String im;
    // Email
    @Column(length = 60, nullable = true)
    @Length(min = 1, max = 60, message = "E-mail deve conter entre {min} e {max} caracteres")
    private String email;

    // DADOS DE ENDERECO
    @Embedded
    private Endereco endereco;

    public IdentificacaoDestinatario() {
        this.endereco = new Endereco();
    }

    public IdentificacaoDestinatario(Destinatario destinatario) {
        this();
        this.documento = destinatario.getDocumento();
        this.email = destinatario.getEmail();
        this.ie = destinatario.getIe();
        this.im = destinatario.getIm();
        this.indiedest = destinatario.getIndIeDest();
        this.isuf = destinatario.getIsUf();
        this.xnome = destinatario.getXnome();
        this.endereco = new Endereco(destinatario);
    }

    public DadosNFe getDadosNFe() {
        return dadosNFe;
    }

    public void setDadosNFe(DadosNFe dadosNFe) {
        this.dadosNFe = dadosNFe;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getXnome() {
        return xnome;
    }

    public void setXnome(String xnome) {
        this.xnome = xnome;
    }

    public NFeTipoIndicadorIEDestinatario getIndiedest() {
        return indiedest;
    }

    public void setIndiedest(NFeTipoIndicadorIEDestinatario indiedest) {
        this.indiedest = indiedest;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getIsuf() {
        return isuf;
    }

    public void setIsuf(String isuf) {
        this.isuf = isuf;
    }

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}
