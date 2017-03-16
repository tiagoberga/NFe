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
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.ICMS.ICMS20;
import javax.ejb.Local;

/**
 *
 * @author tiago
 */
@Local(IImposto.class)
public class Icms20 implements IImposto {

    private IImposto imposto;
    private ICMS20 icms20;

    @Override
    public void verificaImposto(DetalhamentoProdutoServico item, ICMS icms) throws NFeException {
        if (item.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD20)) {
            icms20 = new ICMS20();
            icms20.setOrig(item.getOrig().getCodigo());
            icms20.setCST(item.getCsticms() != null ? item.getCsticms().getCodigo() : null);
            icms20.setModBC(item.getModbcicms() != null ? item.getModbcicms().getCodigo() : null);
            icms20.setPRedBC(item.getPredbcicms() != null ? item.getPredbcicms().toString() : null);
            icms20.setVBC(item.getVbcicms() != null ? item.getVbcicms().toString() : null);
            icms20.setPICMS(item.getPicms() != null ? item.getPicms().toString() : null);
            icms20.setVICMS(item.getVicms() != null ? item.getVicms().toString() : null);
            // DESONERACAO ICMS
            icms20.setVICMSDeson(item.getVicmsdeson() != null ? item.getVicmsdeson().toString() : null);
            icms20.setMotDesICMS(item.getMotdesicms() != null ? item.getMotdesicms().getCodigo() : null);
            icms.setICMS20(icms20);
        } else {
            this.imposto.verificaImposto(item, icms);
        }
    }

    @Override
    public void proximoImposto(IImposto proximoImposto) {
        this.imposto = proximoImposto;
    }
}
