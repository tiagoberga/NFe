/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.cadastros;

import br.com.ararati.entity.AbstractEntity;
import br.com.ararati.enums.E.NFeTipoIndicadorIEDestinatario;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
@Table(schema = "public", name = "destinatario")
public class Destinatario extends AbstractEntity {

    public static final int P_EMITENTE = 1;
    public static final int P_DOCUMENTO = 2;
    public static final int P_XNOME = 3;

    @ManyToOne
    @NotNull(message = "Empresa Emitente é obrigatório")
    @JoinColumn(name = "emitente_id", nullable = false)
    private Emitente emitente;

    // DADOS DE DESTINATARIO
    @NotNull(message = "Documento é obrigatório")
    @Column(length = 20, nullable = false)
    @Length(min = 1, max = 20, message = "Documento deve conter entre {min} e {max} caracteres")
    private String documento;
    @NotNull(message = "Razão Social é obrigatório")
    @Column(length = 60, nullable = false)
    @Length(min = 2, max = 60, message = "Razão Social deve conter entre {min} e {max} caracteres")
    private String xnome;
    @NotNull(message = "Indicador de IE é obrigatório")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NFeTipoIndicadorIEDestinatario indIeDest;
    @Column(length = 14, nullable = true)
    @Length(min = 2, max = 14, message = "Inscrição Estadual deve conter entre {min} e {max} caracteres")
    private String ie;
    @Column(length = 9, nullable = true)
    @Length(min = 8, max = 9, message = "Inscrição na SUFRAMA deve conter entre {min} e {max} caracteres")
    private String isUf;
    @Column(length = 15, nullable = true)
    @Length(min = 1, max = 15, message = "Inscrição Municipal deve conter entre {min} e {max} caracteres")
    private String im;
    @Column(length = 60, nullable = true)
    @Length(min = 1, max = 60, message = "E-mail deve conter entre {min} e {max} caracteres")
    private String email;
    
    @Column(name = "local_entrega_diferente_destinatario")
    private boolean localEntregaDiferenteDestinatario = false;

    // DADOS DE ENDERECO
    @Embedded
    private Endereco endereco;

    // DADOS DE LOCAL DE ENTREGA
    @Embedded
    private Local localEntrega;

    public Destinatario() {
        this.endereco = new Endereco();
        // novoLocal();
    }

    public void novoLocal() {
        this.localEntrega = new Local();
    }
    public void limpaLocal() {
        this.localEntrega = null;
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

    public NFeTipoIndicadorIEDestinatario getIndIeDest() {
        return indIeDest;
    }

    public void setIndIeDest(NFeTipoIndicadorIEDestinatario indIeDest) {
        this.indIeDest = indIeDest;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getIsUf() {
        return isUf;
    }

    public void setIsUf(String isUf) {
        this.isUf = isUf;
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

    public Local getLocalEntrega() {
        return localEntrega;
    }

    public void setLocalEntrega(Local localEntrega) {
        this.localEntrega = localEntrega;
    }

    public boolean isLocalEntregaDiferenteDestinatario() {
        return localEntregaDiferenteDestinatario;
    }

    public void setLocalEntregaDiferenteDestinatario(boolean localEntregaDiferenteDestinatario) {
        this.localEntregaDiferenteDestinatario = localEntregaDiferenteDestinatario;
    }

}
