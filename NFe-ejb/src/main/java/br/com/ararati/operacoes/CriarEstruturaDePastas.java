/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.operacoes;

import java.io.File;
import java.util.Calendar;

/**
 *
 * @author tiago
 */
public class CriarEstruturaDePastas {

    /**
     * Método responsável por criar estrutura de pastas (ANO(MES(DIA))) em um
     * diretório raiz
     *
     * @param diretorioRaiz : /diretorioRaiz/
     * @return /diretorioRaiz/ANO/MES/DIA/
     */
    public static String criaEstruturaPadrao(String diretorioRaiz) {
        Calendar calendar = Calendar.getInstance();

        File pastaAno = new File(diretorioRaiz.concat(String.valueOf(calendar.get(Calendar.YEAR))));
        File pastaMes = new File(pastaAno, String.valueOf(calendar.get(Calendar.MONTH) + 1));
        File pastaDia = new File(pastaMes, String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));

        if (!pastaAno.exists()) {
            pastaAno.mkdirs();
        }
        if (!pastaMes.exists()) {
            pastaMes.mkdirs();
        }
        if (!pastaDia.exists()) {
            pastaDia.mkdirs();
        }

        return pastaDia.toString().concat("/");
    }
}
