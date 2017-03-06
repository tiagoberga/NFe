package br.com.ararati.filter;

import br.com.ararati.entity.config.ConfiguracaoAmbiente;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author tiago
 */
public class CancelamentoFilter extends AbstractFilterEvento {

    private ConfiguracaoAmbiente ambiente;
    private String justificativa;
    private String tpAmb;
    private String nprot;
    private String corgao;
    private Map<String, Integer> chNFeNSeqEvento = new LinkedHashMap<>();

    public void putChNFeNSeqEvento(String chNFe, int nSeqEvento) {
        this.chNFeNSeqEvento.put(chNFe, nSeqEvento);
    }

    public String getTpEvento() {
        return "110111";
    }

    public String getNseqEvento() {
        return "1";
    }

    public String getDescEvento() {
        return "Cancelamento";
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public String getTpAmb() {
        return tpAmb;
    }

    public void setTpAmb(String tpAmb) {
        this.tpAmb = tpAmb;
    }

    public ConfiguracaoAmbiente getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(ConfiguracaoAmbiente ambiente) {
        this.ambiente = ambiente;
        this.tpAmb = ambiente.getTipoDeAmbiente().toString();
    }

    public Map<String, Integer> getChNFeNSeqEvento() {
        return chNFeNSeqEvento;
    }

    public void setChNFeNSeqEvento(Map<String, Integer> chNFeNSeqEvento) {
        this.chNFeNSeqEvento = chNFeNSeqEvento;
    }

    public String getNprot() {
        return nprot;
    }

    public void setNprot(String nprot) {
        this.nprot = nprot;
    }

    public String getCorgao() {
        return corgao;
    }

    public void setCorgao(String corgao) {
        this.corgao = corgao;
    }

}
