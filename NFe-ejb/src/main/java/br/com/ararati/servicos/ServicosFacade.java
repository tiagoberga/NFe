/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.servicos;

import br.com.ararati.entity.cadastros.Emitente;
import br.com.ararati.entity.config.ConfiguracaoAmbiente;
import br.com.ararati.entity.config.ConfiguracaoWebService;
import br.com.ararati.exception.WSException;
import br.com.ararati.servicos.impl.ConsultaStatusServico;
import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.bind.JAXBException;

/**
 *
 * @author tiago
 */
@Stateless
public class ServicosFacade {

    @EJB
    private ConsultaStatusServico consultaStatusServico;
//    @EJB
//    private AutorizaNFe autorizaNFe;
//    @EJB
//    private ConsultaCadastro consultaCadastro;
//    @EJB
//    private ConsultaRecebimentoNFe recebimentoNFe;
//    @EJB
//    private ConsultaSituacaoNFe consultaNFe;
//    @EJB
//    private DistribuicaoDFe distribuicaoDFe;
//    @EJB
//    private DownloadNFe downloadNFe;
//    @EJB
//    private EventoCancelamento cancelamento;
//    @EJB
//    private EventoCartaDeCorrecao cartaDeCorrecao;
//    @EJB
//    private EventoManifestoDestinatario manifestoDestinatario;
//    @EJB
//    private InutilizaNFe inutilizaNFe;

    public retConsStatServ_v400.TRetConsStatServ consultarStatusServico(ConfiguracaoWebService webService, ConfiguracaoAmbiente ambiente, Emitente empresaLogada) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException, JAXBException, WSException {
        return consultaStatusServico.consultarStatusServico(webService, ambiente, empresaLogada);
    }

//    public consCad_v200.TRetConsCad consultarCadastro(String cnpjCpf, String ie, String uf, Emitente empresaLogada, ConfiguracaoAmbiente ambiente) throws WSException, JAXBException, KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
//        return consultaCadastro.consultarCadastro(cnpjCpf, ie, uf, empresaLogada, ambiente);
//    }
//
//    public retConsSitNFe_v310.TRetConsSitNFe consultarSituacaoNFe(NotaFiscalEletronica nfe, Emitente empresaLogada, ConfiguracaoAmbiente ambiente) throws WSException, JAXBException, KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException, NFeException, DaoException, SAXException, ParserConfigurationException, TransformerException {
//        return consultaNFe.consultarSituacaoNFe(nfe, ambiente, empresaLogada);
//    }
//
//    public retInutNFe_v310.TRetInutNFe inutilizarNFes(InutilizacaoNFeFilter inutFilter, String caminhoSchema, ConfiguracaoAmbiente ambiente) throws UnsupportedEncodingException, KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException, WSException, JAXBException, DaoException, NFeException, Exception {
//        return inutilizaNFe.inutilizarNFes(ambiente, inutFilter, caminhoSchema);
//    }
//
//    public retEnvEvento_v100.TRetEnvEvento realizarCartaDeCorrecaoNFe(NotaFiscalEletronica nfe, CartaDeCorrecaoFilter cceFilter, ConfiguracaoAmbiente ambiente) throws NFeException, JAXBException, KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException, Exception {
//        return cartaDeCorrecao.realizarCartaDeCorrecao(nfe, cceFilter, ambiente);
//    }
//
//    public retEnvEvento_v100.TRetEnvEvento realizarCancelamentoNFe(NotaFiscalEletronica nfe, CancelamentoFilter cancFilter, ConfiguracaoAmbiente ambiente) throws WebServiceException, NFeException, JAXBException, UnsupportedEncodingException, KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException, Exception {
//        return cancelamento.realizarCancelamento(nfe, cancFilter, ambiente);
//    }
//
//    public retEnvEvento_v100.TRetEnvEvento realizarManifestoDestinatarioNFe(List<String> chavesDeAcesso, ManifestoDestinatarioFilter mdeFilter) throws WebServiceException, JAXBException, NFeException, UnsupportedEncodingException, KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException, DaoException, Exception {
//        return manifestoDestinatario.realizarManifestoDestinatario(chavesDeAcesso, mdeFilter);
//    }
//
//    public retenviNFe_v400.TRetEnviNFe realizaAutorizacaoNFe(NotaFiscalEletronica nfe, Emitente empresaLogada, ConfiguracaoAmbiente ambiente, NFeParametros nfep) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException, WSException, NFeException {
//        return autorizaNFe.realizaAutorizacaoNFe(nfe, empresaLogada, ambiente, nfep);
//    }
//
//    public retConsReciNFe_v310.TRetConsReciNFe consultarRecebimentoNFe(retenviNFe_v400.TRetEnviNFe retEnviNFe, NotaFiscalEletronica nfe, Emitente empresaLogada, ConfiguracaoAmbiente ambiente, NFeParametros nfep) throws WSException, NFeException, KeyStoreException, UnsupportedEncodingException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException, JAXBException, SAXException, ParserConfigurationException, TransformerException, DaoException, InterruptedException, SendMailException {
//        return recebimentoNFe.consultarRecebimentoNFe(retEnviNFe, nfe, empresaLogada, ambiente, nfep);
//    }
//
//    public retDownloadNFe_v100.TRetDownloadNFe consultaDownloadNFe(List<String> chavesDeAcesso, DownloadNFeFilter downloadFilter) throws JAXBException, WSException, KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException, NFeException {
//        return downloadNFe.consultaDownloadNFe(chavesDeAcesso, downloadFilter);
//    }
//
//    public retDistDFeInt_v100.RetDistDFeInt consultaDistribuicaoDFe(NFeDistribuicaoDFeFilter dfeFilter) throws WebServiceException, JAXBException, NFeException, UnsupportedEncodingException, KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException, DaoException, Exception {
//        return distribuicaoDFe.consultaDistribuicaoDFe(dfeFilter);
//    }
}
