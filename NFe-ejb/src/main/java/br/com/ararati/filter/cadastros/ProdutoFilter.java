package br.com.ararati.filter.cadastros;

import br.com.ararati.entity.cadastros.Produto;
import br.com.ararati.filter.AbstractFilter;

/**
 *
 * @author tiago
 */
public class ProdutoFilter extends AbstractFilter {

    private String cprod;
    private String cean;
    private String xprod;

    public ProdutoFilter() {

    }

    public ProdutoFilter(Produto produto) {
        super.setEmitente(produto.getEmitente());
        this.cprod = produto.getCprod();
        this.cean = produto.getCean();
        this.xprod = produto.getXprod();
    }

    public String getCprod() {
        return cprod;
    }

    public void setCprod(String cprod) {
        this.cprod = cprod;
    }

    public String getCean() {
        return cean;
    }

    public void setCean(String cean) {
        this.cean = cean;
    }

    public String getXprod() {
        return xprod;
    }

    public void setXprod(String xprod) {
        this.xprod = xprod;
    }

}
