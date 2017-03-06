/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.webservices.nfe.impl;

import br.com.ararati.exception.WSException;
import br.com.ararati.operacoes.GenericJAXBTools;
import br.inf.portalfiscal.www.nfe.wsdl.nfedownloadnf.NfeDownloadNFStub;
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
import retDownloadNFe_v100.TRetDownloadNFe;

/**
 *
 * @author tiago
 */
public class WSDownloadNFe {

    public TRetDownloadNFe getRetNFeDownloadNF(String urlWebservice, String xmlGerado, String codigoEstado, String versaoDados) throws WSException, KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
        try {
            URL url = new URL(urlWebservice);

            OMElement ome = AXIOMUtil.stringToOM(xmlGerado);

            NfeDownloadNFStub.NfeDadosMsg dadosMsg = new NfeDownloadNFStub.NfeDadosMsg();
            dadosMsg.setExtraElement(ome);

            NfeDownloadNFStub.NfeCabecMsg nfeCabecMsg = new NfeDownloadNFStub.NfeCabecMsg();
            nfeCabecMsg.setCUF(codigoEstado);

            nfeCabecMsg.setVersaoDados(versaoDados);
            NfeDownloadNFStub.NfeCabecMsgE nfeCabecMsgE = new NfeDownloadNFStub.NfeCabecMsgE();
            nfeCabecMsgE.setNfeCabecMsg(nfeCabecMsg);

            NfeDownloadNFStub stub = new NfeDownloadNFStub(url.toString());
            NfeDownloadNFStub.NfeDownloadNFResult result = stub.nfeDownloadNF(dadosMsg, nfeCabecMsgE);
            return GenericJAXBTools.unmarshallFromXMLToObject(TRetDownloadNFe.class, result.getExtraElement().toString());
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
            Logger.getLogger(WSDownloadNFe.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
