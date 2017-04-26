/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.webservices.nfe;

import br.com.ararati.exception.WSException;
import br.com.ararati.webservices.nfe.impl.WSDistribuicaoDFe;
import br.com.ararati.webservices.nfe.impl.WSAutorizacaoNFe;
import br.com.ararati.webservices.nfe.impl.WSConsultaSituacaoNFe;
import br.com.ararati.webservices.nfe.impl.WSInutilizacaoNFe;
import br.com.ararati.webservices.nfe.impl.WSConsultaCadastro;
import br.com.ararati.webservices.nfe.impl.WSConsultaRecebimentoNFe;
import br.com.ararati.webservices.nfe.impl.WSConsultaNFeDestinatario;
import br.com.ararati.webservices.nfe.impl.WSEnvioEvento;
import br.com.ararati.webservices.nfe.impl.WSConsultaStatusServico;
import br.com.ararati.webservices.nfe.impl.WSDownloadNFe;
import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

/**
 *
 * @author tiago
 */
public class WSNFeFacade {

    public consCad_v200.TRetConsCad getRetornoConsultaCadastro(String urlWebService, String xmlGerado, String versaoDados, String codigoEstado) throws WSException, KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
        return new WSConsultaCadastro().getRetConsCad(urlWebService, xmlGerado, versaoDados, codigoEstado);
    }

    public retInutNFe_v400.TRetInutNFe getRetornoInutilizcaoNFe(String urlWebService, String xmlGerado, String versaoDados, String codigoEstado) throws WSException, KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
        return new WSInutilizacaoNFe().getRetInutNFe(urlWebService, xmlGerado, versaoDados, codigoEstado);
    }

    public retConsSitNFe_v400.TRetConsSitNFe getRetornoConsultaSituacaoNFe(String urlWebservice, String xmlGerado, String codigoEstado, String versaoDados) throws WSException, KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
        return new WSConsultaSituacaoNFe().getRetConsSitNFe(urlWebservice, xmlGerado, codigoEstado, versaoDados);
    }

    public retConsStatServ_v400.TRetConsStatServ getRetornoConsultaStatusServico(String urlWebService, String xmlGerado, String versaoDados, String codigoEstado) throws WSException, KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
        return new WSConsultaStatusServico().getRetConsStatServ(urlWebService, xmlGerado, versaoDados, codigoEstado);
    }

    public retEnvEvento_v100.TRetEnvEvento getRetornoEnvioEvento(String urlWebService, String xmlGerado, String versaoDados, String codigoEstado) throws WSException, KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
        return new WSEnvioEvento().getRetEnvEvento(urlWebService, xmlGerado, versaoDados, codigoEstado);
    }

    public retEnviNFe_v400.TRetEnviNFe getRetornoAutorizacaoNFe(String urlWebService, String xmlAssinado, String versaoDados, String codigoEstado) throws WSException, KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
        return new WSAutorizacaoNFe().getRetAutorizacaoNFe(urlWebService, xmlAssinado, versaoDados, codigoEstado);
    }

    public retConsReciNFe_v400.TRetConsReciNFe getRetornoConsultaRecebimentoNFe(String urlWebService, String xmlGerado, String versaoDados, String codigoEstado) throws WSException, KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
        return new WSConsultaRecebimentoNFe().getRetConsReciNFe(urlWebService, xmlGerado, versaoDados, codigoEstado);
    }

    public retDistDFeInt_v100.RetDistDFeInt getRetornoDistribuicaoDFe(String urlWebService, String xmlDistDFe) throws WSException, KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
        return new WSDistribuicaoDFe().getRetDistDFe(urlWebService, xmlDistDFe);
    }

    public retDownloadNFe_v100.TRetDownloadNFe getRetornoDownloadNFe(String urlWebservice, String xmlGerado, String codigoEstado, String versaoDados) throws WSException, KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
        return new WSDownloadNFe().getRetNFeDownloadNF(urlWebservice, xmlGerado, codigoEstado, versaoDados);
    }

    public String getRetornoConsultaNFeDestinatario(String urlWebService, String xmlGerado, String versaoDados, String codigoEstado) throws WSException, KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
        return new WSConsultaNFeDestinatario().getRetConsNFeDest(urlWebService, xmlGerado, versaoDados, codigoEstado);
    }

//    public procEventoNFe_v100.TProcEvento getRetornoEventoProcessadoNFe(String xmlEnvEventoAssinado, String xmlRetEnvEventoNFe, String versaoXml) throws SAXException, IOException, ParserConfigurationException, TransformerException {
//        return new WSEventoProcessadoNFe().getRetornoEventoProcessado(xmlEnvEventoAssinado, xmlRetEnvEventoNFe, versaoXml);
//    }
}
