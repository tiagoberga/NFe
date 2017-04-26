/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.nfe.emissao;

import br.com.ararati.entity.cadastros.*;
import br.com.ararati.entity.AbstractEntity;
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
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * Identificação do Destinatário da Nota Fiscal eletrônica
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "dados_nfe")
public class DadosNFe extends AbstractEntity {

    @ManyToOne
    @NotNull(message = "Empresa Emitente é obrigatório")
    @JoinColumn(name = "emitente_id", nullable = false)
    private Emitente emitente;

    // Versão do leiaute 
    @NotNull(message = "Versão é obrigatório")
    @Column(length = 4, nullable = false)
    @Length(min = 1, max = 4, message = "Versão deve conter entre {min} e {max} caracteres")
    private String versao;
    // Identificador da TAG a ser assinada: "NFe00000000000000000000000000000000000000000000"
    @NotNull(message = "Ide é obrigatório")
    @Column(length = 47, nullable = false)
    private String ide;

    @Valid
    @OneToOne(mappedBy = "dadosNFe", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private IdentificacaoNFe identificacaoNFe;

    @Valid
    @OneToOne(mappedBy = "dadosNFe", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private IdentificacaoEmitente identificacaoEmitente;

    @Valid
    @OneToOne(mappedBy = "dadosNFe", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private IdentificacaoAvulsa identificacaoAvulsa;

    @Valid
    @OneToOne(mappedBy = "dadosNFe", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private IdentificacaoDestinatario identificacaoDestinatario;

    @Valid
    @OneToOne(mappedBy = "dadosNFe", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private IdentificacaoLocalRetirada identificacaoLocalRetirada;

    @Valid
    @OneToOne(mappedBy = "dadosNFe", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private IdentificacaoLocalEntrega identificacaoLocalEntrega;

    @Valid
    @OneToMany(mappedBy = "dadosNFe", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<AutorizacaoObterXml> autorizadosObterXml;

    @Valid
    @OneToMany(mappedBy = "dadosNFe", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<DetalhamentoProdutoServico> detalhamentoProdutosServicos;

    @Valid
    @OneToOne(mappedBy = "dadosNFe", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private TotalNFe totalNFe;

    @Valid
    @OneToOne(mappedBy = "dadosNFe", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private CobrancaFatura cobrancaFatura;

    @Valid
    @OneToMany(mappedBy = "dadosNFe", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<CobrancaDuplicata> duplicatas;

    @Valid
    @OneToOne(mappedBy = "dadosNFe", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private InformacoesAdicionais informacoesAdicionais;

    @Valid
    @OneToOne(mappedBy = "dadosNFe", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private ComercioExterior comercioExterior;

    @Valid
    @OneToOne(mappedBy = "dadosNFe", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private Compra compra;

    @Valid
    @OneToOne(mappedBy = "dadosNFe", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private AquisicaoCana aquisicaoCana;

    @Valid
    @OneToOne(mappedBy = "dadosNFe", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private TransporteNFe transporteNFe;

    @Valid
    @OneToMany(mappedBy = "dadosNFe", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private Pagamento pagamento;

    public DadosNFe() {
        this.autorizadosObterXml = new ArrayList<>();
        this.detalhamentoProdutosServicos = new ArrayList<>();
        this.duplicatas = new ArrayList<>();
    }

    public DadosNFe(Emitente emitente) {
        this();
        this.emitente = emitente;
    }

    public void addAutorizacaoObterXml(AutorizacaoObterXml item) {
        this.autorizadosObterXml.add(item);
        item.setDadosNFe(this);
    }

    public void rmvAutorizacaoObterXml(AutorizacaoObterXml item) {
        this.autorizadosObterXml.remove(item);
        item.setDadosNFe(null);
    }

    public void addDetalhamentoProdutosServicos(DetalhamentoProdutoServico item) {
        this.detalhamentoProdutosServicos.add(item);
        item.setDadosNFe(this);
    }

    public void rmvDetalhamentoProdutosServicos(DetalhamentoProdutoServico item) {
        this.detalhamentoProdutosServicos.remove(item);
        item.setDadosNFe(null);
    }

    public void addCobrancaDuplicata(CobrancaDuplicata item) {
        this.duplicatas.add(item);
        item.setDadosNFe(this);
    }

    public void rmvCobrancaDuplicata(CobrancaDuplicata item) {
        this.duplicatas.remove(item);
        item.setDadosNFe(null);
    }

    public Emitente getEmitente() {
        return emitente;
    }

    public void setEmitente(Emitente emitente) {
        this.emitente = emitente;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public String getIde() {
        return ide;
    }

    public void setIde(String ide) {
        this.ide = ide;
    }

    public IdentificacaoNFe getIdentificacaoNFe() {
        return identificacaoNFe;
    }

    public void setIdentificacaoNFe(IdentificacaoNFe identificacaoNFe) {
        this.identificacaoNFe = identificacaoNFe;
    }

    public IdentificacaoEmitente getIdentificacaoEmitente() {
        return identificacaoEmitente;
    }

    public void setIdentificacaoEmitente(IdentificacaoEmitente identificacaoEmitente) {
        this.identificacaoEmitente = identificacaoEmitente;
    }

    public IdentificacaoAvulsa getIdentificacaoAvulsa() {
        return identificacaoAvulsa;
    }

    public void setIdentificacaoAvulsa(IdentificacaoAvulsa identificacaoAvulsa) {
        this.identificacaoAvulsa = identificacaoAvulsa;
    }

    public IdentificacaoDestinatario getIdentificacaoDestinatario() {
        return identificacaoDestinatario;
    }

    public void setIdentificacaoDestinatario(IdentificacaoDestinatario identificacaoDestinatario) {
        this.identificacaoDestinatario = identificacaoDestinatario;
    }

    public IdentificacaoLocalRetirada getIdentificacaoLocalRetirada() {
        return identificacaoLocalRetirada;
    }

    public void setIdentificacaoLocalRetirada(IdentificacaoLocalRetirada identificacaoLocalRetirada) {
        this.identificacaoLocalRetirada = identificacaoLocalRetirada;
    }

    public IdentificacaoLocalEntrega getIdentificacaoLocalEntrega() {
        return identificacaoLocalEntrega;
    }

    public void setIdentificacaoLocalEntrega(IdentificacaoLocalEntrega identificacaoLocalEntrega) {
        this.identificacaoLocalEntrega = identificacaoLocalEntrega;
    }

    public List<AutorizacaoObterXml> getAutorizadosObterXml() {
        return autorizadosObterXml;
    }

    public void setAutorizadosObterXml(List<AutorizacaoObterXml> autorizadosObterXml) {
        this.autorizadosObterXml = autorizadosObterXml;
    }

    public List<DetalhamentoProdutoServico> getDetalhamentoProdutosServicos() {
        return detalhamentoProdutosServicos;
    }

    public void setDetalhamentoProdutosServicos(List<DetalhamentoProdutoServico> detalhamentoProdutosServicos) {
        this.detalhamentoProdutosServicos = detalhamentoProdutosServicos;
    }

    public TotalNFe getTotalNFe() {
        return totalNFe;
    }

    public void setTotalNFe(TotalNFe totalNFe) {
        this.totalNFe = totalNFe;
    }

    public CobrancaFatura getCobrancaFatura() {
        return cobrancaFatura;
    }

    public void setCobrancaFatura(CobrancaFatura cobrancaFatura) {
        this.cobrancaFatura = cobrancaFatura;
    }

    public List<CobrancaDuplicata> getDuplicatas() {
        return duplicatas;
    }

    public void setDuplicatas(List<CobrancaDuplicata> duplicatas) {
        this.duplicatas = duplicatas;
    }

    public InformacoesAdicionais getInformacoesAdicionais() {
        return informacoesAdicionais;
    }

    public void setInformacoesAdicionais(InformacoesAdicionais informacoesAdicionais) {
        this.informacoesAdicionais = informacoesAdicionais;
    }

    public ComercioExterior getComercioExterior() {
        return comercioExterior;
    }

    public void setComercioExterior(ComercioExterior comercioExterior) {
        this.comercioExterior = comercioExterior;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public AquisicaoCana getAquisicaoCana() {
        return aquisicaoCana;
    }

    public void setAquisicaoCana(AquisicaoCana aquisicaoCana) {
        this.aquisicaoCana = aquisicaoCana;
    }

    public TransporteNFe getTransporteNFe() {
        return transporteNFe;
    }

    public void setTransporteNFe(TransporteNFe transporteNFe) {
        this.transporteNFe = transporteNFe;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    
}
