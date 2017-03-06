/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.nfe.emissao;

import br.com.ararati.entity.cadastros.*;
import br.com.ararati.entity.AbstractEntity;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Total da NF-e
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "total_nfe")
public class TotalNFe extends AbstractEntity {

    @ManyToOne
    @NotNull(message = "Empresa Emitente é obrigatório")
    @JoinColumn(name = "emitente_id", nullable = true)
    private Emitente emitente;

    @OneToOne
    @JoinColumn(name = "dados_nfe_id", nullable = false)
    private DadosNFe dadosNFe;

    @Valid
    @OneToOne(mappedBy = "totalNFe", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private TotalNFeICMS totalNFeIcms;

    @Valid
    @OneToOne(mappedBy = "totalNFe", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private TotalNFeISSQN totalNFeIssqn;

    @Valid
    @OneToOne(mappedBy = "totalNFe", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private TotalNFeRetencao totalNFeRetencao;

    public TotalNFe() {
        totalNFeIcms = new TotalNFeICMS();
        totalNFeIssqn = new TotalNFeISSQN();
        totalNFeRetencao = new TotalNFeRetencao();
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

    public TotalNFeICMS getTotalNFeIcms() {
        return totalNFeIcms;
    }

    public void setTotalNFeIcms(TotalNFeICMS totalNFeIcms) {
        this.totalNFeIcms = totalNFeIcms;
    }

    public TotalNFeISSQN getTotalNFeIssqn() {
        return totalNFeIssqn;
    }

    public void setTotalNFeIssqn(TotalNFeISSQN totalNFeIssqn) {
        this.totalNFeIssqn = totalNFeIssqn;
    }

    public TotalNFeRetencao getTotalNFeRetencao() {
        return totalNFeRetencao;
    }

    public void setTotalNFeRetencao(TotalNFeRetencao totalNFeRetencao) {
        this.totalNFeRetencao = totalNFeRetencao;
    }

}
