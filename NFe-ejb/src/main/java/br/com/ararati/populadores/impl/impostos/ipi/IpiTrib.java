/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.populadores.impl.impostos.ipi;

import br.com.ararati.entity.nfe.emissao.DetalhamentoProdutoServico;
import br.com.ararati.enums.O.NFeTipoSituacaoTributariaIPI;
import enviNFe_v310.TIpi;
import enviNFe_v310.TIpi.IPITrib;

/**
 * @author tiago
 */
public class IpiTrib implements IImposto {

    private IImposto imposto;

    @Override
    public void verificaImposto(DetalhamentoProdutoServico item, TIpi ipi) {
        if (item.getCstipi() != null && (item.getCstipi().equals(NFeTipoSituacaoTributariaIPI.COD00) || item.getCstipi().equals(NFeTipoSituacaoTributariaIPI.COD49) || item.getCstipi().equals(NFeTipoSituacaoTributariaIPI.COD50) || item.getCstipi().equals(NFeTipoSituacaoTributariaIPI.COD99))) {
            // CRIA IPI TRIBUTADO
            IPITrib ipiTrib = new IPITrib();

            if (true) {
                ipiTrib.setCST(item.getCstipi() != null ? item.getCstipi().getCodigo() : null);
                ipiTrib.setVBC(item.getVbcipi() != null ? item.getVbcipi().toString() : null);
                ipiTrib.setPIPI(item.getPipi() != null ? item.getPipi().toString() : null);
                ipiTrib.setVIPI(item.getVipi() != null ? item.getVipi().toString() : null);
            } else {
                ipiTrib.setQUnid(item.getQunid() != null ? item.getQunid().toString() : null);
                ipiTrib.setVUnid(item.getVunid() != null ? item.getVunid().toString() : null);
            }

            // CRIA IPI
            ipi.setIPITrib(ipiTrib);
        } else {
            this.imposto.verificaImposto(item, ipi);
        }

    }

    @Override
    public void proximoImposto(IImposto proximoImposto) {
        this.imposto = proximoImposto;
    }
}
