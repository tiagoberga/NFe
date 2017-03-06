/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.config;

import br.com.ararati.entity.AbstractEntity;
import br.com.ararati.entity.cadastros.Emitente;
import br.com.ararati.enums.B.NFeTipoAmbiente;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 * @author tiago
 */
@Entity
@Table(schema = "config", name = "configuracao_numeracao", uniqueConstraints
        = @UniqueConstraint(columnNames = {"emitente_id", "tipo_ambiente", "serie", "versao_nfe"})
)
@NamedQueries({
    @NamedQuery(name = ConfiguracaoNumeracao.Q_FIND_BY_EMPRESA,
            query = "SELECT p "
            + " FROM ConfiguracaoNumeracao p "
            + " WHERE p.emitente = ?1 "),
    @NamedQuery(name = ConfiguracaoNumeracao.Q_FIND_BY_EMPRESA_AMBIENTE,
            query = "SELECT p "
            + " FROM ConfiguracaoNumeracao p "
            + " WHERE p.emitente = ?1 "
            + " AND p.tipoAmbiente = ?2 "),
    @NamedQuery(name = ConfiguracaoNumeracao.Q_FIND_BY_EMPRESA_AMBIENTE_SERIE,
            query = "SELECT p "
            + " FROM ConfiguracaoNumeracao p "
            + " WHERE p.emitente = ?1 "
            + " AND p.tipoAmbiente = ?2 "
            + " AND p.serie = ?3"),
    @NamedQuery(name = ConfiguracaoNumeracao.Q_FIND_SERIES_BY_EMPRESA_AMBIENTE,
            query = "SELECT p.serie "
            + " FROM ConfiguracaoNumeracao p "
            + " WHERE p.emitente = ?1 "
            + " AND p.tipoAmbiente = ?2")
})
public class ConfiguracaoNumeracao extends AbstractEntity {

    public static final String Q_FIND_BY_EMPRESA = "ConfiguracaoNumeracao.findByEmpresa";
    public static final String Q_FIND_SERIES_BY_EMPRESA_AMBIENTE = "ConfiguracaoNumeracao.findSeriesByEmpresaAmbiente";
    public static final String Q_FIND_BY_EMPRESA_AMBIENTE = "ConfiguracaoNumeracao.findByEmpresaAmbiente";
    public static final String Q_FIND_BY_EMPRESA_AMBIENTE_SERIE = "ConfiguracaoNumeracao.findByEmpresaAmbienteSerie";
    public static final Integer P_EMITENTE = 1;
    public static final Integer P_TIPO_AMBIENTE = 2;
    public static final Integer P_SERIE = 2;
    public static final Integer P_ULTIMA_NFE = 4;
    public static final Integer P_VERSAO_NFE = 5;

    @ManyToOne
    @NotNull(message = "Empresa Emitente é obrigatório")
    @JoinColumn(name = "emitente_id", nullable = false)
    private Emitente emitente;

    @NotNull(message = "{nfeparametrosNum.tipoAmbiente.NotNull}")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_ambiente", nullable = false)
    private NFeTipoAmbiente tipoAmbiente;
    @NotNull(message = "{nfeparametrosNum.serie.NotNull}")
    @Column(name = "serie")
    private String serie;
    @NotNull(message = "{nfeparametrosNum.ultimaNFe.NotNull}")
    @Column(name = "ultima_nfe", length = 9)
    private String ultimaNFe;
    @NotNull(message = "{nfeparametrosNum.versaoNFe.NotNull}")
    @Column(name = "versao_nfe")
    private String versaoNFe;

    public NFeTipoAmbiente getTipoAmbiente() {
        return tipoAmbiente;
    }

    public void setTipoAmbiente(NFeTipoAmbiente tipoAmbiente) {
        this.tipoAmbiente = tipoAmbiente;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getUltimaNFe() {
        return ultimaNFe;
    }

    public void setUltimaNFe(String ultimaNFe) {
        this.ultimaNFe = ultimaNFe;
    }

    public String getVersaoNFe() {
        return versaoNFe;
    }

    public void setVersaoNFe(String versaoNFe) {
        this.versaoNFe = versaoNFe;
    }

    public Emitente getEmitente() {
        return emitente;
    }

    public void setEmitente(Emitente emitente) {
        this.emitente = emitente;
    }

    
}
