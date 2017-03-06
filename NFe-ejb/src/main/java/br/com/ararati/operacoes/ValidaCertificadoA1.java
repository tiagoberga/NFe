package br.com.ararati.operacoes;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import org.apache.commons.httpclient.protocol.Protocol;

/**
 *
 * @author tiago
 */
public class ValidaCertificadoA1 {

    /**
     * Método responsável por validar o certificado digital.
     *
     * @param certificado byte[]
     * @param senha String
     * @throws java.io.FileNotFoundException
     * @throws java.security.KeyStoreException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.cert.CertificateException
     * @throws java.security.UnrecoverableKeyException
     */
    public void validaCertificadoA1(byte[] certificado, String senha)
            throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
        try {
            final String glassfishRootProperty = System.getProperty("com.sun.aas.instanceRoot");
            final String glassfishConfigFolder = "config";
            final String fileName = "nfe-cacerts";

            File configFolder = new File(glassfishRootProperty + File.separator + glassfishConfigFolder);
            File configFile = new File(configFolder, fileName);

            if (configFile == null) {
                throw new IOException("Arquivo 'Cacerts' de NF-e não encontrado!");
            }

            InputStream isCertificado = new ByteArrayInputStream(certificado);
            KeyStore ks = KeyStore.getInstance("pkcs12");
            ks.load(isCertificado, senha.toCharArray());

            String alias = "";
            Enumeration<String> aliasesEnum = ks.aliases();
            while (aliasesEnum.hasMoreElements()) {
                alias = (String) aliasesEnum.nextElement();
                if (ks.isKeyEntry(alias)) {
                    break;
                }
            }

            X509Certificate certificate = (X509Certificate) ks.getCertificate(alias);
            PrivateKey privateKey = (PrivateKey) ks.getKey(alias, senha.toCharArray());
            SocketFactory socketFactoryDinamico = new SocketFactory(certificate, privateKey);
            socketFactoryDinamico.setFileCacerts(configFile.getAbsolutePath());

            Protocol protocol = new Protocol("https", socketFactoryDinamico, 443);
            Protocol.registerProtocol("https", protocol);
        } catch (KeyStoreException e) {
            throw new KeyStoreException("Senha do Certificado Digital incorreta ou Certificado inválido!", e);
        } catch (IOException e) {
            throw new IOException("Senha do Certificado Digital incorreta ou Certificado inválido!", e);
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmException("Senha do Certificado Digital incorreta ou Certificado inválido!", e);
        } catch (CertificateException e) {
            throw new CertificateException("Senha do Certificado Digital incorreta ou Certificado inválido!", e);
        }
    }
}
