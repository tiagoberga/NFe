/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.nfe.emissao;

import br.com.ararati.entity.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;

/**
 * Grupo Campo de uso livre do contribuinte
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "informacoes_adicionais_contribuinte")
public class InformacoesAdicionaisContribuinte extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "informacoes_adicionais_id", nullable = false)
    private InformacoesAdicionais informacoesAdicionais;

    // Identificação do campo 
    @Length(min = 1, max = 2000, message = "Identificação deve conter entre {min} e {max} caracteres")
    @Column(length = 2000, nullable = false)
    private String xcampo;
    // Conteúdo do campo
    @Length(min = 1, max = 2000, message = "Conteúdo deve conter entre {min} e {max} caracteres")
    @Column(length = 2000, nullable = false)
    private String xtexto;

    public InformacoesAdicionais getInformacoesAdicionais() {
        return informacoesAdicionais;
    }

    public void setInformacoesAdicionais(InformacoesAdicionais informacoesAdicionais) {
        this.informacoesAdicionais = informacoesAdicionais;
    }

    public String getXcampo() {
        return xcampo;
    }

    public void setXcampo(String xcampo) {
        this.xcampo = xcampo;
    }

    public String getXtexto() {
        return xtexto;
    }

    public void setXtexto(String xtexto) {
        this.xtexto = xtexto;
    }

}
