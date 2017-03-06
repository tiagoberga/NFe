/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.session.config;

import br.com.ararati.entity.config.ConfiguracaoAmbiente;
import br.com.ararati.session.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tiago
 */
@Stateless
public class ConfiguracaoAmbienteFacade extends AbstractFacade<ConfiguracaoAmbiente> {
    @PersistenceContext(unitName = "NFE-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConfiguracaoAmbienteFacade() {
        super(ConfiguracaoAmbiente.class);
    }
    
}
