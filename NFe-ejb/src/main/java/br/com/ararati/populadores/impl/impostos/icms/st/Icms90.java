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
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.ICMS.ICMS90;
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
            icms90.setOrig(item.getOrig());
            icms90.setCST(item.getCsticms() != null ? item.getCsticms().getCodigo() : null);
            icms90.setModBC(item.getModbc() != null ? item.getModbc().getCodigo() : null);
            icms90.setModBCST(item.getModbcst().getCodigo());
            icms90.setPICMS(item.getPicms() != null ? item.getPicms().toString() : null);
            icms90.setPICMSST(item.getPicmsst() != null ? item.getPicmsst().toString() : null);
            icms90.setPRedBC(item.getPredbc() != null ? item.getPredbc().toString() : null);
            icms90.setVBC(item.getVbcicms() != null ? item.getVbcicms().toString() : null);
            icms90.setVBCST(item.getVbcicmsst() != null ? item.getVbcicmsst().toString() : null);
            icms90.setVICMS(item.getVicms() != null ? item.getVicms().toString() : null);
            icms90.setVICMSST(item.getVicmsst() != null ? item.getVicmsst().toString() : null);

            icms90.setPMVAST(item.getPmvast() != null ? item.getPmvast().toString() : null);
            icms90.setPRedBCST(item.getPredbcst() != null ? item.getPredbcst().toString() : null);

            // DESONERACAO ICMS
            icms90.setMotDesICMS(item.getMotdesicms() != null ? item.getMotdesicms().getCodigo() : null);
            icms90.setVICMSDeson(item.getVicmsdeson() != null ? item.getVicmsdeson().toString() : null);

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
