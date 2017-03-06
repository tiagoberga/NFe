/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.view.converter;

import br.com.ararati.converter.PersistentObjectConverter;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

/**
 *
 * @author rogerio
 */
@Named
@SessionScoped
public class GpoConverter implements Converter, Serializable {

    @EJB
    private PersistentObjectConverter persistentObjectConverter;

    @Override
    @SuppressWarnings("unchecked")
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return persistentObjectConverter.getAsObject(getClazz(context, component), value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return persistentObjectConverter.getAsString(value);
    }

    // Gets the class corresponding to the context in jsf page
    @SuppressWarnings("unchecked")
    private Class getClazz(FacesContext facesContext, UIComponent component) {
        return component.getValueExpression("value").getType(facesContext.getELContext());
    }
}
