/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.jsf;

import java.util.Map;
import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

/**
 *
 * @author Josef Hirschbeck
 * @since 23.11.2010, 08:46:58
 */
@SuppressWarnings("unchecked")
public class ViewContext implements Context {

    @Override
    public Class getScope() {
        return ViewScoped.class;
    }

    private Map getViewMap() {
        FacesContext fctx = FacesContext.getCurrentInstance();
        UIViewRoot viewRoot = fctx.getViewRoot();
        return viewRoot.getViewMap(true);
    }

    @Override
    public <T> T get(Contextual<T> contextual, CreationalContext<T> creationalContext) {
        Bean bean = (Bean) contextual;
        Map viewMap = getViewMap();
        if (viewMap.containsKey(bean.getName())) {
            return (T) viewMap.get(bean.getName());
        } else {
            T t = (T) bean.create(creationalContext);
            viewMap.put(bean.getName(), t);
            return t;
        }
    }

    @Override
    public <T> T get(Contextual<T> contextual) {
        Bean bean = (Bean) contextual;
        Map viewMap = getViewMap();
        if (viewMap.containsKey(bean.getName())) {
            return (T) viewMap.get(bean.getName());
        } else {
            return null;
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
