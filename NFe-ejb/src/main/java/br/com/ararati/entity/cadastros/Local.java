/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.cadastros;

import br.com.ararati.Cep;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Identificação de Endereço
 *
 * @author tiago
 */
@Embeddable
public class Local implements Serializable {

//    // Documento
//    @NotNull(message = "Documento é obrigatório")
//    @Length(min = 1, max = 14, message = "Documento deve conter entre {min} e {max} caracteres")
//    @Column(length = 14, nullable = false)
//    private String locDocumento;
//    // Cep
//    @Column(length = 8, nullable = true)
//    private String locCep;
//    // Logradouro
//    @NotNull(message = "Logradouro é obrigatório")
//    @Length(min = 2, max = 60, message = "Logradouro deve conter entre {min} e {max} caracteres")
//    @Column(length = 60, nullable = false)
//    private String locXlgr;
//    // Número
//    @Column(length = 60, nullable = false)
//    @Length(min = 1, max = 60, message = "Número deve conter entre {min} e {max} caracteres")
//    @NotNull(message = "Número é obrigatório")
//    private String locNro;
//    // Complemento
//    @Length(min = 1, max = 60, message = "Complemento deve conter entre {min} e {max} caracteres")
//    @Column(length = 60, nullable = true)
//    private String locXcpl;
//    // Bairro
//    @NotNull(message = "Bairro é obrigatório")
//    @Length(min = 2, max = 60, message = "Bairro deve conter entre {min} e {max} caracteres")
//    @Column(length = 60, nullable = false)
//    private String locXbairro;
//    // Código do município
//    @NotNull(message = "Código de Município é obrigatório")
//    @Column(length = 7, nullable = false)
//    private String locCmun;
//    // Nome do município 
//    @NotNull(message = "Nome do Município é obrigatório")
//    @Length(min = 2, max = 60, message = "Nome do Município deve conter entre {min} e {max} caracteres")
//    @Column(length = 60, nullable = false)
//    private String locXmun;
//    // Sigla da UF
//    @NotNull(message = "UF do Município é obrigatório")
//    @Column(length = 2, nullable = false)
//    private String locUf;
//    
//    
    // Documento
    @NotEmpty(message = "Documento é obrigatório")
    @Length(min = 1, max = 14, message = "Documento deve conter entre {min} e {max} caracteres")
    @Column(length = 14)
    private String locDocumento;
    // Cep
    @Column(length = 8)
    private String locCep;
    // Logradouro
    @NotEmpty(message = "Logradouro é obrigatório")
    @Length(min = 2, max = 60, message = "Logradouro deve conter entre {min} e {max} caracteres")
    @Column(length = 60)
    private String locXlgr;
    // Número
    @NotEmpty(message = "Número é obrigatório")
    @Length(min = 1, max = 60, message = "Número deve conter entre {min} e {max} caracteres")
    @Column(length = 60)
    private String locNro;
    // Complemento
    @Length(min = 1, max = 60, message = "Complemento deve conter entre {min} e {max} caracteres")
    @Column(length = 60)
    private String locXcpl;
    // Bairro
    @NotEmpty(message = "Bairro é obrigatório")
    @Length(min = 2, max = 60, message = "Bairro deve conter entre {min} e {max} caracteres")
    @Column(length = 60)
    private String locXbairro;
    // Código do município
    @NotEmpty(message = "Código de Município é obrigatório")
    @Column(length = 7)
    private String locCmun;
    // Nome do município 
    @NotEmpty(message = "Nome do Município é obrigatório")
    @Length(min = 2, max = 60, message = "Nome do Município deve conter entre {min} e {max} caracteres")
    @Column(length = 60)
    private String locXmun;
    // Sigla da UF
    @NotEmpty(message = "UF do Município é obrigatório")
    @Column(length = 2)
    private String locUf;

    public Local() {
    }

    public Local(Local local) {
        limpaLocal();
        this.locDocumento = local.getLocDocumento();
        this.locCep = local.getLocCep();
        this.locXbairro = local.getLocXbairro();
        this.locXcpl = local.getLocXcpl();
        this.locCmun = local.getLocCmun();
        this.locXmun = local.getLocXmun();
        this.locXlgr = local.getLocXlgr();
        this.locNro = local.getLocNro();
        this.locUf = local.getLocUf();
    }
    
    public Local(Cep cepFound, String documento) {
        limpaLocal();
        this.locDocumento = documento;
        this.locCep = cepFound.getCep() != null ? cepFound.getCep().replace("-", "") : null;
        this.locXbairro = cepFound.getBairro();
        this.locXcpl = cepFound.getComplemento();
        this.locCmun = cepFound.getIbge();
        this.locXmun = cepFound.getLocalidade();
        this.locXlgr = cepFound.getLogradouro();
        this.locUf = cepFound.getUf();
    }

    public final void limpaLocal() {
        this.locDocumento = null;
        this.locCep = null;
        this.locXlgr = null;
        this.locNro = null;
        this.locXcpl = null;
        this.locXbairro = null;
        this.locCmun = null;
        this.locXmun = null;
        this.locUf = null;
    }

    public String getLocDocumento() {
        return locDocumento;
    }

    public void setLocDocumento(String locDocumento) {
        this.locDocumento = locDocumento;
    }

    public String getLocCep() {
        return locCep;
    }

    public void setLocCep(String locCep) {
        this.locCep = locCep;
    }

    public String getLocXlgr() {
        return locXlgr;
    }

    public void setLocXlgr(String locXlgr) {
        this.locXlgr = locXlgr;
    }

    public String getLocNro() {
        return locNro;
    }

    public void setLocNro(String locNro) {
        this.locNro = locNro;
    }

    public String getLocXcpl() {
        return locXcpl;
    }

    public void setLocXcpl(String locXcpl) {
        this.locXcpl = locXcpl;
    }

    public String getLocXbairro() {
        return locXbairro;
    }

    public void setLocXbairro(String locXbairro) {
        this.locXbairro = locXbairro;
    }

    public String getLocCmun() {
        return locCmun;
    }

    public void setLocCmun(String locCmun) {
        this.locCmun = locCmun;
    }

    public String getLocXmun() {
        return locXmun;
    }

    public void setLocXmun(String locXmun) {
        this.locXmun = locXmun;
    }

    public String getLocUf() {
        return locUf;
    }

    public void setLocUf(String locUf) {
        this.locUf = locUf;
    }

}
