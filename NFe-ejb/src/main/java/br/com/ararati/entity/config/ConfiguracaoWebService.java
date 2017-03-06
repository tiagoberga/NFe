/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.config;

import br.com.ararati.entity.AbstractEntity;
import br.com.ararati.entity.cadastros.Emitente;
import br.com.ararati.enums.B.NFeTipoAmbiente;
import br.com.ararati.enums.B.NFeTipoEmissao;
import br.com.ararati.enums.commons.NFeTipoServico;
import br.com.ararati.enums.commons.NFeTipoUFWebService;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

/**
 *
 * @author augusto
 */
@Entity
@Table(schema = "config", name = "configuracao_webservice", uniqueConstraints = @UniqueConstraint(columnNames = {"estado_sigla", "tipo_servico_nfe", "versao"}))
public class ConfiguracaoWebService extends AbstractEntity {

    // CONSTANTES
    public static final int P_ATIVO = 1;
    public static final int P_TIPO_AMBIENTE = 2;
    public static final int P_ESTADO_SIGLA = 3;
    public static final int P_TIPO_SERVICO = 4;
    public static final int P_TIPO_EMISSAO = 5;
    public static final int P_VERSAO = 6;

    @NotNull(message = "Ambiente é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_webservice", nullable = false)
    private NFeTipoAmbiente tipoAmbiente;
    @NotNull(message = "Sigla de Estado é obrigatório")
    @Column(name = "estado_sigla", nullable = false)
    @Enumerated(EnumType.STRING)
    private NFeTipoUFWebService estadoSigla;
    @NotNull(message = "Tipo de Serviço é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_servico_nfe", nullable = false)
    private NFeTipoServico tipoServicoNfe;
    @NotNull(message = "Tipo de Emissão é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_emissao", length = 40, nullable = false)
    private NFeTipoEmissao tpEmis;
    @NotNull(message = "Versão é obrigatório")
    @Column(name = "versao", nullable = false)
    private String versao;
    @NotNull(message = "Ativo/Inativo é obrigatório")
    @Column(name = "ativo", nullable = false)
    private Boolean ativo;
    @NotNull(message = "URL é obrigatório")
    @URL(message = "URL Inválida, ex: http://ararati.com.br/home")
    @Column(name = "url", nullable = false)
    private String url;

    public NFeTipoAmbiente getTipoAmbiente() {
        return tipoAmbiente;
    }

    public void setTipoAmbiente(NFeTipoAmbiente tipoAmbiente) {
        this.tipoAmbiente = tipoAmbiente;
    }

    public NFeTipoUFWebService getEstadoSigla() {
        return estadoSigla;
    }

    public void setEstadoSigla(NFeTipoUFWebService estadoSigla) {
        this.estadoSigla = estadoSigla;
    }

    public NFeTipoServico getTipoServicoNfe() {
        return tipoServicoNfe;
    }

    public void setTipoServicoNfe(NFeTipoServico tipoServicoNfe) {
        this.tipoServicoNfe = tipoServicoNfe;
    }

    public NFeTipoEmissao getTpEmis() {
        return tpEmis;
    }

    public void setTpEmis(NFeTipoEmissao tpEmis) {
        this.tpEmis = tpEmis;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    
}
