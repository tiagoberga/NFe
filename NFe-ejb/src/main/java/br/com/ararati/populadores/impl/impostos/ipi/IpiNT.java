/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.populadores.impl.impostos.ipi;

import br.com.ararati.entity.nfe.emissao.DetalhamentoProdutoServico;
import br.com.ararati.enums.O.NFeTipoSituacaoTributariaIPI;
import enviNFe_v400.TIpi;
import enviNFe_v400.TIpi.IPINT;

/**
 * @author tiago
 */
public class IpiNT implements IImposto {

    @Override
    public void verificaImposto(DetalhamentoProdutoServico item, TIpi ipi) {
        if (item.getCstipi() != null && (item.getCstipi().equals(NFeTipoSituacaoTributariaIPI.COD01) || item.getCstipi().equals(NFeTipoSituacaoTributariaIPI.COD02) || item.getCstipi().equals(NFeTipoSituacaoTributariaIPI.COD03) || item.getCstipi().equals(NFeTipoSituacaoTributariaIPI.COD04) || item.getCstipi().equals(NFeTipoSituacaoTributariaIPI.COD05) || item.getCstipi().equals(NFeTipoSituacaoTributariaIPI.COD51) || item.getCstipi().equals(NFeTipoSituacaoTributariaIPI.COD52) || item.getCstipi().equals(NFeTipoSituacaoTributariaIPI.COD53) || item.getCstipi().equals(NFeTipoSituacaoTributariaIPI.COD54) || item.getCstipi().equals(NFeTipoSituacaoTributariaIPI.COD55))) {
            // CRIA IPI NAO TRIBUTADO
            IPINT ipiNt = new IPINT();
            ipiNt.setCST(item.getCstpis().getCodigo());

            // CRIA IPI
            ipi.setIPINT(ipiNt);
        } else {
            // 
        }
    }

    @Override
    public void proximoImposto(IImposto proximoImposto) {
        // nao faz nada, pois nao tem proximo imposto
    }
}
