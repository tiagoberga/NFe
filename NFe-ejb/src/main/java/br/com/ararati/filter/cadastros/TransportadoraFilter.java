package br.com.ararati.filter.cadastros;

import br.com.ararati.entity.cadastros.Transportadora;
import br.com.ararati.filter.AbstractFilter;

/**
 *
 * @author tiago
 */
public class TransportadoraFilter extends AbstractFilter {

    private String documento;
    private String xnome;
    private String ie;

    public TransportadoraFilter() {
    }

    public TransportadoraFilter(Transportadora transportadora) {
        super.setEmitente(transportadora.getEmitente());
        this.documento = transportadora.getDocumento();
        this.xnome = transportadora.getXnome();
        this.ie = transportadora.getIe();
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

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

}
