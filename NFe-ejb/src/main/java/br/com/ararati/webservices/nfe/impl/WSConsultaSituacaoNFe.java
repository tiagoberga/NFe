/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.webservices.nfe.impl;

import br.com.ararati.exception.WSException;
import br.com.ararati.operacoes.GenericJAXBTools;
import br.inf.portalfiscal.www.nfe.wsdl.nfeconsulta2.NfeConsulta2Stub;
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
import retConsSitNFe_v310.TRetConsSitNFe;

/**
 *
 * @author tiago
 */
public class WSConsultaSituacaoNFe {

    public TRetConsSitNFe getRetConsSitNFe(String urlWebservice, String xmlGerado, String codigoEstado, String versaoDados) throws WSException, KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
        try {
            URL url = new URL(urlWebservice);

            OMElement ome = AXIOMUtil.stringToOM(xmlGerado);

            NfeConsulta2Stub.NfeDadosMsg dadosMsg = new NfeConsulta2Stub.NfeDadosMsg();
            dadosMsg.setExtraElement(ome);

            NfeConsulta2Stub.NfeCabecMsg nfeCabecMsg = new NfeConsulta2Stub.NfeCabecMsg();
            nfeCabecMsg.setCUF(codigoEstado);
            nfeCabecMsg.setVersaoDados(versaoDados);

            NfeConsulta2Stub.NfeCabecMsgE nfeCabecMsgE = new NfeConsulta2Stub.NfeCabecMsgE();
            nfeCabecMsgE.setNfeCabecMsg(nfeCabecMsg);

            NfeConsulta2Stub stub = new NfeConsulta2Stub(url.toString());
            NfeConsulta2Stub.NfeConsultaNF2Result result = stub.nfeConsultaNF2(dadosMsg, nfeCabecMsgE);
            return GenericJAXBTools.unmarshallFromXMLToObject(TRetConsSitNFe.class, result.getExtraElement().toString());
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
            Logger.getLogger(WSConsultaCadastro.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
