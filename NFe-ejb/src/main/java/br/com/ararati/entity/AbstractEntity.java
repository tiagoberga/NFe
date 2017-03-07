/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * @author tiago viado
 */
@MappedSuperclass
public abstract class AbstractEntity implements PersistentObject, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull(message = "ID é obrigatório")
    @Column(length = 40, name = "id")
    private String id = UUID.randomUUID().toString();

    @Transient
    private boolean newObject = true;

    @PostLoad
    public void postLoader() {
        newObject = false;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @JsonIgnore
    @Override
    public boolean isNew() {
        return newObject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof PersistentObject)) {
            return false;
        }

        PersistentObject other = (PersistentObject) o;

        // if the id is missing, return false
        if (id == null) {
            return false;
        }

        // equivalence by id
        return id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        if (id != null) {
            return id.hashCode();
        } else {
            return super.hashCode();
        }
    }

}
