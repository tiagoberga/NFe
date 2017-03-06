/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity;

/**
 *
 * @author tiago
 */
public interface PersistentObject {

    public String getId();

    public void setId(String id);

    public boolean isNew();
}