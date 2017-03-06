/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.populadores.impl.impostos.cofins;

import br.com.ararati.entity.nfe.emissao.DetalhamentoProdutoServico;
import br.com.ararati.enums.S.NFeTipoSituacaoTributariaCOFINS;
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.COFINS;
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.COFINS.COFINSQtde;

/**
 *
 * @author tiago
 */
public class CofinsQtde implements IImposto {

    private IImposto imposto;
    private COFINSQtde cofinsQtde;

    @Override
    public void verificaImposto(DetalhamentoProdutoServico item, COFINS cofins) {
        if (item.getCstcofins().equals(NFeTipoSituacaoTributariaCOFINS.COD03)) {
            cofinsQtde = new COFINSQtde();
            cofinsQtde.setCST(item.getCstcofins() != null ? item.getCstcofins().getCodigo() : null);
            cofinsQtde.setQBCProd(item.getQbcprodcofins() != null ? item.getQbcprodcofins().toString() : null);
            cofinsQtde.setVAliqProd(item.getValiqprodcofins() != null ? item.getValiqprodcofins().toString() : null);
            cofinsQtde.setVCOFINS(item.getVcofins() != null ? item.getVcofins().toString() : null);
            cofins.setCOFINSQtde(cofinsQtde);
        } else {
            this.imposto.verificaImposto(item, cofins);
        }
    }

    @Override
    public void proximoImposto(IImposto proximoImposto) {
        this.imposto = proximoImposto;
    }
}
