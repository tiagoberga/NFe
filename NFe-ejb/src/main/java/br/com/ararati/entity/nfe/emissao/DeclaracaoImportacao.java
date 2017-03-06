/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.nfe.emissao;

import br.com.ararati.entity.cadastros.*;
import br.com.ararati.entity.AbstractEntity;
import br.com.ararati.enums.I.NFeTipoImportacao;
import br.com.ararati.enums.I.NFeTipoViaTransporte;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * Identificação do Destinatário da Nota Fiscal eletrônica
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "declaracao_importacao")
public class DeclaracaoImportacao extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "emitente_id", nullable = false)
    private Emitente emitente;

    @ManyToOne
    @JoinColumn(name = "detalhamento_produto_servico_id", nullable = false)
    private DetalhamentoProdutoServico detalhamentoProdutoServico;

    // Número do Documento de Importação (DI, DSI, DIRE, ...)
    @NotNull(message = "Número do Documento de Importação é obrigatório")
    @Length(min = 1, max = 12, message = "Número do Documento de Importação deve conter entre {min} e {max} caracteres")
    @Column(length = 12, nullable = false)
    private String ndi;
    // Data de Registro do documento
    @NotNull(message = "Data de Registro é obrigatório")
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date ddi;
    // Local de desembaraço
    @NotNull(message = "Local de Desembaraço é obrigatório")
    @Length(min = 1, max = 60, message = "Local de Desembaraço deve conter entre {min} e {max} caracteres")
    @Column(length = 60, nullable = false)
    private String xlocdesemb;
    // Sigla da UF onde ocorreu o Desembaraço Aduaneiro
    @NotNull(message = "UF onde ocorreu o Desembaraço é obrigatório")
    @Column(length = 2, nullable = false)
    private String ufdesemb;
    // Data do Desembaraço Aduaneiro
    @NotNull(message = "Data do Desembaraço é obrigatório")
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date ddesemb;
    // Via de transporte internacional informada na Declaração de Importação (DI)
    @NotNull(message = "Via de Transporte é obrigatório")
    @Column(length = 2, nullable = false)
    @Enumerated(EnumType.STRING)
    private NFeTipoViaTransporte tpviatransp;
    // Valor da AFRMM - Adicional ao Frete para Renovação da Marinha Mercante
    @DecimalMin(value = "0.00")
    @Column(precision = 13, scale = 2, nullable = true)
    private BigDecimal vafrmm;
    // Forma de importação quanto a intermediação
    @NotNull(message = "Forma de Intermediação é obrigatório")
    @Column(length = 1, nullable = false)
    @Enumerated(EnumType.STRING)
    private NFeTipoImportacao tpintermedio;
    // CNPJ do adquirente ou do encomendante 
    @Column(length = 14, nullable = true)
    private String cnpj;
    // Sigla da UF do adquirente ou do encomendante
    @Column(length = 2, nullable = true)
    private String ufterceiro;
    // Código do Exportador
    @NotNull(message = "Código do Exportador é obrigatório")
    @Length(min = 1, max = 60, message = "Código do Exportador deve conter entre {min} e {max} caracteres")
    @Column(length = 60, nullable = false)
    private String cexportador;

    // Adicoes
    @Valid
    @OneToMany(mappedBy = "declaracaoImportacao", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<DeclaracaoImportacaoAdicoes> adicoes;

    public DeclaracaoImportacao() {
        this.adicoes = new ArrayList<>();
    }

    public DetalhamentoProdutoServico getDetalhamentoProdutoServico() {
        return detalhamentoProdutoServico;
    }

    public void setDetalhamentoProdutoServico(DetalhamentoProdutoServico detalhamentoProdutoServico) {
        this.detalhamentoProdutoServico = detalhamentoProdutoServico;
    }

    public Emitente getEmitente() {
        return emitente;
    }

    public void setEmitente(Emitente emitente) {
        this.emitente = emitente;
    }

    public String getNdi() {
        return ndi;
    }

    public void setNdi(String ndi) {
        this.ndi = ndi;
    }

    public Date getDdi() {
        return ddi;
    }

    public void setDdi(Date ddi) {
        this.ddi = ddi;
    }

    public String getXlocdesemb() {
        return xlocdesemb;
    }

    public void setXlocdesemb(String xlocdesemb) {
        this.xlocdesemb = xlocdesemb;
    }

    public String getUfdesemb() {
        return ufdesemb;
    }

    public void setUfdesemb(String ufdesemb) {
        this.ufdesemb = ufdesemb;
    }

    public Date getDdesemb() {
        return ddesemb;
    }

    public void setDdesemb(Date ddesemb) {
        this.ddesemb = ddesemb;
    }

    public NFeTipoViaTransporte getTpviatransp() {
        return tpviatransp;
    }

    public void setTpviatransp(NFeTipoViaTransporte tpviatransp) {
        this.tpviatransp = tpviatransp;
    }

    public BigDecimal getVafrmm() {
        return vafrmm;
    }

    public void setVafrmm(BigDecimal vafrmm) {
        this.vafrmm = vafrmm;
    }

    public NFeTipoImportacao getTpintermedio() {
        return tpintermedio;
    }

    public void setTpintermedio(NFeTipoImportacao tpintermedio) {
        this.tpintermedio = tpintermedio;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getUfterceiro() {
        return ufterceiro;
    }

    public void setUfterceiro(String ufterceiro) {
        this.ufterceiro = ufterceiro;
    }

    public String getCexportador() {
        return cexportador;
    }

    public void setCexportador(String cexportador) {
        this.cexportador = cexportador;
    }

    public List<DeclaracaoImportacaoAdicoes> getAdicoes() {
        return adicoes;
    }

    public void setAdicoes(List<DeclaracaoImportacaoAdicoes> adicoes) {
        this.adicoes = adicoes;
    }

}
