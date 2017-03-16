/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.populadores.impl.impostos.icms.st;

import br.com.ararati.entity.nfe.emissao.DetalhamentoProdutoServico;
import br.com.ararati.enums.N.NFeTipoSituacaoTributariaICMS;
import br.com.ararati.exception.NFeException;
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.ICMS;
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.ICMS.ICMS60;
import javax.ejb.Local;

/**
 *
 * @author tiago
 */
@Local(IImposto.class)
public class Icms60 implements IImposto {

    private IImposto imposto;
    private ICMS60 icms60;

    @Override
    public void verificaImposto(DetalhamentoProdutoServico item, ICMS icms) throws NFeException {
        if (item.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD60)) {
            icms60 = new ICMS60();
            icms60.setOrig(item.getOrig().getCodigo());
            icms60.setCST(item.getCsticms() != null ? item.getCsticms().getCodigo() : null);
            icms60.setVBCSTRet(item.getVbcicmsstret() != null ? item.getVbcicmsstret().toString() : null);
            icms60.setVICMSSTRet(item.getVicmsstret() != null ? item.getVicmsstret().toString() : null);
            icms.setICMS60(icms60);
        } else {
            this.imposto.verificaImposto(item, icms);
        }
    }

    @Override
    public void proximoImposto(IImposto proximoImposto) {
        this.imposto = proximoImposto;
    }
}
