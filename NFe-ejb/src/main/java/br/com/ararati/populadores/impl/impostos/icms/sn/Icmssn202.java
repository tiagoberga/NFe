/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.populadores.impl.impostos.icms.sn;

import br.com.ararati.entity.nfe.emissao.DetalhamentoProdutoServico;
import br.com.ararati.enums.N.NFeTipoSituacaoTributariaICMS;
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.ICMS;
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN202;

/**
 *
 * @author tiago
 */
public class Icmssn202 implements IImposto {

    private IImposto proximoImposto;
    private ICMSSN202 icmssn202;

    @Override
    public void verificaImposto(DetalhamentoProdutoServico item, ICMS icms) {
        if (item.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD202) || item.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD203)) {
            icmssn202 = new ICMSSN202();
            icmssn202.setCSOSN(item.getCsticms() != null ? item.getCsticms().getCodigo() : null);
            icmssn202.setOrig(item.getOrig());
            // ST
            icmssn202.setModBCST(item.getModbcst() != null ? item.getModbcst().getCodigo() : null);
            icmssn202.setPICMSST(item.getPicmsst() != null ? item.getPicmsst().toString() : null);
            icmssn202.setVBCST(item.getVbcicmsst() != null ? item.getVbcicmsst().toString() : null);
            icmssn202.setVICMSST(item.getVicmsst() != null ? item.getVicmsst().toString() : null);

            icmssn202.setPMVAST(item.getPmvast() != null ? item.getPmvast().toString() : null);
            icmssn202.setPRedBCST(item.getPredbcst() != null ? item.getPredbcst().toString() : null);
            icms.setICMSSN202(icmssn202);
        } else {
            this.proximoImposto.verificaImposto(item, icms);
        }
    }

    @Override
    public void proximoImposto(IImposto proximoImposto) {
        this.proximoImposto = proximoImposto;
    }

}
