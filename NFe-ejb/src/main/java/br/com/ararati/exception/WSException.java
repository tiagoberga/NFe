/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.exception;

/**
 *
 * @author tiago
 */
public class WSException extends BaseException {

    public WSException(String message) {
        super(message);
    }

    public WSException(String message, Throwable cause) {
        super(message, cause);
    }
}
