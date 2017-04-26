/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.populadores.impl.impostos.cofins;

import br.com.ararati.entity.nfe.emissao.DetalhamentoProdutoServico;
import enviNFe_v400.TNFe.InfNFe.Det.Imposto.COFINS;

/**
 *
 * @author tiago
 */
public interface IImposto {

    public void verificaImposto(DetalhamentoProdutoServico item, COFINS cofins);

    public void proximoImposto(IImposto proximoImposto);

}
