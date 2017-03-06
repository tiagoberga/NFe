/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.nfe.emissao;

import br.com.ararati.entity.AbstractEntity;
import br.com.ararati.entity.cadastros.Emitente;
import br.com.ararati.entity.cadastros.Produto;
import br.com.ararati.enums.I.NFeTipoIndicadorVProd;
import br.com.ararati.enums.ISSQN.NFeTipoServico;
import br.com.ararati.enums.N.NFeTipoModalidadeBCICMS;
import br.com.ararati.enums.N.NFeTipoModalidadeBCICMSST;
import br.com.ararati.enums.N.NFeTipoMotivoDesoneracaoICMS;
import br.com.ararati.enums.N.NFeTipoSituacaoTributariaICMS;
import br.com.ararati.enums.O.NFeTipoSituacaoTributariaIPI;
import br.com.ararati.enums.Q.NFeTipoSituacaoTributariaPIS;
import br.com.ararati.enums.S.NFeTipoSituacaoTributariaCOFINS;
import br.com.ararati.enums.U.NFeTipoExigibilidadeISS;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
import javax.persistence.PostLoad;
import javax.persistence.Table;
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
@Table(schema = "nfe", name = "detalhamento_produto_servico")
public class DetalhamentoProdutoServico extends AbstractEntity {

    @ManyToOne
    @NotNull(message = "Empresa Emitente é obrigatório")
    @JoinColumn(name = "emitente_id", nullable = false)
    private Emitente emitente;

    @ManyToOne
    @JoinColumn(name = "dados_nfe_id", nullable = false)
    private DadosNFe dadosNFe;

    // Número do item
    @NotNull(message = "Número do Item")
    @Length(min = 1, max = 3, message = "Número do Item deve conter entre {min} e {max} caracteres")
    @Column(length = 3, nullable = false)
    private String nitem;
    // Código do produto ou serviço
    @NotNull(message = "Código é obrigatório")
    @Length(min = 1, max = 60, message = "Código deve conter entre {min} e {max} caracteres")
    @Column(length = 60, nullable = false)
    private String cprod;
    // GTIN (Global Trade Item Number) do produto, antigo código EAN ou código de barras
    @NotNull(message = "EAN é obrigatório")
    @Column(length = 14, nullable = false)
    private String cean;
    // Descrição do produto ou serviço
    @NotNull(message = "Descrição é obrigatório")
    @Length(min = 1, max = 120, message = "Descrição deve conter entre {min} e {max} caracteres")
    @Column(length = 120, nullable = false)
    private String xprod;
    // Código NCM com 8 dígitos
    @NotNull(message = "NCM é obrigatório")
    @Column(length = 8, nullable = false)
    private String ncm;
    // Código CEST
    @Column(length = 7, nullable = true)
    private String cest;
    // EX_TIPI
    @Length(min = 2, max = 3, message = "EX TIPI deve conter entre {min} e {max} caracteres")
    @Column(length = 3, nullable = true)
    private String extipi;
    // Código Fiscal de Operações e Prestações
    @NotNull(message = "CFOP é obrigatório")
    @Column(length = 4, nullable = false)
    private String cfop;
    // Unidade Comercial
    @NotNull(message = "UN Comercial é obrigatório")
    @Length(min = 1, max = 6, message = "UN Comercial deve conter entre {min} e {max} caracteres")
    @Column(length = 6, nullable = false)
    private String ucom;
    // Quantidade Comercial
    @NotNull(message = "Quantidade Comercial é obrigatório")
    @DecimalMin(value = "0.0000")
    @Column(precision = 10, scale = 4, nullable = false)
    private BigDecimal qcom;
    // Valor Unitário de Comercialização
    @NotNull(message = "Valor Unitário de Comercialização é obrigatório")
    @DecimalMin(value = "0.0000")
    @Column(precision = 10, scale = 4, nullable = false)
    private BigDecimal vuncom;
    // Valor Total Bruto dos Produtos ou Serviços
    @NotNull(message = "Valor Total Bruto é obrigatório")
    @DecimalMin(value = "0.00")
    @Column(precision = 13, scale = 2, nullable = false)
    private BigDecimal vprod;
    // GTIN (Global Trade Item Number) da unidade tributável, antigo código EAN ou código de barras
    @NotNull(message = "EAN Tributável é obrigatório")
    @Column(length = 14, nullable = false)
    private String ceantrib;
    // Unidade Tributável
    @NotNull(message = "UN Tributável é obrigatório")
    @Column(length = 6, nullable = false)
    private String utrib;
    // Quantidade Tributável
    @NotNull(message = "Quantidade Tributável é obrigatório")
    @DecimalMin(value = "0.0000")
    @Column(precision = 11, scale = 4, nullable = false)
    private BigDecimal qtrib;
    // Valor Unitário de tributação
    @NotNull(message = "Valor Unitário Tributável é obrigatório")
    @DecimalMin(value = "0.0000")
    @Column(precision = 11, scale = 4, nullable = false)
    private BigDecimal vuntrib;
    // Valor Total do Frete
    @DecimalMin(value = "0.00")
    @Column(precision = 13, scale = 2, nullable = true)
    private BigDecimal vfrete;
    // Valor Total do Seguro
    @DecimalMin(value = "0.00")
    @Column(precision = 13, scale = 2, nullable = true)
    private BigDecimal vseg;
    // Valor do Desconto
    @DecimalMin(value = "0.00")
    @Column(precision = 13, scale = 2, nullable = true)
    private BigDecimal vdesc;
    // Outras despesas acessórias
    @DecimalMin(value = "0.00")
    @Column(precision = 13, scale = 2, nullable = true)
    private BigDecimal voutro;
    // Indica se valor do Item (vProd) entra no valor total da NF-e (vProd)
    @NotNull(message = "Indicador vProd é obrigatório")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NFeTipoIndicadorVProd indtot;

    /* I01. Produtos e Serviços / Declaração de Importação = 100 */
    @Valid
    @OneToMany(mappedBy = "detalhamentoProdutoServico", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<DeclaracaoImportacao> declaracoesDeImportacao;

    /* I03. Produtos e Serviços / Grupo de Exportação = 500 */
    @Valid
    @OneToMany(mappedBy = "detalhamentoProdutoServico", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<GrupoExportacao> gruposDeExportacao;

    /* I05. Produtos e Serviços / Pedido de Compra */
    // Número do Pedido de Compra
    @Length(min = 1, max = 15, message = "Pedido de Compra deve conter entre {min} e {max} caracteres")
    @Column(length = 15, nullable = true)
    private String xped;
    // Item do Pedido de Compra
    @Column(length = 6, nullable = true)
    private String nitemped;

    /* 105a. Codificação NVE - Nomenclatura de Valor Aduaneiro e Estatística. */
    @Valid
    @OneToMany(mappedBy = "detalhamentoProdutoServico", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<NomenclaturaValorAduaneiro> nves;

    /* I07. Produtos e Serviços / Grupo Diversos */
    // Número de controle da FCI - Ficha de Conteúdo de Importação
    @Column(length = 36, nullable = true)
    private String nfci;

    /* JA. Detalhamento Específico de Veículos novos */
    @Valid
    @OneToOne(mappedBy = "detalhamentoProdutoServico", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private DetalhamentoEspecificoVeiculo veiculo;

    /* K. Detalhamento Específico de Medicamento e de matérias-primas farmacêuticas = 500 */
    @Valid
    @OneToMany(mappedBy = "detalhamentoProdutoServico", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<DetalhamentoEspecificoMedicamento> medicamentos;

    /* L. Detalhamento Específico de Armamentos = 500 */
    @Valid
    @OneToMany(mappedBy = "detalhamentoProdutoServico", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<DetalhamentoEspecificoArmamento> armamentos;

    /* LA. Detalhamento Específico de Combustíveis */
    @Valid
    @OneToOne(mappedBy = "detalhamentoProdutoServico", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private DetalhamentoEspecificoCombustivel combustivel;

    /* LB. Detalhamento Específico para Operação com Papel Imune */
    @Valid
    @OneToOne(mappedBy = "detalhamentoProdutoServico", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private DetalhamentoEspecificoPapelImune papelImune;

    /* M. Tributos incidentes no Produto ou Serviço */
    @DecimalMin(value = "0.00")
    @Column(precision = 13, scale = 2, nullable = false)
    private BigDecimal vtottrib;

    /* N. Grupo Tributação do ICMS */
    // Tributação do ICMS
    @Enumerated(EnumType.STRING)
    private NFeTipoSituacaoTributariaICMS csticms;
    // Origem da mercadoria
    private String orig;
    // Alíquota do ICMS
    private BigDecimal picms;
    // Alíquota do ICMS ST
    private BigDecimal picmsst;
    // Modalidade de determinação da BC do ICMS
    @Enumerated(EnumType.STRING)
    private NFeTipoModalidadeBCICMS modbc;
    // Modalidade de determinação da BC do ICMS ST
    @Enumerated(EnumType.STRING)
    private NFeTipoModalidadeBCICMSST modbcst;
    // Valor da BC do ICMS 
    private BigDecimal vbcicms;
    // Valor da BC do ICMS ST
    private BigDecimal vbcicmsst;
    // Valor da BC do ICMS ST retido
    private BigDecimal vbcicmsstret;
    // Valor da BC do ICMS ST da UF destino
    private BigDecimal vbcicmsstdest;
    // Valor do ICMS
    private BigDecimal vicms;
    // Valor do ICMS ST
    private BigDecimal vicmsst;
    // Valor do ICMS ST retido 
    private BigDecimal vicmsstret;
    // Valor do ICMS ST da UF destino
    private BigDecimal vicmsstdest;
    // Valor do ICMS da Operação 
    private BigDecimal vicmsop;
    // Percentual da margem de valor Adicionado do ICMS ST
    private BigDecimal pmvast;
    // Percentual da Redução de BC do ICMS
    private BigDecimal predbc;
    // Percentual da Redução de BC do ICMS ST
    private BigDecimal predbcst;
    // Valor do ICMS desonerado
    private BigDecimal vicmsdeson;
    // Motivo da desoneração do ICMS
    @Enumerated(EnumType.STRING)
    private NFeTipoMotivoDesoneracaoICMS motdesicms;
    // Percentual do diferimento 
    private BigDecimal pdif;
    // Valor do ICMS diferido
    private BigDecimal vicmsdif;
    // Percentual da BC operação própria
    private BigDecimal pbcop;
    // UF para qual é devido o ICMS ST
    private String ufst;
    // Alíquota aplicável de cálculo do crédito 
    private BigDecimal pcredsn;
    // Valor crédito do ICMS que pode ser aproveitado nos termos do art. 23 da LC 123
    private BigDecimal vcredicmssn;

    // NA. ICMS para a UF de destino
    // Valor da BC do ICMS na UF de destino
    @Column(precision = 13, scale = 2)
    private BigDecimal vbcufdest;
    // Percentual do ICMS relativo ao Fundo de Combate à Pobreza (FCP) na UF de destino
    @Column(precision = 13, scale = 2)
    private BigDecimal pfcpufdest;
    // Alíquota interna da UF de destino
    @Column(precision = 13, scale = 2)
    private BigDecimal picmsufdest;
    // Alíquota interestadual das UF envolvidas
    @Column(precision = 13, scale = 2)
    private BigDecimal picmsinter;
    // Percentual provisório de partilha do ICMS Interestadual
    @Column(precision = 13, scale = 2)
    private BigDecimal picmsinterpart;
    // Valor do ICMS relativo ao Fundo de Combate à Pobreza (FCP) da UF de destino
    @Column(precision = 13, scale = 2)
    private BigDecimal vfcpufdest;
    // Valor do ICMS Interestadual para a UF de destino
    @Column(precision = 13, scale = 2)
    private BigDecimal vicmsufdest;
    // Valor do ICMS Interestadual para a UF do remetente
    @Column(precision = 13, scale = 2)
    private BigDecimal vicmsufremet;

    /* O. Grupo Tributação do IPI */
    // Código da situação tributária do IPI 
    @Enumerated(EnumType.STRING)
    private NFeTipoSituacaoTributariaIPI cstipi;
    // Classe de enquadramento do IPI para Cigarros e Bebidas
    private String clenq;
    // CNPJ do produtor da mercadoria, quando diferente do emitente. Somente para os casos de exportação direta ou indireta
    private String cnpjprod;
    // Código do selo de controle IPI 
    private String cselo;
    // Quantidade de selo de controle
    private String qselo;
    // Código de Enquadramento Legal do IPI 
    private String cenq;
    // Valor da BC do IPI 
    private BigDecimal vbcipi;
    // Alíquota do IPI 
    private BigDecimal pipi;
    // Quantidade total na unidade padrão para tributação (somente para os produtos tributados por unidade)
    private BigDecimal qunid;
    // Valor por Unidade Tributável 
    private BigDecimal vunid;
    // Valor do IPI
    private BigDecimal vipi;

    /* P. Grupo Tributação do II */
    // Valor BC do Imposto de Importação 
    private BigDecimal vbcii;
    // Valor despesas aduaneiras 
    private BigDecimal vdespadu;
    // Valor Imposto de Importação 
    private BigDecimal vii;
    // Valor Imposto sobre Operações Financeiras 
    private BigDecimal viof;

    /* Q. Grupo Tributação do PIS */
    // Código de Situação Tributária do PIS 
    @Enumerated(EnumType.STRING)
    private NFeTipoSituacaoTributariaPIS cstpis;
    // Valor da Base de Cálculo do PIS
    private BigDecimal vbcpis;
    // Alíquota do PIS (em percentual) 
    private BigDecimal ppis;
    // Valor do PIS
    private BigDecimal vpis;
    // Quantidade Vendida
    private BigDecimal qbcprodpis;
    // Alíquota do PIS (em reais) 
    private BigDecimal valiqprodpis;

    /* S. Grupo Tributação do COFINS */
    // Código de Situação Tributária da COFINS
    @Enumerated(EnumType.STRING)
    private NFeTipoSituacaoTributariaCOFINS cstcofins;
    // Valor da Base de Cálculo da COFINS 
    private BigDecimal vbccofins;
    // Alíquota da COFINS (em percentual) 
    private BigDecimal pcofins;
    // Valor da COFINS
    private BigDecimal vcofins;
    // Quantidade Vendida
    private BigDecimal qbcprodcofins;
    // Alíquota da COFINS (em reais) 
    private BigDecimal valiqprodcofins;

    /* U. Grupo Tributação do ISSQN */
    // Valor da Base de Cálculo do ISSQN
    private BigDecimal vbcissqn;
    // Alíquota do ISSQN
    private BigDecimal valiqissqn;
    // Valor do ISSQN
    private BigDecimal vissqn;
    // Código do município de ocorrência do fato gerador do ISSQN
    private String cmunfg;
    // Item da Lista de Serviços 
    private NFeTipoServico clistserv;
    // Valor dedução para redução da Base de Calculo
    private BigDecimal vdeducao;
    // Valor outras retenções 
    private BigDecimal voutroissqn;
    // Valor desconto incondicionado 
    private BigDecimal vdescincond;
    // Valor desconto condicionado 
    private BigDecimal vdesccond;
    // Valor retenção ISS 
    private BigDecimal vissret;
    // Indicador da exigibilidade do ISS 
    private NFeTipoExigibilidadeISS indiss;
    // Código do serviço prestado dentro do município
    private String cservico;
    // Código do Município de incidência do imposto
    private String cmun;
    // Código do País onde o serviço foi prestado
    private String cpais;
    // Número do processo judicial ou administrativo de suspensão da exigibilidade
    private String nprocesso;
    // Indicador de incentivo Fiscal 
    private String indincentivo;

    /* UA. Tributos Devolvidos */
    // Percentual da mercadoria devolvida
    private BigDecimal pdevol;
    // Valor do IPI devolvido
    private BigDecimal vipidevol;

    /* V. Informações Adicionais */
    // Informações Adicionais do Produto
    private String infadprod;

    public DetalhamentoProdutoServico() {

    }

    public DetalhamentoProdutoServico(Produto produto) {
        this.cprod = produto.getCprod();
        this.xprod = produto.getXprod();
        this.cean = produto.getCean();
        this.ncm = produto.getNcm();
        this.cest = produto.getCest();
        this.extipi = produto.getExtipi();
        this.cfop = produto.getCfop();
        this.ucom = produto.getUcom();
        this.qcom = produto.getQcom();
        this.vuncom = produto.getVuncom();
        this.vprod = produto.getVprod();
        this.ceantrib = produto.getCeantrib();
        this.utrib = produto.getUtrib();
        this.qtrib = produto.getQtrib();
        this.vuntrib = produto.getVuntrib();
        this.vfrete = produto.getVfrete();
        this.vseg = produto.getVseg();
        this.vdesc = produto.getVdesc();
        this.voutro = produto.getVoutro();
        this.indtot = produto.getIndTot();
    }

    @PostLoad
    public void init() {
        this.armamentos = new ArrayList<>();
        this.combustivel = new DetalhamentoEspecificoCombustivel();
        this.declaracoesDeImportacao = new ArrayList<>();
        this.gruposDeExportacao = new ArrayList<>();
        this.medicamentos = new ArrayList<>();
        this.nves = new ArrayList<>();
        this.veiculo = new DetalhamentoEspecificoVeiculo();
    }

    public void limpaDadosIcms() {
        // this.csticms = null;
        // this.orig = null;
        this.picms = null;
        this.picmsst = null;
        this.modbc = null;
        this.modbcst = null;
        this.vbcicms = null;
        this.vbcicmsst = null;
        this.vbcicmsstret = null;
        this.vbcicmsstdest = null;
        this.vicms = null;
        this.vicmsst = null;
        this.vicmsstret = null;
        this.vicmsstdest = null;
        this.vicmsop = null;
        this.pmvast = null;
        this.predbc = null;
        this.predbcst = null;
        this.vicmsdeson = null;
        this.motdesicms = null;
        this.pdif = null;
        this.vicmsdif = null;
        this.pbcop = null;
        this.ufst = null;
        this.pcredsn = null;
        this.vcredicmssn = null;
    }

    public void limpaDadosIcmsInter() {
        this.vbcufdest = null;
        this.pfcpufdest = null;
        this.picmsufdest = null;
        this.picmsinter = null;
        this.picmsinterpart = null;
        this.vfcpufdest = null;
        this.vicmsufdest = null;
        this.vicmsufremet = null;
    }

    public void limpaDadosIssqn() {
        this.vbcissqn = null;
        this.valiqissqn = null;
        this.vissqn = null;
        this.cmunfg = null;
        this.clistserv = null;
        this.vdeducao = null;
        this.voutroissqn = null;
        this.vdescincond = null;
        this.vdesccond = null;
        this.vissret = null;
        this.indiss = null;
        this.cservico = null;
        this.cmun = null;
        this.cpais = null;
        this.nprocesso = null;
        this.indincentivo = null;
    }

    public void limpaDadosPis() {
        // cstpis = null;
        vbcpis = null;
        ppis = null;
        vpis = null;
        qbcprodpis = null;
        valiqprodpis = null;
    }

    public void limpaDadosCofins() {
        // cstcofins = null;
        vbccofins = null;
        pcofins = null;
        vcofins = null;
        qbcprodcofins = null;
        valiqprodcofins = null;
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

    public String getNitem() {
        return nitem;
    }

    public void setNitem(String nitem) {
        this.nitem = nitem;
    }

    public String getCprod() {
        return cprod;
    }

    public void setCprod(String cprod) {
        this.cprod = cprod;
    }

    public String getCean() {
        return cean;
    }

    public void setCean(String cean) {
        this.cean = cean;
    }

    public String getXprod() {
        return xprod;
    }

    public void setXprod(String xprod) {
        this.xprod = xprod;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    public String getCest() {
        return cest;
    }

    public void setCest(String cest) {
        this.cest = cest;
    }

    public String getExtipi() {
        return extipi;
    }

    public void setExtipi(String extipi) {
        this.extipi = extipi;
    }

    public String getCfop() {
        return cfop;
    }

    public void setCfop(String cfop) {
        this.cfop = cfop;
    }

    public String getUcom() {
        return ucom;
    }

    public void setUcom(String ucom) {
        this.ucom = ucom;
    }

    public BigDecimal getQcom() {
        return qcom;
    }

    public void setQcom(BigDecimal qcom) {
        this.qcom = qcom;
    }

    public BigDecimal getVuncom() {
        return vuncom;
    }

    public void setVuncom(BigDecimal vuncom) {
        this.vuncom = vuncom;
    }

    public BigDecimal getVprod() {
        return vprod;
    }

    public void setVprod(BigDecimal vprod) {
        this.vprod = vprod;
    }

    public String getCeantrib() {
        return ceantrib;
    }

    public void setCeantrib(String ceantrib) {
        this.ceantrib = ceantrib;
    }

    public String getUtrib() {
        return utrib;
    }

    public void setUtrib(String utrib) {
        this.utrib = utrib;
    }

    public BigDecimal getQtrib() {
        return qtrib;
    }

    public void setQtrib(BigDecimal qtrib) {
        this.qtrib = qtrib;
    }

    public BigDecimal getVuntrib() {
        return vuntrib;
    }

    public void setVuntrib(BigDecimal vuntrib) {
        this.vuntrib = vuntrib;
    }

    public BigDecimal getVfrete() {
        return vfrete;
    }

    public void setVfrete(BigDecimal vfrete) {
        this.vfrete = vfrete;
    }

    public BigDecimal getVseg() {
        return vseg;
    }

    public void setVseg(BigDecimal vseg) {
        this.vseg = vseg;
    }

    public BigDecimal getVdesc() {
        return vdesc;
    }

    public void setVdesc(BigDecimal vdesc) {
        this.vdesc = vdesc;
    }

    public BigDecimal getVoutro() {
        return voutro;
    }

    public void setVoutro(BigDecimal voutro) {
        this.voutro = voutro;
    }

    public NFeTipoIndicadorVProd getIndtot() {
        return indtot;
    }

    public void setIndtot(NFeTipoIndicadorVProd indtot) {
        this.indtot = indtot;
    }

    public List<DeclaracaoImportacao> getDeclaracoesDeImportacao() {
        return declaracoesDeImportacao;
    }

    public void setDeclaracoesDeImportacao(List<DeclaracaoImportacao> declaracoesDeImportacao) {
        this.declaracoesDeImportacao = declaracoesDeImportacao;
    }

    public List<GrupoExportacao> getGruposDeExportacao() {
        return gruposDeExportacao;
    }

    public void setGruposDeExportacao(List<GrupoExportacao> gruposDeExportacao) {
        this.gruposDeExportacao = gruposDeExportacao;
    }

    public String getXped() {
        return xped;
    }

    public void setXped(String xped) {
        this.xped = xped;
    }

    public String getNitemped() {
        return nitemped;
    }

    public void setNitemped(String nitemped) {
        this.nitemped = nitemped;
    }

    public List<NomenclaturaValorAduaneiro> getNves() {
        return nves;
    }

    public void setNves(List<NomenclaturaValorAduaneiro> nves) {
        this.nves = nves;
    }

    public String getNfci() {
        return nfci;
    }

    public void setNfci(String nfci) {
        this.nfci = nfci;
    }

    public DetalhamentoEspecificoVeiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(DetalhamentoEspecificoVeiculo veiculo) {
        this.veiculo = veiculo;
    }

    public List<DetalhamentoEspecificoMedicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<DetalhamentoEspecificoMedicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public List<DetalhamentoEspecificoArmamento> getArmamentos() {
        return armamentos;
    }

    public void setArmamentos(List<DetalhamentoEspecificoArmamento> armamentos) {
        this.armamentos = armamentos;
    }

    public DetalhamentoEspecificoCombustivel getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(DetalhamentoEspecificoCombustivel combustivel) {
        this.combustivel = combustivel;
    }

    public DetalhamentoEspecificoPapelImune getPapelImune() {
        return papelImune;
    }

    public void setPapelImune(DetalhamentoEspecificoPapelImune papelImune) {
        this.papelImune = papelImune;
    }

    public BigDecimal getVtottrib() {
        return vtottrib;
    }

    public void setVtottrib(BigDecimal vtottrib) {
        this.vtottrib = vtottrib;
    }

    public NFeTipoSituacaoTributariaICMS getCsticms() {
        return csticms;
    }

    public void setCsticms(NFeTipoSituacaoTributariaICMS csticms) {
        this.csticms = csticms;
    }

    public String getOrig() {
        return orig;
    }

    public void setOrig(String orig) {
        this.orig = orig;
    }

    public BigDecimal getPicms() {
        return picms;
    }

    public void setPicms(BigDecimal picms) {
        this.picms = picms;
    }

    public BigDecimal getPicmsst() {
        return picmsst;
    }

    public void setPicmsst(BigDecimal picmsst) {
        this.picmsst = picmsst;
    }

    public NFeTipoModalidadeBCICMS getModbc() {
        return modbc;
    }

    public void setModbc(NFeTipoModalidadeBCICMS modbc) {
        this.modbc = modbc;
    }

    public NFeTipoModalidadeBCICMSST getModbcst() {
        return modbcst;
    }

    public void setModbcst(NFeTipoModalidadeBCICMSST modbcst) {
        this.modbcst = modbcst;
    }

    public BigDecimal getVbcicms() {
        return vbcicms;
    }

    public void setVbcicms(BigDecimal vbcicms) {
        this.vbcicms = vbcicms;
    }

    public BigDecimal getVbcicmsst() {
        return vbcicmsst;
    }

    public void setVbcicmsst(BigDecimal vbcicmsst) {
        this.vbcicmsst = vbcicmsst;
    }

    public BigDecimal getVbcicmsstret() {
        return vbcicmsstret;
    }

    public void setVbcicmsstret(BigDecimal vbcicmsstret) {
        this.vbcicmsstret = vbcicmsstret;
    }

    public BigDecimal getVbcicmsstdest() {
        return vbcicmsstdest;
    }

    public void setVbcicmsstdest(BigDecimal vbcicmsstdest) {
        this.vbcicmsstdest = vbcicmsstdest;
    }

    public BigDecimal getVicms() {
        return vicms;
    }

    public void setVicms(BigDecimal vicms) {
        this.vicms = vicms;
    }

    public BigDecimal getVicmsst() {
        return vicmsst;
    }

    public void setVicmsst(BigDecimal vicmsst) {
        this.vicmsst = vicmsst;
    }

    public BigDecimal getVicmsstret() {
        return vicmsstret;
    }

    public void setVicmsstret(BigDecimal vicmsstret) {
        this.vicmsstret = vicmsstret;
    }

    public BigDecimal getVicmsstdest() {
        return vicmsstdest;
    }

    public void setVicmsstdest(BigDecimal vicmsstdest) {
        this.vicmsstdest = vicmsstdest;
    }

    public BigDecimal getVicmsop() {
        return vicmsop;
    }

    public void setVicmsop(BigDecimal vicmsop) {
        this.vicmsop = vicmsop;
    }

    public BigDecimal getPmvast() {
        return pmvast;
    }

    public void setPmvast(BigDecimal pmvast) {
        this.pmvast = pmvast;
    }

    public BigDecimal getPredbc() {
        return predbc;
    }

    public void setPredbc(BigDecimal predbc) {
        this.predbc = predbc;
    }

    public BigDecimal getPredbcst() {
        return predbcst;
    }

    public void setPredbcst(BigDecimal predbcst) {
        this.predbcst = predbcst;
    }

    public BigDecimal getVicmsdeson() {
        return vicmsdeson;
    }

    public void setVicmsdeson(BigDecimal vicmsdeson) {
        this.vicmsdeson = vicmsdeson;
    }

    public NFeTipoMotivoDesoneracaoICMS getMotdesicms() {
        return motdesicms;
    }

    public void setMotdesicms(NFeTipoMotivoDesoneracaoICMS motdesicms) {
        this.motdesicms = motdesicms;
    }

    public BigDecimal getPdif() {
        return pdif;
    }

    public void setPdif(BigDecimal pdif) {
        this.pdif = pdif;
    }

    public BigDecimal getVicmsdif() {
        return vicmsdif;
    }

    public void setVicmsdif(BigDecimal vicmsdif) {
        this.vicmsdif = vicmsdif;
    }

    public BigDecimal getPbcop() {
        return pbcop;
    }

    public void setPbcop(BigDecimal pbcop) {
        this.pbcop = pbcop;
    }

    public String getUfst() {
        return ufst;
    }

    public void setUfst(String ufst) {
        this.ufst = ufst;
    }

    public BigDecimal getPcredsn() {
        return pcredsn;
    }

    public void setPcredsn(BigDecimal pcredsn) {
        this.pcredsn = pcredsn;
    }

    public BigDecimal getVcredicmssn() {
        return vcredicmssn;
    }

    public void setVcredicmssn(BigDecimal vcredicmssn) {
        this.vcredicmssn = vcredicmssn;
    }

    public BigDecimal getVbcufdest() {
        return vbcufdest;
    }

    public void setVbcufdest(BigDecimal vbcufdest) {
        this.vbcufdest = vbcufdest;
    }

    public BigDecimal getPfcpufdest() {
        return pfcpufdest;
    }

    public void setPfcpufdest(BigDecimal pfcpufdest) {
        this.pfcpufdest = pfcpufdest;
    }

    public BigDecimal getPicmsufdest() {
        return picmsufdest;
    }

    public void setPicmsufdest(BigDecimal picmsufdest) {
        this.picmsufdest = picmsufdest;
    }

    public BigDecimal getPicmsinter() {
        return picmsinter;
    }

    public void setPicmsinter(BigDecimal picmsinter) {
        this.picmsinter = picmsinter;
    }

    public BigDecimal getPicmsinterpart() {
        return picmsinterpart;
    }

    public void setPicmsinterpart(BigDecimal picmsinterpart) {
        this.picmsinterpart = picmsinterpart;
    }

    public BigDecimal getVfcpufdest() {
        return vfcpufdest;
    }

    public void setVfcpufdest(BigDecimal vfcpufdest) {
        this.vfcpufdest = vfcpufdest;
    }

    public BigDecimal getVicmsufdest() {
        return vicmsufdest;
    }

    public void setVicmsufdest(BigDecimal vicmsufdest) {
        this.vicmsufdest = vicmsufdest;
    }

    public BigDecimal getVicmsufremet() {
        return vicmsufremet;
    }

    public void setVicmsufremet(BigDecimal vicmsufremet) {
        this.vicmsufremet = vicmsufremet;
    }

    public NFeTipoSituacaoTributariaIPI getCstipi() {
        return cstipi;
    }

    public void setCstipi(NFeTipoSituacaoTributariaIPI cstipi) {
        this.cstipi = cstipi;
    }

    public String getClenq() {
        return clenq;
    }

    public void setClenq(String clenq) {
        this.clenq = clenq;
    }

    public String getCnpjprod() {
        return cnpjprod;
    }

    public void setCnpjprod(String cnpjprod) {
        this.cnpjprod = cnpjprod;
    }

    public String getCselo() {
        return cselo;
    }

    public void setCselo(String cselo) {
        this.cselo = cselo;
    }

    public String getQselo() {
        return qselo;
    }

    public void setQselo(String qselo) {
        this.qselo = qselo;
    }

    public String getCenq() {
        return cenq;
    }

    public void setCenq(String cenq) {
        this.cenq = cenq;
    }

    public BigDecimal getVbcipi() {
        return vbcipi;
    }

    public void setVbcipi(BigDecimal vbcipi) {
        this.vbcipi = vbcipi;
    }

    public BigDecimal getPipi() {
        return pipi;
    }

    public void setPipi(BigDecimal pipi) {
        this.pipi = pipi;
    }

    public BigDecimal getQunid() {
        return qunid;
    }

    public void setQunid(BigDecimal qunid) {
        this.qunid = qunid;
    }

    public BigDecimal getVunid() {
        return vunid;
    }

    public void setVunid(BigDecimal vunid) {
        this.vunid = vunid;
    }

    public BigDecimal getVipi() {
        return vipi;
    }

    public void setVipi(BigDecimal vipi) {
        this.vipi = vipi;
    }

    public BigDecimal getVbcii() {
        return vbcii;
    }

    public void setVbcii(BigDecimal vbcii) {
        this.vbcii = vbcii;
    }

    public BigDecimal getVdespadu() {
        return vdespadu;
    }

    public void setVdespadu(BigDecimal vdespadu) {
        this.vdespadu = vdespadu;
    }

    public BigDecimal getVii() {
        return vii;
    }

    public void setVii(BigDecimal vii) {
        this.vii = vii;
    }

    public BigDecimal getViof() {
        return viof;
    }

    public void setViof(BigDecimal viof) {
        this.viof = viof;
    }

    public NFeTipoSituacaoTributariaPIS getCstpis() {
        return cstpis;
    }

    public void setCstpis(NFeTipoSituacaoTributariaPIS cstpis) {
        this.cstpis = cstpis;
    }

    public BigDecimal getVbcpis() {
        return vbcpis;
    }

    public void setVbcpis(BigDecimal vbcpis) {
        this.vbcpis = vbcpis;
    }

    public BigDecimal getPpis() {
        return ppis;
    }

    public void setPpis(BigDecimal ppis) {
        this.ppis = ppis;
    }

    public BigDecimal getVpis() {
        return vpis;
    }

    public void setVpis(BigDecimal vpis) {
        this.vpis = vpis;
    }

    public BigDecimal getQbcprodpis() {
        return qbcprodpis;
    }

    public void setQbcprodpis(BigDecimal qbcprodpis) {
        this.qbcprodpis = qbcprodpis;
    }

    public BigDecimal getValiqprodpis() {
        return valiqprodpis;
    }

    public void setValiqprodpis(BigDecimal valiqprodpis) {
        this.valiqprodpis = valiqprodpis;
    }

    public NFeTipoSituacaoTributariaCOFINS getCstcofins() {
        return cstcofins;
    }

    public void setCstcofins(NFeTipoSituacaoTributariaCOFINS cstcofins) {
        this.cstcofins = cstcofins;
    }

    public BigDecimal getVbccofins() {
        return vbccofins;
    }

    public void setVbccofins(BigDecimal vbccofins) {
        this.vbccofins = vbccofins;
    }

    public BigDecimal getPcofins() {
        return pcofins;
    }

    public void setPcofins(BigDecimal pcofins) {
        this.pcofins = pcofins;
    }

    public BigDecimal getVcofins() {
        return vcofins;
    }

    public void setVcofins(BigDecimal vcofins) {
        this.vcofins = vcofins;
    }

    public BigDecimal getQbcprodcofins() {
        return qbcprodcofins;
    }

    public void setQbcprodcofins(BigDecimal qbcprodcofins) {
        this.qbcprodcofins = qbcprodcofins;
    }

    public BigDecimal getValiqprodcofins() {
        return valiqprodcofins;
    }

    public void setValiqprodcofins(BigDecimal valiqprodcofins) {
        this.valiqprodcofins = valiqprodcofins;
    }

    public BigDecimal getVbcissqn() {
        return vbcissqn;
    }

    public void setVbcissqn(BigDecimal vbcissqn) {
        this.vbcissqn = vbcissqn;
    }

    public BigDecimal getValiqissqn() {
        return valiqissqn;
    }

    public void setValiqissqn(BigDecimal valiqissqn) {
        this.valiqissqn = valiqissqn;
    }

    public BigDecimal getVissqn() {
        return vissqn;
    }

    public void setVissqn(BigDecimal vissqn) {
        this.vissqn = vissqn;
    }

    public String getCmunfg() {
        return cmunfg;
    }

    public void setCmunfg(String cmunfg) {
        this.cmunfg = cmunfg;
    }

    public NFeTipoServico getClistserv() {
        return clistserv;
    }

    public void setClistserv(NFeTipoServico clistserv) {
        this.clistserv = clistserv;
    }

    public BigDecimal getVdeducao() {
        return vdeducao;
    }

    public void setVdeducao(BigDecimal vdeducao) {
        this.vdeducao = vdeducao;
    }

    public BigDecimal getVoutroissqn() {
        return voutroissqn;
    }

    public void setVoutroissqn(BigDecimal voutroissqn) {
        this.voutroissqn = voutroissqn;
    }

    public BigDecimal getVdescincond() {
        return vdescincond;
    }

    public void setVdescincond(BigDecimal vdescincond) {
        this.vdescincond = vdescincond;
    }

    public BigDecimal getVdesccond() {
        return vdesccond;
    }

    public void setVdesccond(BigDecimal vdesccond) {
        this.vdesccond = vdesccond;
    }

    public BigDecimal getVissret() {
        return vissret;
    }

    public void setVissret(BigDecimal vissret) {
        this.vissret = vissret;
    }

    public NFeTipoExigibilidadeISS getIndiss() {
        return indiss;
    }

    public void setIndiss(NFeTipoExigibilidadeISS indiss) {
        this.indiss = indiss;
    }

    public String getCservico() {
        return cservico;
    }

    public void setCservico(String cservico) {
        this.cservico = cservico;
    }

    public String getCmun() {
        return cmun;
    }

    public void setCmun(String cmun) {
        this.cmun = cmun;
    }

    public String getCpais() {
        return cpais;
    }

    public void setCpais(String cpais) {
        this.cpais = cpais;
    }

    public String getNprocesso() {
        return nprocesso;
    }

    public void setNprocesso(String nprocesso) {
        this.nprocesso = nprocesso;
    }

    public String getIndincentivo() {
        return indincentivo;
    }

    public void setIndincentivo(String indincentivo) {
        this.indincentivo = indincentivo;
    }

    public BigDecimal getPdevol() {
        return pdevol;
    }

    public void setPdevol(BigDecimal pdevol) {
        this.pdevol = pdevol;
    }

    public BigDecimal getVipidevol() {
        return vipidevol;
    }

    public void setVipidevol(BigDecimal vipidevol) {
        this.vipidevol = vipidevol;
    }

    public String getInfadprod() {
        return infadprod;
    }

    public void setInfadprod(String infadprod) {
        this.infadprod = infadprod;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.cprod);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DetalhamentoProdutoServico other = (DetalhamentoProdutoServico) obj;
        if (!Objects.equals(this.cprod, other.cprod)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return xprod;
    }

}
