/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.populadores.impl.impostos.ipi;

import br.com.ararati.entity.nfe.emissao.DetalhamentoProdutoServico;
import enviNFe_v310.TIpi;

/**
 *
 * @author tiago
 */
public interface IImposto {

    public void verificaImposto(DetalhamentoProdutoServico item, TIpi ipis);

    public void proximoImposto(IImposto proximoImposto);
}
