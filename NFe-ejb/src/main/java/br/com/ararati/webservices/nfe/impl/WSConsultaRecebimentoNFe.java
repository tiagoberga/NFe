/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.webservices.nfe.impl;

import br.com.ararati.exception.WSException;
import br.com.ararati.operacoes.GenericJAXBTools;
import br.inf.portalfiscal.www.nfe.wsdl.nferetautorizacao.NfeRetAutorizacaoStub;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axis2.AxisFault;
import retConsReciNFe_v310.TRetConsReciNFe;

/**
 *
 * @author tiago
 */
public class WSConsultaRecebimentoNFe {

    /**
     *
     * @param urlWebService
     * @param xmlGerado
     * @param versaoDados
     * @param codigoEstado
     * @return String XmlRetConsReciNFe
     * @throws java.security.KeyStoreException
     * @throws java.io.IOException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.cert.CertificateException
     * @throws java.security.UnrecoverableKeyException
     */
    public TRetConsReciNFe getRetConsReciNFe(String urlWebService, String xmlGerado, String versaoDados, String codigoEstado) throws WSException, KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
        URL url;

        try {
            url = new URL(urlWebService);

            OMElement ome = AXIOMUtil.stringToOM(xmlGerado);

            NfeRetAutorizacaoStub.NfeDadosMsg dadosMsg = new NfeRetAutorizacaoStub.NfeDadosMsg();
            dadosMsg.setExtraElement(ome);

            NfeRetAutorizacaoStub.NfeCabecMsg nfeCabecMsg = new NfeRetAutorizacaoStub.NfeCabecMsg();
            nfeCabecMsg.setVersaoDados(versaoDados);
            nfeCabecMsg.setCUF(codigoEstado);

            NfeRetAutorizacaoStub.NfeCabecMsgE nfeCabecMsgE = new NfeRetAutorizacaoStub.NfeCabecMsgE();
            nfeCabecMsgE.setNfeCabecMsg(nfeCabecMsg);

            NfeRetAutorizacaoStub stub = new NfeRetAutorizacaoStub(url.toString());
            NfeRetAutorizacaoStub.NfeRetAutorizacaoLoteResult result = stub.nfeRetAutorizacaoLote(dadosMsg, nfeCabecMsgE);
            return GenericJAXBTools.unmarshallFromXMLToObject(retConsReciNFe_v310.TRetConsReciNFe.class, result.getExtraElement().toString());
        } catch (MalformedURLException ex) {
            Logger.getLogger(WSConsultaStatusServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new WSException("URL do web service não é valida, por favor verifique. " + ex.getMessage());
        } catch (XMLStreamException ex) {
            Logger.getLogger(WSConsultaStatusServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new WSException("Problemas com o web service: " + ex.getMessage());
        } catch (AxisFault ex) {
            Logger.getLogger(WSConsultaStatusServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new WSException("Problemas com o web service: " + ex.getMessage());
        } catch (RemoteException ex) {
            Logger.getLogger(WSConsultaStatusServico.class.getName()).log(Level.SEVERE, null, ex);
            throw new WSException("Problemas com o web service: " + ex.getMessage());
        } catch (JAXBException ex) {
            Logger.getLogger(WSConsultaRecebimentoNFe.class.getName()).log(Level.SEVERE, null, ex);
            throw new WSException(ex.getMessage());
        }
    }
}
