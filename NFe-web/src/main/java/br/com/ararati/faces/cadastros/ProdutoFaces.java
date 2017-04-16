/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.faces.cadastros;

import br.com.ararati.entity.cadastros.Emitente;
import br.com.ararati.entity.cadastros.Produto;
import br.com.ararati.enums.I.NFeTipoIndicadorVProd;
import br.com.ararati.exception.DaoException;
import br.com.ararati.faces.UtilFaces;
import br.com.ararati.filter.cadastros.ProdutoFilter;
import br.com.ararati.session.cadastros.EmitenteFacade;
import br.com.ararati.session.cadastros.ProdutoFacade;
import java.io.Serializable;
import java.math.BigDecimal;
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
 *
 * @author tiago
 */
@Named(value = "produtoFaces")
@ViewScoped
public class ProdutoFaces implements Serializable {

    @EJB
    private EmitenteFacade emitenteFacade;
    @EJB
    private ProdutoFacade produtoFacade;

    // FILTERS
    private ProdutoFilter produtoFilter;

    // LISTS
    private List<Produto> model = new ArrayList<>();

    private Produto produto;
    private Emitente emitente;

    @PostConstruct
    public void init() {
        this.emitente = emitenteFacade.findAll().get(0);

        novo();
        initFilter();
    }

    public void novo() {
        this.produto = new Produto();
        this.produto.setEmitente(this.emitente);
    }

    public void initFilter() {
        this.produtoFilter = new ProdutoFilter();
        this.produtoFilter.setEmitente(this.emitente);
    }

    public void pesquisar() {
        this.model = this.produtoFacade.findByFilter(this.produtoFilter);
    }

    /**
     * Método responsavel por excluir um registro de produto.
     *
     * @param produtoId id do produto a ser removido.
     */
    public void deleteBy(String produtoId) {
        Produto toRemove = this.produtoFacade.find(produtoId);

        if (toRemove != null) {
            try {
                this.produtoFacade.remove(toRemove);
                pesquisar();
            } catch (DaoException ex) {
                UtilFaces.showDialogMessageError(ex.getMessage());
            }
        }
    }

    public void salvar() {
        try {
            // SALVA EMITENTE
            this.produtoFacade.save(this.produto);
            UtilFaces.showDialogMessageInfo("Dados do produto salvos com sucesso!");

            if (this.produto.isNew()) {
                // ATRIBUI DADOS SALVOS AO FILTRO
                this.produtoFilter = new ProdutoFilter(this.produto);
                pesquisar();
            }

            novo();
        } catch (DaoException ex) {
            Logger.getLogger(ProdutoFaces.class.getName()).log(Level.SEVERE, null, ex);
            UtilFaces.showDialogMessageError(ex.getMessage());
        }
    }

    public void limparTela() {
        novo();
    }

    public List<String> completeCFOP(String query) {
        List<String> listaString = new ArrayList<>();

        listaString.add("COD1");
        listaString.add("COD2");
        listaString.add("COD3");

        return listaString.stream()
                .filter(v -> StringUtils.containsIgnoreCase(v, query))
                .collect(Collectors.toList());
    }

    public List<String> completeCEST(String query) {
        List<String> listaString = new ArrayList<>();

        listaString.add("COOOOD1");
        listaString.add("COOOOD2");
        listaString.add("COOOOD3");

        return listaString.stream()
                .filter(v -> StringUtils.containsIgnoreCase(v, query))
                .collect(Collectors.toList());
    }

    public List<String> completeNCM(String query) {
        List<String> listaString = new ArrayList<>();

        listaString.add("COOOOOD1");
        listaString.add("COOOOOD2");
        listaString.add("COOOOOD3");

        return listaString.stream()
                .filter(v -> StringUtils.containsIgnoreCase(v, query))
                .collect(Collectors.toList());
    }

    public List<NFeTipoIndicadorVProd> completeIndicadorVProd(String query) {
        return NFeTipoIndicadorVProd.valuesAsList().stream()
                .filter(v -> StringUtils.containsIgnoreCase(v.getDescricao(), query))
                .collect(Collectors.toList());
    }

    public List<NFeTipoIndicadorVProd> getIndicadoresDeProdutoNaNFe() {
        return NFeTipoIndicadorVProd.valuesAsList();
    }

    public void calculaVProd() {
        double vprod = 0;

        if (this.produto == null) {
            // nao calcula
        } else {
            if (this.produto.getQcom() != null && this.produto.getVuncom() != null) {
                vprod = this.produto.getQcom().doubleValue() * this.produto.getVuncom().doubleValue();
            }

            if (this.produto.getVfrete() != null) {
                vprod += this.produto.getVfrete().doubleValue();
            }

            if (this.produto.getVseg() != null) {
                vprod += this.produto.getVseg().doubleValue();
            }

            if (this.produto.getVoutro() != null) {
                vprod += this.produto.getVoutro().doubleValue();
            }

            if (this.produto.getVdesc() != null) {
                vprod -= this.produto.getVdesc().doubleValue();
            }

            this.produto.setVprod(new BigDecimal(vprod));
        }
    }

    // GETTS N SETTS
    public ProdutoFilter getProdutoFilter() {
        return produtoFilter;
    }

    public void setProdutoFilter(ProdutoFilter produtoFilter) {
        this.produtoFilter = produtoFilter;
    }

    public List<Produto> getModel() {
        return model;
    }

    public void setModel(List<Produto> model) {
        this.model = model;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

}
