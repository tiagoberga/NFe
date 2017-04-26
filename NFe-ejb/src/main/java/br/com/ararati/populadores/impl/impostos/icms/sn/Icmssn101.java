/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.populadores.impl.impostos.icms.sn;

import br.com.ararati.entity.nfe.emissao.DetalhamentoProdutoServico;
import br.com.ararati.enums.N.NFeTipoSituacaoTributariaICMS;
import enviNFe_v400.TNFe.InfNFe.Det.Imposto.ICMS;
import enviNFe_v400.TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN101;

/**
 *
 * @author tiago
 */
public class Icmssn101 implements IImposto {

    private IImposto proximoImposto;
    private ICMSSN101 icmssn101;

    @Override
    public void verificaImposto(DetalhamentoProdutoServico item, ICMS icms) {
        if (item.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD101)) {
            icmssn101 = new ICMSSN101();
            icmssn101.setOrig(item.getOrig().getCodigo());
            icmssn101.setCSOSN(item.getCsticms() != null ? item.getCsticms().getCodigo() : null);
            icmssn101.setPCredSN(item.getPcredsn() != null ? item.getPcredsn().toString() : null);
            icmssn101.setVCredICMSSN(item.getVcredicmssn() != null ? item.getVcredicmssn().toString() : null);
            icms.setICMSSN101(icmssn101);
        } else {
            proximoImposto.verificaImposto(item, icms);
        }
    }

    @Override
    public void proximoImposto(IImposto proximoImposto) {
        this.proximoImposto = proximoImposto;
    }

}
