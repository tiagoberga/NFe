/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.populadores.impl.impostos.icms.sn;

import br.com.ararati.entity.nfe.emissao.DetalhamentoProdutoServico;
import br.com.ararati.enums.N.NFeTipoSituacaoTributariaICMS;
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.ICMS;
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN500;

/**
 *
 * @author tiago
 */
public class Icmssn500 implements IImposto {

    private IImposto proximoImposto;
    private ICMSSN500 icmssn500 = new ICMSSN500();

    @Override
    public void verificaImposto(DetalhamentoProdutoServico item, ICMS icms) {
        if (item.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD500)) {
            icmssn500 = new ICMSSN500();
            icmssn500.setCSOSN(item.getCsticms() != null ? item.getCsticms().getCodigo() : null);
            icmssn500.setOrig(item.getOrig().getCodigo());
            icmssn500.setVBCSTRet(item.getVbcicmsstret() != null ? item.getVbcicmsstret().toString() : null);
            icmssn500.setVICMSSTRet(item.getVicmsstret() != null ? item.getVicmsstret().toString() : null);
            icms.setICMSSN500(icmssn500);
        } else {
            proximoImposto.verificaImposto(item, icms);
        }
    }

    @Override
    public void proximoImposto(IImposto proximoImposto) {
        this.proximoImposto = proximoImposto;
    }

}
