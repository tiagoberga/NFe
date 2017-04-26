
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.populadores.impl.impostos.icms.sn;

import br.com.ararati.entity.nfe.emissao.DetalhamentoProdutoServico;
import enviNFe_v400.TNFe.InfNFe.Det.Imposto.ICMS;

/**
 *
 * @author tiago
 */
public class ControleICMSSN {

    public void controleICMSSN(DetalhamentoProdutoServico item, ICMS icms) {
        IImposto icmssn101 = new Icmssn101();
        IImposto icmssn102 = new Icmssn102();
        IImposto icmssn201 = new Icmssn201();
        IImposto icmssn202 = new Icmssn202();
        IImposto icmssn500 = new Icmssn500();
        IImposto icmssn900 = new Icmssn900();

        // proximos icmssn
        icmssn101.proximoImposto(icmssn102);
        icmssn102.proximoImposto(icmssn201);
        icmssn201.proximoImposto(icmssn202);
        icmssn202.proximoImposto(icmssn500);
        icmssn500.proximoImposto(icmssn900);

        icmssn101.verificaImposto(item, icms);
    }
}
