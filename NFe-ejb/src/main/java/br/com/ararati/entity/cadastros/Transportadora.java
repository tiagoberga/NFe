/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.cadastros;

import br.com.ararati.entity.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * Informações do Transporte da NF-e.
 *
 * @author tiago
 */
@Entity
@Table(schema = "public", name = "transportadora")
public class Transportadora extends AbstractEntity {

    public static final int P_EMITENTE = 1;
    public static final int P_DOCUMENTO = 2;
    public static final int P_XNOME = 3;
    public static final int P_IE = 4;

    @ManyToOne
    @NotNull(message = "Empresa Emitente é obrigatório")
    @JoinColumn(name = "emitente_id", nullable = false)
    private Emitente emitente;

    // DADOS DE TRANPORTADORA
    @NotNull(message = "Documento é obrigatório")
    @Column(length = 20, nullable = false)
    @Length(min = 1, max = 20, message = "Documento deve conter entre {min} e {max} caracteres")
    private String documento;
    @NotNull(message = "Razão Social é obrigatório")
    @Column(length = 60, nullable = false)
    @Length(min = 2, max = 60, message = "Razão Social deve conter entre {min} e {max} caracteres")
    private String xnome;
    @Column(length = 14, nullable = true)
    @Length(min = 2, max = 14, message = "Inscrição Estadual deve conter entre {min} e {max} caracteres")
    private String ie;

    // ENDERECO
    @Embedded
    private Endereco endereco;

    public Transportadora() {
        this.endereco = new Endereco();
    }

    public Emitente getEmitente() {
        return emitente;
    }

    public void setEmitente(Emitente emitente) {
        this.emitente = emitente;
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

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}
