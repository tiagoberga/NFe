package br.com.ararati.filter;

import br.com.ararati.entity.config.ConfiguracaoAmbiente;
import br.com.ararati.entity.config.ConfiguracaoGeral;

/**
 *
 * @author tiago
 */
public class DownloadNFeFilter extends AbstractFilter {

    private ConfiguracaoAmbiente ambiente;
    private ConfiguracaoGeral nfeParametros;

    public ConfiguracaoAmbiente getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(ConfiguracaoAmbiente ambiente) {
        this.ambiente = ambiente;
    }

    public ConfiguracaoGeral getNfeParametros() {
        return nfeParametros;
    }

    public void setNfeParametros(ConfiguracaoGeral nfeParametros) {
        this.nfeParametros = nfeParametros;
    }

}
