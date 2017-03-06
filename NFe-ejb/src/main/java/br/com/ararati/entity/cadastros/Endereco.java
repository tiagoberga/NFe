/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.cadastros;

import br.com.ararati.Cep;
import enviNFe_v310.TUfEmi;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * Identificação de Endereço
 *
 * @author tiago
 */
@Embeddable
public class Endereco implements Serializable {

    // DADOS DE ENDERECO
    @NotNull(message = "Logradouro é obrigatório")
    @Column(length = 60, nullable = false)
    @Length(min = 2, max = 60, message = "Logradouro deve conter entre {min} e {max} caracteres")
    private String endXlgr;
    @NotNull(message = "Número é obrigatório")
    @Column(length = 60, nullable = false)
    @Length(min = 1, max = 60, message = "Número deve conter entre {min} e {max} caracteres")
    private String endNro;
    @Column(length = 60, nullable = true)
    @Length(min = 1, max = 60, message = "Complemento deve conter entre {min} e {max} caracteres")
    private String endXcpl;
    @NotNull(message = "Bairro é obrigatório")
    @Column(length = 60, nullable = false)
    @Length(min = 2, max = 60, message = "Bairro deve conter entre {min} e {max} caracteres")
    private String endXbairro;
    @NotNull(message = "Código de Município é obrigatório")
    @Column(length = 7, nullable = false)
    private String endCmun;
    @NotNull(message = "Nome do Município é obrigatório")
    @Column(length = 60, nullable = false)
    @Length(min = 2, max = 60, message = "Nome do Município deve conter entre {min} e {max} caracteres")
    private String endXmun;
    @NotNull(message = "UF do Município é obrigatório")
    @Column(length = 2, nullable = false)
    private String endUf;
    @NotNull(message = "CEP do Município é obrigatório")
    @Column(length = 8, nullable = false)
    private String endCep;
    @Column(length = 4, nullable = true)
    @Length(min = 2, max = 4, message = "Código do País deve conter entre {min} e {max} caracteres")
    private String endCpais;
    @Column(length = 60, nullable = true)
    @Length(min = 2, max = 60, message = "Nome do País deve conter entre {min} e {max} caracteres")
    private String endXpais;
    @Column(length = 14, nullable = true)
    @Length(min = 6, max = 14, message = "Telefone deve conter entre {min} e {max} caracteres")
    private String endFone;

    public Endereco() {
    }

    public Endereco(Destinatario destinatario) {
        this.endCep = destinatario.getEndereco().getEndCep();
        this.endCmun = destinatario.getEndereco().getEndCmun();
        this.endCpais = destinatario.getEndereco().getEndCpais();
        this.endFone = destinatario.getEndereco().getEndFone();
        this.endNro = destinatario.getEndereco().getEndNro();
        this.endUf = destinatario.getEndereco().getEndUf();
        this.endXbairro = destinatario.getEndereco().getEndXbairro();
        this.endXcpl = destinatario.getEndereco().getEndXcpl();
        this.endXlgr = destinatario.getEndereco().getEndXlgr();
        this.endXmun = destinatario.getEndereco().getEndXmun();
        this.endXpais = destinatario.getEndereco().getEndXpais();
    }

    public Endereco(Emitente emitente) {
        this.endCep = emitente.getEndereco().getEndCep();
        this.endCmun = emitente.getEndereco().getEndCmun();
        this.endCpais = emitente.getEndereco().getEndCpais();
        this.endFone = emitente.getEndereco().getEndFone();
        this.endNro = emitente.getEndereco().getEndNro();
        this.endUf = emitente.getEndereco().getEndUf();
        this.endXbairro = emitente.getEndereco().getEndXbairro();
        this.endXcpl = emitente.getEndereco().getEndXcpl();
        this.endXlgr = emitente.getEndereco().getEndXlgr();
        this.endXmun = emitente.getEndereco().getEndXmun();
        this.endXpais = emitente.getEndereco().getEndXpais();
    }

    public Endereco(Cep cepFound) {
        limpaEndereco();
        this.endCep = cepFound.getCep() != null ? cepFound.getCep().replace("-", "") : null;
        this.endCmun = cepFound.getIbge();
        this.endUf = cepFound.getUf();
        this.endXbairro = cepFound.getBairro();
        this.endXcpl = cepFound.getComplemento();
        this.endXmun = cepFound.getLocalidade();
        this.endXlgr = cepFound.getLogradouro();
    }

    public final void limpaEndereco() {
        this.endXlgr = null;
        this.endNro = null;
        this.endXcpl = null;
        this.endXbairro = null;
        this.endCmun = null;
        this.endXmun = null;
        this.endUf = null;
        this.endCep = null;
        this.endCpais = null;
        this.endXpais = null;
        this.endFone = null;
    }

    public String getEndXlgr() {
        return endXlgr;
    }

    public void setEndXlgr(String endXlgr) {
        this.endXlgr = endXlgr;
    }

    public String getEndNro() {
        return endNro;
    }

    public void setEndNro(String endNro) {
        this.endNro = endNro;
    }

    public String getEndXcpl() {
        return endXcpl;
    }

    public void setEndXcpl(String endXcpl) {
        this.endXcpl = endXcpl;
    }

    public String getEndXbairro() {
        return endXbairro;
    }

    public void setEndXbairro(String endXbairro) {
        this.endXbairro = endXbairro;
    }

    public String getEndCmun() {
        return endCmun;
    }

    public void setEndCmun(String endCmun) {
        this.endCmun = endCmun;
    }

    public String getEndXmun() {
        return endXmun;
    }

    public void setEndXmun(String endXmun) {
        this.endXmun = endXmun;
    }

    public String getEndUf() {
        return endUf;
    }

    public void setEndUf(String endUf) {
        this.endUf = endUf;
    }

    public String getEndCep() {
        return endCep;
    }

    public void setEndCep(String endCep) {
        this.endCep = endCep;
    }

    public String getEndCpais() {
        return endCpais;
    }

    public void setEndCpais(String endCpais) {
        this.endCpais = endCpais;
    }

    public String getEndXpais() {
        return endXpais;
    }

    public void setEndXpais(String endXpais) {
        this.endXpais = endXpais;
    }

    public String getEndFone() {
        return endFone;
    }

    public void setEndFone(String endFone) {
        this.endFone = endFone;
    }

}
