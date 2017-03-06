package br.com.ararati.filter;

import br.com.ararati.entity.config.ConfiguracaoAmbiente;
import br.com.ararati.enums.commons.NFeTipoManifesto;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author tiago
 */
public class ManifestoDestinatarioFilter extends AbstractFilterEvento {

    private ConfiguracaoAmbiente ambiente;
    private NFeTipoManifesto tipoManifesto = NFeTipoManifesto.CIENCIA;
    private String xjust;
    private String tpAmb;
    private Map<String, Integer> chNFeNSeqEvento = new LinkedHashMap<>();

    public void putChNFeNSeqEvento(String chNFe, int nSeqEvento) {
        this.chNFeNSeqEvento.put(chNFe, nSeqEvento);
    }

    public String getCOrgao() {
        return "91";
    }

    public String getNseqEvento() {
        return "1";
    }

    public String getDhEvento() {
        SimpleDateFormat sdfDateHour = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        return sdfDateHour.format(new Date());
    }

    public String getIdLote() {
        SimpleDateFormat sdfLote = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdfLote.format(new Date());
    }

    public NFeTipoManifesto getTipoManifesto() {
        return tipoManifesto;
    }

    public void setTipoManifesto(NFeTipoManifesto tipoManifesto) {
        this.tipoManifesto = tipoManifesto;
    }

    public String getXjust() {
        return xjust;
    }

    public void setXjust(String xjust) {
        this.xjust = xjust;
    }

    public Map<String, Integer> getChNFeNSeqEvento() {
        return chNFeNSeqEvento;
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

}
