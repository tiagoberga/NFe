/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.nfe.emissao;

import br.com.ararati.Cep;
import br.com.ararati.entity.cadastros.Local;
import br.com.ararati.entity.cadastros.*;
import br.com.ararati.entity.AbstractEntity;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Identificação do Destinatário da Nota Fiscal eletrônica
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "identificacao_local_entrega")
public class IdentificacaoLocalEntrega extends AbstractEntity {

    @ManyToOne
    @NotNull(message = "Empresa Emitente é obrigatório")
    @JoinColumn(name = "emitente_id", nullable = false)
    private Emitente emitente;

    @OneToOne
    @JoinColumn(name = "dados_nfe_id", nullable = false)
    private DadosNFe dadosNFe;

    // DADOS DE ENDERECO
    @Embedded
    private Local local;

    public IdentificacaoLocalEntrega() {
        this.local = new Local();
    }

    public IdentificacaoLocalEntrega(Local local) {
        this.local = new Local(local);
    }

    public IdentificacaoLocalEntrega(Cep cepFound, String documento) {
        this.local = new Local(cepFound, documento);
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

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

}
