/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.populadores.impl.impostos.pis;

import br.com.ararati.entity.nfe.emissao.DetalhamentoProdutoServico;
import br.com.ararati.enums.Q.NFeTipoSituacaoTributariaPIS;
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.PIS;
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.PIS.PISQtde;

/**
 *
 * @author tiago
 */
public class PisQtde implements IImposto {

    private IImposto imposto;
    private PISQtde pisQtde;

    @Override
    public void verificaImposto(DetalhamentoProdutoServico item, PIS pis) {
        if (item.getCstpis().equals(NFeTipoSituacaoTributariaPIS.COD03)) {
            pisQtde = new PISQtde();
            pisQtde.setCST(item.getCstpis().getCodigo());
            pisQtde.setQBCProd(item.getQbcprodpis() != null ? item.getQbcprodpis().toString() : null);
            pisQtde.setVAliqProd(item.getValiqprodpis() != null ? item.getValiqprodpis().toString() : null);
            pisQtde.setVPIS(item.getVpis() != null ? item.getVpis().toString() : null);
            pis.setPISQtde(pisQtde);
        } else {
            this.imposto.verificaImposto(item, pis);
        }
    }

    @Override
    public void proximoImposto(IImposto proximoImposto) {
        this.imposto = proximoImposto;
    }

}
