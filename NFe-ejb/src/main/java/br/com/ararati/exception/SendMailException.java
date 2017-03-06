/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.exception;

/**
 *
 * @author leandro
 */
public class SendMailException extends BaseException {

    public SendMailException(String message) {
        super(message);
    }

    public SendMailException(String message, Throwable cause) {
        super(message, cause);
    }
}
