/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.faces.config;

import br.com.ararati.entity.config.ConfiguracaoWebService;
import br.com.ararati.enums.B.NFeTipoAmbiente;
import br.com.ararati.enums.B.NFeTipoEmissao;
import br.com.ararati.enums.commons.NFeTipoServico;
import br.com.ararati.enums.commons.NFeTipoUFWebService;
import br.com.ararati.exception.DaoException;
import br.com.ararati.faces.UtilFaces;
import br.com.ararati.filter.WebServiceFilter;
import br.com.ararati.session.cadastros.EmitenteFacade;
import br.com.ararati.session.config.ConfiguracaoWebServiceFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 * @author tiago
 */
@Named(value = "webserviceFaces")
@ViewScoped
public class WebServiceFaces implements Serializable {

    @EJB
    private EmitenteFacade emitenteFacade;
    @EJB
    private ConfiguracaoWebServiceFacade webServiceFacade;

    // FILTERS
    private WebServiceFilter webServiceFilter;

    // LISTS
    private List<ConfiguracaoWebService> model = new ArrayList<>();

    private ConfiguracaoWebService webService;

    @PostConstruct
    public void init() {
        novo();
        initFilter();
    }

    public void novo() {
        this.webService = new ConfiguracaoWebService();
        this.webService.setTipoAmbiente(NFeTipoAmbiente.PRODUCAO);
    }

    public void initFilter() {
        this.webServiceFilter = new WebServiceFilter();
    }

    public void pesquisar() {
        this.model = this.webServiceFacade.findAll();
    }

    public void deleteById(String wsId) {
        ConfiguracaoWebService toRemove = this.webServiceFacade.find(wsId);

        if (toRemove != null) {
            try {
                this.webServiceFacade.remove(toRemove);
                pesquisar();
            } catch (DaoException ex) {
                UtilFaces.showDialogMessageError(ex.getMessage());
            }
        }
    }

    public void salvar() {
        try {
            // SALVA EMITENTE
            this.webServiceFacade.save(this.webService);
            UtilFaces.showDialogMessageInfo("Dados de Web Service salvos com sucesso!");

            if (this.webService.isNew()) {
                // ATRIBUI DADOS SALVOS AO FILTRO
                this.webServiceFilter = new WebServiceFilter();
                pesquisar();
            }

            novo();
        } catch (DaoException ex) {
            Logger.getLogger(WebServiceFaces.class.getName()).log(Level.SEVERE, null, ex);
            UtilFaces.showDialogMessageError(ex.getMessage());
        }
    }

    public List<NFeTipoUFWebService> getUfsDeWebService() {
        return NFeTipoUFWebService.valuesAsList();
    }

    public List<NFeTipoServico> getServicosSefaz() {
        return NFeTipoServico.valuesAsList();
    }

    public List<NFeTipoEmissao> getFormasDeEmissao() {
        return NFeTipoEmissao.valuesAsList();
    }

    public void onEditComplete(ActionEvent event) {
        RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
        RequestContext.getCurrentInstance().execute("PF('accWs').select(1);");
    }

    // GETTS SETTS
    public WebServiceFilter getWebServiceFilter() {
        return webServiceFilter;
    }

    public void setWebServiceFilter(WebServiceFilter webServiceFilter) {
        this.webServiceFilter = webServiceFilter;
    }

    public List<ConfiguracaoWebService> getModel() {
        return model;
    }

    public void setModel(List<ConfiguracaoWebService> model) {
        this.model = model;
    }

    public ConfiguracaoWebService getWebService() {
        return webService;
    }

    public void setWebService(ConfiguracaoWebService webService) {
        this.webService = webService;
    }

}
