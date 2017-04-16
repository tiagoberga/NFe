/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.autenticacao;

import java.util.Arrays;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author Leandroc
 */
public enum Role {

    ADMIN("ADMIN"),
    COMUM("COMUM");

    private String descricao;

    private Role(String descricao) {

        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static List<Role> valuesAsList() {
        return Arrays.asList(Role.values());
    }

    @Override
    public String toString() {
        return getDescricao();
    }   
}
