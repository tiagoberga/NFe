/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.atenticacao;

import br.com.ararati.enums.Role;
import br.com.ararati.entity.AbstractEntity;
import br.com.ararati.entity.cadastros.Emitente;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Leandroc
 */
@Entity
@Table(name = "usuario")
public class Usuario extends AbstractEntity {

    @Column(name = "login", length = 30, unique = true)
    @NotNull(message = "login é obrigatório")
    private String login;

    @Column(name = "senha")
    private String senha;

    @NotNull(message = "A opção ativo é obrigatório")
    @Column(name = "ativo", nullable = false)
    private boolean ativo = true;

    @NotNull(message = "A opção bloqueado é obrigatório")
    @Column(name = "bloqueado", nullable = false)
    private boolean bloqueado = false;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_expiracao")
    private Date dataExpiracao;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Role> userRoles = new ArrayList<>();

    @ManyToOne
    @NotNull(message = "Empresa Emitente é obrigatório")
    @JoinColumn(name = "emitente_id", nullable = false)
    private Emitente emitente;

    public boolean isUsuarioExpirado() {
        return (dataExpiracao != null ? dataExpiracao : new Date()).after(new Date());
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Role> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<Role> userRoles) {
        this.userRoles = userRoles;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public Date getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(Date dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    public Emitente getEmitente() {
        return emitente;
    }

    public void setEmitente(Emitente emitente) {
        this.emitente = emitente;
    }

}
