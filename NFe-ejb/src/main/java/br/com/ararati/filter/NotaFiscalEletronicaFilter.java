package br.com.ararati.filter;

import br.com.ararati.enums.commons.NFeTipoSituacao;
import java.util.Date;

/**
 * Classe responsável por conter os filtros de busca de NF-e.
 *
 * @author tiago
 */
public class NotaFiscalEletronicaFilter extends AbstractFilter {

    private String buscaDestinatario;
    private String numeroNfe;
    private Date dataInicio = new Date();
    private Date dataFim = new Date();
    private NFeTipoSituacao situacao;
    private Boolean emailEnviado;
    private String tpAmb;

    public String getBuscaDestinatario() {
        return buscaDestinatario;
    }

    public void setBuscaDestinatario(String buscaDestinatario) {
        this.buscaDestinatario = buscaDestinatario;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public NFeTipoSituacao getSituacao() {
        return situacao;
    }

    public void setSituacao(NFeTipoSituacao situacao) {
        this.situacao = situacao;
    }

    public Boolean getEmailEnviado() {
        return emailEnviado;
    }

    public void setEmailEnviado(Boolean emailEnviado) {
        this.emailEnviado = emailEnviado;
    }

    public String getNumeroNfe() {
        return numeroNfe;
    }

    public void setNumeroNfe(String numeroNfe) {
        this.numeroNfe = numeroNfe;
    }

    public String getTpAmb() {
        return tpAmb;
    }

    public void setTpAmb(String tpAmb) {
        this.tpAmb = tpAmb;
    }
}
