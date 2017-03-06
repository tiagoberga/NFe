/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.populadores.impl.impostos.pis;

import br.com.ararati.entity.nfe.emissao.DetalhamentoProdutoServico;
import br.com.ararati.enums.Q.NFeTipoSituacaoTributariaPIS;
import enviNFe_v310.TNFe;
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.PISST;

/**
 *
 * @author tiago
 */
public class PisSt implements IImposto {

    private IImposto imposto;
    private PISST pisst;

    @Override
    public void verificaImposto(DetalhamentoProdutoServico item, TNFe.InfNFe.Det.Imposto.PIS pis) {
        if (item.getCstpis().equals(NFeTipoSituacaoTributariaPIS.COD05)) {
            pisst = new PISST();
            pisst.setPPIS(item.getPpis() != null ? item.getPpis().toString() : null);
            pisst.setQBCProd(item.getQbcprodpis() != null ? item.getQbcprodpis().toString() : null);
            pisst.setVAliqProd(item.getValiqprodpis() != null ? item.getValiqprodpis().toString() : null);
            pisst.setVBC(item.getVbcpis() != null ? item.getVbcpis().toString() : null);
            pisst.setVPIS(item.getVpis() != null ? item.getVpis().toString() : null);
        } else {
            this.imposto.verificaImposto(item, pis);
        }
    }

    @Override
    public void proximoImposto(IImposto proximoImposto) {
        this.imposto = proximoImposto;
    }

}
