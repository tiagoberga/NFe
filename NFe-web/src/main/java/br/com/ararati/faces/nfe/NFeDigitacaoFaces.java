/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.faces.nfe;

import br.com.ararati.Cep;
import br.com.ararati.ConsultaCepException;
import br.com.ararati.entity.cadastros.Destinatario;
import br.com.ararati.entity.cadastros.Emitente;
import br.com.ararati.entity.cadastros.Endereco;
import br.com.ararati.entity.cadastros.Produto;
import br.com.ararati.entity.cadastros.Transportadora;
import br.com.ararati.entity.commons.TabelaCEST;
import br.com.ararati.entity.commons.TabelaCFOP;
import br.com.ararati.entity.commons.TabelaNCM;
import br.com.ararati.entity.nfe.emissao.AquisicaoCana;
import br.com.ararati.entity.nfe.emissao.AquisicaoCanaDeducao;
import br.com.ararati.entity.nfe.emissao.AquisicaoCanaFornecimento;
import br.com.ararati.entity.nfe.emissao.AutorizacaoObterXml;
import br.com.ararati.entity.nfe.emissao.CobrancaDuplicata;
import br.com.ararati.entity.nfe.emissao.CobrancaFatura;
import br.com.ararati.entity.nfe.emissao.ComercioExterior;
import br.com.ararati.entity.nfe.emissao.Compra;
import br.com.ararati.entity.nfe.emissao.DadosNFe;
import br.com.ararati.entity.nfe.emissao.DeclaracaoImportacao;
import br.com.ararati.entity.nfe.emissao.DeclaracaoImportacaoAdicoes;
import br.com.ararati.entity.nfe.emissao.DetalhamentoEspecificoArmamento;
import br.com.ararati.entity.nfe.emissao.DetalhamentoEspecificoCombustivel;
import br.com.ararati.entity.nfe.emissao.DetalhamentoEspecificoMedicamento;
import br.com.ararati.entity.nfe.emissao.DetalhamentoEspecificoPapelImune;
import br.com.ararati.entity.nfe.emissao.DetalhamentoEspecificoVeiculo;
import br.com.ararati.entity.nfe.emissao.DetalhamentoProdutoServico;
import br.com.ararati.entity.nfe.emissao.DocumentoFiscalReferenciado;
import br.com.ararati.entity.nfe.emissao.GrupoDiverso;
import br.com.ararati.entity.nfe.emissao.GrupoExportacao;
import br.com.ararati.entity.nfe.emissao.IdentificacaoAvulsa;
import br.com.ararati.entity.nfe.emissao.IdentificacaoDestinatario;
import br.com.ararati.entity.nfe.emissao.IdentificacaoEmitente;
import br.com.ararati.entity.nfe.emissao.IdentificacaoLocalEntrega;
import br.com.ararati.entity.nfe.emissao.IdentificacaoLocalRetirada;
import br.com.ararati.entity.nfe.emissao.IdentificacaoNFe;
import br.com.ararati.entity.nfe.emissao.InformacoesAdicionais;
import br.com.ararati.entity.nfe.emissao.InformacoesAdicionaisContribuinte;
import br.com.ararati.entity.nfe.emissao.InformacoesAdicionaisFisco;
import br.com.ararati.entity.nfe.emissao.InformacoesAdicionaisProcessoReferenciado;
import br.com.ararati.entity.nfe.emissao.NomenclaturaValorAduaneiro;
import br.com.ararati.entity.nfe.emissao.TotalNFe;
import br.com.ararati.entity.nfe.emissao.TransporteLacre;
import br.com.ararati.entity.nfe.emissao.TransporteNFe;
import br.com.ararati.entity.nfe.emissao.TransporteReboque;
import br.com.ararati.entity.nfe.emissao.TransporteRetencaoIcms;
import br.com.ararati.entity.nfe.emissao.TransporteVeiculo;
import br.com.ararati.entity.nfe.emissao.TransporteVolume;
import br.com.ararati.enums.B.NFeTipoAmbiente;
import br.com.ararati.enums.B.NFeTipoDestinoOperacao;
import br.com.ararati.enums.B.NFeTipoEmissao;
import br.com.ararati.enums.B.NFeTipoFinalidadeEmissao;
import br.com.ararati.enums.B.NFeTipoFormatoImpressao;
import br.com.ararati.enums.B.NFeTipoModeloDocumentoFiscalECF;
import br.com.ararati.enums.B.NFeTipoModeloDocumentoFiscal;
import br.com.ararati.enums.B.NFeTipoModeloDocumentoFiscalProdutorRural;
import br.com.ararati.enums.B.NFeTipoOperacao;
import br.com.ararati.enums.B.NFeTipoOperacaoConsumidor;
import br.com.ararati.enums.B.NFeTipoPresencaCompradorOperacao;
import br.com.ararati.enums.B.NFeTipoProcessoEmissao;
import br.com.ararati.enums.C.NFeTipoRegimeTributario;
import br.com.ararati.enums.E.NFeTipoIndicadorIEDestinatario;
import br.com.ararati.enums.I.NFeTipoGrupoTributacaoICMS;
import br.com.ararati.enums.I.NFeTipoImportacao;
import br.com.ararati.enums.I.NFeTipoIndicadorVProd;
import br.com.ararati.enums.I.NFeTipoProdutoEspecifico;
import br.com.ararati.enums.I.NFeTipoViaTransporte;
import br.com.ararati.enums.ISSQN.NFeTipoServico;
import br.com.ararati.enums.J.NFeTipoVeiculosNovosCombustivel;
import br.com.ararati.enums.J.NFeTipoVeiculosNovosCondicao;
import br.com.ararati.enums.J.NFeTipoVeiculosNovosCondicaoVIN;
import br.com.ararati.enums.J.NFeTipoVeiculosNovosCor;
import br.com.ararati.enums.J.NFeTipoVeiculosNovosEspecie;
import br.com.ararati.enums.J.NFeTipoVeiculosNovosOperacao;
import br.com.ararati.enums.J.NFeTipoVeiculosNovosRestricao;
import br.com.ararati.enums.J.NFeTipoVeiculosNovosVeiculo;
import br.com.ararati.enums.L.NFeTipoArma;
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
import br.com.ararati.enums.W.NFeTipoRegimeEspecialTributacao;
import br.com.ararati.enums.X.NFeTipoModalidadeFrete;
import br.com.ararati.enums.Y.NFeTipoBandeiraOperadoraCartaoCredito;
import br.com.ararati.enums.Y.NFeTipoFormaPagamento;
import br.com.ararati.enums.Z.NFeTipoOrigemProcesso;
import br.com.ararati.enums.commons.NFeTipoCalculo;
import br.com.ararati.enums.commons.NFeTipoDistribuicao;
import br.com.ararati.enums.commons.NFeTipoDocumento;
import br.com.ararati.enums.commons.NFeTipoEvento;
import br.com.ararati.enums.commons.NFeTipoManifesto;
import br.com.ararati.enums.commons.NFeTipoNSU;
import br.com.ararati.enums.commons.NFeTipoTributacao;
import br.com.ararati.enums.commons.NFeTipoVeiculo;
import br.com.ararati.exception.DaoException;
import br.com.ararati.exception.NFeException;
import br.com.ararati.faces.UtilFaces;
import br.com.ararati.faces.cadastros.DestinatarioFaces;
import br.com.ararati.faces.cadastros.EmitenteFaces;
import br.com.ararati.populadores.impl.EnviNFe;
import br.com.ararati.session.cadastros.DestinatarioFacade;
import br.com.ararati.session.cadastros.EmitenteFacade;
import br.com.ararati.session.cadastros.ProdutoFacade;
import br.com.ararati.session.cadastros.TransportadoraFacade;
import br.com.ararati.session.commons.TabelaCESTFacade;
import br.com.ararati.session.commons.TabelaCFOPFacade;
import br.com.ararati.session.commons.TabelaNCMFacade;
import br.com.ararati.session.nfe.DadosNFeFacade;
import br.com.ararati.webservices.cep.WSCepFacade;
import enviNFe_v310.TUf;
import enviNFe_v310.TUfEmi;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.xml.bind.JAXBException;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.SelectEvent;

@Named(value = "digitacaoNFeFaces")
@ViewScoped
public class NFeDigitacaoFaces implements Serializable {

    @EJB
    private DadosNFeFacade dadosNFeFacade;
    @EJB
    private DestinatarioFacade destinatarioFacade;
    @EJB
    private EmitenteFacade emitenteFacade;
    @EJB
    private ProdutoFacade produtoFacade;
    @EJB
    private TabelaCESTFacade cestFacade;
    @EJB
    private TabelaCFOPFacade cfopFacade;
    @EJB
    private TabelaNCMFacade ncmFacade;
    @EJB
    private TransportadoraFacade transpotadoraFacade;

    // VARIAVEIS DE OBJETOS NFE
    private AquisicaoCana aquisicaoCana;
    private AquisicaoCanaDeducao aquisicaoCanaDeducoes;
    private AquisicaoCanaFornecimento aquisicaoCanaFornecimento;
    private AutorizacaoObterXml autorizacaoObterXml;
    private CobrancaDuplicata cobrancaDuplicata;
    private CobrancaFatura cobrancaFatura;
    private ComercioExterior comercioExterior;
    private Compra compra;
    private DadosNFe dadosNFe;
    private DeclaracaoImportacao declaracaoImportacao;
    private DeclaracaoImportacaoAdicoes declaracaoImportacaoAdicao;
    private DetalhamentoEspecificoArmamento armamento;
    private DetalhamentoEspecificoCombustivel combustivel;
    private DetalhamentoEspecificoMedicamento medicamento;
    private DetalhamentoEspecificoPapelImune papelImune;
    private DetalhamentoEspecificoVeiculo veiculoEspecifico;
    private DetalhamentoProdutoServico produtoServico;
    private DocumentoFiscalReferenciado documentoFiscalReferenciado;
    private GrupoDiverso grupoDiverso;
    private GrupoExportacao grupoExportacao;
    private IdentificacaoAvulsa ideAvulsa;
    private IdentificacaoDestinatario ideDestinatario;
    private IdentificacaoEmitente ideEmitente;
    private IdentificacaoLocalEntrega ideLocalEntrega;
    private IdentificacaoLocalRetirada ideLocalRetirada;
    private IdentificacaoNFe ideNFe;
    private InformacoesAdicionais informacoesAdicionais;
    private InformacoesAdicionaisContribuinte informacoesAdicionaisContribuinte;
    private InformacoesAdicionaisFisco informacoesAdicionaisFisco;
    private InformacoesAdicionaisProcessoReferenciado processoReferenciado;
    private NomenclaturaValorAduaneiro nomenclaturaValorAduaneiro;
    private TotalNFe total;
    private TransporteLacre lacre;
    private TransporteNFe transporteNFe;
    private TransporteRetencaoIcms retencaoIcms;
    private TransporteReboque reboque;
    private TransporteVeiculo veiculo;
    private TransporteVolume volume;

    // VARIAVEIS PARA CONTROLE LOCAL
    private NFeTipoRegimeTributario icmsRegime = NFeTipoRegimeTributario.SIMPLES1;
    private NFeTipoProdutoEspecifico produtoEspecifico;
    private NFeTipoTributacao tributacao = NFeTipoTributacao.ICMS;
    private NFeTipoDocumento tipoDocumento = NFeTipoDocumento.NFE;
    private NFeTipoVeiculo tipoVeiculo = NFeTipoVeiculo.REBOQUE;
    private NFeTipoCalculo tipoCalculoIpi;
    private NFeTipoCalculo tipoCalculoPis;
    private NFeTipoCalculo tipoCalculoPisSt;
    private NFeTipoCalculo tipoCalculoCofins;
    private NFeTipoCalculo tipoCalculoCofinsSt;
    private Emitente emitenteLogado;
    private Produto produto;
    private Destinatario destinatario;
    private Transportadora transportadora;
    private boolean localRetiradaDiferenteEmitente = false;
    private boolean localEntregaDiferenteDestinatario = false;

    // LISTAS
    private List<TabelaCEST> cests = new ArrayList<>();
    private List<TabelaCFOP> cfops = new ArrayList<>();
    private List<TabelaNCM> ncms = new ArrayList<>();

    @PostConstruct
    public void init() {
        this.emitenteLogado = emitenteFacade.findAll().get(0);
        initNFe();
    }

    public void initNFe() {
        this.dadosNFe = new DadosNFe(this.emitenteLogado);
        this.dadosNFe.setVersao("3.10");
        this.dadosNFe.setIde("NFe00000000000000000000000000000000000000000000");

        this.aquisicaoCana = new AquisicaoCana();
        this.aquisicaoCanaDeducoes = new AquisicaoCanaDeducao();
        this.aquisicaoCanaFornecimento = new AquisicaoCanaFornecimento();
        this.autorizacaoObterXml = new AutorizacaoObterXml();
        this.cobrancaDuplicata = new CobrancaDuplicata();
        this.cobrancaFatura = new CobrancaFatura();
        this.comercioExterior = new ComercioExterior();
        this.compra = new Compra();
        this.declaracaoImportacao = new DeclaracaoImportacao();
        this.declaracaoImportacaoAdicao = new DeclaracaoImportacaoAdicoes();
        this.armamento = new DetalhamentoEspecificoArmamento();
        this.combustivel = new DetalhamentoEspecificoCombustivel();
        this.medicamento = new DetalhamentoEspecificoMedicamento();
        this.papelImune = new DetalhamentoEspecificoPapelImune();
        this.veiculoEspecifico = new DetalhamentoEspecificoVeiculo();
        this.produtoServico = new DetalhamentoProdutoServico();
        this.documentoFiscalReferenciado = new DocumentoFiscalReferenciado();
        this.grupoDiverso = new GrupoDiverso();
        this.grupoExportacao = new GrupoExportacao();
        this.ideAvulsa = new IdentificacaoAvulsa();
        this.ideDestinatario = new IdentificacaoDestinatario();
        this.ideEmitente = new IdentificacaoEmitente(emitenteLogado);
        this.ideLocalEntrega = new IdentificacaoLocalEntrega();

        this.localRetiradaDiferenteEmitente = emitenteLogado.isLocalRetiradaDiferenteEmitente();
        this.ideLocalRetirada = new IdentificacaoLocalRetirada(emitenteLogado.getLocalRetirada());

        this.ideNFe = new IdentificacaoNFe();
        this.ideNFe.setTpamb(NFeTipoAmbiente.PRODUCAO);
        this.ideNFe.setCdv("1");
        this.ideNFe.setCnf("1");
        this.ideNFe.setVerproc("1");
        this.ideNFe.setProcemi(NFeTipoProcessoEmissao.AVULSO_CONTRIBUINTE);

        this.informacoesAdicionais = new InformacoesAdicionais();
        this.informacoesAdicionaisContribuinte = new InformacoesAdicionaisContribuinte();
        this.informacoesAdicionaisFisco = new InformacoesAdicionaisFisco();
        this.processoReferenciado = new InformacoesAdicionaisProcessoReferenciado();
        this.nomenclaturaValorAduaneiro = new NomenclaturaValorAduaneiro();
        this.total = new TotalNFe();
        this.lacre = new TransporteLacre();
        this.transporteNFe = new TransporteNFe();
        this.retencaoIcms = new TransporteRetencaoIcms();
        this.reboque = new TransporteReboque();
        this.veiculo = new TransporteVeiculo();
        this.volume = new TransporteVolume();
    }

    public void gerarxml() {
        this.dadosNFe.setAquisicaoCana(aquisicaoCana);
        this.dadosNFe.setCobrancaFatura(cobrancaFatura);
        this.dadosNFe.setComercioExterior(comercioExterior);
        this.dadosNFe.setCompra(compra);
        this.dadosNFe.setIdentificacaoAvulsa(ideAvulsa);
        this.dadosNFe.setIdentificacaoDestinatario(ideDestinatario);
        this.dadosNFe.setIdentificacaoEmitente(ideEmitente);
        this.dadosNFe.setIdentificacaoLocalEntrega(ideLocalEntrega);
        this.dadosNFe.setIdentificacaoLocalRetirada(ideLocalRetirada);
        this.dadosNFe.setIdentificacaoNFe(ideNFe);
        this.dadosNFe.setInformacoesAdicionais(informacoesAdicionais);
        this.dadosNFe.setTotalNFe(total);
        this.dadosNFe.setTransporteNFe(transporteNFe);

        try {
            String textt = new EnviNFe().fillTEnviNFe(dadosNFe);
            System.out.println(textt);
        } catch (NFeException | JAXBException ex) {
            UtilFaces.showDialogMessageError(ex.getMessage());
        }
    }

    public void salvar() {
        try {
            // SALVA NFE
            this.dadosNFe.setIdentificacaoNFe(this.ideNFe);
            this.dadosNFe.setIdentificacaoEmitente(this.ideEmitente);
            this.dadosNFe.setIdentificacaoDestinatario(this.ideDestinatario);
//            this.dadosNFe.setTotalNFe(this.total);
            this.dadosNFeFacade.save(this.dadosNFe);
            UtilFaces.showDialogMessageInfo("Nota Fiscal Nº" + dadosNFe.getIdentificacaoNFe().getNnf() + " salva com sucesso.");
            this.dadosNFe = new DadosNFe();
        } catch (DaoException ex) {
            Logger.getLogger(NFeDigitacaoFaces.class.getName()).log(Level.SEVERE, null, ex);
            UtilFaces.showDialogMessageError(ex.getMessage());
        }
    }

    /**
     * Método busca cep do emitente.
     */
    public void buscaCepEmitente() {
        try {
            String cep = this.ideEmitente.getEndereco().getEndCep();
            // ENCONTRA CEP
            Cep cepFound = new WSCepFacade().getCepFromXml(cep);
            Endereco endereco = new Endereco(cepFound);
            this.ideEmitente.setEndereco(endereco);
        } catch (ConsultaCepException ex) {
            Logger.getLogger(EmitenteFaces.class.getName()).log(Level.SEVERE, null, ex);
            UtilFaces.showDialogMessageError(ex.getMessage());
        }
    }

    /**
     * Método busca cep do emitente.
     */
    public void buscaCepEmitenteLocalRetirada() {
        try {
            if (this.ideLocalRetirada.getLocal() != null) {
                String cep = this.ideLocalRetirada.getLocal().getLocCep();
                String documento = this.ideLocalRetirada.getLocal().getLocDocumento();
                // ENCONTRA CEP
                Cep cepFound = new WSCepFacade().getCepFromXml(cep);
                this.ideLocalRetirada = new IdentificacaoLocalRetirada(cepFound, documento);
            }
        } catch (ConsultaCepException ex) {
            Logger.getLogger(EmitenteFaces.class.getName()).log(Level.SEVERE, null, ex);
            UtilFaces.showDialogMessageError(ex.getMessage());
        }
    }

    /**
     * Método busca cep do destinatario.
     */
    public void buscaCepDestinatario() {
        try {
            String cep = this.ideDestinatario.getEndereco().getEndCep();
            // ENCONTRA CEP
            Cep cepFound = new WSCepFacade().getCepFromXml(cep);
            Endereco endereco = new Endereco(cepFound);
            this.ideDestinatario.setEndereco(endereco);
        } catch (ConsultaCepException ex) {
            Logger.getLogger(DestinatarioFaces.class.getName()).log(Level.SEVERE, null, ex);
            UtilFaces.showDialogMessageError(ex.getMessage());
        }
    }

    /**
     * Método busca cep do emitente.
     */
    public void buscaCepDestinatarioLocalEntrega() {
        try {
            if (this.ideLocalEntrega != null) {
                String cep = this.ideLocalEntrega.getLocal().getLocCep();
                String documento = this.ideLocalEntrega.getLocal().getLocDocumento();
                // ENCONTRA CEP
                Cep cepFound = new WSCepFacade().getCepFromXml(cep);
                this.ideLocalEntrega = new IdentificacaoLocalEntrega(cepFound, documento);
            }
        } catch (ConsultaCepException ex) {
            Logger.getLogger(EmitenteFaces.class.getName()).log(Level.SEVERE, null, ex);
            UtilFaces.showDialogMessageError(ex.getMessage());
        }
    }

    /**
     * Método utilizado com auto complete para busca de destinatario.
     */
    public List<Destinatario> completeDestinatario(String query) {
        return this.destinatarioFacade.findBy(query, this.emitenteLogado);
    }

    /**
     * Método utilizado com auto complete para busca de transportadora.
     */
    public List<Transportadora> completeTransportadora(String query) {
        return this.transpotadoraFacade.findBy(query, this.emitenteLogado);
    }

    /**
     * Método utilizado com auto complete para busca de produto.
     */
    public List<Produto> completeProduto(String query) {
        return this.produtoFacade.findBy(query, this.emitenteLogado);
    }

    /**
     * Método vincula o produto encontrado ao objeto de produto da nfe.
     */
    public void onProdutoSelect(SelectEvent event) {
        this.produtoServico = new DetalhamentoProdutoServico(this.produto);
    }

    /**
     * Método vincula o destinatario encontrado ao objeto de produto da nfe.
     */
    public void onDestinatarioSelect(SelectEvent event) {
        this.ideDestinatario = new IdentificacaoDestinatario(this.destinatario);
        this.localEntregaDiferenteDestinatario = this.destinatario.isLocalEntregaDiferenteDestinatario();
        this.ideLocalEntrega = new IdentificacaoLocalEntrega(this.destinatario.getLocalEntrega());
    }

    /**
     * Método vincula transportador encontrado ao objeto de produto da nfe.
     */
    public void onTransportadoraSelect(SelectEvent event) {
        this.transporteNFe = new TransporteNFe(this.transportadora);
    }

    /**
     * Calcula valor de VProd
     */
    public void calculaVProd() {
        double vprod = 0;

        if (this.produtoServico == null) {
            // nao calcula
        } else {
            if (this.produtoServico.getQcom() != null && this.produtoServico.getVuncom() != null) {
                vprod = this.produtoServico.getQcom().doubleValue() * this.produtoServico.getVuncom().doubleValue();
            }

            if (this.produtoServico.getVfrete() != null) {
                vprod += this.produtoServico.getVfrete().doubleValue();
            }

            if (this.produtoServico.getVseg() != null) {
                vprod += this.produtoServico.getVseg().doubleValue();
            }

            if (this.produtoServico.getVoutro() != null) {
                vprod += this.produtoServico.getVoutro().doubleValue();
            }

            if (this.produtoServico.getVdesc() != null) {
                vprod -= this.produtoServico.getVdesc().doubleValue();
            }

            this.produtoServico.setVprod(new BigDecimal(vprod));
        }
    }

    /**
     * Adiciona nova duplicata de cobrança à lista.
     */
    public void addCobrancaDuplicata() {
        try {
            this.dadosNFe.addCobrancaDuplicata(this.cobrancaDuplicata);
            this.cobrancaDuplicata = new CobrancaDuplicata();
        } catch (Exception e) {
            UtilFaces.showDialogMessageError("Erro: " + e.getMessage());
        }
    }

    /**
     * Adiciona novo produto/servico à lista.
     */
    public void addDetalhamentoProdutosServicos() {
        try {
            this.dadosNFe.addDetalhamentoProdutosServicos(this.produtoServico);
            this.produtoServico = new DetalhamentoProdutoServico();
            this.produto = null;
            UtilFaces.showDialogMessageInfo("Produto inserido com sucesso!");
        } catch (Exception e) {
            UtilFaces.showDialogMessageError("Erro: " + e.getMessage());
        }
    }

    /**
     * Remove produto/servico da lista.
     */
    public void rmvDetalhamentoProdutosServicos() {
        this.dadosNFe.rmvDetalhamentoProdutosServicos(this.produtoServico);
        this.produtoServico = new DetalhamentoProdutoServico();
    }

    /**
     * Remove duplicata de cobrança da lista.
     */
    public void rmvCobrancaDuplicata() {
        this.dadosNFe.rmvCobrancaDuplicata(this.cobrancaDuplicata);
        this.cobrancaDuplicata = new CobrancaDuplicata();
    }

    public List<String> completeCFOP(String query) {
        List<String> listaString = new ArrayList<>();

        listaString.add("COD1");
        listaString.add("COD2");
        listaString.add("COD3");

        return listaString.stream()
                .filter(v -> StringUtils.containsIgnoreCase(v, query))
                .collect(Collectors.toList());
    }
    
    public List<String> completeNCM(String query) {
        List<String> listaString = new ArrayList<>();

        listaString.add("COOOOOD1");
        listaString.add("COOOOOD2");
        listaString.add("COOOOOD3");

        return listaString.stream()
                .filter(v -> StringUtils.containsIgnoreCase(v, query))
                .collect(Collectors.toList());
    }
    
    public List<String> completeCEST(String query) {
        List<String> listaString = new ArrayList<>();

        listaString.add("COOOOD1");
        listaString.add("COOOOD2");
        listaString.add("COOOOD3");

        return listaString.stream()
                .filter(v -> StringUtils.containsIgnoreCase(v, query))
                .collect(Collectors.toList());
    }
    
    public List<NFeTipoIndicadorVProd> completeIndicadorVProd(String query) {
        return NFeTipoIndicadorVProd.valuesAsList().stream()
                .filter(v -> StringUtils.containsIgnoreCase(v.getDescricao(), query))
                .collect(Collectors.toList());
    }
    
    public List<String> getUfs() {
        return Stream.of(TUf.values()).map((aEnum) -> aEnum.name()).collect(Collectors.toList());
    }

    public List<String> getUfsEmi() {
        return Stream.of(TUfEmi.values()).map((aEnum) -> aEnum.name()).collect(Collectors.toList());
    }

    public List<NFeTipoAmbiente> getTiposDeAmbiente() {
        return NFeTipoAmbiente.valuesAsList();
    }

    public List<NFeTipoArma> getTiposDeArma() {
        return NFeTipoArma.valuesAsList();
    }

    public List<NFeTipoBandeiraOperadoraCartaoCredito> getTiposDeBandeiraDeCartao() {
        return NFeTipoBandeiraOperadoraCartaoCredito.valuesAsList();
    }

    public List<NFeTipoDestinoOperacao> completeNFeTipoDestinoOperacao(String query) {
        return NFeTipoDestinoOperacao.valuesAsList().stream()
                .filter(v -> StringUtils.containsIgnoreCase(v.getDescricao(), query))
                .collect(Collectors.toList());
    }
//    public List<NFeTipoDestinoOperacao> getTiposDeDestinoDeOperacao() {
//        return NFeTipoDestinoOperacao.valuesAsList();
//    }

    public List<NFeTipoDistribuicao> getTiposDeDistribuicao() {
        return NFeTipoDistribuicao.valuesAsList();
    }

    public List<NFeTipoDocumento> getTiposDeDocumento() {
        return NFeTipoDocumento.valuesAsList();
    }

    public List<NFeTipoEmissao> completeNFeTipoEmissao(String query) {
        return NFeTipoEmissao.valuesAsList().stream()
                .filter(v -> StringUtils.containsIgnoreCase(v.getDescricao(), query))
                .collect(Collectors.toList());
    }

//    public List<NFeTipoEmissao> getTiposDeEmissao() {
//        return NFeTipoEmissao.valuesAsList();
//    }
    public List<NFeTipoEvento> getTiposDeEvento() {
        return NFeTipoEvento.valuesAsList();
    }

    public List<NFeTipoExigibilidadeISS> getTiposDeExigibilidadeIss() {
        return NFeTipoExigibilidadeISS.valuesAsList();
    }

    public List<NFeTipoFinalidadeEmissao> completeNFeTipoFinalidadeEmissao(String query) {
        return NFeTipoFinalidadeEmissao.valuesAsList().stream()
                .filter(v -> StringUtils.containsIgnoreCase(v.getDescricao(), query))
                .collect(Collectors.toList());
    }

//    public List<NFeTipoFinalidadeEmissao> getTiposDeFinalidadeDeEmissso() {
//        return NFeTipoFinalidadeEmissao.valuesAsList();
//    }
    public List<NFeTipoFormaPagamento> getTiposDeFormaDePagamento() {
        return NFeTipoFormaPagamento.valuesAsList();
    }

    public List<NFeTipoOrigemProcesso> getTiposOrigemProcesso() {
        return NFeTipoOrigemProcesso.valuesAsList();
    }

    public List<NFeTipoFormatoImpressao> completeNFeTipoFormatoImpressao(String query) {
        return NFeTipoFormatoImpressao.valuesAsList().stream()
                .filter(v -> StringUtils.containsIgnoreCase(v.getDescricao(), query))
                .collect(Collectors.toList());
    }
//    public List<NFeTipoFormatoImpressao> getTiposDeFormatoDeImpressao() {
//        return NFeTipoFormatoImpressao.valuesAsList();
//    }

    public List<NFeTipoGrupoTributacaoICMS> getTiposDeGrupoTributacaoIcms() {
        return NFeTipoGrupoTributacaoICMS.valuesAsList();
    }

    public List<NFeTipoImportacao> getTiposDeImportacao() {
        return NFeTipoImportacao.valuesAsList();
    }

    public List<NFeTipoIncentivoFiscal> getTiposDeIncentivoFiscal() {
        return NFeTipoIncentivoFiscal.valuesAsList();
    }

    public List<NFeTipoFormaPagamento> getFormasDePagamento() {
        return NFeTipoFormaPagamento.valuesAsList();
    }

    public List<NFeTipoIndicadorIEDestinatario> completeNFeTipoIndicadorIEDestinatario(String query) {
        return NFeTipoIndicadorIEDestinatario.valuesAsList().stream()
                .filter(v -> StringUtils.containsIgnoreCase(v.getDescricao(), query))
                .collect(Collectors.toList());
    }
//    public List<NFeTipoIndicadorIEDestinatario> getTiposDeIndicadorIEDestinatario() {
//        return NFeTipoIndicadorIEDestinatario.valuesAsList();
//    }
    public List<NFeTipoIndicadorVProd> getTiposDeIndicadorVProd() {
        return NFeTipoIndicadorVProd.valuesAsList();
    }

    public List<NFeTipoProdutoEspecifico> completeNFeTipoProdutoEspecifico(String query) {
        return NFeTipoProdutoEspecifico.valuesAsList().stream()
                .filter(v -> StringUtils.containsIgnoreCase(v.getDescricao(), query))
                .collect(Collectors.toList());
    }
//    public List<NFeTipoProdutoEspecifico> getTiposDeProdutoEspecifico() {
//        return NFeTipoProdutoEspecifico.valuesAsList();
//    }

    public List<NFeTipoViaTransporte> getTiposViaTransporte() {
        return NFeTipoViaTransporte.valuesAsList();
    }

    public List<NFeTipoVeiculosNovosCombustivel> getTiposDeVeiculosNovosCombustivel() {
        return NFeTipoVeiculosNovosCombustivel.valuesAsList();
    }

    public List<NFeTipoVeiculosNovosCondicao> getTiposVeiculosNovosCondicao() {
        return NFeTipoVeiculosNovosCondicao.valuesAsList();
    }

    public List<NFeTipoVeiculosNovosCondicaoVIN> getTiposVeiculosNovosCondicaoVIN() {
        return NFeTipoVeiculosNovosCondicaoVIN.valuesAsList();
    }

    public List<NFeTipoVeiculosNovosCor> getTiposVeiculosNovosCor() {
        return NFeTipoVeiculosNovosCor.valuesAsList();
    }

    public List<NFeTipoVeiculosNovosEspecie> getTiposVeiculosNovosEspecie() {
        return NFeTipoVeiculosNovosEspecie.valuesAsList();
    }

    public List<NFeTipoVeiculosNovosOperacao> getTiposVeiculosNovosOperacao() {
        return NFeTipoVeiculosNovosOperacao.valuesAsList();
    }

    public List<NFeTipoVeiculosNovosRestricao> getTiposVeiculosNovosRestricao() {
        return NFeTipoVeiculosNovosRestricao.valuesAsList();
    }

    public List<NFeTipoVeiculosNovosVeiculo> getTiposVeiculosNovosVeiculo() {
        return NFeTipoVeiculosNovosVeiculo.valuesAsList();
    }

    public List<NFeTipoVeiculo> getTiposVeiculos() {
        return NFeTipoVeiculo.valuesAsList();
    }

    public List<NFeTipoManifesto> getTiposDeManifesto() {
        return NFeTipoManifesto.valuesAsList();
    }

    public List<NFeTipoModalidadeBCICMS> getTiposDeModalidadeBcIcms() {
        return NFeTipoModalidadeBCICMS.valuesAsList();
    }

    public List<NFeTipoModalidadeBCICMSST> getTiposDeModalidadeBcIcmsSt() {
        return NFeTipoModalidadeBCICMSST.valuesAsList();
    }

    public List<NFeTipoModalidadeFrete> getTiposDeModalidadeDeFrete() {
        return NFeTipoModalidadeFrete.valuesAsList();
    }

    public List<NFeTipoModeloDocumentoFiscalECF> getTiposDeModeloDeDocumentoFiscalEcf() {
        return NFeTipoModeloDocumentoFiscalECF.valuesAsList();
    }

    public List<NFeTipoModeloDocumentoFiscal> getTiposDeModeloDeDocumentoFiscal() {
        return NFeTipoModeloDocumentoFiscal.valuesAsList();
    }

    public List<NFeTipoModeloDocumentoFiscal> completeNFeTipoModeloDocumentoFiscal(String query) {
        return NFeTipoModeloDocumentoFiscal.valuesAsList().stream()
                .filter(v -> StringUtils.containsIgnoreCase(v.getDescricao(), query))
                .collect(Collectors.toList());
    }

    public List<NFeTipoModeloDocumentoFiscalProdutorRural> getTiposDeModeloDeDocumentoFiscalProdutorRural() {
        return NFeTipoModeloDocumentoFiscalProdutorRural.valuesAsList();
    }

    public List<NFeTipoMotivoDesoneracaoICMS> getMotivosDesoneracaoIcms() {
        if (this.produtoServico != null && this.produtoServico.getCsticms() != null) {
            switch (this.produtoServico.getCsticms()) {
                case COD20:
                case COD70:
                case COD90:
                    // BASE ICMS 20
                    return NFeTipoMotivoDesoneracaoICMS.getMotivosDesoneracaoICMS20();
                case COD30:
                    // BASE ICMS 30
                    return NFeTipoMotivoDesoneracaoICMS.getMotivosDesoneracaoICMS30();
                case COD40:
                case COD41:
                case COD50:
                    // BASE ICMS 40
                    return NFeTipoMotivoDesoneracaoICMS.getMotivosDesoneracaoICMS40();
                default:
                    break;
            }
        }

        return null;
    }

    public List<NFeTipoNSU> getTiposDeNsu() {
        return NFeTipoNSU.valuesAsList();
    }

    public List<NFeTipoOperacao> completeNFeTipoOperacao(String query) {
        return NFeTipoOperacao.valuesAsList().stream()
                .filter(v -> StringUtils.containsIgnoreCase(v.getDescricao(), query))
                .collect(Collectors.toList());
    }

//    public List<NFeTipoOperacao> getTiposDeOperacao() {
//        return NFeTipoOperacao.valuesAsList();
//    }
    public List<NFeTipoOperacaoConsumidor> completeNFeTipoOperacaoConsumidor(String query) {
        return NFeTipoOperacaoConsumidor.valuesAsList().stream()
                .filter(v -> StringUtils.containsIgnoreCase(v.getDescricao(), query))
                .collect(Collectors.toList());
    }
//    public List<NFeTipoOperacaoConsumidor> getTiposDeOperacaoConsumidor() {
//        return NFeTipoOperacaoConsumidor.valuesAsList();
//    }

    public List<NFeTipoRegimeEspecialTributacao> getTiposDeRegimeEspecialTributacao() {
        return NFeTipoRegimeEspecialTributacao.valuesAsList();
    }

    public List<NFeTipoRegimeTributario> completeNFeTipoRegimeTributario(String query) {
        return NFeTipoRegimeTributario.valuesAsList().stream()
                .filter(v -> StringUtils.containsIgnoreCase(v.getDescricao(), query))
                .collect(Collectors.toList());
    }
//    public List<NFeTipoRegimeTributario> getTiposDeRegimeTributario() {
//        return NFeTipoRegimeTributario.valuesAsList();
//    }

    public List<NFeTipoPresencaCompradorOperacao> completeNFeTipoPresencaCompradorOperacao(String query) {
        return NFeTipoPresencaCompradorOperacao.valuesAsList().stream()
                .filter(v -> StringUtils.containsIgnoreCase(v.getDescricao(), query))
                .collect(Collectors.toList());
    }
//    public List<NFeTipoPresencaCompradorOperacao> getTiposDePresencaComprador() {
//        return NFeTipoPresencaCompradorOperacao.valuesAsList();
//    }

    public List<NFeTipoProcessoEmissao> getTiposDeProcessoDeEmissao() {
        return NFeTipoProcessoEmissao.valuesAsList();
    }

    public List<NFeTipoOrigemMercadoria> getTiposDeOrigemMercadoria() {
        return NFeTipoOrigemMercadoria.valuesAsList();
    }

    public List<NFeTipoSituacaoTributariaICMS> getCstsRegimeNormal() {
        return NFeTipoSituacaoTributariaICMS.getCstsRegimeNormal();
    }

    public List<NFeTipoSituacaoTributariaICMS> getCstsRegimeSimples() {
        return NFeTipoSituacaoTributariaICMS.getCstsRegimeSimples();
    }

    public List<NFeTipoSituacaoTributariaIPI> getCstsIpi() {
        return NFeTipoSituacaoTributariaIPI.valuesAsList();
    }

    public List<NFeTipoSituacaoTributariaPIS> getCstsPis() {
        return NFeTipoSituacaoTributariaPIS.valuesAsList();
    }

    public List<NFeTipoSituacaoTributariaCOFINS> getCstsCofins() {
        return NFeTipoSituacaoTributariaCOFINS.valuesAsList();
    }

    public List<NFeTipoTributacao> getTiposDeTributacao() {
        return NFeTipoTributacao.valuesAsList();
    }

    public List<NFeTipoCalculo> getTiposDeCalculo() {
        return NFeTipoCalculo.valuesAsList();
    }

    public List<NFeTipoServico> getTiposDeServicoIssqn() {
        return NFeTipoServico.valuesAsList();
    }

    public void limpaLocalEntrega() {
        if (this.ideLocalEntrega != null) {
            this.ideLocalEntrega = new IdentificacaoLocalEntrega();
        }
    }

    public void limpaLocalRetirada() {
        if (this.ideLocalRetirada != null) {
            this.ideLocalRetirada = new IdentificacaoLocalRetirada();
        }
    }

    public void limpaTransacaoEntreIcmssIssqn() {
        limpaDadosIcmsInter();
        limpaDadosIssqn();
        limpaDadosIcms();
    }

    public void limpaDadosIcmsInter() {
        if (this.produtoServico != null) {
            this.produtoServico.limpaDadosIcmsInter();
        }
    }

    public void limpaDadosIssqn() {
        if (this.produtoServico != null) {
            this.produtoServico.limpaDadosIssqn();
        }
    }

    public void limpaDadosIcms() {
        if (this.produtoServico != null) {
            this.produtoServico.limpaDadosIcms();
        }
    }

    public void onTipoDocumentoChange() {
        this.documentoFiscalReferenciado.limpaDadosConhecimentoFiscalReferenciado();

        if (isPgNFeIdentificacaoConhecimentoReferenciadoRefNFVisible()) {
            this.documentoFiscalReferenciado.setRefnfemod("01");
        }
    }

    // BOOLEANS
    public boolean isPgNFeIdentificacaoConhecimentoReferenciadoRefNFeVisible() {
        return this.tipoDocumento != null && this.tipoDocumento.equals(NFeTipoDocumento.NFE);
    }

    public boolean isPgNFeIdentificacaoConhecimentoReferenciadoRefCTeVisible() {
        return this.tipoDocumento != null && this.tipoDocumento.equals(NFeTipoDocumento.CTE);
    }

    public boolean isPgNFeIdentificacaoConhecimentoReferenciadoRefNFVisible() {
        return this.tipoDocumento != null && this.tipoDocumento.equals(NFeTipoDocumento.NF);
    }

    public boolean isRegimeIcmsSimples() {
        return this.icmsRegime != NFeTipoRegimeTributario.NORMAL;
    }

    // ICMS REGIME SIMPLES
    public boolean isPgSimplesIcmsVisible() {
        return isRegimeIcmsSimples() && this.produtoServico != null && this.produtoServico.getCsticms() != null
                && this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD900);
    }

    public boolean isPgSimplesIcmsStVisible() {
        return isRegimeIcmsSimples() && this.produtoServico != null && this.produtoServico.getCsticms() != null
                && (this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD201)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD202)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD203)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD500)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD900));
    }

    public boolean isPcRedSnVisible() {
        return isRegimeIcmsSimples() && this.produtoServico != null && this.produtoServico.getCsticms() != null
                && (this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD101)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD201)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD900));
    }

    public boolean isVcredIcmssnVisible() {
        return isRegimeIcmsSimples() && this.produtoServico != null && this.produtoServico.getCsticms() != null
                && (this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD101)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD201)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD900));
    }

    public boolean isPicmsStVisible() {
        return isRegimeIcmsSimples() && this.produtoServico != null && this.produtoServico.getCsticms() != null
                && (this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD201)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD202)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD203)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD900));
    }

    public boolean isSimplesIcmsStRetVisible() {
        return isRegimeIcmsSimples() && this.produtoServico != null && this.produtoServico.getCsticms() != null
                && this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD500);
    }

    public boolean isVicmsStVisible() {
        return isRegimeIcmsSimples() && this.produtoServico != null && this.produtoServico.getCsticms() != null
                && (this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD201)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD202)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD203)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD900));
    }

    public boolean isSimplesMsgVisible() {
        return isRegimeIcmsSimples()
                && this.produtoServico != null
                && this.produtoServico.getCsticms() != null
                && (this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD201)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD202));
    }

    // ICMS REGIME NORMAL
    public boolean isPgNormalIcmsVisible() {
        return !isRegimeIcmsSimples() && this.produtoServico != null && this.produtoServico.getCsticms() != null
                && !this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD30)
                && !this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD41_ICMSST)
                && !this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD60);
    }

    public boolean isPgNormalIcmsStVisible() {
        return !isRegimeIcmsSimples() && this.produtoServico != null && this.produtoServico.getCsticms() != null
                && (this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD10)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD10_PARTILHA)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD30)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD41_ICMSST)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD60)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD70)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD90)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD90_PARTILHA));
    }

    public boolean isModBcVisible() {
        return !isRegimeIcmsSimples() && this.produtoServico != null && this.produtoServico.getCsticms() != null
                && (this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD00)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD10)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD10_PARTILHA)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD20)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD30)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD51)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD70)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD90)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD90_PARTILHA));
    }

    public boolean isPredBcVisible() {
        return !isRegimeIcmsSimples() && this.produtoServico != null && this.produtoServico.getCsticms() != null
                && (this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD10_PARTILHA)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD20)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD30)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD51)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD70)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD90)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD90_PARTILHA));
    }

    public boolean isVbcIcmsVisible() {
        return !isRegimeIcmsSimples() && this.produtoServico != null && this.produtoServico.getCsticms() != null
                && (this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD00)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD10)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD10_PARTILHA)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD20)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD30)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD51)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD70)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD90)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD90_PARTILHA));
    }

    public boolean isPicmsVisible() {
        return !isRegimeIcmsSimples() && this.produtoServico != null && this.produtoServico.getCsticms() != null
                && (this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD00)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD10)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD10_PARTILHA)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD20)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD30)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD51)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD70)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD90)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD90_PARTILHA));
    }

    public boolean isVicmsVisible() {
        return !isRegimeIcmsSimples() && this.produtoServico != null && this.produtoServico.getCsticms() != null
                && (this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD00)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD10)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD10_PARTILHA)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD20)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD30)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD51)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD70)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD90)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD90_PARTILHA));
    }

    public boolean isIcmsDifVisible() {
        return !isRegimeIcmsSimples() && this.produtoServico != null && this.produtoServico.getCsticms() != null
                && this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD51);
    }

    public boolean isPbcOpVisible() {
        return !isRegimeIcmsSimples() && this.produtoServico != null && this.produtoServico.getCsticms() != null
                && (this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD10_PARTILHA)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD90_PARTILHA));
    }

    public boolean isIcmsDesonVisible() {
        return !isRegimeIcmsSimples() && this.produtoServico != null && this.produtoServico.getCsticms() != null
                && (this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD20)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD30)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD40)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD41)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD41_ICMSST)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD50)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD70)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD90));
    }

    public boolean isNormalMsgVisible() {
        return !isRegimeIcmsSimples() && this.produtoServico != null && this.produtoServico.getCsticms() != null
                && (this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD00)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD10)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD10_PARTILHA)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD20)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD51)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD70)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD90)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD90_PARTILHA));
    }

    public boolean isModBcStVisible() {
        return !isRegimeIcmsSimples() && this.produtoServico != null && this.produtoServico.getCsticms() != null
                && (this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD10)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD10_PARTILHA)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD30)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD70)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD90)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD90_PARTILHA));
    }

    public boolean isIcmsStDevidoUfDestinoVisible() {
        return !isRegimeIcmsSimples() && this.produtoServico != null && this.produtoServico.getCsticms() != null
                && this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD41_ICMSST);
    }

    public boolean isNormalIcmsStRetVisible() {
        return !isRegimeIcmsSimples() && this.produtoServico != null && this.produtoServico.getCsticms() != null
                && this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD60);
    }

    public boolean isIcmsStDesonVisible() {
        return !isRegimeIcmsSimples() && this.produtoServico != null && this.produtoServico.getCsticms() != null
                && (this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD20)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD30)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD40)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD41)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD50));
    }

    public boolean isUfStVisible() {
        return !isRegimeIcmsSimples() && this.produtoServico != null && this.produtoServico.getCsticms() != null
                && (this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD10_PARTILHA)
                || this.produtoServico.getCsticms().equals(NFeTipoSituacaoTributariaICMS.COD90_PARTILHA));
    }

    // TABS DE PRODUTOS ESPECIFICOS
    public boolean isTabVeiculoNovoAvailable() {
        return this.produtoEspecifico != null && this.produtoEspecifico.equals(NFeTipoProdutoEspecifico.VEICULO);
    }

    public boolean isTabCombustivelAvailable() {
        return this.produtoEspecifico != null && this.produtoEspecifico.equals(NFeTipoProdutoEspecifico.COMBUSTIVEL);
    }

    public boolean isTabMedicamentoAvailable() {
        return this.produtoEspecifico != null && this.produtoEspecifico.equals(NFeTipoProdutoEspecifico.MEDICAMENTO);
    }

    public boolean isTabPapelImuneAvailable() {
        return this.produtoEspecifico != null && this.produtoEspecifico.equals(NFeTipoProdutoEspecifico.PAPEL);
    }

    public boolean isTabArmamentoAvailable() {
        return this.produtoEspecifico != null && this.produtoEspecifico.equals(NFeTipoProdutoEspecifico.ARMAMENTO);
    }

    // TIPO DE TRIBUTACAO = PRODUTO OU SERVICO
    public boolean isTributacaoIcmsSelected() {
        return this.tributacao != null && this.tributacao.equals(NFeTipoTributacao.ICMS);
    }

    // GETTERS 'N SETTERS
    public AquisicaoCana getAquisicaoCana() {
        return aquisicaoCana;
    }

    public void setAquisicaoCana(AquisicaoCana aquisicaoCana) {
        this.aquisicaoCana = aquisicaoCana;
    }

    public AquisicaoCanaDeducao getAquisicaoCanaDeducoes() {
        return aquisicaoCanaDeducoes;
    }

    public void setAquisicaoCanaDeducoes(AquisicaoCanaDeducao aquisicaoCanaDeducoes) {
        this.aquisicaoCanaDeducoes = aquisicaoCanaDeducoes;
    }

    public AquisicaoCanaFornecimento getAquisicaoCanaFornecimento() {
        return aquisicaoCanaFornecimento;
    }

    public void setAquisicaoCanaFornecimento(AquisicaoCanaFornecimento aquisicaoCanaFornecimento) {
        this.aquisicaoCanaFornecimento = aquisicaoCanaFornecimento;
    }

    public AutorizacaoObterXml getAutorizacaoObterXml() {
        return autorizacaoObterXml;
    }

    public void setAutorizacaoObterXml(AutorizacaoObterXml autorizacaoObterXml) {
        this.autorizacaoObterXml = autorizacaoObterXml;
    }

    public CobrancaDuplicata getCobrancaDuplicata() {
        return cobrancaDuplicata;
    }

    public void setCobrancaDuplicata(CobrancaDuplicata cobrancaDuplicata) {
        this.cobrancaDuplicata = cobrancaDuplicata;
    }

    public CobrancaFatura getCobrancaFatura() {
        return cobrancaFatura;
    }

    public void setCobrancaFatura(CobrancaFatura cobrancaFatura) {
        this.cobrancaFatura = cobrancaFatura;
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

    public DadosNFe getDadosNFe() {
        return dadosNFe;
    }

    public void setDadosNFe(DadosNFe dadosNFe) {
        this.dadosNFe = dadosNFe;
    }

    public DeclaracaoImportacao getDeclaracaoImportacao() {
        return declaracaoImportacao;
    }

    public void setDeclaracaoImportacao(DeclaracaoImportacao declaracaoImportacao) {
        this.declaracaoImportacao = declaracaoImportacao;
    }

    public DetalhamentoEspecificoArmamento getArmamento() {
        return armamento;
    }

    public DeclaracaoImportacaoAdicoes getDeclaracaoImportacaoAdicao() {
        return declaracaoImportacaoAdicao;
    }

    public void setDeclaracaoImportacaoAdicao(DeclaracaoImportacaoAdicoes declaracaoImportacaoAdicao) {
        this.declaracaoImportacaoAdicao = declaracaoImportacaoAdicao;
    }

    public void setArmamento(DetalhamentoEspecificoArmamento armamento) {
        this.armamento = armamento;
    }

    public DetalhamentoEspecificoCombustivel getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(DetalhamentoEspecificoCombustivel combustivel) {
        this.combustivel = combustivel;
    }

    public DetalhamentoEspecificoMedicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(DetalhamentoEspecificoMedicamento medicamento) {
        this.medicamento = medicamento;
    }

    public DetalhamentoEspecificoPapelImune getPapelImune() {
        return papelImune;
    }

    public void setPapelImune(DetalhamentoEspecificoPapelImune papelImune) {
        this.papelImune = papelImune;
    }

    public DetalhamentoEspecificoVeiculo getVeiculoEspecifico() {
        return veiculoEspecifico;
    }

    public void setVeiculoEspecifico(DetalhamentoEspecificoVeiculo veiculoEspecifico) {
        this.veiculoEspecifico = veiculoEspecifico;
    }

    public DetalhamentoProdutoServico getProdutoServico() {
        return produtoServico;
    }

    public void setProdutoServico(DetalhamentoProdutoServico produtoServico) {
        this.produtoServico = produtoServico;
    }

    public DocumentoFiscalReferenciado getDocumentoFiscalReferenciado() {
        return documentoFiscalReferenciado;
    }

    public void setDocumentoFiscalReferenciado(DocumentoFiscalReferenciado documentoFiscalReferenciado) {
        this.documentoFiscalReferenciado = documentoFiscalReferenciado;
    }

    public GrupoDiverso getGrupoDiverso() {
        return grupoDiverso;
    }

    public void setGrupoDiverso(GrupoDiverso grupoDiverso) {
        this.grupoDiverso = grupoDiverso;
    }

    public GrupoExportacao getGrupoExportacao() {
        return grupoExportacao;
    }

    public void setGrupoExportacao(GrupoExportacao grupoExportacao) {
        this.grupoExportacao = grupoExportacao;
    }

    public IdentificacaoDestinatario getIdeDestinatario() {
        return ideDestinatario;
    }

    public void setIdeDestinatario(IdentificacaoDestinatario ideDestinatario) {
        this.ideDestinatario = ideDestinatario;
    }

    public IdentificacaoEmitente getIdeEmitente() {
        return ideEmitente;
    }

    public void setIdeEmitente(IdentificacaoEmitente ideEmitente) {
        this.ideEmitente = ideEmitente;
    }

    public IdentificacaoLocalEntrega getIdeLocalEntrega() {
        return ideLocalEntrega;
    }

    public void setIdeLocalEntrega(IdentificacaoLocalEntrega ideLocalEntrega) {
        this.ideLocalEntrega = ideLocalEntrega;
    }

    public IdentificacaoLocalRetirada getIdeLocalRetirada() {
        return ideLocalRetirada;
    }

    public void setIdeLocalRetirada(IdentificacaoLocalRetirada ideLocalRetirada) {
        this.ideLocalRetirada = ideLocalRetirada;
    }

    public IdentificacaoNFe getIdeNFe() {
        return ideNFe;
    }

    public void setIdeNFe(IdentificacaoNFe ideNFe) {
        this.ideNFe = ideNFe;
    }

    public InformacoesAdicionais getInformacoesAdicionais() {
        return informacoesAdicionais;
    }

    public void setInformacoesAdicionais(InformacoesAdicionais informacoesAdicionais) {
        this.informacoesAdicionais = informacoesAdicionais;
    }

    public InformacoesAdicionaisContribuinte getInformacoesAdicionaisContribuinte() {
        return informacoesAdicionaisContribuinte;
    }

    public void setInformacoesAdicionaisContribuinte(InformacoesAdicionaisContribuinte informacoesAdicionaisContribuinte) {
        this.informacoesAdicionaisContribuinte = informacoesAdicionaisContribuinte;
    }

    public InformacoesAdicionaisFisco getInformacoesAdicionaisFisco() {
        return informacoesAdicionaisFisco;
    }

    public void setInformacoesAdicionaisFisco(InformacoesAdicionaisFisco informacoesAdicionaisFisco) {
        this.informacoesAdicionaisFisco = informacoesAdicionaisFisco;
    }

    public InformacoesAdicionaisProcessoReferenciado getProcessoReferenciado() {
        return processoReferenciado;
    }

    public void setProcessoReferenciado(InformacoesAdicionaisProcessoReferenciado processoReferenciado) {
        this.processoReferenciado = processoReferenciado;
    }

    public NomenclaturaValorAduaneiro getNomenclaturaValorAduaneiro() {
        return nomenclaturaValorAduaneiro;
    }

    public void setNomenclaturaValorAduaneiro(NomenclaturaValorAduaneiro nomenclaturaValorAduaneiro) {
        this.nomenclaturaValorAduaneiro = nomenclaturaValorAduaneiro;
    }

    public TotalNFe getTotal() {
        return total;
    }

    public void setTotal(TotalNFe total) {
        this.total = total;
    }

    public TransporteLacre getLacre() {
        return lacre;
    }

    public void setLacre(TransporteLacre lacre) {
        this.lacre = lacre;
    }

    public TransporteNFe getTransporteNFe() {
        return transporteNFe;
    }

    public void setTransporteNFe(TransporteNFe transporteNFe) {
        this.transporteNFe = transporteNFe;
    }

    public TransporteRetencaoIcms getRetencaoIcms() {
        return retencaoIcms;
    }

    public void setRetencaoIcms(TransporteRetencaoIcms retencaoIcms) {
        this.retencaoIcms = retencaoIcms;
    }

    public TransporteReboque getReboque() {
        return reboque;
    }

    public void setReboque(TransporteReboque reboque) {
        this.reboque = reboque;
    }

    public TransporteVeiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(TransporteVeiculo veiculo) {
        this.veiculo = veiculo;
    }

    public TransporteVolume getVolume() {
        return volume;
    }

    public void setVolume(TransporteVolume volume) {
        this.volume = volume;
    }

    public List<TabelaCEST> getCests() {
        return cests;
    }

    public void setCests(List<TabelaCEST> cests) {
        this.cests = cests;
    }

    public List<TabelaCFOP> getCfops() {
        return cfops;
    }

    public void setCfops(List<TabelaCFOP> cfops) {
        this.cfops = cfops;
    }

    public List<TabelaNCM> getNcms() {
        return ncms;
    }

    public void setNcms(List<TabelaNCM> ncms) {
        this.ncms = ncms;
    }

    public NFeTipoRegimeTributario getIcmsRegime() {
        return icmsRegime;
    }

    public void setIcmsRegime(NFeTipoRegimeTributario icmsRegime) {
        this.icmsRegime = icmsRegime;
    }

    public NFeTipoProdutoEspecifico getProdutoEspecifico() {
        return produtoEspecifico;
    }

    public void setProdutoEspecifico(NFeTipoProdutoEspecifico produtoEspecifico) {
        this.produtoEspecifico = produtoEspecifico;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Destinatario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Destinatario destinatario) {
        this.destinatario = destinatario;
    }

    public Transportadora getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora;
    }

    public NFeTipoTributacao getTributacao() {
        return tributacao;
    }

    public void setTributacao(NFeTipoTributacao tributacao) {
        this.tributacao = tributacao;
    }

    public boolean getLocalRetiradaDiferenteEmitente() {
        return localRetiradaDiferenteEmitente;
    }

    public void setLocalRetiradaDiferenteEmitente(boolean localRetiradaDiferenteEmitente) {
        this.localRetiradaDiferenteEmitente = localRetiradaDiferenteEmitente;
    }

    public boolean isLocalEntregaDiferenteDestinatario() {
        return localEntregaDiferenteDestinatario;
    }

    public void setLocalEntregaDiferenteDestinatario(boolean localEntregaDiferenteDestinatario) {
        this.localEntregaDiferenteDestinatario = localEntregaDiferenteDestinatario;
    }

    public NFeTipoVeiculo getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(NFeTipoVeiculo tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public NFeTipoCalculo getTipoCalculoPis() {
        return tipoCalculoPis;
    }

    public void setTipoCalculoPis(NFeTipoCalculo tipoCalculoPis) {
        this.tipoCalculoPis = tipoCalculoPis;
    }

    public NFeTipoCalculo getTipoCalculoIpi() {
        return tipoCalculoIpi;
    }

    public void setTipoCalculoIpi(NFeTipoCalculo tipoCalculoIpi) {
        this.tipoCalculoIpi = tipoCalculoIpi;
    }

    public NFeTipoCalculo getTipoCalculoPisSt() {
        return tipoCalculoPisSt;
    }

    public void setTipoCalculoPisSt(NFeTipoCalculo tipoCalculoPisSt) {
        this.tipoCalculoPisSt = tipoCalculoPisSt;
    }

    public NFeTipoCalculo getTipoCalculoCofins() {
        return tipoCalculoCofins;
    }

    public void setTipoCalculoCofins(NFeTipoCalculo tipoCalculoCofins) {
        this.tipoCalculoCofins = tipoCalculoCofins;
    }

    public NFeTipoCalculo getTipoCalculoCofinsSt() {
        return tipoCalculoCofinsSt;
    }

    public void setTipoCalculoCofinsSt(NFeTipoCalculo tipoCalculoCofinsSt) {
        this.tipoCalculoCofinsSt = tipoCalculoCofinsSt;
    }

    public NFeTipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(NFeTipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

}
