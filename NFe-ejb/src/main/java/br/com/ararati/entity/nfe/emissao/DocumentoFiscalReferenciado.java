/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.nfe.emissao;

import br.com.ararati.entity.cadastros.*;
import br.com.ararati.entity.AbstractEntity;
import br.com.ararati.enums.B.NFeTipoModeloDocumentoFiscalECF;
import br.com.ararati.enums.B.NFeTipoModeloDocumentoFiscalProdutorRural;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * Identificação do Destinatário da Nota Fiscal eletrônica
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "documento_fiscal_referenciado")
public class DocumentoFiscalReferenciado extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "identificacao_nfe_id", nullable = false)
    private IdentificacaoNFe identificacaoNFe;

    @Column(length = 44, nullable = false)
    private String refnfe;
    @Column(length = 44, nullable = true)
    private String refcte;

    // Informação da NF modelo 1/1A referenciada
    @Column(length = 2, nullable = true)
    private String refnfecuf;
    @Column(length = 4, nullable = true)
    private String refnfeaamm;
    @Column(length = 14, nullable = true)
    private String refnfecnpj;
    @Column(length = 12, nullable = true)
    private String refnfemod;
    @Length(min = 1, max = 3, message = "Série deve conter entre {min} e {max} caracteres")
    @Column(length = 3, nullable = true)
    private String refnfeserie;
    @Length(min = 1, max = 9, message = "Número deve conter entre {min} e {max} caracteres")
    @Column(length = 9, nullable = true)
    private String refnfennf;

    // Informações da NF de produtor rural referenciada
    @Column(length = 2, nullable = true)
    private String refnfpcuf;
    @Column(length = 4, nullable = true)
    private String refnfpaamm;
    @Column(length = 14, nullable = true)
    private String refnfpcnpj;
    @Column(length = 11, nullable = true)
    private String refnfpcpf;
    @Length(min = 1, max = 14, message = "Inscrição Estadual deve conter entre {min} e {max} caracteres")
    @Column(length = 14, nullable = true)
    private String refnfpie;
    @Column(nullable = true)
    private NFeTipoModeloDocumentoFiscalProdutorRural refnfpmod;
    @Length(min = 1, max = 3, message = "Série deve conter entre {min} e {max} caracteres")
    @Column(length = 3, nullable = true)
    private String refnfpserie;
    @Length(min = 1, max = 9, message = "Número deve conter entre {min} e {max} caracteres")
    @Column(length = 9, nullable = true)
    private String refnfpnnf;

    // Informações do Cupom Fiscal referenciado
    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private NFeTipoModeloDocumentoFiscalECF refecfmod;
    @Column(length = 3, nullable = true)
    private String refecfnecf;
    @Column(length = 6, nullable = true)
    private String refecfncoo;

    public void limpaDadosConhecimentoFiscalReferenciado() {
        refnfe = null;
        refcte = null;
        refnfecuf = null;
        refnfeaamm = null;
        refnfecnpj = null;
        refnfemod = null;
        refnfeserie = null;
        refnfennf = null;
    }

    public IdentificacaoNFe getIdentificacaoNFe() {
        return identificacaoNFe;
    }

    public void setIdentificacaoNFe(IdentificacaoNFe identificacaoNFe) {
        this.identificacaoNFe = identificacaoNFe;
    }

    public String getRefnfe() {
        return refnfe;
    }

    public void setRefnfe(String refnfe) {
        this.refnfe = refnfe;
    }

    public String getRefnfecuf() {
        return refnfecuf;
    }

    public void setRefnfecuf(String refnfecuf) {
        this.refnfecuf = refnfecuf;
    }

    public String getRefnfeaamm() {
        return refnfeaamm;
    }

    public void setRefnfeaamm(String refnfeaamm) {
        this.refnfeaamm = refnfeaamm;
    }

    public String getRefnfecnpj() {
        return refnfecnpj;
    }

    public void setRefnfecnpj(String refnfecnpj) {
        this.refnfecnpj = refnfecnpj;
    }

    public String getRefnfemod() {
        return refnfemod;
    }

    public void setRefnfemod(String refnfemod) {
        this.refnfemod = refnfemod;
    }

    public String getRefnfeserie() {
        return refnfeserie;
    }

    public void setRefnfeserie(String refnfeserie) {
        this.refnfeserie = refnfeserie;
    }

    public String getRefnfennf() {
        return refnfennf;
    }

    public void setRefnfennf(String refnfennf) {
        this.refnfennf = refnfennf;
    }

    public String getRefnfpcuf() {
        return refnfpcuf;
    }

    public void setRefnfpcuf(String refnfpcuf) {
        this.refnfpcuf = refnfpcuf;
    }

    public String getRefnfpaamm() {
        return refnfpaamm;
    }

    public void setRefnfpaamm(String refnfpaamm) {
        this.refnfpaamm = refnfpaamm;
    }

    public String getRefnfpcnpj() {
        return refnfpcnpj;
    }

    public void setRefnfpcnpj(String refnfpcnpj) {
        this.refnfpcnpj = refnfpcnpj;
    }

    public String getRefnfpcpf() {
        return refnfpcpf;
    }

    public void setRefnfpcpf(String refnfpcpf) {
        this.refnfpcpf = refnfpcpf;
    }

    public String getRefnfpie() {
        return refnfpie;
    }

    public void setRefnfpie(String refnfpie) {
        this.refnfpie = refnfpie;
    }

    public NFeTipoModeloDocumentoFiscalProdutorRural getRefnfpmod() {
        return refnfpmod;
    }

    public void setRefnfpmod(NFeTipoModeloDocumentoFiscalProdutorRural refnfpmod) {
        this.refnfpmod = refnfpmod;
    }

    public String getRefnfpserie() {
        return refnfpserie;
    }

    public void setRefnfpserie(String refnfpserie) {
        this.refnfpserie = refnfpserie;
    }

    public String getRefnfpnnf() {
        return refnfpnnf;
    }

    public void setRefnfpnnf(String refnfpnnf) {
        this.refnfpnnf = refnfpnnf;
    }

    public String getRefcte() {
        return refcte;
    }

    public void setRefcte(String refcte) {
        this.refcte = refcte;
    }

    public NFeTipoModeloDocumentoFiscalECF getRefecfmod() {
        return refecfmod;
    }

    public void setRefecfmod(NFeTipoModeloDocumentoFiscalECF refecfmod) {
        this.refecfmod = refecfmod;
    }

    public String getRefecfnecf() {
        return refecfnecf;
    }

    public void setRefecfnecf(String refecfnecf) {
        this.refecfnecf = refecfnecf;
    }

    public String getRefecfncoo() {
        return refecfncoo;
    }

    public void setRefecfncoo(String refecfncoo) {
        this.refecfncoo = refecfncoo;
    }

}
