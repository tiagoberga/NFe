/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.session.cadastros;

import br.com.ararati.entity.cadastros.Emitente;
import br.com.ararati.entity.cadastros.Produto;
import br.com.ararati.filter.cadastros.ProdutoFilter;
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
public class ProdutoFacade extends AbstractFacade<Produto> {

    @PersistenceContext(unitName = "NFE-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProdutoFacade() {
        super(Produto.class);
    }

    public List<Produto> findBy(String stringToSearch, Emitente emitente) {
        StringBuilder sql = new StringBuilder()
                .append(" SELECT e ")
                .append(" FROM Produto e ")
                .append(" WHERE e.emitente = ?").append(Produto.P_EMITENTE)
                .append(" AND (UPPER(e.cprod) LIKE UPPER(?").append(Produto.P_CPROD).append(")")
                .append(" OR UPPER(e.xprod) LIKE UPPER(?").append(Produto.P_XPROD).append(")")
                .append(" OR UPPER(e.cean) LIKE UPPER(?").append(Produto.P_CEAN).append(")")
                .append(" OR UPPER(e.cfop) LIKE UPPER(?").append(Produto.P_CFOP).append(")")
                .append(" ) ORDER BY e.xprod");
        TypedQuery<Produto> q = getEntityManager().createQuery(sql.toString(), Produto.class).setMaxResults(100);
        q.setParameter(Produto.P_EMITENTE, emitente);
        q.setParameter(Produto.P_CPROD, "%" + stringToSearch + "%");
        q.setParameter(Produto.P_XPROD, "%" + stringToSearch + "%");
        q.setParameter(Produto.P_CEAN, "%" + stringToSearch + "%");
        q.setParameter(Produto.P_CFOP, "%" + stringToSearch + "%");

        try {
            return q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Produto> findByFilter(ProdutoFilter filter) {
        StringBuilder sql = new StringBuilder()
                .append(" SELECT e ")
                .append(" FROM Produto e ")
                .append(" WHERE e.emitente = ?").append(Produto.P_EMITENTE);

        if (filter.getCprod() != null) {
            sql.append(" AND e.cprod LIKE ?").append(Produto.P_CPROD);
        }

        if (filter.getXprod() != null) {
            sql.append(" AND UPPER(e.xprod) LIKE (?").append(Produto.P_XPROD).append(")");
        }

        if (filter.getCean() != null) {
            sql.append(" AND UPPER(e.cean) LIKE UPPER(?").append(Produto.P_CEAN).append(")");
        }

        sql.append(" ORDER BY e.xprod");

        TypedQuery<Produto> q = getEntityManager().createQuery(sql.toString(), Produto.class).setMaxResults(100);
        q.setParameter(Produto.P_EMITENTE, filter.getEmitente());

        if (filter.getCprod() != null) {
            q.setParameter(Produto.P_CPROD, "%" + filter.getCprod().toUpperCase() + "%");
        }

        if (filter.getXprod() != null) {
            q.setParameter(Produto.P_XPROD, "%" + filter.getXprod().toUpperCase() + "%");
        }

        if (filter.getCean() != null) {
            q.setParameter(Produto.P_CEAN, "%" + filter.getCean() + "%");
        }

        try {
            return q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

}
