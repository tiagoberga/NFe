/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.populadores.impl.impostos.icms.sn;

import br.com.ararati.entity.nfe.emissao.DetalhamentoProdutoServico;
import br.com.ararati.enums.N.NFeTipoSituacaoTributariaICMS;
import enviNFe_v400.TNFe.InfNFe.Det.Imposto.ICMS;
import enviNFe_v400.TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN900;

/**
 *
 * @author tiago
 */
public class Icmssn900 implements IImposto {

    private ICMSSN900 icmssn900;

    @Override
    public void verificaImposto(DetalhamentoProdutoServico item, ICMS icms) {
        if (item.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD900)) {
            icmssn900 = new ICMSSN900();
            icmssn900.setCSOSN(item.getCsticms() != null ? item.getCsticms().getCodigo() : null);
            icmssn900.setOrig(item.getOrig().getCodigo());
            icmssn900.setModBC(item.getModbcicms() != null ? item.getModbcicms().getCodigo() : null);
            icmssn900.setVBC(item.getVbcicms() != null ? item.getVbcicms().toString() : null);
            icmssn900.setPRedBC(item.getPredbcicms() != null ? item.getPredbcicms().toString() : null);
            icmssn900.setPICMS(item.getPicms() != null ? item.getPicms().toString() : null);
            icmssn900.setVICMS(item.getVicms() != null ? item.getVicms().toString() : null);
            icmssn900.setPCredSN(item.getPcredsn() != null ? item.getPcredsn().toString() : null);
            icmssn900.setVCredICMSSN(item.getVcredicmssn() != null ? item.getVcredicmssn().toString() : null);
            //ST
            icmssn900.setModBCST(item.getModbcicmsst() != null ? item.getModbcicmsst().getCodigo() : null);
            icmssn900.setPICMSST(item.getPicmsst() != null ? item.getPicmsst().toString() : null);
            icmssn900.setVBCST(item.getVbcicmsst() != null ? item.getVbcicmsst().toString() : null);
            icmssn900.setVICMSST(item.getVicmsst() != null ? item.getVicmsst().toString() : null);

            icmssn900.setPMVAST(item.getPmvast() != null ? item.getPmvast().toString() : null);
            icmssn900.setPRedBCST(item.getPredbcicmsst() != null ? item.getPredbcicmsst().toString() : null);
            
            icmssn900.setVBCFCPST(item.getVbcfcpst() != null ? item.getVbcfcpst().toString() : null);
            icmssn900.setPFCPST(item.getPfcpst()!= null ? item.getPfcpst().toString() : null);
            icmssn900.setVFCPST(item.getVfcpst()!= null ? item.getVfcpst().toString() : null);
            icms.setICMSSN900(icmssn900);
        } else {
        }
    }

    @Override
    public void proximoImposto(IImposto proximoImposto) {
    }
}
