/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.operacoes;

import br.com.ararati.entity.config.ConfiguracaoAmbiente;
import br.com.ararati.enums.B.NFeTipoAmbiente;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.ejb.Stateless;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

@Stateless
public class ConstroiCacerts {

    private static final String JSSECACERTS = "nfe-cacerts";
    private static final int TIMEOUT_WS = 30;

    public String geraCacerts(ConfiguracaoAmbiente ambiente) {
        StringBuilder msg = new StringBuilder();

        try {
            char[] passphrase = "changeit".toCharArray();

            File file = new File(JSSECACERTS);
            if (file.isFile() == false) {
                char SEP = File.separatorChar;
                File dir = new File(System.getProperty("java.home") + SEP + "lib" + SEP + "security");
                file = new File(dir, JSSECACERTS);
                if (file.isFile() == false) {
                    file = new File(dir, "cacerts");
                }
            }

            msg.append("Carregando Keystore ").append(file).append("...").append("\n\n");

            InputStream in = new FileInputStream(file);
            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(in, passphrase);
            in.close();

            // GERA CACERTS
            if (ambiente.getTipoDeAmbiente().equals(NFeTipoAmbiente.PRODUCAO)) {
                msg.append(get("nfe.sefaz.am.gov.br", 443, ks));
                msg.append(get("nfe.sefaz.ba.gov.br", 443, ks));
                msg.append(get("nfe.sefaz.ce.gov.br", 443, ks));
                msg.append(get("nfe.sefaz.go.gov.br", 443, ks));
                msg.append(get("nfe.fazenda.mg.gov.br", 443, ks));
                msg.append(get("sistemas.sefaz.ma.gov.br", 443, ks));
                msg.append(get("nfe.fazenda.ms.gov.br", 443, ks));
                msg.append(get("nfe.sefaz.mt.gov.br", 443, ks));
                msg.append(get("nfe.sefaz.pe.gov.br", 443, ks));
                msg.append(get("nfe.fazenda.pr.gov.br", 443, ks));
                msg.append(get("cad.sefazrs.rs.gov.br", 443, ks));
                msg.append(get("nfe.sefazrs.rs.gov.br", 443, ks));
                msg.append(get("nfe.fazenda.sp.gov.br", 443, ks));
                msg.append(get("www.sefazvirtual.fazenda.gov.br", 443, ks));
                msg.append(get("nfe.svrs.rs.gov.br", 443, ks));
                msg.append(get("www.svc.fazenda.gov.br", 443, ks));
                msg.append(get("www.nfe.fazenda.gov.br", 443, ks));
                msg.append(get("www1.nfe.fazenda.gov.br", 443, ks));
            } else {
                msg.append(get("homnfe.sefaz.am.gov.br", 443, ks));
                msg.append(get("hnfe.sefaz.ba.gov.br", 443, ks));
                msg.append(get("nfeh.sefaz.ce.gov.br", 443, ks));
                msg.append(get("homolog.sefaz.go.gov.br", 443, ks));
                msg.append(get("hnfe.fazenda.mg.gov.br", 443, ks));
                msg.append(get("sistemas.sefaz.ma.gov.br", 443, ks));
                msg.append(get("homologacao.nfe.ms.gov.br", 443, ks));
                msg.append(get("homologacao.sefaz.mt.gov.br", 443, ks));
                msg.append(get("nfehomolog.sefaz.pe.gov.br", 443, ks));
                msg.append(get("homologacao.nfe.fazenda.pr.gov.br", 443, ks));
                msg.append(get("cad.sefazrs.rs.gov.br", 443, ks));
                msg.append(get("nfe-homologacao.sefazrs.rs.gov.br", 443, ks));
                msg.append(get("homologacao.nfe.fazenda.sp.gov.br", 443, ks));
                msg.append(get("hom.sefazvirtual.fazenda.gov.br", 443, ks));
                msg.append(get("nfe-homologacao.svrs.rs.gov.br", 443, ks));
                msg.append(get("cad.svrs.rs.gov.br", 443, ks));
                msg.append(get("hom.svc.fazenda.gov.br", 443, ks));
                msg.append(get("hom.nfe.fazenda.gov.br", 443, ks));
            }

//            // ENCONTRA OS WEBSERVICES DOS CACERTS
//            this.cacertsWebServices = this.cacertsWebServiceFacade.findAllByTipoAmbiente(ambiente);
//
//            for (CacertsWebService cacerts : cacertsWebServices) {
//                msg.append(get(cacerts.getUrl(), Integer.valueOf(cacerts.getPorta()), ks));
//            }
//            
            File cafile = new File(JSSECACERTS);
            OutputStream out = new FileOutputStream(cafile);
            ks.store(out, passphrase);
            out.close();

            if (cafile != null) {
                msg.append("Cacerts gerado em: ").append(cafile.getAbsolutePath());
            }
        } catch (Exception e) {
            msg.append("Problema ao gerar Cacerts: ").append(e.toString());
        }

        return msg.toString();
    }

    private static String get(String host, int port, KeyStore ks) throws Exception {
        SSLContext context = SSLContext.getInstance("TLS");
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(ks);
        X509TrustManager defaultTrustManager = (X509TrustManager) tmf.getTrustManagers()[0];
        SavingTrustManager tm = new SavingTrustManager(defaultTrustManager);
        context.init(null, new TrustManager[]{tm}, null);
        SSLSocketFactory factory = context.getSocketFactory();

        SSLSocket socket = (SSLSocket) factory.createSocket(host, port);
        socket.setSoTimeout(TIMEOUT_WS * 1000);

        StringBuilder msg = new StringBuilder();

        try {
            socket.startHandshake();
            socket.close();
        } catch (SSLHandshakeException e) {
            msg.append("Cacerts não foi gerado. ").append(e.toString()).append("\n");
        } catch (SSLException e) {
            msg.append(e.toString()).append("\n");
        } catch (java.net.UnknownHostException e) {
            msg.append(e.toString()).append("\n");
        }

        X509Certificate[] chain = tm.chain;

        if (chain == null) {
            msg.append(" Não foi possível obter a chave do certificado\n");
        }

        MessageDigest sha1 = MessageDigest.getInstance("SHA1");
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        for (int i = 0; i < chain.length; i++) {
            X509Certificate cert = chain[i];
            sha1.update(cert.getEncoded());
            md5.update(cert.getEncoded());

            String alias = host + "-" + (i);
            ks.setCertificateEntry(alias, cert);
            msg.append("Certificado adicionado ").append(JSSECACERTS).append(" - nome:").append(alias).append("\n");
        }

        return msg.append("\n").toString();
    }

    private static class SavingTrustManager implements X509TrustManager {

        private final X509TrustManager tm;
        private X509Certificate[] chain;

        SavingTrustManager(X509TrustManager tm) {
            this.tm = tm;
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            throw new UnsupportedOperationException();
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            this.chain = chain;
            tm.checkServerTrusted(chain, authType);
        }
    }
}
