/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.populadores.impl.impostos.icms.sn;

import br.com.ararati.entity.nfe.emissao.DetalhamentoProdutoServico;
import br.com.ararati.enums.N.NFeTipoSituacaoTributariaICMS;
import enviNFe_v400.TNFe.InfNFe.Det.Imposto.ICMS;
import enviNFe_v400.TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN500;

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
            icmssn500.setVBCFCPSTRet(item.getVbcfcpstret() != null ? item.getVbcfcpstret().toString() : null);
            icmssn500.setPFCPSTRet(item.getPfcpstret() != null ? item.getPfcpstret().toString() : null);
            icmssn500.setVFCPSTRet(item.getVfcpstret() != null ? item.getVfcpstret().toString() : null);
            icmssn500.setPST(item.getPst() != null ? item.getPst().toString() : null);
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
