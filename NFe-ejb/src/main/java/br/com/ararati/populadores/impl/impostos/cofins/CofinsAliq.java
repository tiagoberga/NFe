/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.populadores.impl.impostos.cofins;

import br.com.ararati.entity.nfe.emissao.DetalhamentoProdutoServico;
import br.com.ararati.enums.S.NFeTipoSituacaoTributariaCOFINS;
import enviNFe_v400.TNFe.InfNFe.Det.Imposto.COFINS;
import enviNFe_v400.TNFe.InfNFe.Det.Imposto.COFINS.COFINSAliq;

/**
 *
 * @author tiago
 */
public class CofinsAliq implements IImposto {

    private IImposto imposto;
    private COFINSAliq cofinsAliq;

    @Override
    public void verificaImposto(DetalhamentoProdutoServico item, COFINS cofins) {
        if (item.getCstcofins().equals(NFeTipoSituacaoTributariaCOFINS.COD01) || item.getCstcofins().equals(NFeTipoSituacaoTributariaCOFINS.COD02)) {
            cofinsAliq = new COFINSAliq();
            cofinsAliq.setCST(item.getCstcofins() != null ? item.getCstcofins().getCodigo() : null);
            cofinsAliq.setPCOFINS(item.getPcofins() != null ? item.getPcofins().toString() : null);
            cofinsAliq.setVBC(item.getVbccofins() != null ? item.getVbccofins().toString() : null);
            cofinsAliq.setVCOFINS(item.getVcofins() != null ? item.getVcofins().toString() : null);
            cofins.setCOFINSAliq(cofinsAliq);
        } else {
            this.imposto.verificaImposto(item, cofins);
        }
    }

    @Override
    public void proximoImposto(IImposto proximoImposto) {
        this.imposto = proximoImposto;
    }
}
