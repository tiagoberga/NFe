/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.webservices.nfe.impl;

import br.com.ararati.exception.WSException;
import br.com.ararati.operacoes.GenericJAXBTools;
import br.inf.portalfiscal.www.nfe.wsdl.nfeinutilizacao2.NfeInutilizacao2Stub;
import retInutNFe_v310.TRetInutNFe;
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

/**
 *
 * @author tiago
 */
public class WSInutilizacaoNFe {

    /**
     * Método responsável por retornar o xml de Retorno de Inutilização.
     *
     * @param urlWebService String
     * @param xmlGerado String
     * @param versaoDados String
     * @param codigoEstado String
     * @return String
     */
    public TRetInutNFe getRetInutNFe(String urlWebService, String xmlGerado, String versaoDados, String codigoEstado) throws WSException, KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
        URL url;

        try {
            url = new URL(urlWebService);

            OMElement ome = AXIOMUtil.stringToOM(xmlGerado);

            NfeInutilizacao2Stub.NfeDadosMsg dadosMsg = new NfeInutilizacao2Stub.NfeDadosMsg();
            dadosMsg.setExtraElement(ome);

            NfeInutilizacao2Stub.NfeCabecMsg nfeCabecMsg = new NfeInutilizacao2Stub.NfeCabecMsg();
            nfeCabecMsg.setVersaoDados(versaoDados);
            nfeCabecMsg.setCUF(codigoEstado);

            NfeInutilizacao2Stub.NfeCabecMsgE nfeCabecMsgE = new NfeInutilizacao2Stub.NfeCabecMsgE();
            nfeCabecMsgE.setNfeCabecMsg(nfeCabecMsg);

            NfeInutilizacao2Stub stub = new NfeInutilizacao2Stub(url.toString());
            NfeInutilizacao2Stub.NfeInutilizacaoNF2Result result = stub.nfeInutilizacaoNF2(dadosMsg, nfeCabecMsgE);
            return GenericJAXBTools.unmarshallFromXMLToObject(TRetInutNFe.class, result.getExtraElement().toString());
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
