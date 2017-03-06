/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.session.cadastros;

import br.com.ararati.entity.cadastros.Emitente;
import br.com.ararati.entity.cadastros.Transportadora;
import br.com.ararati.filter.cadastros.TransportadoraFilter;
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
public class TransportadoraFacade extends AbstractFacade<Transportadora> {

    @PersistenceContext(unitName = "NFE-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TransportadoraFacade() {
        super(Transportadora.class);
    }

    public List<Transportadora> findBy(String stringToSearch, Emitente emitente) {
        StringBuilder sql = new StringBuilder()
                .append(" SELECT e ")
                .append(" FROM Transportadora e ")
                .append(" WHERE e.emitente = ?").append(Transportadora.P_EMITENTE)
                .append(" AND (UPPER(e.documento) LIKE UPPER(?").append(Transportadora.P_DOCUMENTO).append(")")
                .append(" OR UPPER(e.xnome) LIKE UPPER(?").append(Transportadora.P_XNOME).append(")")
                .append(" ) ORDER BY e.xnome");
        TypedQuery<Transportadora> q = getEntityManager().createQuery(sql.toString(), Transportadora.class).setMaxResults(100);
        q.setParameter(Transportadora.P_EMITENTE, emitente);
        q.setParameter(Transportadora.P_DOCUMENTO, "%" + stringToSearch + "%");
        q.setParameter(Transportadora.P_XNOME, "%" + stringToSearch + "%");

        try {
            return q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Transportadora> findByFilter(TransportadoraFilter filter) {
        StringBuilder sql = new StringBuilder().append(" SELECT e FROM Transportadora e WHERE 1 = 1 ");

        if (filter.getDocumento() != null) {
            sql.append(" AND UPPER(e.documento) LIKE ?").append(Transportadora.P_DOCUMENTO);
        }

        if (filter.getXnome() != null) {
            sql.append(" AND UPPER(e.xnome) LIKE ?").append(Transportadora.P_XNOME);
        }

        if (filter.getIe() != null) {
            sql.append(" AND e.ie LIKE ?").append(Transportadora.P_IE);
        }

        sql.append(" ORDER BY e.xnome");

        TypedQuery<Transportadora> q = getEntityManager().createQuery(sql.toString(), Transportadora.class).setMaxResults(100);

        if (filter.getDocumento() != null) {
            q.setParameter(Transportadora.P_DOCUMENTO, filter.getDocumento().toUpperCase() + "%");
        }

        if (filter.getXnome() != null) {
            q.setParameter(Transportadora.P_XNOME, "%" + filter.getXnome().toUpperCase() + "%");
        }

        if (filter.getIe() != null) {
            q.setParameter(Transportadora.P_IE, filter.getIe() + "%");
        }

        try {
            return q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
