/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.webservices.nfe.impl;

import br.com.ararati.exception.WSException;
import br.inf.portalfiscal.www.nfe.wsdl.nfeconsultadest.NFeConsultaDestStub;
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
import javax.xml.stream.XMLStreamException;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axis2.AxisFault;

/**
 *
 * @author tiago
 */
public class WSConsultaNFeDestinatario {

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
    public String getRetConsNFeDest(String urlWebService, String xmlGerado, String versaoDados, String codigoEstado) throws WSException, KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
        URL url;

        try {
            url = new URL(urlWebService);

            OMElement ome = AXIOMUtil.stringToOM(xmlGerado);

            NFeConsultaDestStub.NfeDadosMsg dadosMsg = new NFeConsultaDestStub.NfeDadosMsg();
            dadosMsg.setExtraElement(ome);

            NFeConsultaDestStub.NfeCabecMsg nfeCabecMsg = new NFeConsultaDestStub.NfeCabecMsg();
            nfeCabecMsg.setVersaoDados(versaoDados);
            nfeCabecMsg.setCUF(codigoEstado);

            NFeConsultaDestStub.NfeCabecMsgE nfeCabecMsgE = new NFeConsultaDestStub.NfeCabecMsgE();
            nfeCabecMsgE.setNfeCabecMsg(nfeCabecMsg);

            NFeConsultaDestStub stub = new NFeConsultaDestStub(url.toString());
            NFeConsultaDestStub.NfeConsultaNFDestResult result = stub.nfeConsultaNFDest(dadosMsg, nfeCabecMsgE);
            return result.getExtraElement().toString();
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
        }
    }
}
