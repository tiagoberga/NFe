/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.populadores.impl.impostos.pis;

import br.com.ararati.entity.nfe.emissao.DetalhamentoProdutoServico;
import br.com.ararati.enums.Q.NFeTipoSituacaoTributariaPIS;
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.PIS;
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.PIS.PISAliq;
import javax.ejb.Local;

/**
 *
 * @author tiago
 */
@Local(IImposto.class)
public class PisAliq implements IImposto {

    private IImposto imposto;
    private PISAliq pisAliq;

    @Override
    public void verificaImposto(DetalhamentoProdutoServico item, PIS pis) {
        if (item.getCstpis().equals(NFeTipoSituacaoTributariaPIS.COD01) || item.getCstpis().equals(NFeTipoSituacaoTributariaPIS.COD02)) {
            pisAliq = new PISAliq();
            pisAliq.setCST(item.getCstpis().getCodigo());
            pisAliq.setPPIS(item.getPpis() != null ? item.getPpis().toString() : null);
            pisAliq.setVBC(item.getVbcpis() != null ? item.getVbcpis().toString() : null);
            pisAliq.setVPIS(item.getVpis() != null ? item.getVpis().toString() : null);
            pis.setPISAliq(pisAliq);
        } else {
            this.imposto.verificaImposto(item, pis);
        }
    }

    @Override
    public void proximoImposto(IImposto proximoImposto) {
        this.imposto = proximoImposto;
    }

}
