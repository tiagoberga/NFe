/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.session.nfe;

import br.com.ararati.entity.nfe.emissao.DadosNFe;
import br.com.ararati.exception.DaoException;
import br.com.ararati.session.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ararati1
 */
@Stateless
public class DadosNFeFacade extends AbstractFacade<DadosNFe> {

    @PersistenceContext(unitName = "NFE-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DadosNFeFacade() {
        super(DadosNFe.class);
    }

    @Override
    public void remove(DadosNFe entity) throws DaoException {
        super.remove(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(DadosNFe entity) throws DaoException {
        super.save(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(List<DadosNFe> entities) throws DaoException {
        super.save(entities); //To change body of generated methods, choose Tools | Templates.
    }

}
