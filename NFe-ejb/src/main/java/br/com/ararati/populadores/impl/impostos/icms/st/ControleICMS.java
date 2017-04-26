
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.populadores.impl.impostos.icms.st;

import br.com.ararati.entity.nfe.emissao.DetalhamentoProdutoServico;
import br.com.ararati.exception.NFeException;
import enviNFe_v400.TNFe.InfNFe.Det.Imposto.ICMS;

/**
 *
 * @author tiago
 */
public class ControleICMS {

    public void controleICMS(DetalhamentoProdutoServico item, ICMS icms) throws NFeException {
        IImposto icms00 = new Icms00();
        IImposto icms10 = new Icms10();
        IImposto icms20 = new Icms20();
        IImposto icms30 = new Icms30();
        IImposto icms40 = new Icms40();
        IImposto icms51 = new Icms51();
        IImposto icms60 = new Icms60();
        IImposto icms70 = new Icms70();
        IImposto icms90 = new Icms90();
        IImposto icmsst = new IcmsSt();
        IImposto icmspart = new IcmsPart();

        icms00.proximoImposto(icms10);
        icms10.proximoImposto(icms20);
        icms20.proximoImposto(icms30);
        icms30.proximoImposto(icms40);
        icms40.proximoImposto(icms51);
        icms51.proximoImposto(icms60);
        icms60.proximoImposto(icms70);
        icms70.proximoImposto(icms90);
        icms90.proximoImposto(icmsst);
        icmsst.proximoImposto(icmspart);
        
        icms00.verificaImposto(item, icms);
    }
}