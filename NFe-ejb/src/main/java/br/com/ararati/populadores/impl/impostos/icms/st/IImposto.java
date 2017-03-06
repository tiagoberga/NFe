/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.populadores.impl.impostos.icms.st;

import br.com.ararati.entity.nfe.emissao.DetalhamentoProdutoServico;
import br.com.ararati.exception.NFeException;
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.ICMS;

/**
 *
 * @author tiago
 */
//@Local
public interface IImposto {

    public void verificaImposto(DetalhamentoProdutoServico item, ICMS icms) throws NFeException;

    public void proximoImposto(IImposto proximoImposto);
}
