/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.faces.cadastros;

import br.com.ararati.Cep;
import br.com.ararati.ConsultaCepException;
import br.com.ararati.entity.cadastros.Emitente;
import br.com.ararati.entity.cadastros.Transportadora;
import br.com.ararati.exception.DaoException;
import br.com.ararati.faces.UtilFaces;
import br.com.ararati.filter.cadastros.TransportadoraFilter;
import br.com.ararati.session.cadastros.EmitenteFacade;
import br.com.ararati.session.cadastros.TransportadoraFacade;
import br.com.ararati.webservices.cep.WSCepFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author tiago
 */
@Named(value = "transportadoraFaces")
@ViewScoped
public class TransportadoraFaces implements Serializable {

    @EJB
    private EmitenteFacade emitenteFacade;
    @EJB
    private TransportadoraFacade transportadoraFacade;

    // FILTER
    private TransportadoraFilter transportadoraFilter;

    // LISTS
    private List<Transportadora> model = new ArrayList<>();

    private Transportadora transportadora;
    private Emitente emitente;

    public TransportadoraFaces() {
        transportadora = new Transportadora();
    }

    @PostConstruct
    public void init() {
        this.emitente = this.emitenteFacade.findAll().get(0);

        novo();
        initFilter();
    }

    public void novo() {
        this.transportadora = new Transportadora();
        this.transportadora.setEmitente(this.emitente);
    }

    public void initFilter() {
        this.transportadoraFilter = new TransportadoraFilter();
        this.transportadoraFilter.setEmitente(this.emitente);
    }

    public void pesquisar() {
        this.model = this.transportadoraFacade.findByFilter(this.transportadoraFilter);
    }

    public void deleteById(String transportadoraId) {
        Transportadora toRemove = this.transportadoraFacade.find(transportadoraId);

        if (toRemove != null) {
            try {
                this.transportadoraFacade.remove(toRemove);
                pesquisar();
            } catch (DaoException ex) {
                UtilFaces.showDialogMessageError(ex.getMessage());
            }
        }
    }

    public void salvar() {
        try {
            // SALVA TRANSPORTADORA
            this.transportadoraFacade.save(this.transportadora);
            UtilFaces.showDialogMessageInfo("Dados de Transportador salvos com sucesso!");

            if (this.transportadora.isNew()) {
                // ATRIBUI DADOS SALVOS AO FILTRO
                this.transportadoraFilter = new TransportadoraFilter(this.transportadora);
                pesquisar();
            }

            novo();
        } catch (DaoException ex) {
            Logger.getLogger(TransportadoraFaces.class.getName()).log(Level.SEVERE, null, ex);
            UtilFaces.showDialogMessageError(ex.getMessage());
        }
    }

    public void limparTela() {
        novo();
    }

    public void buscaCep() {
        try {
            String cep = this.transportadora.getEndereco().getEndCep();
            this.transportadora.getEndereco().limpaEndereco();

            // ENCONTRA CEP
            Cep cepFound = new WSCepFacade().getCepFromXml(cep);
            this.transportadora.getEndereco().setEndCep(cepFound.getCep() != null ? cepFound.getCep().replace("-", "") : null);
            this.transportadora.getEndereco().setEndXbairro(cepFound.getBairro());
            this.transportadora.getEndereco().setEndXcpl(cepFound.getComplemento());
            this.transportadora.getEndereco().setEndCmun(cepFound.getIbge());
            this.transportadora.getEndereco().setEndXmun(cepFound.getLocalidade());
            this.transportadora.getEndereco().setEndXlgr(cepFound.getLogradouro());
            this.transportadora.getEndereco().setEndUf(cepFound.getUf());
        } catch (ConsultaCepException ex) {
            Logger.getLogger(TransportadoraFaces.class.getName()).log(Level.SEVERE, null, ex);
            UtilFaces.showDialogMessageError(ex.getMessage());
        }
    }

    // GETTS SETTS
    public TransportadoraFilter getTransportadoraFilter() {
        return transportadoraFilter;
    }

    public void setTransportadoraFilter(TransportadoraFilter transportadoraFilter) {
        this.transportadoraFilter = transportadoraFilter;
    }

    public List<Transportadora> getModel() {
        return model;
    }

    public void setModel(List<Transportadora> model) {
        this.model = model;
    }

    public Transportadora getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora;
    }

}
