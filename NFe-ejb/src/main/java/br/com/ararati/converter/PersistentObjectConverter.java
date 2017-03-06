/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.converter;

import br.com.ararati.entity.PersistentObject;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rogerio
 */
@Stateless
public class PersistentObjectConverter implements Serializable {
    
    @PersistenceContext(unitName = "NFE-PU")
    private EntityManager em;

    public <T> T find(Class<T> entityClass, String id) {
        return (T) em.find(entityClass, id);
    }

    public  <T> T getAsObject(Class<T> returnType, String value) {
        if (returnType == null) {
            throw new NullPointerException("Trying to getAsObject with a null return type.");
        }
        if (value == null) {
            throw new NullPointerException("Trying to getAsObject with a null value.");
        }
        T result = find(returnType, value);
        return result;
    }

    public  String getAsString(Object value) {
        if (value instanceof PersistentObject) {
            PersistentObject result = (PersistentObject) value;
            return String.valueOf(result.getId());
        }
        return null;
    }

   
}
