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
import br.com.ararati.enums.N.NFeTipoOrigemMercadoria;
import br.com.ararati.enums.N.NFeTipoSituacaoTributariaICMS;
import br.com.ararati.enums.O.NFeTipoSituacaoTributariaIPI;
import br.com.ararati.enums.Q.NFeTipoSituacaoTributariaPIS;
import br.com.ararati.enums.S.NFeTipoSituacaoTributariaCOFINS;
import br.com.ararati.enums.U.NFeTipoExigibilidadeISS;
import br.com.ararati.enums.U.NFeTipoIncentivoFiscal;
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

    // Número do item (1-990)
    @NotNull(message = "Número do Item é obrigatório")
    @Length(min = 1, max = 3, message = "Número do Item deve conter entre {min} e {max} caracteres")
    @Column(length = 3, nullable = false)
    private String nitem;

    /**
     * Código do produto ou serviço, preencher com CFOP, caso se trate de itens
     * não relacionados com mercadorias/produtos e que o contribuinte não possua
     * codificação própria. Formato: ”CFOP9999”
     */
    @NotNull(message = "Código é obrigatório")
    @Length(min = 1, max = 60, message = "Código deve conter entre {min} e {max} caracteres")
    @Column(length = 60, nullable = false)
    private String cprod;

    /**
     * GTIN (Global Trade Item Number) do produto, antigo código EAN ou código
     * de barras, preencher com o código GTIN-8, GTIN-12, GTIN-13 ou GTIN-14
     * (antigos códigos EAN, UPC e DUN-14), não informar o conteúdo da TAG em
     * caso de o produto não possuir este código.
     */
    @NotNull(message = "EAN é obrigatório")
    @Column(length = 14, nullable = false)
    private String cean;

    /**
     * Descrição do produto ou serviço
     */
    @NotNull(message = "Descrição é obrigatório")
    @Length(min = 1, max = 120, message = "Descrição deve conter entre {min} e {max} caracteres")
    @Column(length = 120, nullable = false)
    private String xprod;

    /**
     * Código NCM com 8 dígitos, obrigatória informação do NCM completo (8
     * dígitos). Nota: Em caso de item de serviço ou item que não tenham produto
     * (ex. transferência de crédito, crédito do ativo imobilizado, etc.),
     * informar o valor 00 (dois zeros). (NT 2014/004)
     */
    @NotNull(message = "NCM é obrigatório")
    @Column(length = 8, nullable = false)
    private String ncm;

    /**
     * Codificação NVE - Nomenclatura de Valor Aduaneiro e Estatística.
     * Codificação opcional que detalha alguns NCM. Formato: duas letras
     * maiúsculas e 4 algarismos. Se a mercadoria se enquadrar em mais de uma
     * codificação, informar até 8 codificações principais. Vide: Anexo XII.03 -
     * Identificador NVE.
     */
    @Valid
    @OneToMany(mappedBy = "detalhamentoProdutoServico", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<NomenclaturaValorAduaneiro> nves;

    /**
     * Preencher de acordo com o código EX da TIPI. Em caso de serviço, não
     * incluir a TAG.
     */
    @Length(min = 2, max = 3, message = "EX TIPI deve conter entre {min} e {max} caracteres")
    @Column(length = 3)
    private String extipi;

    /**
     * Código Especificador da Substituição Tributária – CEST, que estabelece a
     * sistemática de uniformização e identificação das mercadorias e bens
     * passíveis de sujeição aos regimes de substituição tributária e de
     * antecipação de recolhimento do ICMS
     */
    @Column(length = 7)
    private String cest;

    /**
     * Código Fiscal de Operações e Prestações
     */
    @NotNull(message = "CFOP é obrigatório")
    @Column(length = 4, nullable = false)
    private String cfop;

    /**
     * Unidade Comercial - Informar a unidade de comercialização do produto.
     */
    @NotNull(message = "UN Comercial é obrigatório")
    @Length(min = 1, max = 6, message = "UN Comercial deve conter entre {min} e {max} caracteres")
    @Column(length = 6, nullable = false)
    private String ucom;

    /**
     * Quantidade Comercial - Informar a quantidade de comercialização do
     * produto (v2.0).
     */
    @NotNull(message = "Quantidade Comercial é obrigatório")
    @DecimalMin(value = "0.0000")
    @Column(precision = 15, scale = 4, nullable = false)
    private BigDecimal qcom;

    // 
    /**
     * Valor Unitário de Comercialização, valor Total Bruto dos Produtos ou
     * Serviços, informar o valor unitário de comercialização do produto, campo
     * meramente informativo, o contribuinte pode utilizar a precisão desejada
     * (0-10 decimais). Para efeitos de cálculo, o valor unitário será obtido
     * pela divisão do valor do produto pela quantidade comercial.
     */
    @NotNull(message = "Valor Unitário de Comercialização é obrigatório")
    @DecimalMin(value = "0.0000")
    @Column(precision = 15, scale = 4, nullable = false)
    private BigDecimal vuncom;

    /**
     * Valor Total Bruto dos Produtos ou Serviços
     */
    @NotNull(message = "Valor Total Bruto é obrigatório")
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal vprod;

    /**
     * GTIN (Global Trade Item Number) da unidade tributável, antigo código EAN
     * ou código de barras, preencher com o código GTIN-8, GTIN-12, GTIN-13 ou
     * GTIN-14 (antigos códigos EAN, UPC e DUN-14) da unidade tributável do
     * produto, não informar o conteúdo da TAG em caso de o produto não possuir
     * este código.
     */
    @NotNull(message = "EAN Tributável é obrigatório")
    @Column(length = 14, nullable = false)
    private String ceantrib;

    /**
     * Unidade Tributável
     */
    @NotNull(message = "UN Tributável é obrigatório")
    @Column(length = 6, nullable = false)
    private String utrib;

    /**
     * Quantidade Tributável, informar a quantidade de tributação do produto
     */
    @NotNull(message = "Quantidade Tributável é obrigatório")
    @DecimalMin(value = "0.0000")
    @Column(precision = 15, scale = 4, nullable = false)
    private BigDecimal qtrib;

    /**
     * Valor Unitário de Tributação, iInformar o valor unitário de tributação do
     * produto, campo meramente informativo, o contribuinte pode utilizar a
     * precisão desejada (0-10 decimais). Para efeitos de cálculo, o valor
     * unitário será obtido pela divisão do valor do produto pela quantidade
     * tributável (NT 2013/003).
     */
    @NotNull(message = "Valor Unitário Tributável é obrigatório")
    @DecimalMin(value = "0.0000")
    @Column(precision = 15, scale = 4, nullable = false)
    private BigDecimal vuntrib;

    /**
     * Valor Total do Frete
     */
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2)
    private BigDecimal vfrete;

    /**
     * Valor Total do Seguro
     */
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2)
    private BigDecimal vseg;

    /**
     * Valor do Desconto
     */
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2)
    private BigDecimal vdesc;

    /**
     * Outras despesas acessórias
     */
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2)
    private BigDecimal voutro;

    /**
     * Indica se valor do Item (vProd) entra no valor total da NF-e (vProd)
     */
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
    @Column(length = 15)
    private String xped;
    // Item do Pedido de Compra
    @Column(length = 6)
    private String nitemped;

    /**
     * I07. Produtos e Serviços / Grupo Diversos // Número de controle da FCI -
     * Ficha de Conteúdo de Importação , Informação relacionada com a Resolução
     * 13/2012 do Senado Federal. Formato: Algarismos, letras maiúsculas de "A"
     * a "F" e o caractere hífen. Exemplo: B01F70AF-10BF-4B1F-848C-65FF57F616FE
     */
    @Column(length = 36)
    private String nfci;

    /* Grupo I80. Rastreabilidade de produto */
    // Obrigatório o preenchimento deste grupo no caso de medicamentos e produtos farmacêuticos.
    @Valid
    @OneToMany(mappedBy = "detalhamentoProdutoServico", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<RastreabilidadeProduto> rastreabilidadeDeProdutos;

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
    @Column(precision = 15, scale = 2)
    private BigDecimal vtottrib;

    /* N. Grupo Tributação do ICMS */
    // Origem da mercadoria
    @NotNull(message = "Origem da Mercadoria é obrigatório")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NFeTipoOrigemMercadoria orig;
    // Tributação do ICMS
    @Enumerated(EnumType.STRING)
    private NFeTipoSituacaoTributariaICMS csticms;

    /* INFORMACOES ICMS */
    // Modalidade de determinação da BC do ICMS
    @Enumerated(EnumType.STRING)
    private NFeTipoModalidadeBCICMS modbcicms;
    // Alíquota do ICMS
    @DecimalMin(value = "0.0000", message = "Valor mínimo permitido: {value}")
    @Column(precision = 7, scale = 4)
    private BigDecimal picms;
    // Valor da BC do ICMS 
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vbcicms;
    // Valor do ICMS
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vicms;
    // Percentual da Redução de BC do ICMS
    @DecimalMin(value = "0.0000", message = "Valor mínimo permitido: {value}")
    @Column(precision = 7, scale = 4)
    private BigDecimal predbcicms;
    // Valor do ICMS da Operação 
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vicmsop;
    // Percentual do diferimento 
    @DecimalMin(value = "0.0000", message = "Valor mínimo permitido: {value}")
    @Column(precision = 7, scale = 4)
    private BigDecimal pdif;
    // Valor do ICMS diferido
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vicmsdif;
    // Valor do ICMS desonerado
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vicmsdeson;
    // Motivo da desoneração do ICMS
    @Enumerated(EnumType.STRING)
    private NFeTipoMotivoDesoneracaoICMS motdesicms;
    // Alíquota aplicável de cálculo do crédito (SIMPLES NACIONAL).
    @DecimalMin(value = "0.0000", message = "Valor mínimo permitido: {value}")
    @Column(precision = 7, scale = 4)
    private BigDecimal pcredsn;
    // Valor crédito do ICMS que pode ser aproveitado nos termos do art. 23 da LC 123 (SIMPLES NACIONAL)
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vcredicmssn;
    // Valor da Base de Cálculo do FCP
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vbcfcp;
    // Percentual do ICMS relativo ao Fundo de Combate à Pobreza (FCP)
    @DecimalMin(value = "0.0000", message = "Valor mínimo permitido: {value}")
    @Column(precision = 7, scale = 4)
    private BigDecimal pfcp;
    // Valor do ICMS relativo ao Fundo de Combate à Pobreza (FCP)
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vfcp;

    /* INFORMACOES ICMSST */
    // Modalidade de determinação da BC do ICMS ST
    @Enumerated(EnumType.STRING)
    private NFeTipoModalidadeBCICMSST modbcicmsst;
    // Alíquota do ICMS ST
    @DecimalMin(value = "0.0000", message = "Valor mínimo permitido: {value}")
    @Column(precision = 7, scale = 4)
    private BigDecimal picmsst;
    // Valor da BC do ICMS ST
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vbcicmsst;
    // Valor do ICMS ST
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vicmsst;
    // Percentual da margem de valor Adicionado do ICMS ST
    @DecimalMin(value = "0.0000", message = "Valor mínimo permitido: {value}")
    @Column(precision = 7, scale = 4)
    private BigDecimal pmvast;
    // Percentual da Redução de BC do ICMS ST
    @DecimalMin(value = "0.0000", message = "Valor mínimo permitido: {value}")
    @Column(precision = 7, scale = 4)
    private BigDecimal predbcicmsst;
    // Valor da BC do ICMS ST retido
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vbcicmsstret;
    // Valor do ICMS ST retido 
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vicmsstret;
    // Valor da BC do ICMS ST da UF destino
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vbcicmsstdest;
    // Valor do ICMS ST da UF destino
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vicmsstdest;
    // Valor da Base de Cálculo do FCP retido anteriormente
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vbcfcpstret;
    // Percentual do FCP retido anteriormente por Substituição Tributária
    @DecimalMin(value = "0.0000", message = "Valor mínimo permitido: {value}")
    @Column(precision = 7, scale = 4)
    private BigDecimal pfcpstret;
    // Valor do FCP ST retido por Substituição Tributária
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vfcpstret;
    /* Alíquota suportada pelo Consumidor Final - Deve ser informada a alíquota do cálculo do ICMS-ST, já
    incluso o FCP. Exemplo: alíquota da mercadoria na venda
    ao consumidor final = 18% e 2% de FCP. A alíquota a ser
    informada no campo pST deve ser 20%. icms60, icmssn500 */
    @DecimalMin(value = "0.0000", message = "Valor mínimo permitido: {value}")
    @Column(precision = 7, scale = 4)
    private BigDecimal pst;
    // Valor da Base de Cálculo ST do FCP
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vbcfcpst;
    // Percentual do FCP ST retido por Substituição Tributária 
    @DecimalMin(value = "0.0000", message = "Valor mínimo permitido: {value}")
    @Column(precision = 7, scale = 4)
    private BigDecimal pfcpst;
    // Valor do FCP ST retido por Substituição Tributária
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vfcpst;
    // Percentual da BC operação própria
    @DecimalMin(value = "0.0000", message = "Valor mínimo permitido: {value}")
    @Column(precision = 7, scale = 4)
    private BigDecimal pbcop;
    // UF para qual é devido o ICMS ST
    @Column(length = 2)
    private String ufst;

    /* NA. ICMS para a UF de destino */
    // Valor da BC do ICMS na UF de destino
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vbcicmsufdest;
    // Valor da BC FCP na UF de destino
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vbcfcpufdest;
    // Percentual do ICMS relativo ao Fundo de Combate à Pobreza (FCP) na UF de destino
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal pfcpufdest;
    // Alíquota interna da UF de destino
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal picmsufdest;
    // Alíquota interestadual das UF envolvidas
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal picmsinter;
    // Percentual provisório de partilha do ICMS Interestadual
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal picmsinterpart;
    // Valor do ICMS relativo ao Fundo de Combate à Pobreza (FCP) da UF de destino
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vfcpufdest;
    // Valor do ICMS Interestadual para a UF de destino
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vicmsufdest;
    // Valor do ICMS Interestadual para a UF do remetente
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vicmsufremet;

    /* O. Grupo Tributação do IPI */
    // Código da situação tributária do IPI 
    @Enumerated(EnumType.STRING)
    private NFeTipoSituacaoTributariaIPI cstipi;
    // Classe de enquadramento do IPI para Cigarros e Bebidas
    @Length(min = 1, max = 5, message = "Classe de enquadramento do IPI deve conter entre {min} e {max} caracteres")
    @Column(length = 5)
    private String clenq;
    // CNPJ do produtor da mercadoria, quando diferente do emitente. Somente para os casos de exportação direta ou indireta
    @Column(length = 14)
    private String cnpjprod;
    // Código do selo de controle IPI 
    // @NotNull(message = "Número do Item é obrigatório")
    @Length(min = 1, max = 60, message = "Código do selo de controle IPI deve conter entre {min} e {max} caracteres")
    @Column(length = 60)
    private String cselo;
    // Quantidade de selo de controle
    @DecimalMin(value = "1", message = "Valor mínimo permitido: {value}")
    @Column(length = 12)
    private String qselo;
    // Código de Enquadramento Legal do IPI 
    @Length(min = 1, max = 3, message = "Código de Enquadramento Legal do IPI deve conter entre {min} e {max} caracteres")
    @Column(length = 3)
    private String cenq;
    // Valor da BC do IPI
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vbcipi;
    // Alíquota do IPI
    @DecimalMin(value = "0.0000", message = "Valor mínimo permitido: {value}")
    @Column(precision = 7, scale = 4)
    private BigDecimal pipi;
    // Quantidade total na unidade padrão para tributação (somente para os produtos tributados por unidade)
    @DecimalMin(value = "0.0000", message = "Valor mínimo permitido: {value}")
    @Column(precision = 16, scale = 4)
    private BigDecimal qunid;
    // Valor por Unidade Tributável 
    @DecimalMin(value = "0.0000", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 4)
    private BigDecimal vunid;
    // Valor do IPI
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vipi;

    /* P. Grupo Tributação do II */
    // Valor BC do Imposto de Importação 
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vbcii;
    // Valor despesas aduaneiras 
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vdespadu;
    // Valor Imposto de Importação 
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vii;
    // Valor Imposto sobre Operações Financeiras 
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal viof;

    /* Q. Grupo Tributação do PIS */
    // Código de Situação Tributária do PIS 
    @Enumerated(EnumType.STRING)
    private NFeTipoSituacaoTributariaPIS cstpis;
    // Valor da Base de Cálculo do PIS
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vbcpis;
    // Alíquota do PIS (em percentual) 
    @DecimalMin(value = "0.0000", message = "Valor mínimo permitido: {value}")
    @Column(precision = 7, scale = 4)
    private BigDecimal ppis;
    // Valor do PIS
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vpis;
    // Quantidade Vendida
    @DecimalMin(value = "0.0000", message = "Valor mínimo permitido: {value}")
    @Column(precision = 16, scale = 4)
    private BigDecimal qbcprodpis;
    // Alíquota do PIS (em reais) 
    @DecimalMin(value = "0.0000", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 4)
    private BigDecimal valiqprodpis;

    /* S. Grupo Tributação do COFINS */
    // Código de Situação Tributária da COFINS
    @Enumerated(EnumType.STRING)
    private NFeTipoSituacaoTributariaCOFINS cstcofins;
    // Valor da Base de Cálculo da COFINS 
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vbccofins;
    // Alíquota da COFINS (em percentual) 
    @DecimalMin(value = "0.0000", message = "Valor mínimo permitido: {value}")
    @Column(precision = 7, scale = 4)
    private BigDecimal pcofins;
    // Valor da COFINS
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vcofins;
    // Quantidade Vendida
    @DecimalMin(value = "0.0000", message = "Valor mínimo permitido: {value}")
    @Column(precision = 16, scale = 4)
    private BigDecimal qbcprodcofins;
    // Alíquota da COFINS (em reais) 
    @DecimalMin(value = "0.0000", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 4)
    private BigDecimal valiqprodcofins;

    /* U. Grupo Tributação do ISSQN */
    // Valor da Base de Cálculo do ISSQN
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vbcissqn;
    // Alíquota do ISSQN
    @DecimalMin(value = "0.0000", message = "Valor mínimo permitido: {value}")
    @Column(precision = 7, scale = 4)
    private BigDecimal valiqissqn;
    // Valor do ISSQN
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vissqn;
    // Código do município de ocorrência do fato gerador do ISSQN
    @Column(length = 7)
    private String cmunfg;
    // Item da Lista de Serviços 
    @Enumerated(EnumType.STRING)
    private NFeTipoServico clistserv;
    // Valor dedução para redução da Base de Calculo
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vdeducao;
    // Valor outras retenções 
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal voutroissqn;
    // Valor desconto incondicionado 
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vdescincond;
    // Valor desconto condicionado 
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vdesccond;
    // Valor retenção ISS 
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vissret;
    // Indicador da exigibilidade do ISS 
    @Enumerated(EnumType.STRING)
    private NFeTipoExigibilidadeISS indiss;
    // Código do serviço prestado dentro do município
    @Length(min = 1, max = 20, message = "Código do serviço deve conter entre {min} e {max} caracteres")
    @Column(length = 20)
    private String cservico;
    // Código do Município de incidência do imposto
    @Length(min = 7, max = 7, message = "Código do Município deve conter {max} caracteres")
    @Column(length = 7)
    private String cmun;
    // Código do País onde o serviço foi prestado
    @Length(min = 4, max = 4, message = "Código do País deve conter {max} caracteres")
    @Column(length = 4)
    private String cpais;
    // Número do processo judicial ou administrativo de suspensão da exigibilidade
    @Length(min = 1, max = 30, message = "Número do processo judicial deve conter entre {min} e {max} caracteres")
    @Column(length = 30)
    private String nprocesso;
    // Indicador de incentivo Fiscal 
    @Enumerated(EnumType.STRING)
    private NFeTipoIncentivoFiscal indincentivo;

    /* UA. Tributos Devolvidos */
    // Percentual da mercadoria devolvida
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 5, scale = 2)
    private BigDecimal pdevol;
    // Valor do IPI devolvido
    @DecimalMin(value = "0.00", message = "Valor mínimo permitido: {value}")
    @Column(precision = 15, scale = 2)
    private BigDecimal vipidevol;

    /* V. Informações Adicionais */
    // Informações Adicionais do Produto
    @Length(min = 1, max = 500, message = "Informações Adicionais do Produto deve conter entre {min} e {max} caracteres")
    @Column(length = 500)
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
        this.picms = null;
        this.picmsst = null;
        this.modbcicms = null;
        this.modbcicmsst = null;
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
        this.predbcicms = null;
        this.predbcicmsst = null;
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
        this.vbcicmsufdest = null;
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
        vbcpis = null;
        ppis = null;
        vpis = null;
        qbcprodpis = null;
        valiqprodpis = null;
    }

    public void limpaDadosCofins() {
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

    public List<NomenclaturaValorAduaneiro> getNves() {
        return nves;
    }

    public void setNves(List<NomenclaturaValorAduaneiro> nves) {
        this.nves = nves;
    }

    public String getExtipi() {
        return extipi;
    }

    public void setExtipi(String extipi) {
        this.extipi = extipi;
    }

    public String getCest() {
        return cest;
    }

    public void setCest(String cest) {
        this.cest = cest;
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

    public String getNfci() {
        return nfci;
    }

    public void setNfci(String nfci) {
        this.nfci = nfci;
    }

    public List<RastreabilidadeProduto> getRastreabilidadeDeProdutos() {
        return rastreabilidadeDeProdutos;
    }

    public void setRastreabilidadeDeProdutos(List<RastreabilidadeProduto> rastreabilidadeDeProdutos) {
        this.rastreabilidadeDeProdutos = rastreabilidadeDeProdutos;
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

    public NFeTipoOrigemMercadoria getOrig() {
        return orig;
    }

    public void setOrig(NFeTipoOrigemMercadoria orig) {
        this.orig = orig;
    }

    public NFeTipoSituacaoTributariaICMS getCsticms() {
        return csticms;
    }

    public void setCsticms(NFeTipoSituacaoTributariaICMS csticms) {
        this.csticms = csticms;
    }

    public NFeTipoModalidadeBCICMS getModbcicms() {
        return modbcicms;
    }

    public void setModbcicms(NFeTipoModalidadeBCICMS modbcicms) {
        this.modbcicms = modbcicms;
    }

    public BigDecimal getPicms() {
        return picms;
    }

    public void setPicms(BigDecimal picms) {
        this.picms = picms;
    }

    public BigDecimal getVbcicms() {
        return vbcicms;
    }

    public void setVbcicms(BigDecimal vbcicms) {
        this.vbcicms = vbcicms;
    }

    public BigDecimal getVicms() {
        return vicms;
    }

    public void setVicms(BigDecimal vicms) {
        this.vicms = vicms;
    }

    public BigDecimal getPredbcicms() {
        return predbcicms;
    }

    public void setPredbcicms(BigDecimal predbcicms) {
        this.predbcicms = predbcicms;
    }

    public BigDecimal getVicmsop() {
        return vicmsop;
    }

    public void setVicmsop(BigDecimal vicmsop) {
        this.vicmsop = vicmsop;
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

    public BigDecimal getVbcfcp() {
        return vbcfcp;
    }

    public void setVbcfcp(BigDecimal vbcfcp) {
        this.vbcfcp = vbcfcp;
    }

    public BigDecimal getPfcp() {
        return pfcp;
    }

    public void setPfcp(BigDecimal pfcp) {
        this.pfcp = pfcp;
    }

    public BigDecimal getVfcp() {
        return vfcp;
    }

    public void setVfcp(BigDecimal vfcp) {
        this.vfcp = vfcp;
    }

    public NFeTipoModalidadeBCICMSST getModbcicmsst() {
        return modbcicmsst;
    }

    public void setModbcicmsst(NFeTipoModalidadeBCICMSST modbcicmsst) {
        this.modbcicmsst = modbcicmsst;
    }

    public BigDecimal getPicmsst() {
        return picmsst;
    }

    public void setPicmsst(BigDecimal picmsst) {
        this.picmsst = picmsst;
    }

    public BigDecimal getVbcicmsst() {
        return vbcicmsst;
    }

    public void setVbcicmsst(BigDecimal vbcicmsst) {
        this.vbcicmsst = vbcicmsst;
    }

    public BigDecimal getVicmsst() {
        return vicmsst;
    }

    public void setVicmsst(BigDecimal vicmsst) {
        this.vicmsst = vicmsst;
    }

    public BigDecimal getPmvast() {
        return pmvast;
    }

    public void setPmvast(BigDecimal pmvast) {
        this.pmvast = pmvast;
    }

    public BigDecimal getPredbcicmsst() {
        return predbcicmsst;
    }

    public void setPredbcicmsst(BigDecimal predbcicmsst) {
        this.predbcicmsst = predbcicmsst;
    }

    public BigDecimal getVbcicmsstret() {
        return vbcicmsstret;
    }

    public void setVbcicmsstret(BigDecimal vbcicmsstret) {
        this.vbcicmsstret = vbcicmsstret;
    }

    public BigDecimal getVicmsstret() {
        return vicmsstret;
    }

    public void setVicmsstret(BigDecimal vicmsstret) {
        this.vicmsstret = vicmsstret;
    }

    public BigDecimal getVbcicmsstdest() {
        return vbcicmsstdest;
    }

    public void setVbcicmsstdest(BigDecimal vbcicmsstdest) {
        this.vbcicmsstdest = vbcicmsstdest;
    }

    public BigDecimal getVicmsstdest() {
        return vicmsstdest;
    }

    public void setVicmsstdest(BigDecimal vicmsstdest) {
        this.vicmsstdest = vicmsstdest;
    }

    public BigDecimal getVbcfcpstret() {
        return vbcfcpstret;
    }

    public void setVbcfcpstret(BigDecimal vbcfcpstret) {
        this.vbcfcpstret = vbcfcpstret;
    }

    public BigDecimal getPfcpstret() {
        return pfcpstret;
    }

    public void setPfcpstret(BigDecimal pfcpstret) {
        this.pfcpstret = pfcpstret;
    }

    public BigDecimal getVfcpstret() {
        return vfcpstret;
    }

    public void setVfcpstret(BigDecimal vfcpstret) {
        this.vfcpstret = vfcpstret;
    }

    public BigDecimal getPst() {
        return pst;
    }

    public void setPst(BigDecimal pst) {
        this.pst = pst;
    }

    public BigDecimal getVbcfcpst() {
        return vbcfcpst;
    }

    public void setVbcfcpst(BigDecimal vbcfcpst) {
        this.vbcfcpst = vbcfcpst;
    }

    public BigDecimal getPfcpst() {
        return pfcpst;
    }

    public void setPfcpst(BigDecimal pfcpst) {
        this.pfcpst = pfcpst;
    }

    public BigDecimal getVfcpst() {
        return vfcpst;
    }

    public void setVfcpst(BigDecimal vfcpst) {
        this.vfcpst = vfcpst;
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

    public BigDecimal getVbcicmsufdest() {
        return vbcicmsufdest;
    }

    public void setVbcicmsufdest(BigDecimal vbcicmsufdest) {
        this.vbcicmsufdest = vbcicmsufdest;
    }

    public BigDecimal getVbcfcpufdest() {
        return vbcfcpufdest;
    }

    public void setVbcfcpufdest(BigDecimal vbcfcpufdest) {
        this.vbcfcpufdest = vbcfcpufdest;
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

    public NFeTipoIncentivoFiscal getIndincentivo() {
        return indincentivo;
    }

    public void setIndincentivo(NFeTipoIncentivoFiscal indincentivo) {
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
