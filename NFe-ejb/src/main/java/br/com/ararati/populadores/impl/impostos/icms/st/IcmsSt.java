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
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.ICMS.ICMSST;
import javax.ejb.Local;

/**
 * Grupo de Repasse de ICMS ST retido anteriormente em operações interestaduais
 * com repasses através do Substituto Tributário.
 *
 * @author tiago
 */
@Local(IImposto.class)
public class IcmsSt implements IImposto {

    private IImposto imposto;
    private ICMSST icmsSt;

    @Override
    public void verificaImposto(DetalhamentoProdutoServico item, ICMS icms) throws NFeException {
        if (item.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD41_ICMSST) || item.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD60)) {
            icmsSt = new ICMSST();
            icmsSt.setOrig(item.getOrig().getCodigo());
            icmsSt.setCST(item.getCsticms() != null ? item.getCsticms().getCodigo() : null);
            icmsSt.setVBCSTDest(item.getVbcicmsstdest() != null ? item.getVbcicmsstdest().toString() : null);
            icmsSt.setVBCSTRet(item.getVbcicmsstret() != null ? item.getVbcicmsstret().toString() : null);
            icmsSt.setVICMSSTDest(item.getVicmsstdest() != null ? item.getVicmsstdest().toString() : null);
            icmsSt.setVICMSSTRet(item.getVicmsstret() != null ? item.getVicmsstret().toString() : null);
            icms.setICMSST(icmsSt);
        } else {
            this.imposto.verificaImposto(item, icms);
        }
    }

    @Override
    public void proximoImposto(IImposto proximoImposto) {
        this.imposto = proximoImposto;
    }

}
