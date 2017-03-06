/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.nfe.emissao;

import br.com.ararati.entity.cadastros.*;
import br.com.ararati.entity.AbstractEntity;
import br.com.ararati.enums.Z.NFeTipoOrigemProcesso;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * Informações Adicionais da NF-e
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "informacoes_adicionais_processo_referenciado")
public class InformacoesAdicionaisProcessoReferenciado extends AbstractEntity {

    @ManyToOne
    @NotNull(message = "Empresa Emitente é obrigatório")
    @JoinColumn(name = "emitente_id", nullable = true)
    private Emitente emitente;

    @ManyToOne
    @JoinColumn(name = "informacoes_adicionais_id", nullable = false)
    private InformacoesAdicionais informacoesAdicionais;

    // Identificador do processo ou ato concessório
    @NotNull(message = "Identificação é obrigatório")
    @Length(min = 1, max = 60, message = "Identificação deve conter entre {min} e {max} caracteres")
    @Column(length = 60, nullable = false)
    private String nproc;
    // Indicador da origem do processo
    @NotNull(message = "Indicador é obrigatório")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NFeTipoOrigemProcesso indproc;

    public Emitente getEmitente() {
        return emitente;
    }

    public void setEmitente(Emitente emitente) {
        this.emitente = emitente;
    }

    public InformacoesAdicionais getInformacoesAdicionais() {
        return informacoesAdicionais;
    }

    public void setInformacoesAdicionais(InformacoesAdicionais informacoesAdicionais) {
        this.informacoesAdicionais = informacoesAdicionais;
    }

    public String getNproc() {
        return nproc;
    }

    public void setNproc(String nproc) {
        this.nproc = nproc;
    }

    public NFeTipoOrigemProcesso getIndproc() {
        return indproc;
    }

    public void setIndproc(NFeTipoOrigemProcesso indproc) {
        this.indproc = indproc;
    }

}
