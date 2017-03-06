
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.populadores.impl.impostos.icms;

import br.com.ararati.entity.nfe.emissao.DetalhamentoProdutoServico;
import br.com.ararati.exception.NFeException;
import br.com.ararati.populadores.impl.impostos.icms.sn.ControleICMSSN;
import br.com.ararati.populadores.impl.impostos.icms.st.ControleICMS;
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.ICMS;

/**
 *
 * @author tiago
 */
public class ConstroleDeIcms {

    private final ControleICMS controleICMS = new ControleICMS();
    private final ControleICMSSN controleICMSSN = new ControleICMSSN();

    /**
     * Método responsável por controlar qual icms st será aplicado
     *
     * @param item
     * @param icms ICMS
     */
    public void verificaTipoICMS(DetalhamentoProdutoServico item, ICMS icms) throws NFeException {
        if (item.getCsticms() != null) {
            if (item.getEmitente().isRegimeSimples()) {
                controleICMSSN.controleICMSSN(item, icms);
            } else {
                controleICMS.controleICMS(item, icms);
            }
        }
    }
}
