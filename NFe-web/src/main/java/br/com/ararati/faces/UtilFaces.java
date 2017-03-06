/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.faces;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author tiago
 */
public class UtilFaces implements Serializable {

    private static FacesContext context;

    public static void showDialogMessageInfo(String message) {
        RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", message));
    }

    public static void showDialogMessageWarn(String message) {
        RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", message));
    }

    public static void showDialogMessageFatal(String message) {
        RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal", message));
    }

    public static void showDialogMessageError(String message) {
        RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", message));
    }
//
//    public static void showMessageInfo(String message) {
//        context = FacesContext.getCurrentInstance();
//        context.addMessage(null, new FacesMessage(message));
//    }
//
//    public static void showMessageWarn(String message) {
//        context = FacesContext.getCurrentInstance();
//        context.addMessage(null, new FacesMessage(message));
//    }
//
//    public static void showMessageFatal(String message) {
//        context = FacesContext.getCurrentInstance();
//        context.addMessage(null, new FacesMessage(message));
//    }
//
//    public static void showMessageError(String message) {
//        context = FacesContext.getCurrentInstance();
//        context.addMessage(null, new FacesMessage(message));
//    }

}
