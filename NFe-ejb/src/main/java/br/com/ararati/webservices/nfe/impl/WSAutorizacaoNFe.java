/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.webservices.nfe.impl;

import br.com.ararati.exception.WSException;
import br.com.ararati.operacoes.GenericJAXBTools;
import br.inf.portalfiscal.www.nfe.wsdl.nfeautorizacao.NfeAutorizacaoStub;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axis2.AxisFault;
import retEnviNFe_v310.TRetEnviNFe;

/**
 *
 * @author tiago
 */
public class WSAutorizacaoNFe {

    /**
     * Método responsável por retornar buscar o xml de Retorno de Envio do Lote
     *
     * @param xmlAssinado String
     * @param urlWebService String
     * @param versaoDados ex: 1.00, 2.00, 3.10 etc...
     * @param codigoEstado ex: SP = 35
     * @return xml em String
     * @throws WSException
     * @throws java.security.KeyStoreException
     * @throws java.io.IOException
     * @throws java.security.UnrecoverableKeyException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.cert.CertificateException
     */
    public TRetEnviNFe getRetAutorizacaoNFe(String urlWebService, String xmlAssinado, String versaoDados, String codigoEstado) throws WSException, KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
        URL url;
        try {
            url = new URL(urlWebService);

            OMElement ome = AXIOMUtil.stringToOM(xmlAssinado);

            Iterator<?> children = ome.getChildrenWithLocalName("NFe");
            while (children.hasNext()) {
                OMElement omElement = (OMElement) children.next();
                if ((omElement != null) && ("NFe".equals(omElement.getLocalName()))) {
                    omElement.addAttribute("xmlns", "http://www.portalfiscal.inf.br/nfe", null);
                }
            }

            NfeAutorizacaoStub.NfeDadosMsg nfeDadosMsg = new NfeAutorizacaoStub.NfeDadosMsg();
            nfeDadosMsg.setExtraElement(ome);

            NfeAutorizacaoStub.NfeCabecMsg nfeCabecMsg = new NfeAutorizacaoStub.NfeCabecMsg();
            nfeCabecMsg.setCUF(codigoEstado);
            nfeCabecMsg.setVersaoDados(versaoDados);

            NfeAutorizacaoStub.NfeCabecMsgE nfeCabecMsgE = new NfeAutorizacaoStub.NfeCabecMsgE();
            nfeCabecMsgE.setNfeCabecMsg(nfeCabecMsg);

            NfeAutorizacaoStub stub = new NfeAutorizacaoStub(url.toString());
            NfeAutorizacaoStub.NfeAutorizacaoLoteResult result = stub.nfeAutorizacaoLote(nfeDadosMsg, nfeCabecMsgE);
            return GenericJAXBTools.unmarshallFromXMLToObject(TRetEnviNFe.class, result.getExtraElement().toString());
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
            Logger.getLogger(WSAutorizacaoNFe.class.getName()).log(Level.SEVERE, null, ex);
            throw new WSException(ex.getMessage());
        }
    }
}
