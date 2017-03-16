/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.populadores.impl.impostos.icms.sn;

import br.com.ararati.entity.nfe.emissao.DetalhamentoProdutoServico;
import br.com.ararati.enums.N.NFeTipoSituacaoTributariaICMS;
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.ICMS;
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN201;

/**
 *
 * @author tiago
 */
public class Icmssn201 implements IImposto {

    private IImposto proximoImposto;
    private ICMSSN201 icmssn201;

    @Override
    public void verificaImposto(DetalhamentoProdutoServico item, ICMS icms) {
        if (item.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD201)) {
            icmssn201 = new ICMSSN201();
            icmssn201.setCSOSN(item.getCsticms() != null ? item.getCsticms().getCodigo() : null);
            icmssn201.setOrig(item.getOrig().getCodigo());
            icmssn201.setPCredSN(item.getPcredsn() != null ? item.getPcredsn().toString() : null);
            icmssn201.setVCredICMSSN(item.getVcredicmssn() != null ? item.getVcredicmssn().toString() : null);
            // ST
            icmssn201.setModBCST(item.getModbcicmsst() != null ? item.getModbcicmsst().getCodigo() : null);
            icmssn201.setPICMSST(item.getPicmsst() != null ? item.getPicmsst().toString() : null);
            icmssn201.setVBCST(item.getVbcicmsst() != null ? item.getVbcicmsst().toString() : null);
            icmssn201.setVICMSST(item.getVicmsst() != null ? item.getVicmsst().toString() : null);

            icmssn201.setPMVAST(item.getPmvast() != null ? item.getPmvast().toString() : null);
            icmssn201.setPRedBCST(item.getPredbcicmsst() != null ? item.getPredbcicmsst().toString() : null);
            icms.setICMSSN201(icmssn201);
        } else {
            this.proximoImposto.verificaImposto(item, icms);
        }
    }

    @Override
    public void proximoImposto(IImposto proximoImposto) {
        this.proximoImposto = proximoImposto;
    }

}
