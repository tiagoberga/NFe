/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.commons;

import br.com.ararati.entity.cadastros.Emitente;
import br.com.ararati.entity.AbstractEntity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author leandro
 */
@Entity
@Table(schema = "public", name = "email")
@NamedQueries({
    @NamedQuery(name = Email.FIND_ALL_MAIL_NOT_SENT, query = "SELECT o FROM Email o WHERE o.enviado = ?1")})
public class Email extends AbstractEntity {

    public static final String FIND_ALL_MAIL_NOT_SENT = "Email.findAllMailNotSent";
    public static final int P_ENVIADO = 1;
    public static final int P_EMPRESA = 2;

    @ManyToOne
    @NotNull(message = "Empresa Emitente é obrigatório")
    @JoinColumn(name = "emitente_id", nullable = false)
    private Emitente emitente;

    @NotNull(message = "{email.email.notNull}")
    private String email;
    @NotNull(message = "{email.assunto.notNull}")
    private String assunto;
    @Column(name = "email_resposta")
    private String emailResposta;
    @Lob
    @NotNull(message = "{email.mensagem.notNull}")
    private String mensagem;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataHora;
    private Boolean enviado = Boolean.FALSE;

    public Email() {
    }

    public Email(String email, String assunto, String mensagem, String emailResposta, Boolean enviado) {
        this.email = email;
        this.assunto = assunto;
        this.emailResposta = emailResposta;
        this.mensagem = mensagem;
        this.enviado = enviado;
    }

    public Email(String email, String assunto, String mensagem, String emailResposta, Boolean enviado, Emitente emitente) {
        this.email = email;
        this.assunto = assunto;
        this.emailResposta = emailResposta;
        this.mensagem = mensagem;
        this.enviado = enviado;
        this.emitente = emitente;
    }

    @PrePersist
    @PreUpdate
    public void prePersist() {
        dataHora = new Date();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public Boolean getEnviado() {
        return enviado;
    }

    public void setEnviado(Boolean enviado) {
        this.enviado = enviado;
    }

    public String getEmailResposta() {
        return emailResposta;
    }

    public void setEmailResposta(String emailResposta) {
        this.emailResposta = emailResposta;
    }

    public Emitente getEmitente() {
        return emitente;
    }

    public void setEmitente(Emitente emitente) {
        this.emitente = emitente;
    }

}
