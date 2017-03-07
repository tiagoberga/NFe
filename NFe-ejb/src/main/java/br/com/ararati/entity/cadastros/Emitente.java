/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.cadastros;

import br.com.ararati.entity.AbstractEntity;
import br.com.ararati.enums.C.NFeTipoRegimeTributario;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * Identificação do Emitente da Nota Fiscal eletrônica
 *
 * @author tiago
 */
@Entity
@Table(schema = "public", name = "emitente")
public class Emitente extends AbstractEntity {

    public static final int P_DOCUMENTO = 1;
    public static final int P_XNOME = 2;
    public static final int P_XFANT = 3;
    public static final int P_IE = 4;
    public static final int P_IEST = 5;
    public static final int P_IM = 6;
    public static final int P_CNAE = 7;
    public static final int P_CRT = 8;

    // DADOS DE EMITENTE
    @NotNull(message = "Documento é obrigatório")
    @Column(length = 20, nullable = false)
    @Length(min = 1, max = 20, message = "Documento deve conter entre {min} e {max} caracteres")
    private String documento;
    @NotNull(message = "Razão Social é obrigatório")
    @Column(length = 60, nullable = false)
    @Length(min = 2, max = 60, message = "Razão Social deve conter entre {min} e {max} caracteres")
    private String xnome;
    @Column(length = 60, nullable = true)
    @Length(min = 1, max = 60, message = "Nome Fantasia deve conter entre {min} e {max} caracteres")
    private String xfant;
    @NotNull(message = "Inscrição Estadual é obrigatório")
    @Column(length = 14, nullable = false)
    @Length(min = 2, max = 14, message = "Inscrição Estadual deve conter entre {min} e {max} caracteres")
    private String ie;
    @Column(length = 14, nullable = true)
    @Length(min = 2, max = 14, message = "Inscrição Substituto Tributário deve conter entre {min} e {max} caracteres")
    private String iest;
    @NotNull(message = "Inscrição Municipal é obrigatório")
    @Column(length = 15, nullable = false)
    @Length(min = 1, max = 15, message = "Inscrição Municipal deve conter entre {min} e {max} caracteres")
    private String im;
    @Column(length = 7, nullable = true)
    private String cnae;
    @NotNull(message = "Regime Tributário é obrigatório")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NFeTipoRegimeTributario crt;

    @Column(name = "local_retirada_diferente_emitente")
    private boolean localRetiradaDiferenteEmitente = false;

    // DADOS DE ENDERECO
    @Embedded
    private Endereco endereco;

    // DADOS DE LOCAL DE RETIRADA
    @Embedded
    @Valid
    private Local localRetirada;

    public Emitente() {
        this.endereco = new Endereco();
        // novoLocal();
    }

    public void novoLocal() {
        this.localRetirada = new Local();
    }

    public void limpaLocal() {
        this.localRetirada = null;
    }

    public boolean isRegimeSimples() {
        return this.crt != null && !this.crt.equals(NFeTipoRegimeTributario.NORMAL);
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

    public Local getLocalRetirada() {
        return localRetirada;
    }

    public void setLocalRetirada(Local localRetirada) {
        this.localRetirada = localRetirada;
    }

    public boolean isLocalRetiradaDiferenteEmitente() {
        return localRetiradaDiferenteEmitente;
    }

    public void setLocalRetiradaDiferenteEmitente(boolean localRetiradaDiferenteEmitente) {
        this.localRetiradaDiferenteEmitente = localRetiradaDiferenteEmitente;
    }

}
