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
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.ICMS.ICMS00;
import javax.ejb.Local;

/**
 *
 * @author tiago
 */
@Local(IImposto.class)
public class Icms00 implements IImposto {

    private IImposto imposto;
    private ICMS00 icms00;

    @Override
    public void verificaImposto(DetalhamentoProdutoServico item, ICMS icms) throws NFeException {
        if (item.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD00)) {
            icms00 = new ICMS00();
            icms00.setOrig(item.getOrig().getCodigo());
            icms00.setCST(item.getCsticms() != null ? item.getCsticms().getCodigo() : null);
            icms00.setModBC(item.getModbcicms() != null ? item.getModbcicms().getCodigo() : null);
            icms00.setVBC(item.getVbcicms() != null ? item.getVbcicms().toString() : null);
            icms00.setPICMS(item.getPicms() != null ? item.getPicms().toString() : null);
            icms00.setVICMS(item.getVicms() != null ? item.getVicms().toString() : null);
            icms.setICMS00(icms00);
        } else {
            this.imposto.verificaImposto(item, icms);
        }
    }

    @Override
    public void proximoImposto(IImposto proximoImposto) {
        this.imposto = proximoImposto;
    }
}
