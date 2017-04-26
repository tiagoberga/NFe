/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.populadores.impl.impostos.icms.st;

import br.com.ararati.entity.nfe.emissao.DetalhamentoProdutoServico;
import br.com.ararati.enums.N.NFeTipoSituacaoTributariaICMS;
import br.com.ararati.exception.NFeException;
import enviNFe_v400.TNFe.InfNFe.Det.Imposto.ICMS;
import enviNFe_v400.TNFe.InfNFe.Det.Imposto.ICMS.ICMS10;
import javax.ejb.Local;

/**
 *
 * @author tiago
 */
@Local(IImposto.class)
public class Icms10 implements IImposto {

    private IImposto imposto;
    private ICMS10 icms10;

    @Override
    public void verificaImposto(DetalhamentoProdutoServico item, ICMS icms) throws NFeException {
        if (item.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD10) || item.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD10_PARTILHA)) {
            icms10 = new ICMS10();
            icms10.setOrig(item.getOrig().getCodigo());
            icms10.setCST(item.getCsticms() != null ? item.getCsticms().getCodigo() : null);
            icms10.setModBC(item.getModbcicms() != null ? item.getModbcicms().getCodigo() : null);
            icms10.setModBCST(item.getModbcicmsst().getCodigo());
            icms10.setPICMS(item.getPicms() != null ? item.getPicms().toString() : null);
            icms10.setPICMSST(item.getPicmsst() != null ? item.getPicmsst().toString() : null);
            icms10.setVBC(item.getVbcicms() != null ? item.getVbcicms().toString() : null);
            icms10.setVBCST(item.getVbcicmsst() != null ? item.getVbcicmsst().toString() : null);
            icms10.setVICMS(item.getVicms() != null ? item.getVicms().toString() : null);
            icms10.setVICMSST(item.getVicmsst() != null ? item.getVicmsst().toString() : null);
            icms10.setPMVAST(item.getPmvast() != null ? item.getPmvast().toString() : null);
            icms10.setPRedBCST(item.getPredbcicmsst() != null ? item.getPredbcicmsst().toString() : null);
            icms10.setVBCFCPST(item.getVbcfcpst() != null ? item.getVbcfcpst().toString() : null);
            icms10.setPFCPST(item.getPfcpst()!= null ? item.getPfcpst().toString() : null);
            icms10.setVFCPST(item.getVfcpst()!= null ? item.getVfcpst().toString() : null);
            icms.setICMS10(icms10);
        } else {
            this.imposto.verificaImposto(item, icms);
        }
    }

    @Override
    public void proximoImposto(IImposto proximoImposto) {
        this.imposto = proximoImposto;
    }
}
