/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.nfe.emissao;

import br.com.ararati.entity.cadastros.*;
import br.com.ararati.entity.AbstractEntity;
import br.com.ararati.enums.B.NFeTipoAmbiente;
import br.com.ararati.enums.B.NFeTipoDestinoOperacao;
import br.com.ararati.enums.B.NFeTipoEmissao;
import br.com.ararati.enums.B.NFeTipoFinalidadeEmissao;
import br.com.ararati.enums.B.NFeTipoFormatoImpressao;
import br.com.ararati.enums.B.NFeTipoModeloDocumentoFiscal;
import br.com.ararati.enums.B.NFeTipoOperacao;
import br.com.ararati.enums.B.NFeTipoOperacaoConsumidor;
import br.com.ararati.enums.B.NFeTipoPresencaCompradorOperacao;
import br.com.ararati.enums.B.NFeTipoProcessoEmissao;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * Identificação do Destinatário da Nota Fiscal eletrônica
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "identificacao_nfe")
public class IdentificacaoNFe extends AbstractEntity {

    @ManyToOne
    @NotNull(message = "Empresa Emitente é obrigatório")
    @JoinColumn(name = "emitente_id", nullable = false)
    private Emitente emitente;

    @OneToOne
    @JoinColumn(name = "dados_nfe_id", nullable = false)
    private DadosNFe dadosNFe;

    // Código da UF do emitente do Documento Fiscal
    @NotNull(message = "UF do Emitente é obrigatório")
    @Column(length = 2, nullable = false)
    private String cuf;
    
    // Código Numérico que compõe a Chave de Acesso
    @NotNull(message = "Código Numérico é obrigatório")
    @Column(length = 8, nullable = false)
    private String cnf;
    
    // Descrição da Natureza da Operação 
    @NotNull(message = "Natureza de Operação é obrigatório")
    @Length(min = 1, max = 60, message = "Natureza de Operação deve conter entre {min} e {max} caracteres")
    @Column(length = 60, nullable = false)
    private String natop;
    
    // Código do Modelo do Documento Fiscal
    @NotNull(message = "Modelo do Documento é obrigatório")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NFeTipoModeloDocumentoFiscal modelo;
    
    // Série do Documento Fiscal 
    @NotNull(message = "Série do Documento é obrigatório")
    @Length(min = 1, max = 30, message = "Série deve conter entre {min} e {max} caracteres")
    @Column(length = 3, nullable = false)
    private String serie;
    
    // Número do Documento Fiscal
    @Length(min = 1, max = 9, message = "Número deve conter entre {min} e {max} caracteres")
    @NotNull(message = "Número do Documento é obrigatório")
    @Column(length = 9, nullable = false)
    private String nnf;
    
    // Data e hora de emissão do Documento Fiscal
    @NotNull(message = "Data de Emissao Documento é obrigatório")
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dhemi;
    
    // Data e hora de Saída ou da Entrada da Mercadoria/Produtos
    @Column(nullable = true)
    @Temporal(TemporalType.DATE)
    private Date dhsaient;
    
    // Tipo de Operação 
    @NotNull(message = "Tipo de Operação é obrigatório")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NFeTipoOperacao tpnf;
    
    // Identificador de local de destino da operação
    @NotNull(message = "Identificador de Operação é obrigatório")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NFeTipoDestinoOperacao iddest;
    
    // Código do Município de Ocorrência do Fato Gerador
    @NotNull(message = "Código do Município de Ocorrência é obrigatório")
    @Column(length = 7, nullable = false)
    private String cmunfg;
    
    // Formato de Impressão do DANFE 
    @NotNull(message = "Formato de Impressão é obrigatório")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NFeTipoFormatoImpressao tpimp;
    
    // Tipo de Emissão da NF-e 
    @NotNull(message = "Tipo de Emissão é obrigatório")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NFeTipoEmissao tpemis;
    
    // Dígito Verificador da Chave de Acesso da NF-e
    @NotNull(message = "Dígito da Chave de Acesso é obrigatório")
    @Column(length = 1, nullable = false)
    private String cdv;
    
    // Identificação do Ambiente
    @NotNull(message = "Identificação do Ambiente é obrigatório")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NFeTipoAmbiente tpamb = NFeTipoAmbiente.HOMOLOGACAO;
    
    // Finalidade de emissão da NF-e 
    @NotNull(message = "Finalidade de Emissão é obrigatório")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NFeTipoFinalidadeEmissao finnfe;
    
    // Indica operação com Consumidor final
    @NotNull(message = "Tipo de Consumidor é obrigatório")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NFeTipoOperacaoConsumidor indfinal;
    
    // Indicador de presença do comprador no estabelecimento comercial no momento da operação
    @NotNull(message = "Indicador de Presença do Comprador é obrigatório")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NFeTipoPresencaCompradorOperacao indpres;
    
    // Processo de emissão da NF-e
    @NotNull(message = "Processo de Emissão é obrigatório")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NFeTipoProcessoEmissao procemi;
    
    // Versão do Processo de emissão da NF-e
    @NotNull(message = "Versão do Processo de Emissão é obrigatório")
    @Length(min = 1, max = 20, message = "Versão do Processo de Emissão deve conter entre {min} e {max} caracteres")
    @Column(length = 20, nullable = false)
    private String verproc;
    
    // Data e Hora da entrada em contingência
    @Temporal(TemporalType.DATE)
    private Date dhcont;
    
    // Justificativa da entrada em contingência
    @Column(length = 256, nullable = true)
    @Length(min = 15, max = 256, message = "Justificativa de Contingência deve conter entre {min} e {max} caracteres")
    private String xjust;

    @Valid
    @OneToMany(mappedBy = "identificacaoNFe", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<DocumentoFiscalReferenciado> documentosFiscaisReferenciados;

    public IdentificacaoNFe() {
        this.documentosFiscaisReferenciados = new ArrayList<>();
    }

    /**
     * Adiciona DocumentoFiscalReferenciado à lista.
     */
    public void addDocumentoFiscalReferenciado(DocumentoFiscalReferenciado item) {
        this.documentosFiscaisReferenciados.add(item);
        item.setIdentificacaoNFe(this);
        item.setEmitente(emitente);
    }

    /**
     * Adiciona DocumentoFiscalReferenciado à lista.
     */
    public void rmvDocumentoFiscalReferenciado(DocumentoFiscalReferenciado item) {
        this.documentosFiscaisReferenciados.remove(item);
        item.setIdentificacaoNFe(null);
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

    public String getCuf() {
        return cuf;
    }

    public void setCuf(String cuf) {
        this.cuf = cuf;
    }

    public String getCnf() {
        return cnf;
    }

    public void setCnf(String cnf) {
        this.cnf = cnf;
    }

    public String getNatop() {
        return natop;
    }

    public void setNatop(String natop) {
        this.natop = natop;
    }

    public NFeTipoModeloDocumentoFiscal getModelo() {
        return modelo;
    }

    public void setModelo(NFeTipoModeloDocumentoFiscal modelo) {
        this.modelo = modelo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getNnf() {
        return nnf;
    }

    public void setNnf(String nnf) {
        this.nnf = nnf;
    }

    public Date getDhemi() {
        return dhemi;
    }

    public void setDhemi(Date dhemi) {
        this.dhemi = dhemi;
    }

    public Date getDhsaient() {
        return dhsaient;
    }

    public void setDhsaient(Date dhsaient) {
        this.dhsaient = dhsaient;
    }

    public NFeTipoDestinoOperacao getIddest() {
        return iddest;
    }

    public void setIddest(NFeTipoDestinoOperacao iddest) {
        this.iddest = iddest;
    }

    public String getCmunfg() {
        return cmunfg;
    }

    public void setCmunfg(String cmunfg) {
        this.cmunfg = cmunfg;
    }

    public NFeTipoFormatoImpressao getTpimp() {
        return tpimp;
    }

    public void setTpimp(NFeTipoFormatoImpressao tpimp) {
        this.tpimp = tpimp;
    }

    public NFeTipoEmissao getTpemis() {
        return tpemis;
    }

    public void setTpemis(NFeTipoEmissao tpemis) {
        this.tpemis = tpemis;
    }

    public String getCdv() {
        return cdv;
    }

    public void setCdv(String cdv) {
        this.cdv = cdv;
    }

    public NFeTipoAmbiente getTpamb() {
        return tpamb;
    }

    public void setTpamb(NFeTipoAmbiente tpamb) {
        this.tpamb = tpamb;
    }

    public NFeTipoFinalidadeEmissao getFinnfe() {
        return finnfe;
    }

    public void setFinnfe(NFeTipoFinalidadeEmissao finnfe) {
        this.finnfe = finnfe;
    }

    public NFeTipoOperacaoConsumidor getIndfinal() {
        return indfinal;
    }

    public void setIndfinal(NFeTipoOperacaoConsumidor indfinal) {
        this.indfinal = indfinal;
    }

    public NFeTipoPresencaCompradorOperacao getIndpres() {
        return indpres;
    }

    public void setIndpres(NFeTipoPresencaCompradorOperacao indpres) {
        this.indpres = indpres;
    }

    public NFeTipoOperacao getTpnf() {
        return tpnf;
    }

    public void setTpnf(NFeTipoOperacao tpnf) {
        this.tpnf = tpnf;
    }

    public NFeTipoProcessoEmissao getProcemi() {
        return procemi;
    }

    public void setProcemi(NFeTipoProcessoEmissao procemi) {
        this.procemi = procemi;
    }

    public String getVerproc() {
        return verproc;
    }

    public void setVerproc(String verproc) {
        this.verproc = verproc;
    }

    public Date getDhcont() {
        return dhcont;
    }

    public void setDhcont(Date dhcont) {
        this.dhcont = dhcont;
    }

    public String getXjust() {
        return xjust;
    }

    public void setXjust(String xjust) {
        this.xjust = xjust;
    }

    public List<DocumentoFiscalReferenciado> getDocumentosFiscaisReferenciados() {
        return documentosFiscaisReferenciados;
    }

    public void setDocumentosFiscaisReferenciados(List<DocumentoFiscalReferenciado> documentosFiscaisReferenciados) {
        this.documentosFiscaisReferenciados = documentosFiscaisReferenciados;
    }

}
