package br.com.ararati.filter.cadastros;

import br.com.ararati.entity.cadastros.Emitente;
import br.com.ararati.filter.AbstractFilter;

/**
 *
 * @author tiago
 */
public class EmitenteFilter extends AbstractFilter {

    private String documento;
    private String xnome;

    public EmitenteFilter() {
    }

    public EmitenteFilter(Emitente emitente) {
        this.documento = emitente.getDocumento();
        this.xnome = emitente.getXnome();
    }

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
