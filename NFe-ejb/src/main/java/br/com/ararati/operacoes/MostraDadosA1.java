/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.operacoes;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Enumeration;

/**
 *
 * @author tiago
 */
public class MostraDadosA1 {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public String apresentaCertificado(byte[] certificado, char[] senhaDecoded) {
        StringBuilder msg = new StringBuilder();

        try {
            KeyStore ks = KeyStore.getInstance("pkcs12");
            ks.load(new ByteArrayInputStream(certificado), senhaDecoded);

            Enumeration<String> al = ks.aliases();
            while (al.hasMoreElements()) {
                String alias = al.nextElement();
                if (ks.containsAlias(alias)) {
                    X509Certificate cert = (X509Certificate) ks.getCertificate(alias);
                    msg.append("Emitido para........: ").append(alias).append("\n");
                    msg.append("SubjectDN...........: ").append(cert.getSubjectDN().toString()).append("\n");
                    msg.append("Versão.............: ").append(cert.getVersion()).append("\n");
                    msg.append("Número Serial........: ").append(cert.getSerialNumber()).append("\n");
                    msg.append("SigAlgName..........: ").append(cert.getSigAlgName()).append("\n");
                    msg.append("Válido a partir de..: ").append(dateFormat.format(cert.getNotBefore())).append("\n");
                    msg.append("Válido até..........: ").append(dateFormat.format(cert.getNotAfter())).append("\n");
                } else {
                    msg.append("Alias não existe: ").append(alias);
                }
            }

        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
            msg.append(e.toString());
        }

        return msg.toString();
    }
}
