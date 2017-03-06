/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.nfe.emissao;

import br.com.ararati.entity.cadastros.*;
import br.com.ararati.entity.AbstractEntity;
import br.com.ararati.enums.C.NFeTipoRegimeTributario;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
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
@Table(schema = "nfe", name = "identificacao_emitente")
public class IdentificacaoEmitente extends AbstractEntity {

    @ManyToOne
    @NotNull(message = "Empresa Emitente é obrigatório")
    @JoinColumn(name = "emitente_id", nullable = false)
    private Emitente emitente;

    @OneToOne
    @JoinColumn(name = "dados_nfe_id", nullable = false)
    private DadosNFe dadosNFe;

    // DADOS DE EMITENTE
    @NotNull(message = "Documento é obrigatório")
    @Length(min = 1, max = 20, message = "Documento deve conter entre {min} e {max} caracteres")
    @Column(length = 20, nullable = false)
    private String documento;
    @NotNull(message = "Razão Social é obrigatório")
    @Length(min = 2, max = 60, message = "Razão Social deve conter entre {min} e {max} caracteres")
    @Column(length = 60, nullable = false)
    private String xnome;
    @Length(min = 1, max = 60, message = "Nome Fantasia deve conter entre {min} e {max} caracteres")
    @Column(length = 60, nullable = true)
    private String xfant;
    @NotNull(message = "Inscrição Estadual é obrigatório")
    @Length(min = 2, max = 14, message = "Inscrição Estadual deve conter entre {min} e {max} caracteres")
    @Column(length = 14, nullable = false)
    private String ie;
    @Length(min = 2, max = 14, message = "Inscrição Substituto Tributário deve conter entre {min} e {max} caracteres")
    @Column(length = 14, nullable = true)
    private String iest;
    @NotNull(message = "Inscrição Municipal é obrigatório")
    @Length(min = 1, max = 15, message = "Inscrição Municipal deve conter entre {min} e {max} caracteres")
    @Column(length = 15, nullable = false)
    private String im;
    @Column(length = 7, nullable = true)
    private String cnae;
    @NotNull(message = "Regime Tributário é obrigatório")
    @Column(nullable = false)
    private NFeTipoRegimeTributario crt;

    // DADOS DE ENDERECO
    @Embedded
    private Endereco endereco;

    public IdentificacaoEmitente() {
        this.endereco = new Endereco();
    }

    public IdentificacaoEmitente(Emitente emitente) {
        this();
        this.cnae = emitente.getCnae();
        this.crt = emitente.getCrt();
        this.documento = emitente.getDocumento();
        this.ie = emitente.getIe();
        this.iest = emitente.getIest();
        this.im = emitente.getIm();
        this.xnome = emitente.getXnome();
        this.xfant = emitente.getXfant();
        this.endereco = new Endereco(emitente);
    }

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

    public String getXfant() {
        return xfant;
    }

    public void setXfant(String xfant) {
        this.xfant = xfant;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getIest() {
        return iest;
    }

    public void setIest(String iest) {
        this.iest = iest;
    }

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im;
    }

    public String getCnae() {
        return cnae;
    }

    public void setCnae(String cnae) {
        this.cnae = cnae;
    }

    public NFeTipoRegimeTributario getCrt() {
        return crt;
    }

    public void setCrt(NFeTipoRegimeTributario crt) {
        this.crt = crt;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}
