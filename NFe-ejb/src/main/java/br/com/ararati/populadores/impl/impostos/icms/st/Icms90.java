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
import enviNFe_v400.TNFe.InfNFe.Det.Imposto.ICMS.ICMS90;
import javax.ejb.Local;

/**
 *
 * @author tiago
 */
@Local(IImposto.class)
public class Icms90 implements IImposto {

    private IImposto proximoImposto;
    private ICMS90 icms90;

    @Override
    public void verificaImposto(DetalhamentoProdutoServico item, ICMS icms) throws NFeException {
        if (item.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD90)) {
            icms90 = new ICMS90();
            icms90.setOrig(item.getOrig().getCodigo());
            icms90.setCST(item.getCsticms() != null ? item.getCsticms().getCodigo() : null);
            icms90.setModBC(item.getModbcicms() != null ? item.getModbcicms().getCodigo() : null);
            icms90.setModBCST(item.getModbcicmsst().getCodigo());
            icms90.setPICMS(item.getPicms() != null ? item.getPicms().toString() : null);
            icms90.setPICMSST(item.getPicmsst() != null ? item.getPicmsst().toString() : null);
            icms90.setPRedBC(item.getPredbcicms() != null ? item.getPredbcicms().toString() : null);
            icms90.setVBC(item.getVbcicms() != null ? item.getVbcicms().toString() : null);
            icms90.setVBCST(item.getVbcicmsst() != null ? item.getVbcicmsst().toString() : null);
            icms90.setVICMS(item.getVicms() != null ? item.getVicms().toString() : null);
            icms90.setVICMSST(item.getVicmsst() != null ? item.getVicmsst().toString() : null);
            icms90.setPMVAST(item.getPmvast() != null ? item.getPmvast().toString() : null);
            icms90.setPRedBCST(item.getPredbcicmsst() != null ? item.getPredbcicmsst().toString() : null);
            icms90.setMotDesICMS(item.getMotdesicms() != null ? item.getMotdesicms().getCodigo() : null);
            icms90.setVICMSDeson(item.getVicmsdeson() != null ? item.getVicmsdeson().toString() : null);
            icms90.setVBCFCP(item.getVbcfcp() != null ? item.getVbcfcp().toString() : null);
            icms90.setVBCFCPST(item.getVbcfcpst() != null ? item.getVbcfcpst().toString() : null);
            icms90.setPFCP(item.getPfcp()!= null ? item.getPfcp().toString() : null);
            icms90.setPFCPST(item.getPfcpst()!= null ? item.getPfcpst().toString() : null);
            icms90.setVFCP(item.getVfcp()!= null ? item.getVfcp().toString() : null);
            icms90.setVFCPST(item.getVfcpst()!= null ? item.getVfcpst().toString() : null);
            icms.setICMS90(icms90);
        } else {
            this.proximoImposto.verificaImposto(item, icms);
        }
    }

    @Override
    public void proximoImposto(IImposto proximoImposto) {
        this.proximoImposto = proximoImposto;
    }
}
