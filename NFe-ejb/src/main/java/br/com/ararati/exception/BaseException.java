/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.exception;

import java.io.Serializable;

/**
 *
 * @author rogerio
 */
public class BaseException extends Exception implements Serializable {

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

}
