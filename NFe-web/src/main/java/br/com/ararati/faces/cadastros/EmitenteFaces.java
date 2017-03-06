/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.faces.cadastros;

import br.com.ararati.Cep;
import br.com.ararati.ConsultaCepException;
import br.com.ararati.entity.cadastros.Emitente;
import br.com.ararati.entity.cadastros.Endereco;
import br.com.ararati.entity.cadastros.Local;
import br.com.ararati.enums.C.NFeTipoRegimeTributario;
import br.com.ararati.exception.DaoException;
import br.com.ararati.faces.UtilFaces;
import br.com.ararati.filter.cadastros.EmitenteFilter;
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
@Named(value = "emitenteFaces")
@ViewScoped
public class EmitenteFaces implements Serializable {

    @EJB
    private EmitenteFacade emitenteFacade;

    // VARIAVEIS LOCAIS
    private Local localRetirada;

    // FILTERS
    private EmitenteFilter emitenteFilter;

    // LISTS
    private List<Emitente> model = new ArrayList<>();

    private Emitente emitenteSelected;
    private Emitente emitente;

    @PostConstruct
    public void init() {
        novo();
        initFilter();
    }

    public void novo() {
        this.emitente = new Emitente();
    }

    public void initFilter() {
        this.emitenteFilter = new EmitenteFilter();
    }

    public void limparTela() {
        novo();
    }

    public void pesquisar() {
        this.model = this.emitenteFacade.findByFilter(this.emitenteFilter);
    }

    public void deleteById(String emitenteId) {
        Emitente toRemove = this.emitenteFacade.find(emitenteId);

        if (toRemove != null) {
            try {
                this.emitenteFacade.remove(toRemove);
                pesquisar();
            } catch (DaoException ex) {
                UtilFaces.showDialogMessageError(ex.getMessage());
            }
        }
    }

    public void salvar() {
        try {
            // SALVA EMITENTE
            this.emitenteFacade.save(this.emitente);
            UtilFaces.showDialogMessageInfo("Dados de Emitente salvos com sucesso!");

            if (this.emitente.isNew()) {
                // ATRIBUI DADOS SALVOS AO FILTRO
                this.emitenteFilter = new EmitenteFilter(emitente);
                pesquisar();
            }

            novo();
        } catch (DaoException ex) {
            Logger.getLogger(EmitenteFaces.class.getName()).log(Level.SEVERE, null, ex);
            UtilFaces.showDialogMessageError(ex.getMessage());
        }
    }

    public void buscaCep() {
        try {
            String cep = this.emitente.getEndereco().getEndCep();
            // ENCONTRA CEP
            Cep cepFound = new WSCepFacade().getCepFromXml(cep);
            Endereco endereco = new Endereco(cepFound);
            this.emitente.setEndereco(endereco);
        } catch (ConsultaCepException ex) {
            Logger.getLogger(EmitenteFaces.class.getName()).log(Level.SEVERE, null, ex);
            UtilFaces.showDialogMessageError(ex.getMessage());
        }
    }

    public void buscaCepEmitenteLocalRetirada() {
        try {
            if (this.emitente != null) {
                String cep = this.emitente.getLocalRetirada().getLocCep();
                String documento = this.emitente.getLocalRetirada().getLocDocumento();
                // ENCONTRA CEP
                Cep cepFound = new WSCepFacade().getCepFromXml(cep);
                Local local = new Local(cepFound, documento);
                this.emitente.setLocalRetirada(local);
            }
        } catch (ConsultaCepException ex) {
            Logger.getLogger(EmitenteFaces.class.getName()).log(Level.SEVERE, null, ex);
            UtilFaces.showDialogMessageError(ex.getMessage());
        }
    }

    public List<NFeTipoRegimeTributario> completeEnum(String query) {
        return NFeTipoRegimeTributario.valuesAsList().stream()
                .filter(v -> StringUtils.containsIgnoreCase(v.getDescricao(), query))
                .collect(Collectors.toList());
    }

    public List<NFeTipoRegimeTributario> getTiposDeRegimesTributarios() {
        return NFeTipoRegimeTributario.valuesAsList();
    }

    public void iniciaLocalRetirada() {
        if (this.emitente != null) {
            if (this.emitente.isLocalRetiradaDiferenteEmitente()) {
                this.emitente.novoLocal();
            } else {
                this.emitente.limpaLocal();
            }
        }
    }

    // GETTS SETTS
    public Emitente getEmitente() {
        return emitente;
    }

    public void setEmitente(Emitente emitente) {
        this.emitente = emitente;
    }

    public List<Emitente> getModel() {
        return model;
    }

    public void setModel(List<Emitente> model) {
        this.model = model;
    }

    public EmitenteFacade getEmitenteFacade() {
        return emitenteFacade;
    }

    public void setEmitenteFacade(EmitenteFacade emitenteFacade) {
        this.emitenteFacade = emitenteFacade;
    }

    public EmitenteFilter getEmitenteFilter() {
        return emitenteFilter;
    }

    public void setEmitenteFilter(EmitenteFilter emitenteFilter) {
        this.emitenteFilter = emitenteFilter;
    }

    public Emitente getEmitenteSelected() {
        return emitenteSelected;
    }

    public void setEmitenteSelected(Emitente emitenteSelected) {
        this.emitenteSelected = emitenteSelected;
    }

    public Local getLocalRetirada() {
        return localRetirada;
    }

    public void setLocalRetirada(Local localRetirada) {
        this.localRetirada = localRetirada;
    }

}
