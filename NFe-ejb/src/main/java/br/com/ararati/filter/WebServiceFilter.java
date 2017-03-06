package br.com.ararati.filter;

import br.com.ararati.entity.cadastros.Emitente;
import br.com.ararati.enums.B.NFeTipoAmbiente;
import br.com.ararati.enums.B.NFeTipoEmissao;
import br.com.ararati.enums.commons.NFeTipoServico;
import br.com.ararati.enums.commons.NFeTipoUFWebService;
import java.io.Serializable;

/**
 * Classe responsável por conter os filtros de busca de web service.
 *
 * @author tiago
 */
public class WebServiceFilter extends AbstractFilter implements Serializable {

    private NFeTipoUFWebService estadoSigla;
    private NFeTipoServico tipoServicoNfe;
    private NFeTipoEmissao tpEmis;
    private NFeTipoAmbiente tipoAmbiente;
    private String versao;
    private String url;

    public WebServiceFilter() {

    }

    public WebServiceFilter(Emitente emitente) {
        super.setEmitente(emitente);
    }

    public WebServiceFilter(NFeTipoUFWebService estadoSigla, NFeTipoServico tipoServicoNfe, NFeTipoEmissao tpEmis, NFeTipoAmbiente tipoAmbiente) {
        this.estadoSigla = estadoSigla;
        this.tipoServicoNfe = tipoServicoNfe;
        this.tpEmis = tpEmis;
        this.tipoAmbiente = tipoAmbiente;
    }

    public WebServiceFilter(NFeTipoUFWebService estadoSigla, NFeTipoServico tipoServicoNfe, NFeTipoEmissao tpEmis, NFeTipoAmbiente tipoAmbiente, String versao) {
        this.estadoSigla = estadoSigla;
        this.tipoServicoNfe = tipoServicoNfe;
        this.tpEmis = tpEmis;
        this.tipoAmbiente = tipoAmbiente;
        this.versao = versao;
    }

    public NFeTipoUFWebService getEstadoSigla() {
        return estadoSigla;
    }

    public void setEstadoSigla(NFeTipoUFWebService estadoSigla) {
        this.estadoSigla = estadoSigla;
    }

    public NFeTipoServico getTipoServicoNfe() {
        return tipoServicoNfe;
    }

    public void setTipoServicoNfe(NFeTipoServico tipoServicoNfe) {
        this.tipoServicoNfe = tipoServicoNfe;
    }

    public NFeTipoEmissao getTpEmis() {
        return tpEmis;
    }

    public void setTpEmis(NFeTipoEmissao tpEmis) {
        this.tpEmis = tpEmis;
    }

    public NFeTipoAmbiente getTipoAmbiente() {
        return tipoAmbiente;
    }

    public void setTipoAmbiente(NFeTipoAmbiente tipoAmbiente) {
        this.tipoAmbiente = tipoAmbiente;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
