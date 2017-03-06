/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.commons;

import br.com.ararati.entity.AbstractEntity;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author tiago
 *
 * Tudo o que precisar saber sobre o IBPT: http://deolhonoimposto.ibpt.org.br/
 */
@Entity
@Table(schema = "public", name = "tabela_ncm")
public class TabelaNCM extends AbstractEntity {

    @NotNull(message = "Código não pode estar vazio")
    @Column(name = "codigo")
    private String codigo;
    @NotNull(message = "Excessão não pode estar vazio")
    @Column(name = "excessao")
    private String excessao;
    @Column(name = "tabela")
    private String tabela;
    @NotNull(message = "Descrição não pode estar vazio")
    @Size(max = 500)
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "aliqnac", precision = 15, scale = 2)
    private BigDecimal aliqnac = BigDecimal.ZERO;
    @Column(name = "aliqimp", precision = 15, scale = 2)
    private BigDecimal aliqimp = BigDecimal.ZERO;
    @Size(max = 2147483647)
    @Column(name = "versao")
    private String versao;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getExcessao() {
        return excessao;
    }

    public void setExcessao(String excessao) {
        this.excessao = excessao;
    }

    public String getTabela() {
        return tabela;
    }

    public void setTabela(String tabela) {
        this.tabela = tabela;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getAliqnac() {
        return aliqnac;
    }

    public void setAliqnac(BigDecimal aliqnac) {
        this.aliqnac = aliqnac;
    }

    public BigDecimal getAliqimp() {
        return aliqimp;
    }

    public void setAliqimp(BigDecimal aliqimp) {
        this.aliqimp = aliqimp;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

}
