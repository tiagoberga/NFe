/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.model;

/**
 *
 * @author leandro
 */
public class EmailAnexo {

    private String nomeArquivo;
    private String tipoAnexo;
    private byte[] arquivo;

    public EmailAnexo(String nomeArquivo, String tipoAnexo, byte[] arquivo) {
        this.nomeArquivo = nomeArquivo;
        this.tipoAnexo = tipoAnexo;
        this.arquivo = arquivo;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getTipoAnexo() {
        return tipoAnexo;
    }

    public void setTipoAnexo(String tipoAnexo) {
        this.tipoAnexo = tipoAnexo;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

}
