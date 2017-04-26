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
import enviNFe_v400.TNFe.InfNFe.Det.Imposto.ICMS.ICMSPart;
import enviNFe_v400.TUf;
import javax.ejb.Local;

/**
 *
 * @author tiago
 */
@Local(IImposto.class)
public class IcmsPart implements IImposto {

    private ICMSPart icmsPart;

    @Override
    public void verificaImposto(DetalhamentoProdutoServico item, ICMS icms) throws NFeException {
        if (item.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD10_PARTILHA) || item.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD90_PARTILHA)) {
            icmsPart = new ICMSPart();
            icmsPart.setOrig(item.getOrig().getCodigo());
            icmsPart.setCST(item.getCsticms() != null ? item.getCsticms().getCodigo() : null);
            icmsPart.setModBC(item.getModbcicms() != null ? item.getModbcicms().getCodigo() : null);
            icmsPart.setModBCST(item.getModbcicmsst() != null ? item.getModbcicmsst().getCodigo() : null);
            icmsPart.setPBCOp(item.getPbcop() != null ? item.getPbcop().toString() : null);
            icmsPart.setPICMS(item.getPicms() != null ? item.getPicms().toString() : null);
            icmsPart.setPICMSST(item.getPicmsst() != null ? item.getPicmsst().toString() : null);
            icmsPart.setUFST(item.getUfst() != null ? TUf.valueOf(item.getUfst()) : null);
            icmsPart.setVBC(item.getVbcicms() != null ? item.getVbcicms().toString() : null);
            icmsPart.setVBCST(item.getVbcicmsst() != null ? item.getVbcicmsst().toString() : null);
            icmsPart.setVICMS(item.getVicms() != null ? item.getVicms().toString() : null);
            icmsPart.setVICMSST(item.getVicmsst() != null ? item.getVicmsst().toString() : null);

            icmsPart.setPMVAST(item.getPmvast() != null ? item.getPmvast().toString() : null);
            icmsPart.setPRedBC(item.getPredbcicms() != null ? item.getPredbcicms().toString() : null);
            icmsPart.setPRedBCST(item.getPredbcicmsst() != null ? item.getPredbcicmsst().toString() : null);
            icms.setICMSPart(icmsPart);
        }else{
            
        }
    }

    @Override
    public void proximoImposto(IImposto proximoImposto) {
        // nao faz nada
    }

}
