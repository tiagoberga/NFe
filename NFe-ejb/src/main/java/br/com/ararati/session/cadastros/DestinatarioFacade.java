/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.session.cadastros;

import br.com.ararati.entity.cadastros.Destinatario;
import br.com.ararati.entity.cadastros.Emitente;
import br.com.ararati.entity.cadastros.Produto;
import br.com.ararati.filter.cadastros.DestinatarioFilter;
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
public class DestinatarioFacade extends AbstractFacade<Destinatario> {

    @PersistenceContext(unitName = "NFE-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DestinatarioFacade() {
        super(Destinatario.class);
    }

    public List<Destinatario> findBy(String stringToSearch, Emitente emitente) {
        StringBuilder sql = new StringBuilder()
                .append(" SELECT e ")
                .append(" FROM Destinatario e ")
                .append(" WHERE e.emitente = ?").append(Destinatario.P_EMITENTE)
                .append(" AND (UPPER(e.documento) LIKE UPPER(?").append(Destinatario.P_DOCUMENTO).append(")")
                .append(" OR UPPER(e.xnome) LIKE UPPER(?").append(Destinatario.P_XNOME).append(")")
                .append(" ) ORDER BY e.xnome");
        TypedQuery<Destinatario> q = getEntityManager().createQuery(sql.toString(), Destinatario.class).setMaxResults(100);
        q.setParameter(Destinatario.P_EMITENTE, emitente);
        q.setParameter(Destinatario.P_DOCUMENTO, "%" + stringToSearch + "%");
        q.setParameter(Destinatario.P_XNOME, "%" + stringToSearch + "%");

        try {
            return q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Destinatario> findByFilter(DestinatarioFilter filter) {
        StringBuilder sql = new StringBuilder()
                .append(" SELECT d ")
                .append(" FROM Destinatario d ")
                .append(" WHERE d.emitente = ?").append(Destinatario.P_EMITENTE);

        if (filter.getDocumento() != null) {
            sql.append(" AND d.documento LIKE ?").append(Destinatario.P_DOCUMENTO);
        }

        if (filter.getDocumento() != null) {
            sql.append(" AND UPPER(d.xnome) = UPPER(?").append(Destinatario.P_XNOME).append(")");
        }

        sql.append(" ORDER BY d.documento ");

        TypedQuery<Destinatario> q = getEntityManager().createQuery(sql.toString(), Destinatario.class).setMaxResults(100);
        q.setParameter(Destinatario.P_EMITENTE, filter.getEmitente());

        if (filter.getDocumento() != null) {
            q.setParameter(Destinatario.P_DOCUMENTO, filter.getDocumento());
        }

        if (filter.getDocumento() != null) {
            q.setParameter(Destinatario.P_XNOME, "%" + filter.getXnome().toUpperCase() + "%");
        }

        try {
            return q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

}
