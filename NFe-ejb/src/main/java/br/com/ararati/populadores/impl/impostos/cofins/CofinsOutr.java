/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.populadores.impl.impostos.cofins;

import br.com.ararati.entity.nfe.emissao.DetalhamentoProdutoServico;
import br.com.ararati.enums.S.NFeTipoSituacaoTributariaCOFINS;
import enviNFe_v400.TNFe.InfNFe.Det.Imposto.COFINS;
import enviNFe_v400.TNFe.InfNFe.Det.Imposto.COFINS.COFINSOutr;

/**
 *
 * @author tiago
 */
public class CofinsOutr implements IImposto {

    private IImposto imposto;
    private COFINSOutr cofinsOutr;

    @Override
    public void verificaImposto(DetalhamentoProdutoServico item, COFINS cofins) {
        if (item.getCstcofins().equals(NFeTipoSituacaoTributariaCOFINS.COD04)) {
            cofinsOutr = new COFINSOutr();
            cofinsOutr.setCST(item.getCstcofins() != null ? item.getCstcofins().getCodigo() : null);
            cofinsOutr.setPCOFINS(item.getPcofins() != null ? item.getPcofins().toString() : null);

            cofinsOutr.setQBCProd(item.getQbcprodcofins() != null ? item.getQbcprodcofins().toString() : null);
            cofinsOutr.setVAliqProd(item.getValiqprodcofins() != null ? item.getValiqprodcofins().toString() : null);

            cofinsOutr.setVBC(item.getVbccofins() != null ? item.getVbccofins().toString() : null);
            cofinsOutr.setVCOFINS(item.getVcofins() != null ? item.getVcofins().toString() : null);
            cofins.setCOFINSOutr(cofinsOutr);
        }
    }

    @Override
    public void proximoImposto(IImposto proximoImposto) {
    }
}
