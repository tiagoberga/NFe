package br.com.ararati.filter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author tiago
 */
public class CartaDeCorrecaoFilter extends AbstractFilterEvento {

    private String xcorrecao;
    private String corgao;
    private String chNFe;
    private String tpAmb;
    private String nseqEvento;
    private Map<String, Integer> chNFeNSeqEvento = new LinkedHashMap<>();

    public void putChNFeNSeqEvento(String chNFe, int nSeqEvento) {
        this.chNFeNSeqEvento.put(chNFe, nSeqEvento);
    }

    public String getXcondUso() {
        StringBuilder xConduso = new StringBuilder()
                .append("A Carta de Correcao e disciplinada pelo paragrafo 1o-A do ")
                .append("art. 7o do Convenio S/N, de 15 de dezembro de 1970 e ")
                .append("pode ser utilizada para regularizacao de erro ocorrido na ")
                .append("emissao de documento fiscal, desde que o erro nao esteja ")
                .append("relacionado com: I - as variaveis que determinam o valor ")
                .append("do imposto tais como: base de calculo, aliquota, diferenca ")
                .append("de preco, quantidade, valor da operacao ou da prestacao; ")
                .append("II - a correcao de dados cadastrais que implique mudanca ")
                .append("do remetente ou do destinatario; III - a data de emissao ou ")
                .append("de saida.");

        return xConduso.toString();
    }

    public String getTpEvento() {
        return "110110";
    }

    public String getDescEvento() {
        return "Carta de Correcao";
    }

    public String getDhEvento() {
        SimpleDateFormat sdfDateHour = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        return sdfDateHour.format(new Date());
    }

    public String getIdLote() {
        SimpleDateFormat sdfLote = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdfLote.format(new Date());
    }

    public String getXcorrecao() {
        return xcorrecao;
    }

    public void setXcorrecao(String xCorrecao) {
        this.xcorrecao = xCorrecao;
    }

    public String getTpAmb() {
        return tpAmb;
    }

    public void setTpAmb(String tpAmb) {
        this.tpAmb = tpAmb;
    }

    public String getChNFe() {
        return chNFe;
    }

    public void setChNFe(String chNFe) {
        this.chNFe = chNFe;
    }

    public String getNseqEvento() {
        return nseqEvento;
    }

    public void setNseqEvento(String nseqEvento) {
        this.nseqEvento = nseqEvento;
    }

    public Map<String, Integer> getChNFeNSeqEvento() {
        return chNFeNSeqEvento;
    }

    public String getCorgao() {
        return corgao;
    }

    public void setCorgao(String corgao) {
        this.corgao = corgao;
    }

}
