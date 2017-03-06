/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.nfe.emissao;

import br.com.ararati.entity.cadastros.*;
import br.com.ararati.entity.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * Informações de Comércio Exterior
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "compra")
public class Compra extends AbstractEntity {

    @ManyToOne
    @NotNull(message = "Empresa Emitente é obrigatório")
    @JoinColumn(name = "emitente_id", nullable = false)
    private Emitente emitente;

    @OneToOne
    @JoinColumn(name = "dados_nfe_id", nullable = false)
    private DadosNFe dadosNFe;

    // Nota de Empenho
    @Length(min = 1, max = 22, message = "Nota de Empenho deve conter entre {min} e {max} caracteres")
    @Column(length = 22, nullable = true)
    private String xnemp;
    // Pedido
    @Length(min = 1, max = 60, message = "Pedido deve conter entre {min} e {max} caracteres")
    @Column(length = 60, nullable = true)
    private String xped;
    // Contrato
    @Length(min = 1, max = 60, message = "Contrato deve conter entre {min} e {max} caracteres")
    @Column(length = 60, nullable = true)
    private String xcont;

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

    public String getXnemp() {
        return xnemp;
    }

    public void setXnemp(String xnemp) {
        this.xnemp = xnemp;
    }

    public String getXped() {
        return xped;
    }

    public void setXped(String xped) {
        this.xped = xped;
    }

    public String getXcont() {
        return xcont;
    }

    public void setXcont(String xcont) {
        this.xcont = xcont;
    }

    
}
