/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author rogerio
 */
public class UtilConvert {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat sdfMY = new SimpleDateFormat("yyMM");
    private static final DecimalFormat df = new DecimalFormat("#.0000");
    private static final DecimalFormat dfMoeda = new DecimalFormat("#,##0.00");
    private static final String[] REPLACES = {"a", "e", "i", "o", "u", "c", "A", "E", "I", "O", "U", "C"};
    private static Pattern[] PATTERNS = null;

    private enum tipoConverte {

        MINUSCULA, MAIUSCULA
    }

    public static String join(String[] parts, String separator) {
        StringBuilder sb = new StringBuilder();
        if (parts.length > 0) {
            sb.append(parts[0]);
            for (int i = 1; i < parts.length; ++i) {
                sb.append(separator).append(parts[i]);
            }
        }
        return sb.toString();
    }

    public static String join(Collection<String> parts, String separator) {
        return join(parts.toArray(new String[parts.size()]), separator);
    }

    private static void compilePatterns() {
        PATTERNS = new Pattern[REPLACES.length];
        PATTERNS[0] = Pattern.compile("[âãáàä]");
        PATTERNS[1] = Pattern.compile("[éèêë]");
        PATTERNS[2] = Pattern.compile("[íìîï]");
        PATTERNS[3] = Pattern.compile("[óòôõö]");
        PATTERNS[4] = Pattern.compile("[úùûü]");
        PATTERNS[5] = Pattern.compile("[ç]");
        PATTERNS[6] = Pattern.compile("[ÂÃÁÀÄ]");
        PATTERNS[7] = Pattern.compile("[ÉÈÊË]");
        PATTERNS[8] = Pattern.compile("[ÍÌÎÏ]");
        PATTERNS[9] = Pattern.compile("[ÓÒÔÕÖ]");
        PATTERNS[10] = Pattern.compile("[ÚÙÛÜ]");
        PATTERNS[11] = Pattern.compile("[Ç]");
    }

    public static List<BigDecimal> divideEmParcelas(int quantidadeParcelas, BigDecimal valorTotal, int quantidadeDecimais) {
        if (quantidadeDecimais <= 0 || quantidadeDecimais > 10) {
            throw new IllegalArgumentException("Quantidade de decimais é inválida. [1 - 10]");
        }
        List<BigDecimal> toReturn = new ArrayList<>();
        BigDecimal soma = BigDecimal.ZERO;
        BigDecimal valor;
        for (int i = 1; i <= quantidadeParcelas - 1; i++) {
            valor = valorTotal.divide(new BigDecimal(quantidadeParcelas), quantidadeDecimais, RoundingMode.HALF_UP);
            soma = soma.add(valor);
            toReturn.add(valor);
        }
        toReturn.add((valorTotal.subtract(soma)));
        return toReturn;
    }

    public static String removeFormatacaoAcentosMinuscula(String text) {
        return removeAcentosMinuscula(removeFormatacao(text));
    }

    public static String removeFormatacao(String documento) {
        return documento.replace("/", "").replace("-", "").replace(".", "");
    }

    public static String removeAcentosMinuscula(String text) {
        return removeAcentosMaiscula(text, UtilConvert.tipoConverte.MINUSCULA).trim();
    }

    public static String removeAcentosMaiuscula(String text) {
        return removeAcentosMaiscula(text, UtilConvert.tipoConverte.MAIUSCULA).trim();
    }

    public static String removeAcentos(String text) {
        return removeAcentosMaiscula(text, null);
    }

    private static String removeAcentosMaiscula(String text, UtilConvert.tipoConverte tipo) {

        if (tipo == UtilConvert.tipoConverte.MAIUSCULA) {
            text = text.toUpperCase();
        }
        if (tipo == UtilConvert.tipoConverte.MINUSCULA) {
            text = text.toLowerCase();
        }

        if (PATTERNS == null) {
            compilePatterns();
        }

        String result = text;
        for (int i = 0; i < PATTERNS.length; i++) {
            Matcher matcher = PATTERNS[i].matcher(result);
            result = matcher.replaceAll(REPLACES[i]);
        }
        return result;
    }

    public static double calcIva(double icms, double icmsOrigem, double ivaOriginal) {
        return (((1 + ivaOriginal) * (1 - icms) / (1 - icmsOrigem)) - 1);
    }

    public static String date2NFe(Date data) {
        return sdf.format(data);
    }

    public static String date2NFeym(Date data) {
        return sdfMY.format(data);
    }

    public static String double2NFe2d(Double valor) {
        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(2);
        return df.format(valor);
    }

    public static String bigDecimal2NFe2d(BigDecimal valor) {
        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(2);
        return df.format(valor);
    }

    public static String bigDecimal2Moeda(BigDecimal valor) {
        dfMoeda.setMinimumFractionDigits(2);
        dfMoeda.setMaximumFractionDigits(2);
        return dfMoeda.format(valor);
    }

    public static String double2NFe3d(Double valor) {
        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(3);
        return df.format(valor);
    }

    public static String double2NFe4d(Double valor) {
        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(4);
        return df.format(valor);
    }

    public static BigDecimal roundBigdecimal2(BigDecimal valor) {
        BigDecimal toReturn = valor.round(new MathContext(2 + valor.precision() - valor.scale()));
        return toReturn;
    }

    public static BigDecimal roundBigdecimal2(Double valor) {
        return roundBigdecimal2(new BigDecimal(valor));

    }

    public static Long calculaDigitoEan13(final Long ean13) throws Exception {
        return Long.parseLong(calculaDigitoEan13(String.valueOf(ean13)));
    }

    public static String calculaDigitoEan13(final String ean13) throws Exception {
        int fator = 3;
        int soma = 0;

        int aux = 0;

        try {
            if (ean13.length() != 12 && ean13.length() != 13) {
                throw new IllegalArgumentException("Deve possuir 12 ou 13 digitos");
            }
            for (int i = ean13.length(); i > 0; --i) {
                aux = Integer.parseInt(ean13.substring(i - 1, i));
                soma = soma + aux * fator;
                fator = 4 - fator;
            }
        } catch (NumberFormatException e) {
            throw e;
        } catch (IllegalArgumentException e) {
            throw e;
        }
        return String.valueOf((1000 - soma) % 10);
    }

    public static int[] convertePrazos(final String linhaDigitada) throws IllegalArgumentException {
        String[] aux;
        int[] vetor;
        try {
            aux = linhaDigitada.split(",");
            vetor = new int[aux.length];
            for (int i = 0; i < aux.length; i++) {
                vetor[i] = Integer.valueOf(aux[i]);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Linha digitada deve ser composta de numeros inteiros seguidos de virgulas");
        }
        return vetor;
    }

    public static BigDecimal aplicaDescontos(final BigDecimal valor, final BigDecimal... descontos) {
        BigDecimal toReturn = new BigDecimal(valor.doubleValue());
        if (valor != null && valor.doubleValue() > 0.0) {
            for (BigDecimal desc : descontos) {
                if (desc != null && desc.doubleValue() > 0.0) {
                    toReturn = toReturn.multiply(rmvPorcetagem(desc));
                }
            }
        }
//        toReturn = toReturn.setScale(2, RoundingMode.HALF_UP);
        return toReturn;
    }

    public static BigDecimal aplicaAcrecimos(final BigDecimal valor, final BigDecimal... acrecimos) {
        BigDecimal toReturn = new BigDecimal(valor.doubleValue());
        if (valor != null && valor.doubleValue() > 0.0) {
            for (BigDecimal acrec : acrecimos) {
                if (acrec != null && acrec.doubleValue() > 0.0) {
                    toReturn = toReturn.multiply(addPorcentagem(acrec));
                }
            }
        }
//        toReturn = toReturn.setScale(2, RoundingMode.HALF_UP);
        return toReturn;
    }

    public static BigDecimal addPorcentagem(final BigDecimal porcentagem) {
        return BigDecimal.ONE.add(convertePorcentagem(porcentagem));
    }

    public static BigDecimal rmvPorcetagem(final BigDecimal porcentagem) {
        return BigDecimal.ONE.subtract(convertePorcentagem(porcentagem)).abs();
    }

    public static BigDecimal convertePorcentagem(final BigDecimal porcentagem) {
        if (porcentagem.doubleValue() < 0 || porcentagem.doubleValue() > 100) {
            return BigDecimal.ZERO;
        }
        BigDecimal por = porcentagem.setScale(8, RoundingMode.HALF_UP);
        return por.divide(BigDecimal.valueOf(100.0), RoundingMode.HALF_UP);
    }

    public static String convertePrazos(final int[] vetor) {
        String toReturn = "";
        for (int i : vetor) {
            if (i < vetor.length + 1) {
                toReturn = String.format("%d,", i);
            } else {
                toReturn = String.format("%d", i);
            }
        }
        return toReturn;
    }

    public static BigDecimal converteObjectToBigDecimal(Object value) {
        BigDecimal result = new BigDecimal("0");
        try {
            if (value instanceof BigDecimal) {
                result = (BigDecimal) value;
            } else if (value instanceof Double) {
                result = new BigDecimal((Double) value);
            } else if (value instanceof Long) {
                result = new BigDecimal((Long) value);
            } else if (value instanceof String) {
                result = new BigDecimal((String) value);
            } else {
                result = new BigDecimal("0");
            }
        } catch (Exception exception) {
        }
        return result;
    }

    /**
     * Método utilitário para trocar um trecho do StringBuilder.
     *
     * @param sb O objeto StringBuilder.
     * @param from A String que será substituida.
     * @param to A String a que que trocada pela substituida.
     *
     */
    public static void replaceString(StringBuilder stringBuilder, String from, String to) {
        int index = stringBuilder.indexOf(from);
        while (index != -1) {
            stringBuilder.replace(index, index + from.length(), to);
            index += to.length(); // Move para o final da modificação
            index = stringBuilder.indexOf(from, index);
        }
    }

    /**
     * Verifica se o BigDecimal é um inteiro
     *
     * @param valor
     * @return
     */
    public static boolean isWhole(BigDecimal valor) {
        String bistr = valor.toBigInteger().toString();
        String bdstr = valor.setScale(0, RoundingMode.UP).toString();
        return bistr.equals(bdstr);
    }

    public static void main(String[] args) {
        System.out.println("1000 + 10% " + aplicaAcrecimos(new BigDecimal(1000.0), new BigDecimal(10.0)));
        System.out.println("100 + 1% " + aplicaAcrecimos(new BigDecimal(100.0), new BigDecimal(1.0)));
        System.out.println("1000 - 10% " + aplicaDescontos(new BigDecimal(1000.0), new BigDecimal(10.0)));
        System.out.println("---");

        System.out.println("100 + 1% + 1% " + aplicaAcrecimos(new BigDecimal(100.0), new BigDecimal(1.0), new BigDecimal(1.0)));
        System.out.println("100,12 + 1% + 1% + 11% " + aplicaAcrecimos(new BigDecimal(100.12), new BigDecimal(1.0), new BigDecimal(1.0), new BigDecimal(11.0)));
        System.out.println("100 + 1% + 200% " + aplicaAcrecimos(new BigDecimal(100.0), new BigDecimal(1.0), new BigDecimal(200.0)));
        System.out.println("---");

        BigDecimal valor = aplicaAcrecimos(new BigDecimal(100.0), new BigDecimal(10.0), new BigDecimal(200.0));
        System.out.println("100 + 10% + 200% " + valor);
        BigDecimal valor2 = aplicaDescontos(valor, new BigDecimal(10.0), new BigDecimal(200.0));
        System.out.println(valor + " - 10% - 200% " + valor2);

    }

}
