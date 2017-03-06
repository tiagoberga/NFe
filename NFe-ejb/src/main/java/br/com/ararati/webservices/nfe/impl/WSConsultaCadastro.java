/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.webservices.nfe.impl;

import br.com.ararati.exception.WSException;
import br.com.ararati.operacoes.GenericJAXBTools;
import br.inf.portalfiscal.www.nfe.wsdl.cadconsultacadastro2.CadConsultaCadastro2Stub;
import consCad_v200.TRetConsCad;
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
public class WSConsultaCadastro {

    /**
     * Método responsável por realizar a consulta de um cadastro de pessoa.
     *
     * @param xmlConsCad xml ConsCad
     * @param urlWebService String
     * @param versaoDados String
     * @param codigoEstado String
     * @return String
     */
    public TRetConsCad getRetConsCad(String urlWebService, String xmlConsCad, String versaoDados, String codigoEstado) throws WSException, KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
        URL url;

        try {
            url = new URL(urlWebService);

            OMElement ome = AXIOMUtil.stringToOM(xmlConsCad);

            CadConsultaCadastro2Stub.NfeDadosMsg dadosMsg = new CadConsultaCadastro2Stub.NfeDadosMsg();
            dadosMsg.setExtraElement(ome);

            CadConsultaCadastro2Stub.NfeCabecMsg nfeCabecMsg = new CadConsultaCadastro2Stub.NfeCabecMsg();
            nfeCabecMsg.setVersaoDados(versaoDados);
            nfeCabecMsg.setCUF(codigoEstado);

            CadConsultaCadastro2Stub.NfeCabecMsgE nfeCabecMsgE = new CadConsultaCadastro2Stub.NfeCabecMsgE();
            nfeCabecMsgE.setNfeCabecMsg(nfeCabecMsg);

            CadConsultaCadastro2Stub stub = new CadConsultaCadastro2Stub(url.toString());
            CadConsultaCadastro2Stub.ConsultaCadastro2Result result = stub.consultaCadastro2(dadosMsg, nfeCabecMsgE);
            return GenericJAXBTools.unmarshallFromXMLToObject(TRetConsCad.class, result.getExtraElement().toString());
            
            // http://www.javac.com.br/jc/posts/list/1829-soap-nfe-erro-com-string-lt-gt.page
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
