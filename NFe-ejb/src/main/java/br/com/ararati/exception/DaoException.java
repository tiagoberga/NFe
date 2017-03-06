/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.exception;

import java.io.Serializable;
import javax.ejb.ApplicationException;

/**
 *
 * @author rogerio
 */
@ApplicationException(rollback = true)
public class DaoException extends BaseException implements Serializable {

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

}
