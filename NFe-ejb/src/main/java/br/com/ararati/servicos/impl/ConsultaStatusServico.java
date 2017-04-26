package br.com.ararati.servicos.impl;

import br.com.ararati.entity.cadastros.Emitente;
import br.com.ararati.entity.config.ConfiguracaoAmbiente;
import br.com.ararati.entity.config.ConfiguracaoWebService;
import br.com.ararati.exception.WSException;
import br.com.ararati.operacoes.ValidaCertificadoA1;
import br.com.ararati.populadores.GeradorXmlFacade;
import br.com.ararati.webservices.nfe.WSNFeFacade;
import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.ejb.Stateless;
import javax.xml.bind.JAXBException;

/**
 *
 * @author tiago
 */
@Stateless
public class ConsultaStatusServico {

    public retConsStatServ_v400.TRetConsStatServ consultarStatusServico(ConfiguracaoWebService webService, ConfiguracaoAmbiente ambiente, Emitente empresaLogada) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException, JAXBException, WSException {
        // VALIDA O CERTIFICADO DIGITAL CADASTRADO NOS PARAMETROS NF-E
        ValidaCertificadoA1 validaCert = new ValidaCertificadoA1();
        validaCert.validaCertificadoA1(ambiente.getCertificadoArquivo(), ambiente.getKeyDecode());

        // CONSTROI XML DE STATUS DE SERVICO E OBTEM XML DE RETORNO STATUS SERVICO
        String xmlConsStatServ = new GeradorXmlFacade().fillConsultaStatusServico(webService.getEstadoSigla().getCodigo().toString(), webService.getTipoAmbiente().getCodigo(), webService.getVersao());
        retConsStatServ_v400.TRetConsStatServ retConsStatServ = new WSNFeFacade().getRetornoConsultaStatusServico(webService.getUrl(), xmlConsStatServ, webService.getVersao(), webService.getEstadoSigla().getCodigo().toString());

        return retConsStatServ;
    }
}
