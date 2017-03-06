/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.exception;

/**
 *
 * @author tiago
 */
public class NFeException extends BaseException {

    public NFeException(String message) {
        super(message);
    }

    public NFeException(String message, Throwable cause) {
        super(message, cause);
    }
}
