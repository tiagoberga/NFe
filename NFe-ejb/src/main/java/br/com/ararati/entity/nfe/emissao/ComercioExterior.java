/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.nfe.emissao;

import br.com.ararati.entity.cadastros.*;
import br.com.ararati.entity.AbstractEntity;
import enviNFe_v310.TUfEmi;
import javax.persistence.Column;
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
 * Informações de Comércio Exterior
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "comercio_exterior")
public class ComercioExterior extends AbstractEntity {

    @ManyToOne
    @NotNull(message = "Empresa Emitente é obrigatório")
    @JoinColumn(name = "emitente_id", nullable = false)
    private Emitente emitente;

    @OneToOne
    @JoinColumn(name = "dados_nfe_id", nullable = false)
    private DadosNFe dadosNFe;

    // Sigla da UF de Embarque ou de transposição de fronteira
    @NotNull(message = "Sigla da UF é obrigatório")
    @Column(length = 2, nullable = false)
    @Enumerated(EnumType.STRING)
    private TUfEmi ufsaidapais;
    // Descrição do Local de Embarque ou de transposição de fronteira
    @NotNull(message = "Descrição do Local é obrigatório")
    @Length(min = 1, max = 60, message = "Descrição do Local de Embarque deve conter entre {min} e {max} caracteres")
    @Column(length = 60, nullable = false)
    private String xlocexporta;
    // Descrição do local de despacho
    @Length(min = 1, max = 60, message = "Descrição do Local de Despacho deve conter entre {min} e {max} caracteres")
    @Column(length = 60, nullable = true)
    private String xlocdespacho;

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

    public TUfEmi getUfsaidapais() {
        return ufsaidapais;
    }

    public void setUfsaidapais(TUfEmi ufsaidapais) {
        this.ufsaidapais = ufsaidapais;
    }

    public String getXlocexporta() {
        return xlocexporta;
    }

    public void setXlocexporta(String xlocexporta) {
        this.xlocexporta = xlocexporta;
    }

    public String getXlocdespacho() {
        return xlocdespacho;
    }

    public void setXlocdespacho(String xlocdespacho) {
        this.xlocdespacho = xlocdespacho;
    }

}
