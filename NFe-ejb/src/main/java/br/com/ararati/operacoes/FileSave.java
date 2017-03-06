/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.operacoes;

import br.com.ararati.enums.commons.TipoExtensaoArquivo;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 *
 * @author tiago
 */
public class FileSave {

    /**
     * Método resposável por armazenar um arquivo
     *
     * @param fileContent contudo do arquivo a ser salvo
     * @param rootDirectory diretorio raiz a ser salvo
     * @param nameToSave nome do arquivo a ser salvo
     * @param fileExtension extensao do arquivo a ser salvo
     * @throws IOException
     * @throws FileNotFoundException
     */
    public String saveFile(String fileContent, String rootDirectory, String nameToSave, TipoExtensaoArquivo fileExtension) throws IOException, FileNotFoundException {
        Writer file;

        try {
            file = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                    CriarEstruturaDePastas.criaEstruturaPadrao(rootDirectory)
                    .concat(nameToSave)
                    .concat(fileExtension.getExtensao())), "UTF-8"));
            file.write(fileContent);
            file.close();
            return CriarEstruturaDePastas.criaEstruturaPadrao(rootDirectory).concat(nameToSave).concat(fileExtension.getExtensao());
        } catch (IOException ex) {
            throw new FileNotFoundException("Não foi possível salvar o arquivo: " + nameToSave + ". " + ex.getMessage());
        }
    }
}
