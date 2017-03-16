/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.nfe.emissao;

import br.com.ararati.entity.cadastros.*;
import br.com.ararati.entity.AbstractEntity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * Informações do Registro de Aquisição de Cana
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "aquisicao_cana")
public class AquisicaoCana extends AbstractEntity {

    @ManyToOne
    @NotNull(message = "Empresa Emitente é obrigatório")
    @JoinColumn(name = "emitente_id", nullable = false)
    private Emitente emitente;

    @OneToOne
    @JoinColumn(name = "dados_nfe_id", nullable = false)
    private DadosNFe dadosNFe;

    // Identificação da safra
    @NotNull(message = "Identificação da Safra é obrigatório")
    @Column(length = 9, nullable = false)
    private String safra;
    // Mês e ano de referência 
    @NotNull(message = "Referência é obrigatório")
    @Column(length = 7, nullable = false)
    private String ref;
    // Valor dos Fornecimentos
    @NotNull(message = "Valor dos Fornecimentos é obrigatório")
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2)
    private BigDecimal vfor;
    // Valor Total da Dedução
    @NotNull(message = "Valor Total da Dedução é obrigatório")
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2)
    private BigDecimal vtotded;
    // Valor Líquido dos Fornecimentos
    @NotNull(message = "Valor Líquido dos Fornecimentos é obrigatório")
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2)
    private BigDecimal vliqfor;
    // Quantidade Total do Mês 
    @NotNull(message = "Quantidade Total do Mês é obrigatório")
    @DecimalMin(value = "0.0000")
    @Column(precision = 15, scale = 4)
    private BigDecimal qtotmes;
    // Quantidade Total Anterior
    @NotNull(message = "Quantidade Total Anterior é obrigatório")
    @DecimalMin(value = "0.0000")
    @Column(precision = 15, scale = 4)
    private BigDecimal qtotant;
    // Quantidade Total Geral
    @NotNull(message = "Quantidade Total Geral é obrigatório")
    @DecimalMin(value = "0.0000")
    @Column(precision = 15, scale = 4)
    private BigDecimal qtotger;

    @Valid
    @OneToMany(mappedBy = "aquisicaoCana", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<AquisicaoCanaDeducao> deducoes;

    @Valid
    @OneToMany(mappedBy = "aquisicaoCana", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<AquisicaoCanaFornecimento> fornecimentos;

    public AquisicaoCana() {
        this.deducoes = new ArrayList<>();
        this.fornecimentos = new ArrayList<>();
    }

    public Emitente getEmitente() {
        return emitente;
    }

    public void setEmitente(Emitente emitente) {
        this.emitente = emitente;
    }

    public DadosNFe getDadosNFe() {
        return dadosNFe;
    }

    public void setDadosNFe(DadosNFe dadosNFe) {
        this.dadosNFe = dadosNFe;
    }

    public String getSafra() {
        return safra;
    }

    public void setSafra(String safra) {
        this.safra = safra;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public BigDecimal getVfor() {
        return vfor;
    }

    public void setVfor(BigDecimal vfor) {
        this.vfor = vfor;
    }

    public BigDecimal getVtotded() {
        return vtotded;
    }

    public void setVtotded(BigDecimal vtotded) {
        this.vtotded = vtotded;
    }

    public BigDecimal getVliqfor() {
        return vliqfor;
    }

    public void setVliqfor(BigDecimal vliqfor) {
        this.vliqfor = vliqfor;
    }

    public BigDecimal getQtotmes() {
        return qtotmes;
    }

    public void setQtotmes(BigDecimal qtotmes) {
        this.qtotmes = qtotmes;
    }

    public BigDecimal getQtotant() {
        return qtotant;
    }

    public void setQtotant(BigDecimal qtotant) {
        this.qtotant = qtotant;
    }

    public BigDecimal getQtotger() {
        return qtotger;
    }

    public void setQtotger(BigDecimal qtotger) {
        this.qtotger = qtotger;
    }

    public List<AquisicaoCanaDeducao> getDeducoes() {
        return deducoes;
    }

    public void setDeducoes(List<AquisicaoCanaDeducao> deducoes) {
        this.deducoes = deducoes;
    }

    public List<AquisicaoCanaFornecimento> getFornecimentos() {
        return fornecimentos;
    }

    public void setFornecimentos(List<AquisicaoCanaFornecimento> fornecimentos) {
        this.fornecimentos = fornecimentos;
    }

}
