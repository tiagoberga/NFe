/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.populadores.impl.impostos.icms.sn;

import br.com.ararati.entity.nfe.emissao.DetalhamentoProdutoServico;
import br.com.ararati.enums.N.NFeTipoSituacaoTributariaICMS;
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.ICMS;
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN102;

/**
 *
 * @author tiago
 */
public class Icmssn102 implements IImposto {

    private IImposto proximoImposto;
    private ICMSSN102 icmssn102;

    @Override
    public void verificaImposto(DetalhamentoProdutoServico item, ICMS icms) {
        if (item.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD102)
                || item.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD103)
                || item.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD300)
                || item.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD400)) {
            icmssn102 = new ICMSSN102();
            icmssn102.setCSOSN(item.getCsticms() != null ? item.getCsticms().getCodigo() : null);
            icmssn102.setOrig(item.getOrig().getCodigo());
            icms.setICMSSN102(icmssn102);
        } else {
            proximoImposto.verificaImposto(item, icms);
        }
    }

    @Override
    public void proximoImposto(IImposto proximoImposto) {
        this.proximoImposto = proximoImposto;
    }

}
