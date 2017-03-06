/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.session;

import br.com.ararati.entity.PersistentObject;
import br.com.ararati.exception.DaoException;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.postgresql.util.PSQLException;

/**
 *
 * @author tiago
 */
public abstract class AbstractFacade<T> {

    
    private final Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    private void create(T entity) throws DaoException {
        //EntityTransaction tx = getEntityManager().getTransaction();
        try {
            //tx.begin();
            getEntityManager().persist(entity);
            getEntityManager().flush();
            //tx.commit();
        } catch (ConstraintViolationException ex) {
            StringBuilder message = new StringBuilder();
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
            for (ConstraintViolation constraintViolation : constraintViolations) {
                message = message.append(constraintViolation.getMessage()).append("\n");
            }
            throw new DaoException(message.toString(), ex);
        } catch (PersistenceException ex) {
            throw getPSQLException(ex);
        } catch (Exception ex) {
            //tx.rollback();
            throw new DaoException(ex.getMessage(), ex);
        } finally {
            //getEntityManager().close();
        }
    }

    /**
     * Método salva/edita uma lista de objetos.
     *
     * @param entities T
     * @throws DaoException erro ao salvar/editar.
     */
    public void save(List<T> entities) throws DaoException {
        for (T entity : entities) {
            save(entity);
        }
    }

    /**
     *
     * @param entity
     * @throws DaoException
     */
    public void save(T entity) throws DaoException {
        if (entity instanceof PersistentObject) {
            PersistentObject obj = (PersistentObject) entity;
            if (obj.isNew()) {
                create(entity);
            } else {
                edit(entity);
            }
        }
    }

    private void edit(T entity) throws DaoException {
        //EntityTransaction tx = getEntityManager().getTransaction();
        try {
            //tx.begin();
            getEntityManager().merge(entity);
            getEntityManager().flush();
            //tx.commit();
        } catch (ConstraintViolationException ex) {
            StringBuilder message = new StringBuilder();
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
            for (ConstraintViolation constraintViolation : constraintViolations) {
                message = message.append(constraintViolation.getMessage()).append("\n");
            }
            throw new DaoException(message.toString(), ex);
        } catch (PersistenceException ex) {
            throw getPSQLException(ex);
        } catch (Exception ex) {
            //tx.rollback();
            throw new DaoException(ex.getMessage(), ex);
        } finally {
            //getEntityManager().close();
        }
    }

    public void remove(T entity) throws DaoException {
        //EntityTransaction tx = getEntityManager().getTransaction();
        try {
            //tx.begin();
            getEntityManager().remove(getEntityManager().merge(entity));
            getEntityManager().flush();
            //tx.commit();
        } catch (ConstraintViolationException ex) {
            StringBuilder message = new StringBuilder();
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
            for (ConstraintViolation constraintViolation : constraintViolations) {
                message = message.append(constraintViolation.getMessage()).append("\n");
            }
            throw new DaoException(message.toString(), ex);
        } catch (PersistenceException ex) {
            throw getPSQLException(ex);
        } catch (Exception ex) {
            //tx.rollback();
            throw new DaoException(ex.getMessage(), ex);
        } finally {
            //getEntityManager().close();
        }
    }

    public T find(Object id) {
        try {
            if (id != null) {
                return getEntityManager().find(entityClass, id);
            }
        } catch (NullPointerException e) {
            return null;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    @SuppressWarnings("unchecked")
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    private static DaoException getPSQLException(Throwable ex) {
        while (ex != null) {
            if (ex instanceof PSQLException) {
                PSQLException psqlE = (PSQLException) ex;
                //Erros do postgres http://www.postgresql.org/docs/9.4/static/errcodes-appendix.html
                //"23505" Unique key violation
                if (psqlE.getSQLState().equals("23505")) {
                    return new DaoException("Valor(es) já cadastrado(s): " + "\n" + takeFieldAndValueUniqueConstraint(psqlE.getServerErrorMessage().getDetail()), ex);
                }
                //"23502" Not Null violation
                if (psqlE.getSQLState().equals("23502")) {
                    return new DaoException(psqlE.getServerErrorMessage().getMessage(), ex);
                }
                //"23503" Foring key violation
                if (psqlE.getSQLState().equals("23503")) {
                    return new DaoException("Registro(s) não pode(m) ser removido(s) ou alterado(s): " + "\n" + psqlE.getServerErrorMessage().getMessage(), ex);
                }

                return new DaoException(psqlE.getServerErrorMessage().getMessage(), ex);
            }
            ex = ex.getCause();
        }
        return new DaoException("Exceção não encontrada.");
    }

    private static String takeFieldAndValueUniqueConstraint(final String string) {
        String[] f, v;
        Pattern p = Pattern.compile("\\(([^\\)]+)\\)");
        Matcher m = p.matcher(string);

        m.find(); //Busca
        f = m.group(1).split(",");
        m.find(); //Busca
        v = m.group(1).split(",");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < f.length; i++) {
            sb.append(f[i]);
            sb.append(": ");
            sb.append(v[i]);
            sb.append("\n");
        }
        return sb.toString();
    }

}
