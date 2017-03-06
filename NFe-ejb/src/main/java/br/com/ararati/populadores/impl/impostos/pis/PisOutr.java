/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.populadores.impl.impostos.pis;

import br.com.ararati.entity.nfe.emissao.DetalhamentoProdutoServico;
import br.com.ararati.enums.Q.NFeTipoSituacaoTributariaPIS;
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.PIS;
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.PIS.PISOutr;

/**
 *
 * @author tiago
 */
public class PisOutr implements IImposto {

    private PISOutr pisOutr = new PISOutr();

    @Override
    public void verificaImposto(DetalhamentoProdutoServico item, PIS pis) {
        if (item.getCstpis().equals(NFeTipoSituacaoTributariaPIS.COD49)
                || item.getCstpis().equals(NFeTipoSituacaoTributariaPIS.COD50)
                || item.getCstpis().equals(NFeTipoSituacaoTributariaPIS.COD51)
                || item.getCstpis().equals(NFeTipoSituacaoTributariaPIS.COD52)
                || item.getCstpis().equals(NFeTipoSituacaoTributariaPIS.COD53)
                || item.getCstpis().equals(NFeTipoSituacaoTributariaPIS.COD54)
                || item.getCstpis().equals(NFeTipoSituacaoTributariaPIS.COD55)
                || item.getCstpis().equals(NFeTipoSituacaoTributariaPIS.COD56)
                || item.getCstpis().equals(NFeTipoSituacaoTributariaPIS.COD60)
                || item.getCstpis().equals(NFeTipoSituacaoTributariaPIS.COD99)) {
            pisOutr.setCST(item.getCstpis().getCodigo());
            pisOutr.setPPIS(item.getPpis() != null ? item.getPpis().toString() : null);

            pisOutr.setQBCProd(item.getQbcprodpis() != null ? item.getQbcprodpis().toString() : null);
            pisOutr.setVAliqProd(item.getValiqprodpis() != null ? item.getValiqprodpis().toString() : null);

            pisOutr.setVBC(item.getVbcpis() != null ? item.getVbcpis().toString() : null);
            pisOutr.setVPIS(item.getVpis() != null ? item.getVpis().toString() : null);
            pis.setPISOutr(pisOutr);
        }
    }

    @Override
    public void proximoImposto(IImposto proximoImposto) {
    }

}
