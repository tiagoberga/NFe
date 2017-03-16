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
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.ICMS.ICMS40;
import javax.ejb.Local;

/**
 *
 * @author tiago
 */
@Local(IImposto.class)
public class Icms40 implements IImposto {

    private IImposto imposto;
    private ICMS40 icms40;

    @Override
    public void verificaImposto(DetalhamentoProdutoServico item, ICMS icms) throws NFeException {
        if (item.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD40) || item.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD41) || item.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD50)) {
            icms40 = new ICMS40();
            icms40.setOrig(item.getOrig().getCodigo());
            icms40.setCST(item.getCsticms() != null ? item.getCsticms().getCodigo() : null);
            // DESONERACAO ICMS
            icms40.setMotDesICMS(item.getMotdesicms() != null ? item.getMotdesicms().getCodigo() : null);
            icms40.setVICMSDeson(item.getVicmsdeson() != null ? item.getVicmsdeson().toString() : null);
            icms.setICMS40(icms40);
        } else {
            this.imposto.verificaImposto(item, icms);
        }
    }

    @Override
    public void proximoImposto(IImposto proximoImposto) {
        this.imposto = proximoImposto;
    }

}
