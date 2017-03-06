package br.com.ararati.filter.cadastros;

import br.com.ararati.filter.AbstractFilter;

/**
 *
 * @author tiago
 */
public class DestinatarioFilter extends AbstractFilter {

    private String documento;
    private String xnome;

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getXnome() {
        return xnome;
    }

    public void setXnome(String xnome) {
        this.xnome = xnome;
    }

}
