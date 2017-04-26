/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.populadores.impl.impostos.ipi;

import br.com.ararati.entity.nfe.emissao.DetalhamentoProdutoServico;
import enviNFe_v400.TIpi;

/**
 *
 * @author tiago
 */
public class ChainIPI {

    /**
     * Método responsável por controlar qual tipo de IPI será aplicado
     *
     * @param item PedidoVendaItem
     * @param ipi
     */
    public void getChainOfTIpi(DetalhamentoProdutoServico item, TIpi ipi) {
        if (item.getCstipi() != null) {
            IImposto ipitrib = new IpiTrib();
            IImposto ipint = new IpiNT();

            ipitrib.proximoImposto(ipint);

            ipitrib.verificaImposto(item, ipi);
        }
    }
}
