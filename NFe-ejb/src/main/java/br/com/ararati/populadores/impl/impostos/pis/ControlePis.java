/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.populadores.impl.impostos.pis;

import br.com.ararati.entity.nfe.emissao.DetalhamentoProdutoServico;
import enviNFe_v400.TNFe.InfNFe.Det.Imposto.PIS;

/**
 *
 * @author tiago
 */
public class ControlePis {

    /**
     * Método responsável por controlar qual tipo de icms será aplicado
     *
     * @param item
     * @param pis
     */
    public void controlePis(DetalhamentoProdutoServico item, PIS pis) {
        if (item.getCstpis() != null) {
            IImposto pisAliq = new PisAliq();
            IImposto pisQtde = new PisQtde();
            IImposto pisNt = new PisNt();
            IImposto pisOutr = new PisOutr();

            pisAliq.proximoImposto(pisQtde);
            pisQtde.proximoImposto(pisNt);
            pisNt.proximoImposto(pisOutr);

            pisAliq.verificaImposto(item, pis);
        }
    }
}
