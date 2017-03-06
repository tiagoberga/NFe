package br.com.ararati.filter;

import br.com.ararati.entity.cadastros.Emitente;
import java.util.Calendar;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author tiago
 */
public class InutilizacaoNFeFilter extends AbstractFilter {

    private String nnfini;
    private String nnffin;
    private String serie;
    private String tpamb;
    private String xjust;
    private String modelo;

    public InutilizacaoNFeFilter() {
    }

    public InutilizacaoNFeFilter(Emitente empresaLogada, String tpamb) {
        super.setEmitente(empresaLogada);
        this.tpamb = tpamb;
    }

    /**
     * @return Cnpj do emitente.
     */
    public String getCnpj() {
        return super.getEmitente().getDocumento();
    }

    /**
     * @return Código UF solicitante.
     */
    public String getCuf() {
        return null;
//        return super.getEmpresa().getPessoa().getEnderecoPrincipalLocalidade().getEstado().getCodigo().toString();
    }

    /**
     * @return Ano de inutilização da numeração.
     */
    public String getAno() {
        return String.valueOf(Calendar.getInstance().get(Calendar.YEAR)).substring(2, 4);
    }

    /**
     * @return Tipo do serviço.
     */
    public String getXserv() {
        return "INUTILIZAR";
    }

    /**
     * @return Versão do XML.
     */
    public String getVersao() {
        return "3.10";
    }

    /**
     * @return Código da UF + Ano (2 posições) + CNPJ + modelo + série + nro
     * inicial e nro final precedida do literal “ID”, somando um total de 43
     * caracteres.
     */
    public String getId() {
        StringBuilder toReturn = new StringBuilder()
                .append("ID")
                .append(this.getCuf())
                .append(this.getAno())
                .append(this.getCnpj())
                .append(this.getModelo())
                .append(StringUtils.leftPad(this.serie, 3, "0"))
                .append(StringUtils.leftPad(this.nnfini, 9, "0"))
                .append(StringUtils.leftPad(this.nnffin, 9, "0"));

        return toReturn.toString();
    }

    public String getNnfini() {
        return nnfini;
    }

    public void setNnfini(String nnfini) {
        this.nnfini = nnfini;
    }

    public String getNnffin() {
        return nnffin;
    }

    public void setNnffin(String nnffin) {
        this.nnffin = nnffin;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getTpamb() {
        return tpamb;
    }

    public void setTpamb(String tpamb) {
        this.tpamb = tpamb;
    }

    public String getXjust() {
        return xjust;
    }

    public void setXjust(String xjust) {
        this.xjust = xjust;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    
}
