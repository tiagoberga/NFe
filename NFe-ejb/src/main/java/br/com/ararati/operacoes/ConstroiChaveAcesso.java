/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.operacoes;

import br.com.ararati.exception.NFeException;
import enviNFe_v400.TNFe.InfNFe;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author tiago
 */
public class ConstroiChaveAcesso {

    public String constroiChaveDeAcesso(InfNFe infnfe) throws NFeException {
        try {
            String cUF = infnfe.getIde().getCUF(); // Código da UF do emitente do Documento Fiscal.
            String dataAAMM = infnfe.getIde().getDhEmi().substring(2, 4) + infnfe.getIde().getDhEmi().substring(5, 7); // Ano e Mês de emissão da NF-e.  
            String cnpjCpf = infnfe.getEmit().getCNPJ(); // CNPJ do emitente.
            String mod = infnfe.getIde().getMod(); // Modelo do Documento Fiscal.  
            String serie = infnfe.getIde().getSerie();// Série do Documento Fiscal.  
            String tpEmis = infnfe.getIde().getTpEmis();// Forma de emissão da NF-e  
            String nNF = infnfe.getIde().getNNF();// Número do Documento Fiscal.  
            String cNF = infnfe.getIde().getCNF();// Código Numérico que compõe a Chave de Acesso.  

            StringBuilder chave = new StringBuilder();
            chave.append(StringUtils.leftPad(cUF, 2, '0'));
            chave.append(StringUtils.leftPad(dataAAMM, 4, '0'));
            chave.append(StringUtils.leftPad(cnpjCpf.replaceAll("\\D", ""), 14, '0'));
            chave.append(StringUtils.leftPad(mod, 2, '0'));
            chave.append(StringUtils.leftPad(serie, 3, '0'));
            chave.append(StringUtils.leftPad(String.valueOf(nNF), 9, '0'));
            chave.append(StringUtils.leftPad(tpEmis, 1, '0'));
            chave.append(StringUtils.leftPad(cNF, 8, '0'));
            chave.append(modulo11(chave.toString()));

            return chave.toString();
        } catch (Exception e) {
            throw new NFeException("Erro ao construir chave de acesso!");
        }
    }

    private static int modulo11(String chave) {
        int total = 0;
        int peso = 2;

        for (int i = 0; i < chave.length(); i++) {
            total += (chave.charAt((chave.length() - 1) - i) - '0') * peso;
            peso++;
            if (peso == 10) {
                peso = 2;
            }
        }
        int resto = total % 11;
        return (resto == 0 || resto == 1) ? 0 : (11 - resto);
    }
}
