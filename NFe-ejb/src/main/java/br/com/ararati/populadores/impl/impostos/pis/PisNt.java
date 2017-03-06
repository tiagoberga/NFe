/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.populadores.impl.impostos.pis;

import br.com.ararati.entity.nfe.emissao.DetalhamentoProdutoServico;
import br.com.ararati.enums.Q.NFeTipoSituacaoTributariaPIS;
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.PIS;
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.PIS.PISNT;
import javax.ejb.Local;

/**
 *
 * @author tiago
 */
@Local(IImposto.class)
public class PisNt implements IImposto {

    private IImposto imposto;
    private PISNT pisNt;

    @Override
    public void verificaImposto(DetalhamentoProdutoServico item, PIS pis) {
        if (item.getCstpis().equals(NFeTipoSituacaoTributariaPIS.COD04)
                || item.getCstpis().equals(NFeTipoSituacaoTributariaPIS.COD06)
                || item.getCstpis().equals(NFeTipoSituacaoTributariaPIS.COD07)
                || item.getCstpis().equals(NFeTipoSituacaoTributariaPIS.COD08)
                || item.getCstpis().equals(NFeTipoSituacaoTributariaPIS.COD09)) {
            pisNt = new PISNT();
            pisNt.setCST(item.getCstpis().getCodigo());
            pis.setPISNT(pisNt);
        } else {
            this.imposto.verificaImposto(item, pis);
        }
    }

    @Override
    public void proximoImposto(IImposto proximoImposto) {
        this.imposto = proximoImposto;
    }

}
