package br.com.ararati.populadores.impl;

import br.com.ararati.entity.cadastros.Endereco;
import br.com.ararati.entity.cadastros.Local;
import br.com.ararati.entity.nfe.emissao.AquisicaoCana;
import br.com.ararati.entity.nfe.emissao.AquisicaoCanaDeducao;
import br.com.ararati.entity.nfe.emissao.AquisicaoCanaFornecimento;
import br.com.ararati.entity.nfe.emissao.AutorizacaoObterXml;
import br.com.ararati.entity.nfe.emissao.CobrancaDuplicata;
import br.com.ararati.entity.nfe.emissao.CobrancaFatura;
import br.com.ararati.entity.nfe.emissao.ComercioExterior;
import br.com.ararati.entity.nfe.emissao.DadosNFe;
import br.com.ararati.entity.nfe.emissao.DeclaracaoImportacao;
import br.com.ararati.entity.nfe.emissao.DeclaracaoImportacaoAdicoes;
import br.com.ararati.entity.nfe.emissao.DetalhamentoEspecificoArmamento;
import br.com.ararati.entity.nfe.emissao.DetalhamentoEspecificoCombustivel;
import br.com.ararati.entity.nfe.emissao.DetalhamentoEspecificoMedicamento;
import br.com.ararati.entity.nfe.emissao.DetalhamentoEspecificoVeiculo;
import br.com.ararati.entity.nfe.emissao.DetalhamentoProdutoServico;
import br.com.ararati.entity.nfe.emissao.DocumentoFiscalReferenciado;
import br.com.ararati.entity.nfe.emissao.GrupoExportacao;
import br.com.ararati.entity.nfe.emissao.IdentificacaoAvulsa;
import br.com.ararati.entity.nfe.emissao.IdentificacaoDestinatario;
import br.com.ararati.entity.nfe.emissao.IdentificacaoEmitente;
import br.com.ararati.entity.nfe.emissao.IdentificacaoNFe;
import br.com.ararati.entity.nfe.emissao.InformacoesAdicionais;
import br.com.ararati.entity.nfe.emissao.InformacoesAdicionaisContribuinte;
import br.com.ararati.entity.nfe.emissao.InformacoesAdicionaisFisco;
import br.com.ararati.entity.nfe.emissao.InformacoesAdicionaisProcessoReferenciado;
import br.com.ararati.entity.nfe.emissao.TotalNFe;
import br.com.ararati.entity.nfe.emissao.TotalNFeICMS;
import br.com.ararati.entity.nfe.emissao.TotalNFeISSQN;
import br.com.ararati.entity.nfe.emissao.TotalNFeRetencao;
import br.com.ararati.entity.nfe.emissao.TransporteLacre;
import br.com.ararati.entity.nfe.emissao.TransporteNFe;
import br.com.ararati.entity.nfe.emissao.TransporteVeiculo;
import br.com.ararati.entity.nfe.emissao.TransporteVolume;
import br.com.ararati.enums.B.NFeTipoAmbiente;
import br.com.ararati.enums.B.NFeTipoDestinoOperacao;
import br.com.ararati.enums.B.NFeTipoOperacaoConsumidor;
import br.com.ararati.enums.E.NFeTipoIndicadorIEDestinatario;
import br.com.ararati.enums.X.NFeTipoModalidadeFrete;
import br.com.ararati.exception.NFeException;
import br.com.ararati.operacoes.ConstroiChaveAcesso;
import br.com.ararati.populadores.impl.impostos.cofins.ControleCofins;
import br.com.ararati.populadores.impl.impostos.icms.ConstroleDeIcms;
import br.com.ararati.populadores.impl.impostos.ipi.ChainIPI;
import br.com.ararati.populadores.impl.impostos.pis.ControlePis;
import br.com.ararati.utils.UtilConvert;
import enviNFe_v310.ObjectFactory;
import enviNFe_v310.TEnderEmi;
import enviNFe_v310.TEndereco;
import enviNFe_v310.TEnviNFe;
import enviNFe_v310.TIpi;
import enviNFe_v310.TLocal;
import enviNFe_v310.TNFe;
import enviNFe_v310.TNFe.InfNFe;
import enviNFe_v310.TNFe.InfNFe.AutXML;
import enviNFe_v310.TNFe.InfNFe.Avulsa;
import enviNFe_v310.TNFe.InfNFe.Cana;
import enviNFe_v310.TNFe.InfNFe.Cana.Deduc;
import enviNFe_v310.TNFe.InfNFe.Cana.ForDia;
import enviNFe_v310.TNFe.InfNFe.Cobr;
import enviNFe_v310.TNFe.InfNFe.Cobr.Dup;
import enviNFe_v310.TNFe.InfNFe.Cobr.Fat;
import enviNFe_v310.TNFe.InfNFe.Compra;
import enviNFe_v310.TNFe.InfNFe.Dest;
import enviNFe_v310.TNFe.InfNFe.Det;
import enviNFe_v310.TNFe.InfNFe.Det.Imposto;
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.COFINS;
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.COFINSST;
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.ICMS;
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.II;
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.ISSQN;
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.PIS;
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.PISST;
import enviNFe_v310.TNFe.InfNFe.Det.Imposto.ICMSUFDest;
import enviNFe_v310.TNFe.InfNFe.Det.ImpostoDevol;
import enviNFe_v310.TNFe.InfNFe.Det.ImpostoDevol.IPI;
import enviNFe_v310.TNFe.InfNFe.Det.Prod;
import enviNFe_v310.TNFe.InfNFe.Det.Prod.Arma;
import enviNFe_v310.TNFe.InfNFe.Det.Prod.Comb;
import enviNFe_v310.TNFe.InfNFe.Det.Prod.Comb.CIDE;
import enviNFe_v310.TNFe.InfNFe.Det.Prod.DI;
import enviNFe_v310.TNFe.InfNFe.Det.Prod.DI.Adi;
import enviNFe_v310.TNFe.InfNFe.Det.Prod.DetExport;
import enviNFe_v310.TNFe.InfNFe.Det.Prod.DetExport.ExportInd;
import enviNFe_v310.TNFe.InfNFe.Det.Prod.Med;
import enviNFe_v310.TNFe.InfNFe.Det.Prod.VeicProd;
import enviNFe_v310.TNFe.InfNFe.Emit;
import enviNFe_v310.TNFe.InfNFe.Exporta;
import enviNFe_v310.TNFe.InfNFe.Pag;
import enviNFe_v310.TNFe.InfNFe.Pag.Card;
import enviNFe_v310.TNFe.InfNFe.Ide;
import enviNFe_v310.TNFe.InfNFe.Ide.NFref;
import enviNFe_v310.TNFe.InfNFe.Ide.NFref.RefECF;
import enviNFe_v310.TNFe.InfNFe.Ide.NFref.RefNF;
import enviNFe_v310.TNFe.InfNFe.Ide.NFref.RefNFP;
import enviNFe_v310.TNFe.InfNFe.InfAdic;
import enviNFe_v310.TNFe.InfNFe.InfAdic.ObsCont;
import enviNFe_v310.TNFe.InfNFe.InfAdic.ObsFisco;
import enviNFe_v310.TNFe.InfNFe.InfAdic.ProcRef;
import enviNFe_v310.TNFe.InfNFe.Total;
import enviNFe_v310.TNFe.InfNFe.Total.ICMSTot;
import enviNFe_v310.TNFe.InfNFe.Total.ISSQNtot;
import enviNFe_v310.TNFe.InfNFe.Total.RetTrib;
import enviNFe_v310.TNFe.InfNFe.Transp;
import enviNFe_v310.TNFe.InfNFe.Transp.Transporta;
import enviNFe_v310.TNFe.InfNFe.Transp.Vol;
import enviNFe_v310.TNFe.InfNFe.Transp.Vol.Lacres;
import enviNFe_v310.TNFe.InfNFeSupl;
import enviNFe_v310.TUf;
import enviNFe_v310.TUfEmi;
import enviNFe_v310.TVeiculo;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.apache.commons.lang3.StringUtils;

/**
 * Classe responsável por preencher os objetos da Nota Fiscal Eletrônica v3.10
 * com informações geradas no sistema.
 *
 * @author tiago
 */
public class EnviNFe {

    private final ConstroiChaveAcesso constroiChaveAcesso = new ConstroiChaveAcesso();
    private final SimpleDateFormat sdfDateHour = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
    private final SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
    private final SimpleDateFormat sdfLote = new SimpleDateFormat("yyyyMMddHHmmss");

    private String schemaToXML(TEnviNFe enviNfe) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(TEnviNFe.class);
        JAXBElement<TEnviNFe> element = new ObjectFactory().createEnviNFe(enviNfe);
        StringWriter sw = new StringWriter();
        Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        marshaller.marshal(element, sw);

        String xml = sw.toString();
        xml = xml.replaceAll("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\" ", "");
        xml = xml.replaceAll("xmlns=\"http://www.portalfiscal.inf.br/nfe\" ", "");
        xml = xml.replaceAll("<NFe>", "<NFe xmlns=\"http://www.portalfiscal.inf.br/nfe\">");
        return xml;
    }

    public String fillTEnviNFe(DadosNFe dadosNFe) throws NFeException, JAXBException {
        TEnviNFe toReturn = new TEnviNFe();
        toReturn.setVersao(dadosNFe.getVersao());
        toReturn.setIdLote(sdfLote.format(new Date())); // ex: 20140926172705 = ANO/MES/DIA/HORA/MIN/SEG
        toReturn.setIndSinc("0"); // ASSINCRONO
        toReturn.getNFe().addAll(fillTNFe(dadosNFe));
        return schemaToXML(toReturn);
    }

    /**
     * Tag Raiz : TNFe.
     */
    private List<TNFe> fillTNFe(DadosNFe nfe) throws NFeException {
        List<TNFe> toReturn = new ArrayList<>();
        TNFe tnfe;

        // for(){ geração de nf-es em massa (máx 50 por lote)
        tnfe = new TNFe();
        tnfe.setInfNFe(fillInfNFe(nfe));
        tnfe.setInfNFeSupl(null);
        toReturn.add(tnfe);
        // }
        return toReturn;
    }

    /**
     * A - Dados da Nota Fiscal eletrônica.
     */
    private InfNFe fillInfNFe(DadosNFe dadosNFe) throws NFeException {
        if (dadosNFe == null) {
            return null;
        }

        InfNFe toReturn = new InfNFe();
        toReturn.getAutXML().addAll(fillAutXML(dadosNFe));
        toReturn.setAvulsa(fillAvulsa(dadosNFe));
        toReturn.setCana(fillCana(dadosNFe));
        toReturn.setCobr(fillCobranca(dadosNFe));
        toReturn.setCompra(fillCompra(dadosNFe));
        toReturn.setDest(fillDest(dadosNFe));
        toReturn.getDet().addAll(fillDet(dadosNFe));
        toReturn.setEmit(fillEmit(dadosNFe));
        toReturn.setEntrega(fillTLocal(dadosNFe.getIdentificacaoLocalEntrega().getLocal()));
        toReturn.setExporta(fillExportacao(dadosNFe));
        toReturn.setIde(fillIde(dadosNFe));
        toReturn.setId("NFe".concat(constroiChaveAcesso.constroiChaveDeAcesso(toReturn)));
        toReturn.getIde().setCDV(toReturn.getId().substring(46, 47));
        toReturn.setInfAdic(fillDadosAdicionais(dadosNFe));
        toReturn.getPag().addAll(fillPag(dadosNFe));
        toReturn.setRetirada(fillTLocal(dadosNFe.getIdentificacaoLocalRetirada().getLocal()));
        toReturn.setTotal(fillTotais(dadosNFe));
        toReturn.setTransp(fillTransporte(dadosNFe));
        toReturn.setVersao(dadosNFe.getIdentificacaoNFe().getVerproc());
        return toReturn;
    }

    /**
     * B - Identificação da Nota Fiscal eletrônica.
     */
    private Ide fillIde(DadosNFe dadosNFe) {
        IdentificacaoNFe identificacao = dadosNFe.getIdentificacaoNFe();

        if (identificacao == null) {
            return null;
        }

        Ide ide = new Ide();

        Random random = new Random();
        int i = random.nextInt(99999999) * 1;
        String cnf = StringUtils.leftPad(String.valueOf(i), 8, "0");

//        ide.setCDV(dadosNFe.getIde().substring(46, 47));
        ide.setCMunFG(identificacao.getCmunfg());
        ide.setCNF(cnf);
        ide.setCUF(identificacao.getCuf());
        ide.setDhEmi(sdfDateHour.format(identificacao.getDhemi()));
        ide.setDhSaiEnt(sdfDateHour.format(identificacao.getDhsaient()));
        ide.setFinNFe(identificacao.getFinnfe() != null ? identificacao.getFinnfe().getCodigo() : null);
        ide.setIdDest(identificacao.getIddest() != null ? identificacao.getIddest().getCodigo() : null);
        ide.setIndFinal(identificacao.getIndfinal() != null ? identificacao.getIndfinal().getCodigo() : null);
//        ide.setIndPag(identificacao.getIndpag() != null ? identificacao.getIndpag().getCodigo() : null);
        ide.setIndPres(identificacao.getIndpres() != null ? identificacao.getIndpres().getCodigo() : null);
        ide.setMod(identificacao.getModelo() != null ? identificacao.getModelo().getCodigo() : null);
        ide.setNNF(identificacao.getNnf());
        ide.setNatOp(identificacao.getNatop());
        ide.setProcEmi(identificacao.getProcemi() != null ? identificacao.getProcemi().getCodigo() : null);
        ide.setSerie(identificacao.getSerie());
        ide.setTpAmb(identificacao.getTpamb() != null ? identificacao.getTpamb().getCodigo() : null);
        ide.setTpEmis(identificacao.getTpemis() != null ? identificacao.getTpemis().getCodigo() : null);
        ide.setTpImp(identificacao.getTpimp() != null ? identificacao.getTpimp().getCodigo() : null);
        ide.setTpNF(identificacao.getTpnf() != null ? identificacao.getTpnf().getCodigo() : null);
        ide.setVerProc(identificacao.getVerproc());

        // se emissão for em contingência
        if (!ide.getTpEmis().equals("1")) {
            ide.setDhCont(sdfDateHour.format(identificacao.getDhcont()));
            ide.setXJust(identificacao.getXjust());
        }

        ide.getNFref().addAll(fillNFref(identificacao));

        return ide;
    }

    /**
     * BA - Documento Fiscal Referenciado.
     */
    private List<NFref> fillNFref(IdentificacaoNFe identificacao) {
        List<NFref> refs = new ArrayList<>();
        NFref nfref;

        for (DocumentoFiscalReferenciado referencia : identificacao.getDocumentosFiscaisReferenciados()) {
            nfref = new NFref();
            nfref.setRefCTe(referencia.getRefcte());
            nfref.setRefNFe(referencia.getRefnfe());
            nfref.setRefECF(fillRefECF(referencia));
            nfref.setRefNF(fillRefNF(referencia));
            nfref.setRefNFP(fillRefNFP(referencia));
            refs.add(nfref);
        }

        return refs;
    }

    /**
     * BA20 - Informações do Cupom Fiscal referenciado
     */
    private RefECF fillRefECF(DocumentoFiscalReferenciado referencia) {
        if (referencia == null) {
            return null;
        }

        RefECF refecf = new RefECF();
        refecf.setMod(referencia.getRefecfmod() != null ? referencia.getRefecfmod().getCodigo() : null);
        refecf.setNCOO(referencia.getRefecfncoo());
        refecf.setNECF(referencia.getRefecfnecf());
        return refecf;
    }

    /**
     * BA03 - Informação da NF modelo 1/1A referenciada
     */
    private RefNF fillRefNF(DocumentoFiscalReferenciado referencia) {
        if (referencia == null) {
            return null;
        }

        RefNF refnf = new RefNF();
        refnf.setAAMM(referencia.getRefnfeaamm());
        refnf.setCNPJ(referencia.getRefnfecnpj());
        refnf.setCUF(referencia.getRefnfecuf());
        refnf.setMod(referencia.getRefnfemod());
        refnf.setNNF(referencia.getRefnfennf());
        refnf.setSerie(referencia.getRefnfeserie());
        return refnf;
    }

    /**
     * BA10 - Informações da NF de produtor rural referenciada
     */
    private RefNFP fillRefNFP(DocumentoFiscalReferenciado referencia) {
        if (referencia == null) {
            return null;
        }

        RefNFP refnfp = new RefNFP();
        refnfp.setAAMM(referencia.getRefnfpaamm());
        refnfp.setCNPJ(referencia.getRefnfpcnpj());
        refnfp.setCPF(referencia.getRefnfpcpf());
        refnfp.setCUF(referencia.getRefnfpcuf());
        refnfp.setIE(referencia.getRefnfpie());
        refnfp.setMod(referencia.getRefnfpmod() != null ? referencia.getRefnfpmod().getCodigo() : null);
        refnfp.setNNF(referencia.getRefnfpnnf());
        refnfp.setSerie(referencia.getRefnfpserie());
        return refnfp;
    }

    /**
     * C - Identificação do Emitente da Nota Fiscal eletrônica.
     */
    private Emit fillEmit(DadosNFe dadosNFe) {
        IdentificacaoEmitente emitente = dadosNFe.getIdentificacaoEmitente();

        if (emitente == null) {
            return null;
        }

        Emit toReturn = new Emit();
        toReturn.setCNAE(emitente.getCnae());
        toReturn.setCRT(emitente.getCrt() != null ? emitente.getCrt().getCodigo() : null);
        toReturn.setEnderEmit(fillEnderEmi(emitente.getEndereco()));
        toReturn.setIE(emitente.getIe());
        toReturn.setIEST(emitente.getIest());
        toReturn.setIM(emitente.getIm());
        toReturn.setXFant(emitente.getXfant());
        toReturn.setXNome(emitente.getXnome());

        // SETA DOCUMENTO
        if (emitente.getDocumento() != null) {
            if (emitente.getDocumento().length() == 11) {
                toReturn.setCPF(emitente.getDocumento());
            } else if (emitente.getDocumento().length() == 14) {
                toReturn.setCNPJ(emitente.getDocumento());
            }
        }

        return toReturn;
    }

    /**
     * C05 - Endereço do emitente.
     */
    private TEnderEmi fillEnderEmi(Endereco endereco) {
        if (endereco == null) {
            return null;
        }

        TEnderEmi toReturn = new TEnderEmi();
        toReturn.setCEP(endereco.getEndCep());
        toReturn.setCMun(endereco.getEndCmun());
        toReturn.setCPais(endereco.getEndCpais());
        toReturn.setFone(endereco.getEndFone());
        toReturn.setNro(endereco.getEndNro());
        toReturn.setUF(endereco.getEndUf() != null ? TUfEmi.valueOf(endereco.getEndUf()) : null);
        toReturn.setXBairro(endereco.getEndXbairro());
        toReturn.setXCpl(endereco.getEndXcpl());
        toReturn.setXLgr(endereco.getEndXlgr());
        toReturn.setXMun(endereco.getEndXmun());
        toReturn.setXPais(endereco.getEndXpais());
        return toReturn;
    }

    private TLocal fillTLocal(Local local) {
        if (local == null) {
            return null;
        }

        TLocal loc = new TLocal();

        if (local.getLocDocumento() != null) {
            if (local.getLocDocumento().length() > 11) {
                loc.setCNPJ(local.getLocDocumento());
            } else {
                loc.setCPF(local.getLocDocumento());
            }
        }

        loc.setCMun(local.getLocCmun());
        loc.setNro(local.getLocNro());
        loc.setUF(local.getLocUf() != null ? TUf.valueOf(local.getLocUf()) : null);
        loc.setXBairro(local.getLocXbairro());
        loc.setXCpl(local.getLocXcpl());
        loc.setXLgr(local.getLocXlgr());
        loc.setXMun(local.getLocXmun());
        return loc;

    }

    /**
     * D. Identificação do Fisco Emitente da NF-e.
     */
    private Avulsa fillAvulsa(DadosNFe dadosNFe) {
        IdentificacaoAvulsa avulsa = dadosNFe.getIdentificacaoAvulsa();

        if (avulsa == null) {
            return null;
        }

        Avulsa av = new Avulsa();
        av.setCNPJ(avulsa.getCnpj());
        av.setDEmi(avulsa.getDemi() != null ? sdfDate.format(avulsa.getDemi()) : null);
        av.setDPag(avulsa.getDpag() != null ? sdfDate.format(avulsa.getDpag()) : null);
        av.setFone(avulsa.getFone());
        av.setMatr(avulsa.getMatr());
        av.setNDAR(avulsa.getNdar());
        av.setRepEmi(avulsa.getRepemi());
        av.setUF(avulsa.getUf());
        av.setVDAR(avulsa.getVdar() != null ? avulsa.getVdar().toString() : null);
        av.setXAgente(avulsa.getXagente());
        av.setXOrgao(avulsa.getXorgao());
        return av;
    }

    /**
     * E - Identificação do Destinatário da Nota Fiscal Eletrônica.
     */
    private Dest fillDest(DadosNFe dadosNFe) {
        IdentificacaoDestinatario destinatario = dadosNFe.getIdentificacaoDestinatario();

        if (destinatario == null) {
            return null;
        }

        Dest toReturn = new Dest();
        toReturn.setEmail(destinatario.getEmail());
        toReturn.setEnderDest(fillTEndereco(destinatario.getEndereco()));
        toReturn.setIE(destinatario.getIe());
        toReturn.setIM(destinatario.getIm());
        toReturn.setISUF(destinatario.getIsuf());
        toReturn.setIndIEDest(destinatario.getIndiedest() != null ? destinatario.getIndiedest().getCodigo() : null);

        // SETA DOCUMENTO
        if (destinatario.getDocumento() != null) {
            if (destinatario.getDocumento().length() == 11) {
                toReturn.setCPF(destinatario.getDocumento());
            } else if (destinatario.getDocumento().length() == 14) {
                toReturn.setCNPJ(destinatario.getDocumento());
            } else if (destinatario.getDocumento().length() > 14) {
                toReturn.setIdEstrangeiro(destinatario.getDocumento());
            }
        }

        // SETA AMBIENTE
        if (dadosNFe.getIdentificacaoNFe().getTpamb().equals(NFeTipoAmbiente.HOMOLOGACAO)) {
            toReturn.setXNome("NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
        } else {
            toReturn.setXNome(destinatario.getXnome());
        }

        return toReturn;
    }

    /**
     * G - Endereço do Destinatário da NF-e.
     */
    private TEndereco fillTEndereco(Endereco endereco) {
        if (endereco == null) {
            return null;
        }

        TEndereco toReturn = new TEndereco();
        toReturn.setCEP(endereco.getEndCep());
        toReturn.setCMun(endereco.getEndCmun());
        toReturn.setCPais(endereco.getEndCpais());
        toReturn.setFone(endereco.getEndFone());
        toReturn.setNro(endereco.getEndNro());
        toReturn.setUF(endereco.getEndUf() != null ? TUf.valueOf(endereco.getEndUf()) : null);
        toReturn.setXBairro(endereco.getEndXbairro());
        toReturn.setXCpl(endereco.getEndXcpl());
        toReturn.setXLgr(endereco.getEndXlgr());
        toReturn.setXMun(endereco.getEndXmun());
        toReturn.setXPais(endereco.getEndXpais());
        return toReturn;
    }

    /**
     * GA - Autorização para obter XML.
     */
    private List<AutXML> fillAutXML(DadosNFe dadosNFe) {
        List<AutXML> toReturn = new LinkedList<>();
        AutXML autXml;

        for (AutorizacaoObterXml autorizado : dadosNFe.getAutorizadosObterXml()) {
            autXml = new AutXML();

            if (autorizado.getDocumento().length() > 11) {
                autXml.setCNPJ(autorizado.getDocumento());
            } else {
                autXml.setCPF(autorizado.getDocumento());
            }
            toReturn.add(autXml);
        }
        return toReturn;
    }

    /**
     * H - Detalhamento de Produtos e Serviços da NF-e.
     */
    private List<Det> fillDet(DadosNFe dadosNFe) throws NFeException {
        List<Det> toReturn = new ArrayList<>();
        Det det;

        for (DetalhamentoProdutoServico item : dadosNFe.getDetalhamentoProdutosServicos()) {
            det = new Det();
            det.setProd(fillProd(item));
            det.setImposto(fillImpostos(item));
            det.setNItem(item.getNitem());
            det.setImpostoDevol(fillImpostoDevol(item));
            det.setInfAdProd(item.getInfadprod());
            toReturn.add(det);
        }

        return toReturn;
    }

    /**
     * I - Produtos e Serviços da NF-e.
     */
    private Prod fillProd(DetalhamentoProdutoServico item) {
        if (item == null) {
            return null;
        }

        Prod prod = new Prod();
        prod.setCEAN(item.getCean());
        prod.setCEANTrib(item.getCeantrib());
        prod.setCEST(item.getCest());
        prod.setCFOP(item.getCfop());
        prod.setCProd(item.getCprod());
        prod.setComb(fillComb(item)); // LA. Detalhamento Específico de Combustíveis
        prod.setEXTIPI(item.getExtipi());
        prod.setIndTot(item.getIndtot() != null ? item.getIndtot().getCodigo() : null);
        prod.setNCM(item.getNcm());
        prod.setNRECOPI(item.getPapelImune() != null ? item.getPapelImune().getNrecopi() : null); // LB. Detalhamento Específico para Operação com Papel Imune
        prod.setQCom(item.getQcom() != null ? item.getQcom().toString() : null);
        prod.setQTrib(item.getQtrib() != null ? item.getQtrib().toString() : null);
        prod.setUCom(item.getUcom() != null ? UtilConvert.removeAcentosMaiuscula(item.getUcom()) : null);
        prod.setUTrib(item.getUtrib() != null ? UtilConvert.removeAcentosMaiuscula(item.getUtrib()) : null);
        prod.setVDesc(item.getVdesc() != null ? item.getVdesc().toString() : null);
        prod.setVFrete(item.getVfrete() != null ? item.getVfrete().toString() : null);
        prod.setVOutro(item.getVoutro() != null ? item.getVoutro().toString() : null);
        prod.setVProd(item.getVprod() != null ? item.getVprod().toString() : null);
        prod.setVSeg(item.getVseg() != null ? item.getVseg().toString() : null);
        prod.setVUnCom(item.getVuncom() != null ? item.getVuncom().toString() : null);
        prod.setVUnTrib(item.getVuntrib() != null ? item.getVuntrib().toString() : null);
        prod.setVeicProd(fillVeicProd(item)); // JA. Detalhamento Específico de Veículos novos
        prod.setXProd(item.getXprod());
        prod.getArma().addAll(fillArma(item)); // L. Detalhamento Específico de Armamentos
        prod.getDI().addAll(fillDI(item)); // I01. Produtos e Serviços / Declaração de Importação
        prod.getDetExport().addAll(fillDetExport(item)); // I03. Produtos e Serviços / Grupo de Exportação
        prod.getMed().addAll(fillMed(item)); // K. Detalhamento Específico de Medicamento e de matérias-primas farmacêuticas
        prod.getNVE().addAll(fillNVE(item)); // 104a. Codificação NVE - Nomenclatura de Valor Aduaneiro e Estatística.

        // I05. Produtos e Serviços / Pedido de Compra
        prod.setNItemPed(item.getNitemped());
        prod.setXPed(item.getXped());

        // I07. Produtos e Serviços / Grupo Diversos
        prod.setNFCI(item.getNfci());

        return prod;
    }

    /**
     * I18 - Declaração de Importação.
     */
    private List<DI> fillDI(DetalhamentoProdutoServico item) {
        List<DI> toReturn = new ArrayList<>();
        DI di;

        for (DeclaracaoImportacao importacao : item.getDeclaracoesDeImportacao()) {
            di = new DI();
            di.setCExportador(importacao.getCexportador());
            di.setCNPJ(importacao.getCnpj());
            di.setDDI(sdfDate.format(importacao.getDdi()));
            di.setDDesemb(sdfDate.format(importacao.getDdesemb()));
            di.setNDI(importacao.getNdi());
            di.setTpIntermedio(importacao.getTpintermedio() != null ? importacao.getTpintermedio().getCodigo() : null);
            di.setTpViaTransp(importacao.getTpviatransp() != null ? importacao.getTpviatransp().getCodigo() : null);
            di.setUFDesemb(TUfEmi.valueOf(importacao.getUfdesemb()));
            di.setUFTerceiro(TUfEmi.valueOf(importacao.getUfterceiro()));
            di.setVAFRMM(importacao.getVafrmm() != null ? importacao.getVafrmm().toString() : null);
            di.setXLocDesemb(importacao.getXlocdesemb());
            di.getAdi().addAll(fillAdi(importacao));
            toReturn.add(di);
        }
        return toReturn;
    }

    /**
     * I25 - Adições.
     */
    private List<Adi> fillAdi(DeclaracaoImportacao importacao) {
        List<Adi> toReturn = new ArrayList<>();
        Adi adi;

        for (DeclaracaoImportacaoAdicoes adicao : importacao.getAdicoes()) {
            adi = new Adi();
            adi.setCFabricante(adicao.getCfabricante());
            adi.setNAdicao(adicao.getNadicao());
            adi.setNDraw(adicao.getNdraw());
            adi.setNSeqAdic(adicao.getNseqadic());
            adi.setVDescDI(adicao.getVdescdi() != null ? adicao.getVdescdi().toString() : null);
            toReturn.add(adi);
        }
        return toReturn;
    }

    /**
     * I50 - Grupo de informações de exportação para o item.
     */
    private List<DetExport> fillDetExport(DetalhamentoProdutoServico item) {
        List<DetExport> toReturn = new ArrayList<>();
        DetExport detExport;

        for (GrupoExportacao exportacao : item.getGruposDeExportacao()) {
            detExport = new DetExport();
            detExport.setNDraw(exportacao.getNdraw());
            detExport.setExportInd(fillExportInd(exportacao));
            toReturn.add(detExport);
        }
        return toReturn;
    }

    /**
     * I52 - Grupo sobre exportação indireta.
     */
    private ExportInd fillExportInd(GrupoExportacao exportacao) {
        if (exportacao == null) {
            return null;
        }

        ExportInd toReturn = new ExportInd();
        toReturn.setChNFe(exportacao.getChnfe());
        toReturn.setNRE(exportacao.getNre());
        toReturn.setQExport(exportacao.getQexport() != null ? exportacao.getQexport().toString() : null);
        return toReturn;
    }

    /**
     * I05A - Codificação NVE - Nomenclatura de Valor Aduaneiro e Estatística.
     */
    private List<String> fillNVE(DetalhamentoProdutoServico item) {
        List<String> toReturn = new ArrayList<>();
        item.getNves().forEach((nva) -> {
            toReturn.add(nva.getNve());
        });
        return toReturn;
    }

    /**
     * J - Produto Específico
     *
     * JA - Detalhamento Específico de Veículos novos.
     */
    private VeicProd fillVeicProd(DetalhamentoProdutoServico item) {
        if (item == null || item.getVeiculo() == null) {
            return null;
        }

        DetalhamentoEspecificoVeiculo veiculo = item.getVeiculo();

        VeicProd toReturn = new VeicProd();
        toReturn.setAnoFab(veiculo.getAnofab());
        toReturn.setAnoMod(veiculo.getAnomod());
        toReturn.setCCor(veiculo.getCcor());
        toReturn.setCCorDENATRAN(veiculo.getCcorDENATRAN() != null ? veiculo.getCcorDENATRAN().getCodigo() : null);
        toReturn.setCMT(veiculo.getCmt());
        toReturn.setCMod(veiculo.getCmod());
        toReturn.setChassi(veiculo.getChassi());
        toReturn.setCilin(veiculo.getCilin());
        toReturn.setCondVeic(veiculo.getCondveic() != null ? veiculo.getCondveic().getCodigo() : null);
        toReturn.setDist(veiculo.getDist());
        toReturn.setEspVeic(veiculo.getEspveic());
        toReturn.setLota(veiculo.getLota());
        toReturn.setNMotor(veiculo.getNmotor());
        toReturn.setNSerie(veiculo.getNserie());
        toReturn.setPesoB(veiculo.getPesob() != null ? veiculo.getPesob().toString() : null);
        toReturn.setPesoL(veiculo.getPesol() != null ? veiculo.getPesol().toString() : null);
        toReturn.setPot(veiculo.getPot());
        toReturn.setTpComb(veiculo.getTpcomb() != null ? veiculo.getTpcomb().getCodigo() : null);
        toReturn.setTpOp(veiculo.getTpop() != null ? veiculo.getTpop().getCodigo() : null);
        toReturn.setTpPint(veiculo.getTppint());
        toReturn.setTpRest(veiculo.getTprest() != null ? veiculo.getTprest().getCodigo() : null);
        toReturn.setTpVeic(veiculo.getTpveic() != null ? veiculo.getTpveic().getCodigo() : null);
        toReturn.setVIN(veiculo.getVin() != null ? veiculo.getVin().getCodigo() : null);
        toReturn.setXCor(veiculo.getXcor());
        return toReturn;
    }

    /**
     * K - Detalhamento Específico de Medicamento e de matérias-primas
     * farmacêuticas.
     */
    private List<Med> fillMed(DetalhamentoProdutoServico item) {
        List<Med> toReturn = new ArrayList<>();
        Med med;

        for (DetalhamentoEspecificoMedicamento medicamento : item.getMedicamentos()) {
            med = new Med();
//            med.setDFab(sdfDate.format(medicamento.getDfab()));
//            med.setDVal(sdfDate.format(medicamento.getDval()));
//            med.setNLote(medicamento.getNlote());
//            med.setQLote(medicamento.getQlote() != null ? medicamento.getQlote().toString() : null);
            med.setVPMC(medicamento.getVpmc() != null ? medicamento.getVpmc().toString() : null);
            toReturn.add(med);
        }
        return toReturn;
    }

    /**
     * L - Detalhamento Específico de Armamentos.
     */
    private List<Arma> fillArma(DetalhamentoProdutoServico item) {
        List<Arma> toReturn = new ArrayList<>();
        Arma arma;

        for (DetalhamentoEspecificoArmamento armamento : item.getArmamentos()) {
            arma = new Arma();
            arma.setDescr(armamento.getDescr());
            arma.setNCano(armamento.getNcano());
            arma.setNSerie(armamento.getNserie());
            arma.setTpArma(armamento.getTparma());
            toReturn.add(arma);
        }

        return toReturn;
    }

    /**
     * LA - Detalhamento Específico de Combustíveis.
     */
    private Comb fillComb(DetalhamentoProdutoServico item) {
        if (item == null || item.getCombustivel() == null) {
            return null;
        }

        DetalhamentoEspecificoCombustivel combustivel = item.getCombustivel();

        Comb toReturn = new Comb();
        toReturn.setCIDE(fillCIDE(combustivel));
        toReturn.setCODIF(combustivel.getCodif());
        toReturn.setCProdANP(combustivel.getCprodanp());
//        toReturn.setPMixGN(combustivel.getPmixgn() != null ? combustivel.getPmixgn().toString() : null);
        toReturn.setQTemp(combustivel.getQtemp() != null ? combustivel.getQtemp().toString() : null);
        toReturn.setUFCons(combustivel.getUfcons() != null ? TUf.valueOf(combustivel.getUfcons()) : null);
        return toReturn;
    }

    /**
     * LA07 - Informações da CIDE
     */
    private CIDE fillCIDE(DetalhamentoEspecificoCombustivel combustivel) {
        if (combustivel == null) {
            return null;
        }

        CIDE toReturn = new CIDE();
        toReturn.setQBCProd(combustivel.getQbcprod() != null ? combustivel.getQbcprod().toString() : null);
        toReturn.setVAliqProd(combustivel.getValiqprod() != null ? combustivel.getValiqprod().toString() : null);
        toReturn.setVCIDE(combustivel.getVcide() != null ? combustivel.getVcide().toString() : null);
        return toReturn;
    }

    /**
     * M - Tributos incidentes no Produto ou Serviço.
     */
    private Imposto fillImpostos(DetalhamentoProdutoServico item) throws NFeException {
        if (item == null) {
            return null;
        }

        Imposto toReturn = new Imposto();

        // PREENCHE ICMS
        ICMS icms = fillICMS(item);
        if (icms != null) {
            toReturn.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoICMS(icms));
        }

        // PREENCHE IPI
        TIpi ipi = fillIPI(item);
        if (ipi != null) {
            toReturn.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoIPI(ipi));
        }

        // PREENCHE PIS
        PIS pis = fillPIS(item);
        if (pis != null) {
            toReturn.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoPIS(pis));
        }

        // PREENCHE PISST
        PISST pisst = fillPISST(item);
        if (pisst != null) {
            toReturn.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoPISST(pisst));
        }

        // PREENCHE COFINS
        COFINS cofins = fillCOFINS(item);
        if (cofins != null) {
            toReturn.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoCOFINS(cofins));
        }

        // PREENCHE COFINSST
        COFINSST cofinsst = fillCOFINSST(item);
        if (cofinsst != null) {
            toReturn.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoCOFINSST(cofinsst));
        }

        // PREENCHE ICMSUFDEST
        ICMSUFDest icmsUfDest = fillICMSUFDest(item);
        if (icmsUfDest != null) {
            toReturn.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoICMSUFDest(icmsUfDest));
        }

        // PREENCHE ISSQN
        ISSQN issqn = fillISSQN(item);
        if (issqn != null) {
            toReturn.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoISSQN(issqn));
        }

        // PREENCHE II
        II ii = fillII(item);
        if (ii != null) {
            toReturn.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoII(ii));
        }

        // PREENCHE VTOTTRIBUT
        String totTrib = fillVTotTrib(item);
        if (totTrib != null) {
            toReturn.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoVTotTrib(totTrib));
        }

        return toReturn;
    }

    /**
     * M01 - Informação do ICMS Interestadual
     */
    private ICMSUFDest fillICMSUFDest(DetalhamentoProdutoServico item) {
        if (item == null) {
            return null;
        }

        DadosNFe dados = item.getDadosNFe();
        IdentificacaoNFe ide = item.getDadosNFe().getIdentificacaoNFe();

        if (dados.getIdentificacaoDestinatario().getIndiedest() != null
                && dados.getIdentificacaoDestinatario().getIndiedest().equals(NFeTipoIndicadorIEDestinatario.NAO_CONTRIBUINTE)
                && ide.getIddest() != null
                && ide.getIddest().equals(NFeTipoDestinoOperacao.INTERESTADUAL)
                && ide.getIndfinal() != null
                && ide.getIndfinal().equals(NFeTipoOperacaoConsumidor.FINAL)) {

            ICMSUFDest toReturn = new ICMSUFDest();
            toReturn.setPFCPUFDest(item.getPfcpufdest() != null ? item.getPfcpufdest().toString() : null);
            toReturn.setPICMSInter(item.getPicmsinter() != null ? item.getPicmsinter().toString() : null);
            toReturn.setPICMSInterPart(item.getPicmsinterpart() != null ? item.getPicmsinterpart().toString() : null);
            toReturn.setPICMSUFDest(item.getPicmsufdest() != null ? item.getPicmsufdest().toString() : null);
            toReturn.setVBCUFDest(item.getVbcicmsufdest() != null ? item.getVbcicmsufdest().toString() : null);
            toReturn.setVFCPUFDest(item.getVfcpufdest() != null ? item.getVfcpufdest().toString() : null);
            toReturn.setVICMSUFDest(item.getVicmsufdest() != null ? item.getVicmsufdest().toString() : null);
            toReturn.setVICMSUFRemet(item.getVicmsufremet() != null ? item.getVicmsufremet().toString() : null);
            return toReturn;
        }
        return null;
    }

    /**
     * N - ICMS Normal e ST.
     */
    private ICMS fillICMS(DetalhamentoProdutoServico item) throws NFeException {
        if (item == null || item.getCsticms() == null) {
            return null;
        }

        ICMS toReturn = new ICMS();
        ConstroleDeIcms verificaTipoICMS = new ConstroleDeIcms();
        verificaTipoICMS.verificaTipoICMS(item, toReturn);
        return toReturn;
    }

    /**
     * O - Imposto sobre Produtos Industrializados.
     */
    private TIpi fillIPI(DetalhamentoProdutoServico item) {
        if (item == null || item.getCstipi() == null) {
            return null;
        }

        TIpi toReturn = new TIpi();
        ChainIPI chainIpi = new ChainIPI();
        chainIpi.getChainOfTIpi(item, toReturn);

        toReturn.setCNPJProd(item.getCnpjprod());
        toReturn.setCSelo(item.getCselo());
        toReturn.setQSelo(item.getQselo());
        toReturn.setClEnq(item.getClenq());
        toReturn.setCEnq(item.getCenq());
        return toReturn;
    }

    /**
     * P - Imposto de Importação.
     */
    private II fillII(DetalhamentoProdutoServico item) {
        if (item == null) {
            return null;
        }

        II toReturn = new II();
        toReturn.setVBC(item.getVbcii() != null ? item.getVbcii().toString() : null);
        toReturn.setVDespAdu(item.getVdespadu() != null ? item.getVdespadu().toString() : null);
        toReturn.setVII(item.getVii() != null ? item.getVii().toString() : null);
        toReturn.setVIOF(item.getViof() != null ? item.getViof().toString() : null);
        return toReturn;
    }

    /**
     * Q - PIS.
     */
    private PIS fillPIS(DetalhamentoProdutoServico item) {
        if (item == null) {
            return null;
        }

        PIS toReturn = new PIS();
        ControlePis controlePis = new ControlePis();
        controlePis.controlePis(item, toReturn);
        return toReturn;
    }

    /**
     * R - PIS ST.
     */
    private PISST fillPISST(DetalhamentoProdutoServico item) {
        if (item == null) {
            return null;
        }

        PISST toReturn = new PISST();
        toReturn.setPPIS(null);
        toReturn.setQBCProd(null);
        toReturn.setVAliqProd(null);
        toReturn.setVBC(null);
        toReturn.setVPIS(null);
        return toReturn;
    }

    /**
     * S - COFINS
     */
    private COFINS fillCOFINS(DetalhamentoProdutoServico item) {
        if (item == null) {
            return null;
        }

        COFINS toReturn = new COFINS();
        ControleCofins controleCofins = new ControleCofins();
        controleCofins.controleCofins(item, toReturn);
        return toReturn;
    }

    /**
     * T COFINSST.
     */
    private COFINSST fillCOFINSST(DetalhamentoProdutoServico item) {
        if (item == null) {
            return null;
        }

        COFINSST toReturn = new COFINSST();
        toReturn.setPCOFINS(null);
        toReturn.setQBCProd(null);
        toReturn.setVAliqProd(null);
        toReturn.setVBC(null);
        toReturn.setVCOFINS(null);
        return toReturn;
    }

    private String fillVTotTrib(DetalhamentoProdutoServico item) {
        if (item == null) {
            return null;
        }

        return item.getVtottrib() != null ? item.getVtottrib().toString() : null;
    }

    /**
     * U - ISSQN.
     */
    private ISSQN fillISSQN(DetalhamentoProdutoServico item) {
        if (item == null) {
            return null;
        }

        ISSQN toReturn = new ISSQN();
        toReturn.setCListServ(item.getClistserv() != null ? item.getClistserv().getCodigo() : null);
        toReturn.setCMun(item.getCmun());
        toReturn.setCMunFG(item.getCmunfg());
        toReturn.setCPais(item.getCpais());
        toReturn.setCServico(item.getCservico());
        toReturn.setIndISS(item.getIndiss() != null ? item.getIndiss().getCodigo() : null);
        toReturn.setIndIncentivo(item.getIndincentivo() != null ? item.getIndincentivo().getCodigo() : null);
        toReturn.setNProcesso(item.getNprocesso());
        toReturn.setVAliq(item.getValiqissqn() != null ? item.getValiqissqn().toString() : null);
        toReturn.setVBC(item.getVbcissqn() != null ? item.getVbcissqn().toString() : null);
        toReturn.setVDeducao(item.getVdeducao() != null ? item.getVdeducao().toString() : null);
        toReturn.setVDescCond(item.getVdesccond() != null ? item.getVdesccond().toString() : null);
        toReturn.setVDescIncond(item.getVdescincond() != null ? item.getVdescincond().toString() : null);
        toReturn.setVISSQN(item.getVissqn() != null ? item.getVissqn().toString() : null);
        toReturn.setVISSRet(item.getVissret() != null ? item.getVissret().toString() : null);
        toReturn.setVOutro(item.getVoutroissqn() != null ? item.getVoutroissqn().toString() : null);
        return toReturn;
    }

    /**
     * UA - Tributos Devolvidos (para o item da NF-e).
     */
    private ImpostoDevol fillImpostoDevol(DetalhamentoProdutoServico item) {
        if (item == null) {
            return null;
        }

        // IPI ImpostoDevol
        IPI ipi = new IPI();
        ipi.setVIPIDevol(item.getVipidevol() != null ? item.getVipidevol().toString() : null);

        // ImpostoDevol 
        ImpostoDevol toReturn = new ImpostoDevol();
        toReturn.setIPI(ipi);
        toReturn.setPDevol(item.getPdevol() != null ? item.getPdevol().toString() : null);

        return toReturn;
    }

    /**
     * W - Total da NF-e
     */
    private Total fillTotais(DadosNFe dadosNFe) {
        TotalNFe total = dadosNFe.getTotalNFe();

        if (total == null) {
            return null;
        }

        Total toReturn = new Total();
        toReturn.setICMSTot(fillICMSTot(total.getTotalNFeIcms()));
        toReturn.setISSQNtot(fillISSQNtot(total.getTotalNFeIssqn()));
        toReturn.setRetTrib(fillRetTrib(total.getTotalNFeRetencao()));
        return toReturn;
    }

    /**
     * W - Total da NF-e / ICMS.
     */
    private ICMSTot fillICMSTot(TotalNFeICMS total) {
        if (total == null) {
            return null;
        }

        ICMSTot toReturn = new ICMSTot();
        toReturn.setVBC(total.getVbc() != null ? total.getVbc().toString() : null);
        toReturn.setVBCST(total.getVbcst() != null ? total.getVbcst().toString() : null);
        toReturn.setVCOFINS(total.getVcofins() != null ? total.getVcofins().toString() : null);
        toReturn.setVDesc(total.getVdesc() != null ? total.getVdesc().toString() : null);
        toReturn.setVFCPUFDest(total.getVfcpufdest() != null ? total.getVfcpufdest().toString() : null);
        toReturn.setVFrete(total.getVfrete() != null ? total.getVfrete().toString() : null);
        toReturn.setVICMS(total.getVicms() != null ? total.getVicms().toString() : null);
        toReturn.setVICMSDeson(total.getVicmsdeson() != null ? total.getVicmsdeson().toString() : null);
        toReturn.setVICMSUFDest(total.getVicmsufdest() != null ? total.getVicmsufdest().toString() : null);
        toReturn.setVICMSUFRemet(total.getVicmsufremet() != null ? total.getVicmsufremet().toString() : null);
        toReturn.setVII(total.getVii() != null ? total.getVii().toString() : null);
        toReturn.setVIPI(total.getVipi() != null ? total.getVipi().toString() : null);
        toReturn.setVNF(total.getVnf() != null ? total.getVnf().toString() : null);
        toReturn.setVOutro(total.getVoutro() != null ? total.getVoutro().toString() : null);
        toReturn.setVPIS(total.getVpis() != null ? total.getVpis().toString() : null);
        toReturn.setVProd(total.getVprod() != null ? total.getVprod().toString() : null);
        toReturn.setVST(total.getVst() != null ? total.getVst().toString() : null);
        toReturn.setVSeg(total.getVseg() != null ? total.getVseg().toString() : null);
        toReturn.setVTotTrib(total.getVtottrib() != null ? total.getVtottrib().toString() : null);
        return toReturn;
    }

    /**
     * W01 - Total da NF-e / ISSQN.
     */
    private ISSQNtot fillISSQNtot(TotalNFeISSQN total) {
        if (total == null) {
            return null;
        }

        ISSQNtot toReturn = new ISSQNtot();
        toReturn.setCRegTrib(total.getCregtrib());
        toReturn.setDCompet(total.getDcompet() != null ? sdfDate.format(total.getDcompet()) : null);
        toReturn.setVBC(total.getVbc() != null ? total.getVbc().toString() : null);
        toReturn.setVCOFINS(total.getVcofins() != null ? total.getVcofins().toString() : null);
        toReturn.setVDeducao(total.getVdeducao() != null ? total.getVdeducao().toString() : null);
        toReturn.setVDescCond(total.getVdesccond() != null ? total.getVdesccond().toString() : null);
        toReturn.setVDescIncond(total.getVdescincond() != null ? total.getVdescincond().toString() : null);
        toReturn.setVISS(total.getViss() != null ? total.getViss().toString() : null);
        toReturn.setVISSRet(total.getVissret() != null ? total.getVissret().toString() : null);
        toReturn.setVOutro(total.getVoutro() != null ? total.getVoutro().toString() : null);
        toReturn.setVPIS(total.getVpis() != null ? total.getVpis().toString() : null);
        toReturn.setVServ(total.getVserv() != null ? total.getVserv().toString() : null);
        return toReturn;
    }

    /**
     * W02 - Total da NF-e / Retenção de Tributos.
     */
    private RetTrib fillRetTrib(TotalNFeRetencao total) {
        if (total == null) {
            return null;
        }

        RetTrib toReturn = new RetTrib();
        toReturn.setVBCIRRF(total.getVbcirrf() != null ? total.getVbcirrf().toString() : null);
        toReturn.setVBCRetPrev(total.getVbcretprev() != null ? total.getVbcretprev().toString() : null);
        toReturn.setVIRRF(total.getVirrf() != null ? total.getVirrf().toString() : null);
        toReturn.setVRetCOFINS(total.getVretcofins() != null ? total.getVretcofins().toString() : null);
        toReturn.setVRetCSLL(total.getVretcsll() != null ? total.getVretcsll().toString() : null);
        toReturn.setVRetPIS(total.getVretpis() != null ? total.getVretpis().toString() : null);
        toReturn.setVRetPrev(total.getVretprev() != null ? total.getVretprev().toString() : null);
        return toReturn;
    }

    /**
     * X - Informações do Transporte da NF-e
     *
     * X01 - Grupo Informações do Transporte.
     */
    private Transp fillTransporte(DadosNFe dadosNFe) {
        TransporteNFe transporte = dadosNFe.getTransporteNFe();

        if (transporte == null) {
            return null;
        }

        Transp toReturn = new Transp();
        toReturn.setModFrete(transporte.getModfrete() != null ? transporte.getModfrete().getCodigo() : null);

        if (transporte.getModfrete() != null && !transporte.getModfrete().equals(NFeTipoModalidadeFrete.COD9)) {
            toReturn.setTransporta(fillTransportador(transporte));
            toReturn.setVeicTransp(fillVeiculo(transporte));
            toReturn.getVol().addAll(fillVolumes(transporte));

            toReturn.setBalsa(transporte.getBalsa());
            toReturn.setVagao(transporte.getVagao());
            toReturn.setRetTransp(null);
        }

        return toReturn;

    }

    /**
     * X03 - Grupo Transportador.
     */
    private Transporta fillTransportador(TransporteNFe transporte) {
        if (transporte == null) {
            return null;
        }

        Transporta toReturn = new Transporta();

        // SETA DOCUMENTO
        if (transporte.getDocumento().length() == 11) {
            toReturn.setCPF(transporte.getDocumento());
        } else if (transporte.getDocumento().length() > 11) {
            toReturn.setCNPJ(transporte.getDocumento());
        }

        toReturn.setIE(transporte.getIe());
        toReturn.setXNome(transporte.getXnome());
        toReturn.setXEnder(transporte.getXender());
        toReturn.setXMun(transporte.getXmun());
        toReturn.setUF(transporte.getUf() != null ? TUf.valueOf(transporte.getUf()) : null);
        return toReturn;
    }

    /**
     * X18 - Grupo Veículo Transporte.
     */
    private TVeiculo fillVeiculo(TransporteNFe transporte) {
        TransporteVeiculo veiculo = transporte.getVeiculo();

        if (veiculo == null) {
            return null;
        }

        TVeiculo toReturn = new TVeiculo();
        toReturn.setPlaca(veiculo.getPlaca());
        toReturn.setRNTC(veiculo.getRntc());
        toReturn.setUF(veiculo.getUf() != null ? TUf.valueOf(veiculo.getUf()) : null);
        return toReturn;
    }

    /**
     * X26 - Grupo Volumes.
     */
    private List<Vol> fillVolumes(TransporteNFe transporte) {
        List<Vol> toReturn = new LinkedList<>();
        Vol vol;

        for (TransporteVolume volume : transporte.getVolumes()) {
            vol = new Vol();
            vol.setQVol(volume.getQvol());
            vol.setNVol(volume.getNvol());
            vol.setPesoL(volume.getPesol() != null ? volume.getPesol().toString() : null);
            vol.setPesoB(volume.getPesob() != null ? volume.getPesob().toString() : null);
            vol.setMarca(volume.getMarca());
            vol.getLacres().addAll(fillLacres(volume));
        }

        return toReturn;
    }

    /**
     * X33 - Grupo Lacres.
     */
    private List<Lacres> fillLacres(TransporteVolume volume) {
        List<Lacres> toReturn = new LinkedList<>();
        Lacres lac;

        for (TransporteLacre lacre : volume.getLacres()) {
            lac = new Lacres();
            lac.setNLacre(lacre.getNlacre());
            toReturn.add(lac);
        }

        return toReturn;
    }

    /**
     * Y – Grupo de Cobrança.
     */
    private Cobr fillCobranca(DadosNFe dadosNFe) {
        Cobr toReturn = new Cobr();
        toReturn.setFat(fillFatura(dadosNFe));
        toReturn.getDup().addAll(fillDuplicatas(dadosNFe));
        return toReturn;
    }

    /**
     * YA01 - Grupo de Informações de Pagamento.
     */
    private List<Pag> fillPag(DadosNFe dadosNFe) {
        List<Pag> toReturn = new LinkedList<>();
        Pag pag;

        for (int i = 0; i < 0; i++) {
            pag = new Pag();
            pag.setCard(fillCard(dadosNFe));
            pag.setTPag(null);
            pag.setVPag(null);
            toReturn.add(pag);
        }

        return toReturn;
    }

    /**
     * YA04 - Grupo de Cartões.
     */
    private Card fillCard(DadosNFe dadosNFe) {
        Card card = new Card();
        card.setCAut(null);
        card.setCNPJ(null);
        card.setTBand(null);
        card.setTpIntegra(null);
        return card;
    }

    /**
     * Y02 - Grupo Fatura.
     */
    private Fat fillFatura(DadosNFe dadosNFe) {
        CobrancaFatura fatura = dadosNFe.getCobrancaFatura();

        if (fatura == null) {
            return null;
        }

        Fat toReturn = new Fat();
        toReturn.setNFat(fatura.getNfat());
        toReturn.setVDesc(fatura.getVdesc() != null ? fatura.getVdesc().toString() : null);
        toReturn.setVLiq(fatura.getVliq() != null ? fatura.getVliq().toString() : null);
        toReturn.setVOrig(fatura.getVorig() != null ? fatura.getVorig().toString() : null);
        return toReturn;
    }

    /**
     * Y07 - Grupo Duplicata.
     */
    private List<Dup> fillDuplicatas(DadosNFe dadosNFe) {
        List<Dup> toReturn = new ArrayList<>();
        Dup dup;

        for (CobrancaDuplicata duplicata : dadosNFe.getDuplicatas()) {
            dup = new Dup();
            dup.setNDup(duplicata.getNdup());
            dup.setDVenc(duplicata.getDvenc() != null ? sdfDate.format(duplicata.getDvenc()) : null);
            dup.setVDup(duplicata.getVdup() != null ? duplicata.getVdup().toString() : null);
            toReturn.add(dup);
        }

        return toReturn;
    }

    /**
     * Z - Informações Adicionais da NF-e
     *
     * Z01 - Grupo de Informações Adicionais.
     */
    private InfAdic fillDadosAdicionais(DadosNFe dadosNFe) {
        InformacoesAdicionais infadd = dadosNFe.getInformacoesAdicionais();

        if (infadd == null) {
            return null;
        }

        InfAdic toReturn = new InfAdic();
        toReturn.setInfAdFisco(infadd.getInfAdFisco());
        toReturn.setInfCpl(infadd.getInfCpl());
        toReturn.getObsCont().addAll(fillObsCont(infadd));
        toReturn.getObsFisco().addAll(fillObsFisco(infadd));
        toReturn.getProcRef().addAll(fillProcRef(infadd));
        return toReturn;
    }

    /**
     * Z04 - Grupo Campo de uso livre do contribuinte.
     */
    private List<ObsCont> fillObsCont(InformacoesAdicionais infadd) {
        List<ObsCont> toReturn = new ArrayList<>();
        ObsCont obscont;

        for (InformacoesAdicionaisContribuinte iac : infadd.getInformacoesContribuinte()) {
            obscont = new ObsCont();
            obscont.setXCampo(iac.getXcampo());
            obscont.setXTexto(iac.getXtexto());
            toReturn.add(obscont);
        }

        return toReturn;
    }

    /**
     * Z07 - Grupo Campo de uso livre do Fisco.
     */
    private List<ObsFisco> fillObsFisco(InformacoesAdicionais infadd) {
        List<ObsFisco> toReturn = new ArrayList<>();
        ObsFisco obsfisco;

        for (InformacoesAdicionaisFisco iaf : infadd.getInformacoesFisco()) {
            obsfisco = new ObsFisco();
            obsfisco.setXCampo(iaf.getXcampo());
            obsfisco.setXTexto(iaf.getXtexto());
            toReturn.add(obsfisco);
        }

        return toReturn;
    }

    /**
     * Z10 - Grupo Processo referenciado.
     */
    private List<ProcRef> fillProcRef(InformacoesAdicionais infadd) {
        List<ProcRef> toReturn = new ArrayList<>();
        ProcRef procref;

        for (InformacoesAdicionaisProcessoReferenciado iapr : infadd.getInformacoesProcessoReferenciado()) {
            procref = new ProcRef();
            procref.setIndProc(iapr.getIndproc() != null ? iapr.getIndproc().getCodigo() : null);
            procref.setNProc(iapr.getNproc());
            toReturn.add(procref);
        }
        return toReturn;
    }

    /**
     * ZA - Informações de Comércio Exterior
     */
    private Exporta fillExportacao(DadosNFe dadosNFe) {
        ComercioExterior exportacao = dadosNFe.getComercioExterior();

        if (exportacao == null) {
            return null;
        }

        Exporta toReturn = new Exporta();
        toReturn.setUFSaidaPais(exportacao.getUfsaidapais());
        toReturn.setXLocDespacho(exportacao.getXlocdespacho());
        toReturn.setXLocExporta(exportacao.getXlocexporta());
        return toReturn;
    }

    /**
     * ZB - Informações de Compras
     */
    private Compra fillCompra(DadosNFe dadosNFe) {
        br.com.ararati.entity.nfe.emissao.Compra compra = dadosNFe.getCompra();

        if (compra == null) {
            return null;
        }

        Compra toReturn = new Compra();
        toReturn.setXCont(compra.getXcont());
        toReturn.setXNEmp(compra.getXnemp());
        toReturn.setXPed(compra.getXped());
        return toReturn;
    }

    /**
     * ZC - Informações do Registro de Aquisição de Cana
     */
    private Cana fillCana(DadosNFe dadosNFe) {
        AquisicaoCana cana = dadosNFe.getAquisicaoCana();

        if (cana == null) {
            return null;
        }
        Cana toReturn = new Cana();
        toReturn.setQTotAnt(cana.getQtotant() != null ? cana.getQtotant().toString() : null);
        toReturn.setQTotGer(cana.getQtotger() != null ? cana.getQtotger().toString() : null);
        toReturn.setQTotMes(cana.getQtotmes() != null ? cana.getQtotmes().toString() : null);
        toReturn.setRef(cana.getRef());
        toReturn.setSafra(cana.getSafra());
        toReturn.setVFor(cana.getVfor() != null ? cana.getVfor().toString() : null);
        toReturn.setVLiqFor(cana.getVliqfor() != null ? cana.getVliqfor().toString() : null);
        toReturn.setVTotDed(cana.getVtotded() != null ? cana.getVtotded().toString() : null);
        toReturn.getForDia().addAll(fillForDia(cana));
        toReturn.getDeduc().addAll(fillDeduc(cana));
        return toReturn;
    }

    /**
     * ZC04 - Grupo Fornecimento diário de cana.
     */
    private List<ForDia> fillForDia(AquisicaoCana cana) {
        List<ForDia> toReturn = new ArrayList<>();
        ForDia fordia;

        for (AquisicaoCanaFornecimento fornecimento : cana.getFornecimentos()) {
            fordia = new ForDia();
            fordia.setDia(fornecimento.getDia());
            fordia.setQtde(fornecimento.getQtde() != null ? fornecimento.getQtde().toString() : null);
            toReturn.add(fordia);
        }
        return toReturn;
    }

    /**
     * ZC10 - Grupo Deduções – Taxas e Contribuições.
     */
    private List<Deduc> fillDeduc(AquisicaoCana cana) {
        List<Deduc> deducoes = new ArrayList<>();
        Deduc deduc;

        for (AquisicaoCanaDeducao deducao : cana.getDeducoes()) {
            deduc = new Deduc();
            deduc.setVDed(deducao.getVded().toString());
            deduc.setXDed(deducao.getXded());
            deducoes.add(deduc);
        }
        return deducoes;
    }

    /**
     * ZX. Informações Suplementares da Nota Fiscal
     */
    private InfNFeSupl fillInfNFeSupl(DadosNFe dadosNFe) {
        InfNFeSupl toReturn = new InfNFeSupl();
        toReturn.setQrCode(null);
        return toReturn;
    }
}
