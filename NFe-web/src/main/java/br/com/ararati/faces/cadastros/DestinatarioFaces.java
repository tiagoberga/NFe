/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.faces.cadastros;

import br.com.ararati.Cep;
import br.com.ararati.ConsultaCepException;
import br.com.ararati.entity.cadastros.Destinatario;
import br.com.ararati.entity.cadastros.Emitente;
import br.com.ararati.entity.cadastros.Endereco;
import br.com.ararati.entity.cadastros.Local;
import br.com.ararati.enums.E.NFeTipoIndicadorIEDestinatario;
import br.com.ararati.exception.DaoException;
import br.com.ararati.faces.UtilFaces;
import br.com.ararati.filter.cadastros.DestinatarioFilter;
import br.com.ararati.session.cadastros.DestinatarioFacade;
import br.com.ararati.session.cadastros.EmitenteFacade;
import br.com.ararati.webservices.cep.WSCepFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.commons.lang3.StringUtils;

/**
 * @author tiago
 */
@Named(value = "destinatarioFaces")
@ViewScoped
public class DestinatarioFaces implements Serializable {

    @EJB
    private EmitenteFacade emitenteFacade;
    @EJB
    private DestinatarioFacade destinatarioFacade;

    // VARIAVEIS LOCAIS
    private Local localEntrega;

    // FILTERS
    private DestinatarioFilter destinatarioFilter;

    // LISTS
    private List<Destinatario> model = new ArrayList<>();

    private Destinatario destinatario;
    private Emitente emitente;

    @PostConstruct
    public void init() {
        this.emitente = this.emitenteFacade.findAll().get(0);

        novo();
        initFilter();
    }

    public void novo() {
        this.destinatario = new Destinatario();
        this.destinatario.setEmitente(this.emitente);
    }

    public void initModel() {
        this.model = this.destinatarioFacade.findAll();
    }

    public void initFilter() {
        this.destinatarioFilter = new DestinatarioFilter();
        this.destinatarioFilter.setEmitente(this.emitente);
    }

    public void pesquisar() {
        this.model = this.destinatarioFacade.findAll();
    }

    public void deleteById(String destinatarioId) {
        Destinatario toRemove = this.destinatarioFacade.find(destinatarioId);

        if (toRemove != null) {
            try {
                this.destinatarioFacade.remove(toRemove);
                pesquisar();
            } catch (DaoException ex) {
                UtilFaces.showDialogMessageError(ex.getMessage());
            }
        }
    }

    public void salvar() {
        try {
            this.destinatarioFacade.save(this.destinatario);
            UtilFaces.showDialogMessageInfo("Dados de Destinatario salvos com sucesso!");

            if (this.destinatario.isNew()) {
                // ATRIBUI DADOS SALVOS AO FILTRO
                this.destinatarioFilter = new DestinatarioFilter();
                pesquisar();
            }

            novo();
        } catch (DaoException ex) {
            Logger.getLogger(DestinatarioFaces.class.getName()).log(Level.SEVERE, null, ex);
            UtilFaces.showDialogMessageError(ex.getMessage());
        }
    }

    public void buscaCep() {
        try {
            String cep = this.destinatario.getEndereco().getEndCep();
            // ENCONTRA CEP
            Cep cepFound = new WSCepFacade().getCepFromXml(cep);
            Endereco endereco = new Endereco(cepFound);
            this.destinatario.setEndereco(endereco);
        } catch (ConsultaCepException ex) {
            Logger.getLogger(DestinatarioFaces.class.getName()).log(Level.SEVERE, null, ex);
            UtilFaces.showDialogMessageError(ex.getMessage());
        }
    }

    /**
     * Método busca cep do emitente.
     */
    public void buscaCepDestinatarioLocalEntrega() {
        try {
            if (this.destinatario != null) {
                String cep = this.destinatario.getLocalEntrega().getLocCep();
                String documento = this.destinatario.getLocalEntrega().getLocDocumento();
                // ENCONTRA CEP
                Cep cepFound = new WSCepFacade().getCepFromXml(cep);
                Local local = new Local(cepFound, documento);
                this.destinatario.setLocalEntrega(local);
            }
        } catch (ConsultaCepException ex) {
            Logger.getLogger(EmitenteFaces.class.getName()).log(Level.SEVERE, null, ex);
            UtilFaces.showDialogMessageError(ex.getMessage());
        }
    }

    public void iniciaLocalEntrega() {
        if (this.destinatario != null) {
            if (this.destinatario.isLocalEntregaDiferenteDestinatario()) {
                this.destinatario.novoLocal();
            } else {
                this.destinatario.limpaLocal();
            }
        }
    }

    public List<NFeTipoIndicadorIEDestinatario> completeindIeDest(String query) {
        return NFeTipoIndicadorIEDestinatario.valuesAsList().stream()
                .filter(v -> StringUtils.containsIgnoreCase(v.getDescricao(), query))
                .collect(Collectors.toList());
    }

    public List<NFeTipoIndicadorIEDestinatario> getIndicadoresDeIe() {
        return NFeTipoIndicadorIEDestinatario.valuesAsList();
    }

    // GETTS SETTS
    public DestinatarioFilter getDestinatarioFilter() {
        return destinatarioFilter;
    }

    public void setDestinatarioFilter(DestinatarioFilter destinatarioFilter) {
        this.destinatarioFilter = destinatarioFilter;
    }

    public List<Destinatario> getModel() {
        return model;
    }

    public void setModel(List<Destinatario> model) {
        this.model = model;
    }

    public Destinatario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Destinatario destinatario) {
        this.destinatario = destinatario;
    }

    public Local getLocalEntrega() {
        return localEntrega;
    }

    public void setLocalEntrega(Local localEntrega) {
        this.localEntrega = localEntrega;
    }

}
