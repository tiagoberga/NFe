/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.session.cadastros;

import br.com.ararati.entity.cadastros.Emitente;
import br.com.ararati.filter.cadastros.EmitenteFilter;
import br.com.ararati.session.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author tiago
 */
@Stateless
public class EmitenteFacade extends AbstractFacade<Emitente> {

    @PersistenceContext(unitName = "NFE-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmitenteFacade() {
        super(Emitente.class);
    }

    
    public List<Emitente> findByFilter(EmitenteFilter filter) {
        StringBuilder sql = new StringBuilder().append(" SELECT e FROM Emitente e WHERE 1 = 1 ");

        if (filter.getDocumento() != null) {
            sql.append(" AND UPPER(e.documento) LIKE ?").append(Emitente.P_DOCUMENTO);
        }

        if (filter.getXnome() != null) {
            sql.append(" AND UPPER(e.xnome) LIKE ?").append(Emitente.P_XNOME);
        }

        sql.append(" ORDER BY e.xnome");

        TypedQuery<Emitente> q = getEntityManager().createQuery(sql.toString(), Emitente.class).setMaxResults(100);

        if (filter.getDocumento() != null) {
            q.setParameter(Emitente.P_DOCUMENTO, filter.getDocumento().toUpperCase() + "%");
        }

        if (filter.getXnome() != null) {
            q.setParameter(Emitente.P_XNOME, "%" + filter.getXnome().toUpperCase() + "%");
        }

        try {
            return q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

}
