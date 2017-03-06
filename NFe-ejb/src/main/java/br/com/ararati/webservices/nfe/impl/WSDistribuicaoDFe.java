/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.webservices.nfe.impl;

import br.com.ararati.exception.WSException;
import br.com.ararati.operacoes.GenericJAXBTools;
import br.inf.portalfiscal.www.nfe.wsdl.nfedistribuicaodfe.NFeDistribuicaoDFeStub;
import br.inf.portalfiscal.www.nfe.wsdl.nfedistribuicaodfe.NFeDistribuicaoDFeStub.NfeDadosMsg_type0;
import br.inf.portalfiscal.www.nfe.wsdl.nfedistribuicaodfe.NFeDistribuicaoDFeStub.NfeDistDFeInteresse;
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
import retDistDFeInt_v100.RetDistDFeInt;

/**
 *
 * @author tiago
 */
public class WSDistribuicaoDFe {

    /**
     * Método responsável por enviar o xml ao Sefaz e receber o retorno.
     *
     * @param urlWebService
     * @param xmlDistDFe
     * @return String XmlRetConsReciNFe
     */
    public RetDistDFeInt getRetDistDFe(String urlWebService, String xmlDistDFe) throws WSException, KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
        URL url;

        try {
            url = new URL(urlWebService);

            OMElement ome = AXIOMUtil.stringToOM(xmlDistDFe);

            NfeDadosMsg_type0 dadosMsg = new NfeDadosMsg_type0();
            dadosMsg.setExtraElement(ome);

            NfeDistDFeInteresse distDFeInteresse = new NfeDistDFeInteresse();
            distDFeInteresse.setNfeDadosMsg(dadosMsg);

            NFeDistribuicaoDFeStub stub = new NFeDistribuicaoDFeStub(url.toString());
            NFeDistribuicaoDFeStub.NfeDistDFeInteresseResponse result = stub.nfeDistDFeInteresse(distDFeInteresse);
            return GenericJAXBTools.unmarshallFromXMLToObject(RetDistDFeInt.class, result.getNfeDistDFeInteresseResult().getExtraElement().toString());
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
            Logger.getLogger(WSDistribuicaoDFe.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
