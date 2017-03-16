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
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.ICMS.ICMS30;
import javax.ejb.Local;

/**
 *
 * @author tiago
 */
@Local(IImposto.class)
public class Icms30 implements IImposto {

    private IImposto imposto;
    private ICMS30 icms30;

    @Override
    public void verificaImposto(DetalhamentoProdutoServico item, ICMS icms) throws NFeException {
        if (item.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD30)) {
            icms30 = new ICMS30();
            icms30.setOrig(item.getOrig().getCodigo());
            icms30.setCST(item.getCsticms() != null ? item.getCsticms().getCodigo() : null);
            icms30.setModBCST(item.getModbcicmsst() != null ? item.getModbcicmsst().getCodigo() : null);
            icms30.setPICMSST(item.getPicmsst() != null ? item.getPicmsst().toString() : null);
            icms30.setVBCST(item.getVbcicmsst() != null ? item.getVbcicmsst().toString() : null);
            icms30.setVICMSST(item.getVicmsst() != null ? item.getVicmsst().toString() : null);
            icms30.setPMVAST(item.getPmvast() != null ? item.getPmvast().toString() : null);
            icms30.setPRedBCST(item.getPredbcicmsst() != null ? item.getPredbcicmsst().toString() : null);
            // DESONERACAO ICMS
            icms30.setVICMSDeson(item.getVicmsdeson() != null ? item.getVicmsdeson().toString() : null);
            icms30.setMotDesICMS(item.getMotdesicms() != null ? item.getMotdesicms().getCodigo() : null);
            icms.setICMS30(icms30);
        } else {
            this.imposto.verificaImposto(item, icms);
        }
    }

    @Override
    public void proximoImposto(IImposto proximoImposto) {
        this.imposto = proximoImposto;
    }

}
