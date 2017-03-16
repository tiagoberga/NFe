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
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.ICMS.ICMS70;
import javax.ejb.Local;

/**
 *
 * @author tiago
 */
@Local(IImposto.class)
public class Icms70 implements IImposto {

    private IImposto imposto;
    private ICMS70 icms70;

    @Override
    public void verificaImposto(DetalhamentoProdutoServico item, ICMS icms) throws NFeException {
        if (item.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD70)) {
            icms70 = new ICMS70();
            icms70.setOrig(item.getOrig().getCodigo());
            icms70.setCST(item.getCsticms() != null ? item.getCsticms().getCodigo() : null);
            icms70.setModBC(item.getModbcicms() != null ? item.getModbcicms().getCodigo() : null);
            icms70.setModBCST(item.getModbcicmsst().getCodigo());
            icms70.setPICMS(item.getPicms() != null ? item.getPicms().toString() : null);
            icms70.setPICMSST(item.getPicmsst() != null ? item.getPicmsst().toString() : null);
            icms70.setPRedBC(item.getPredbcicms() != null ? item.getPredbcicms().toString() : null);
            icms70.setVBC(item.getVbcicms() != null ? item.getVbcicms().toString() : null);
            icms70.setVBCST(item.getVbcicmsst() != null ? item.getVbcicmsst().toString() : null);
            icms70.setVICMS(item.getVicms() != null ? item.getVicms().toString() : null);
            icms70.setVICMSST(item.getVicmsst() != null ? item.getVicmsst().toString() : null);

            icms70.setPMVAST(item.getPmvast() != null ? item.getPmvast().toString() : null);
            icms70.setPRedBCST(item.getPredbcicmsst() != null ? item.getPredbcicmsst().toString() : null);

            // DESONERACAO ICMS
            icms70.setMotDesICMS(item.getMotdesicms() != null ? item.getMotdesicms().getCodigo() : null);
            icms70.setVICMSDeson(item.getVicmsdeson() != null ? item.getVicmsdeson().toString() : null);
            icms.setICMS70(icms70);
        } else {
            this.imposto.verificaImposto(item, icms);
        }
    }

    @Override
    public void proximoImposto(IImposto proximoImposto) {
        this.imposto = proximoImposto;
    }
}
