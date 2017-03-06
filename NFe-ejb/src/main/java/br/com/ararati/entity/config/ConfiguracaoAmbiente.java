/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.config;

import br.com.ararati.entity.AbstractEntity;
import br.com.ararati.entity.cadastros.Emitente;
import br.com.ararati.enums.B.NFeTipoAmbiente;
import br.com.ararati.tools.EncodeDecodeKey;
import java.io.UnsupportedEncodingException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 * @author tiago
 */
@Entity
@Table(schema = "config", name = "configuracao_ambiente", uniqueConstraints = @UniqueConstraint(columnNames = {"emitente_id"}))
@NamedQuery(name = ConfiguracaoAmbiente.Q_FIND_BY_EMPRESA, query = "SELECT p FROM ConfiguracaoAmbiente p WHERE p.emitente = ?1")
public class ConfiguracaoAmbiente extends AbstractEntity {

    public static final String Q_FIND_BY_EMPRESA = "ConfiguracaoAmbiente.findByEmpresa";
    public static final Integer P_EMITENTE = 1;
    public static final Integer P_TIPO_AMBIENTE = 2;

    @ManyToOne
    @NotNull(message = "Empresa Emitente é obrigatório")
    @JoinColumn(name = "emitente_id", nullable = false)
    private Emitente emitente;

    @Lob
    @NotNull(message = "Arquivo Certificado é obrigatório")
    @Column(name = "certificado_digital_arquivo")
    private byte[] certificadoArquivo;
    @Column(name = "certificado_digital_senha")
    private byte[] certificadoSenha;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_ambiente", length = 40)
    private NFeTipoAmbiente tipoDeAmbiente = NFeTipoAmbiente.HOMOLOGACAO;

    public String getKeyDecode() throws UnsupportedEncodingException {
        return EncodeDecodeKey.makeDecodeKey(this.certificadoSenha);
    }

    public byte[] getKeyEncode(String certificadoSenha) {
        return EncodeDecodeKey.makeEncodeKey(certificadoSenha);
    }

    public byte[] getCertificadoArquivo() {
        return certificadoArquivo;
    }

    public void setCertificadoArquivo(byte[] certificadoArquivo) {
        this.certificadoArquivo = certificadoArquivo;
    }

    public byte[] getCertificadoSenha() {
        return certificadoSenha;
    }

    public void setCertificadoSenha(byte[] certificadoSenha) {
        this.certificadoSenha = certificadoSenha;
    }

    public NFeTipoAmbiente getTipoDeAmbiente() {
        return tipoDeAmbiente;
    }

    public void setTipoDeAmbiente(NFeTipoAmbiente tipoDeAmbiente) {
        this.tipoDeAmbiente = tipoDeAmbiente;
    }

    public Emitente getEmitente() {
        return emitente;
    }

    public void setEmitente(Emitente emitente) {
        this.emitente = emitente;
    }

}
