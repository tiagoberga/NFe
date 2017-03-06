/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.filter;

import br.com.ararati.entity.config.ConfiguracaoAmbiente;
import br.com.ararati.entity.config.ConfiguracaoGeral;
import br.com.ararati.enums.commons.NFeTipoNSU;
import br.com.ararati.enums.commons.NFeTipoSchemaDistribuicaoDFe;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author tiago
 */
public class NFeDistribuicaoDFeFilter extends AbstractFilter {

    private ConfiguracaoGeral nfeParametros;
    private ConfiguracaoAmbiente ambiente;
    private NFeTipoSchemaDistribuicaoDFe tipoSchema;
    private Date dataInicial;
    private Date dataFinal;
    private String nsu;
    private String tpAmb;
    private NFeTipoNSU tiponsu = NFeTipoNSU.NSU_SEQUENCIAL;

    public NFeDistribuicaoDFeFilter() {
        dataFinal = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataFinal);
        calendar.add(Calendar.MONTH, -1);
        dataInicial = calendar.getTime();
    }

    public String getVersao() {
        return "1.00";
    }

    public NFeTipoNSU getTiponsu() {
        return tiponsu;
    }

    public void setTiponsu(NFeTipoNSU tiponsu) {
        this.tiponsu = tiponsu;
    }

    public String getNsu() {
        return nsu;
    }

    public void setNsu(String nsu) {
        this.nsu = nsu;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public ConfiguracaoAmbiente getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(ConfiguracaoAmbiente ambiente) {
        this.ambiente = ambiente;
        this.tpAmb = ambiente.getTipoDeAmbiente().toString();
    }

    public ConfiguracaoGeral getNfeParametros() {
        return nfeParametros;
    }

    public void setNfeParametros(ConfiguracaoGeral nfeParametros) {
        this.nfeParametros = nfeParametros;

    }

    public String getTpAmb() {
        return tpAmb;
    }

    public void setTpAmb(String tpAmb) {
        this.tpAmb = tpAmb;
    }

    public NFeTipoSchemaDistribuicaoDFe getTipoSchema() {
        return tipoSchema;
    }

    public void setTipoSchema(NFeTipoSchemaDistribuicaoDFe tipoSchema) {
        this.tipoSchema = tipoSchema;
    }

}
