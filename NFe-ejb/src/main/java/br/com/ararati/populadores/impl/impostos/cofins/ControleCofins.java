/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.populadores.impl.impostos.cofins;

import br.com.ararati.entity.nfe.emissao.DetalhamentoProdutoServico;
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.COFINS;

/**
 *
 * @author tiago
 */
public class ControleCofins {

    /**
     * * Método responsável por controlar qual tipo de cofins será aplicado
     *
     * @param item DetalhamentoProdutoServico
     * @param cofins COFINS
     */
    public void controleCofins(DetalhamentoProdutoServico item, COFINS cofins) {
        if (item.getCstcofins() != null) {
            IImposto cofinsAliq = new CofinsAliq();
            IImposto cofinsQtde = new CofinsQtde();
            IImposto cofinsNt = new CofinsNt();
            IImposto cofinsOutr = new CofinsOutr();

            cofinsAliq.proximoImposto(cofinsQtde);
            cofinsQtde.proximoImposto(cofinsNt);
            cofinsNt.proximoImposto(cofinsOutr);

            cofinsAliq.verificaImposto(item, cofins);
        }
    }
}
