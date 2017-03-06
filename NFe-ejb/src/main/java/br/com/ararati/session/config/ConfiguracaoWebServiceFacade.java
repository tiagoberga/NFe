/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.session.config;

import br.com.ararati.entity.config.ConfiguracaoWebService;
import br.com.ararati.exception.WSException;
import br.com.ararati.filter.WebServiceFilter;
import br.com.ararati.session.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author augusto
 */
@Stateless
public class ConfiguracaoWebServiceFacade extends AbstractFacade<ConfiguracaoWebService> {

    @PersistenceContext(unitName = "NFE-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConfiguracaoWebServiceFacade() {
        super(ConfiguracaoWebService.class);
    }


    /**
     * Método responsável por encontrar um Web Service cadastrado de acordo com
     * filtro de buscas.
     *
     * @param filter WebServiceFilter
     * @return WebService
     * @throws WSException caso não encontre um web service
     */
    public ConfiguracaoWebService findWebService(WebServiceFilter filter) throws WSException {
        StringBuilder sql = new StringBuilder()
                .append("SELECT o FROM WebService o ")
                .append("WHERE o.estadoSigla = :pEstadoSigla ")
                .append("AND o.tipoAmbiente = :pTipoAmbiente ")
                .append("AND o.tpEmis = :pTpEmis ")
                .append("AND o.tipoServicoNfe = :pTipoServicoNfe ")
                .append("AND o.ativo = :pAtivo ")
                .append("ORDER BY o.versao DESC ");
        try {
            TypedQuery<ConfiguracaoWebService> q = em.createQuery(sql.toString(), ConfiguracaoWebService.class).setMaxResults(1); 
            q.setParameter("pEstadoSigla", filter.getEstadoSigla());
            q.setParameter("pTipoAmbiente", filter.getTipoAmbiente());
            q.setParameter("pTpEmis", filter.getTpEmis());
            q.setParameter("pTipoServicoNfe", filter.getTipoServicoNfe());
            q.setParameter("pAtivo", Boolean.TRUE);

            return q.getSingleResult();
        } catch (NoResultException e) {
            throw new WSException("Nenhum web service foi encontrado para a operação", e);
        }
    }

    /**
     * Método responsável por encontrar Web Services de acordo com filtro de
     * busca.
     *
     * @param filter WebServiceFilter
     * @return List<WebService>
     * @throws WSException caso não encontre um web service
     */
    public List<ConfiguracaoWebService> filter(WebServiceFilter filter) throws WSException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT o FROM WebService o WHERE 1 = 1 ");

        if (filter.getEstadoSigla() != null) {
            sql.append(" AND o.estadoSigla = :pEstadoSigla");
        }

        if (filter.getTipoAmbiente() != null) {
            sql.append(" AND o.tipoAmbiente = :pTipoAmbiente");
        }

        if (filter.getTpEmis() != null) {
            sql.append(" AND o.tpEmis = :pTpEmis");
        }

        if (filter.getTipoServicoNfe() != null) {
            sql.append(" AND o.tipoServicoNfe = :pTipoServicoNfe");
        }

        if (filter.getVersao() != null) {
            sql.append(" AND o.versao = :pVersao ");
        }

        try {

            TypedQuery<ConfiguracaoWebService> q = em.createQuery(sql.toString(), ConfiguracaoWebService.class);

            if (filter.getEstadoSigla() != null) {
                q.setParameter("pEstadoSigla", filter.getEstadoSigla());
            }

            if (filter.getTipoAmbiente() != null) {
                q.setParameter("pTipoAmbiente", filter.getTipoAmbiente());
            }

            if (filter.getTpEmis() != null) {
                q.setParameter("pTpEmis", filter.getTpEmis());
            }

            if (filter.getTipoServicoNfe() != null) {
                q.setParameter("pTipoServicoNfe", filter.getTipoServicoNfe());
            }

            if (filter.getVersao() != null) {
                q.setParameter("pVersao", filter.getVersao());
            }

            return q.getResultList();
        } catch (NoResultException e) {
            throw new WSException("Nenhum web service foi encontrado para a operação", e);
        } catch (Exception e) {
            throw new WSException(e.getMessage());
        }
    }

}
