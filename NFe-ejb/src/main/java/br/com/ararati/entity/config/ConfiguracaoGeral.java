/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.config;

import br.com.ararati.entity.AbstractEntity;
import br.com.ararati.entity.cadastros.Emitente;
import br.com.ararati.enums.B.NFeTipoProcessoEmissao;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.eclipse.persistence.annotations.Index;

/**
 *
 * @author tiago
 */
@Entity
@Table(schema = "config", name = "configuracao_geral")
@Index(columnNames = {"emitente_id"})
@NamedQueries(
        @NamedQuery(name = ConfiguracaoGeral.Q_FIND_BY_EMPRESA, query = "SELECT p FROM ConfiguracaoGeral p WHERE p.emitente = ?1"))
@Cacheable(false)
public class ConfiguracaoGeral extends AbstractEntity {

    public static final String Q_FIND_BY_EMPRESA = "ConfiguracaoGeral.findByEmpresa";
    public static final Integer P_EMITENTE = 1;

        @ManyToOne
    @NotNull(message = "Empresa Emitente é obrigatório")
    @JoinColumn(name = "emitente_id", nullable = false)
    private Emitente emitente;

    @Lob
    @Column(name = "logotipo_arquivo")
    private byte[] logotipoArquivo;
    @NotNull(message = "{nfeparametros.schemasNFeCaminho.NotNull}")
    @Column(name = "schemas_nfe_caminho")
    private String schemasNFeCaminho;
    @Column(name = "ultimo_nsu", length = 15)
    private String ultimoNSU;
    @Column(name = "maximo_nsu", length = 15)
    private String maximoNSU;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_processo_emissao", length = 40)
    private NFeTipoProcessoEmissao processoEmissao = NFeTipoProcessoEmissao.CONTRIBUINTE_FISCO;
    @Column(name = "enviar_email_transmissao", nullable = false)
    private boolean enviarEmailTransmissao = false;

    public byte[] getLogotipoArquivo() {
        return logotipoArquivo;
    }

    public void setLogotipoArquivo(byte[] logotipoArquivo) {
        this.logotipoArquivo = logotipoArquivo;
    }

    public String getUltimoNSU() {
        return ultimoNSU;
    }

    public void setUltimoNSU(String ultimoNSU) {
        this.ultimoNSU = ultimoNSU;
    }

    public String getMaximoNSU() {
        return maximoNSU;
    }

    public void setMaximoNSU(String maximoNSU) {
        this.maximoNSU = maximoNSU;
    }

    public NFeTipoProcessoEmissao getProcessoEmissao() {
        return processoEmissao;
    }

    public void setProcessoEmissao(NFeTipoProcessoEmissao processoEmissao) {
        this.processoEmissao = processoEmissao;
    }

    public boolean getEnviarEmailTransmissao() {
        return enviarEmailTransmissao;
    }

    public void setEnviarEmailTransmissao(boolean enviarEmailTransmissao) {
        this.enviarEmailTransmissao = enviarEmailTransmissao;
    }

    public String getSchemasNFeCaminho() {
        return schemasNFeCaminho;
    }

    public void setSchemasNFeCaminho(String schemasNFeCaminho) {
        this.schemasNFeCaminho = schemasNFeCaminho;
    }

}
