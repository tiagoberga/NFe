/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.populadores.impl.impostos.cofins;

import br.com.ararati.entity.nfe.emissao.DetalhamentoProdutoServico;
import br.com.ararati.enums.S.NFeTipoSituacaoTributariaCOFINS;
import enviNFe_v400.TNFe.InfNFe.Det.Imposto.COFINS;
import enviNFe_v400.TNFe.InfNFe.Det.Imposto.COFINS.COFINSNT;

/**
 *
 * @author tiago
 */
public class CofinsNt implements IImposto {

    private IImposto imposto;
    private COFINSNT cofinsNt = new COFINSNT();

    @Override
    public void verificaImposto(DetalhamentoProdutoServico item, COFINS cofins) {
        if (item.getCstcofins().equals(NFeTipoSituacaoTributariaCOFINS.COD04)
                || item.getCstcofins().equals(NFeTipoSituacaoTributariaCOFINS.COD06)
                || item.getCstcofins().equals(NFeTipoSituacaoTributariaCOFINS.COD07)
                || item.getCstcofins().equals(NFeTipoSituacaoTributariaCOFINS.COD08)
                || item.getCstcofins().equals(NFeTipoSituacaoTributariaCOFINS.COD09)) {
            cofinsNt = new COFINSNT();
            cofinsNt.setCST(item.getCstcofins() != null ? item.getCstcofins().getCodigo() : null);
            cofins.setCOFINSNT(cofinsNt);
        } else {
            this.imposto.verificaImposto(item, cofins);
        }
    }

    @Override
    public void proximoImposto(IImposto proximoImposto) {
        this.imposto = proximoImposto;
    }
}
