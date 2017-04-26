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
import enviNFe_v400.TNFe.InfNFe.Det.Imposto.ICMS.ICMS51;
import javax.ejb.Local;

/**
 *
 * @author tiago
 */
@Local(IImposto.class)
public class Icms51 implements IImposto {
    
    private IImposto imposto;
    private ICMS51 icms51;
    
    @Override
    public void verificaImposto(DetalhamentoProdutoServico item, ICMS icms) throws NFeException {
        if (item.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD51)) {
            icms51 = new ICMS51();
            icms51.setOrig(item.getOrig().getCodigo());
            icms51.setCST(item.getCsticms() != null ? item.getCsticms().getCodigo() : null);
            icms51.setModBC(item.getModbcicms() != null ? item.getModbcicms().getCodigo() : null);
            icms51.setVBC(item.getVbcicms() != null ? item.getVbcicms().toString() : null);
            icms51.setPICMS(item.getPicms() != null ? item.getPicms().toString() : null);
            icms51.setVICMS(item.getVicms() != null ? item.getVicms().toString() : null);
            icms51.setVICMSOp(item.getVicmsop() != null ? item.getVicmsop().toString() : null);
            icms51.setPDif(item.getPdif() != null ? item.getPdif().toString() : null);
            icms51.setVICMSDif(item.getVicmsdif() != null ? item.getVicmsdif().toString() : null);
            icms51.setVBCFCP(item.getVbcfcp() != null ? item.getVbcfcp().toString() : null);
            icms51.setPFCP(item.getPfcp()!= null ? item.getPfcp().toString() : null);
            icms51.setVFCP(item.getVfcp()!= null ? item.getVfcp().toString() : null);
            icms.setICMS51(icms51);
        } else {
            this.imposto.verificaImposto(item, icms);
        }
    }
    
    @Override
    public void proximoImposto(IImposto proximoImposto) {
        this.imposto = proximoImposto;
    }
}
