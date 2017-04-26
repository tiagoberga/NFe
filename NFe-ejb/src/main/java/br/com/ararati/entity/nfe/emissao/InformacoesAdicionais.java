/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.nfe.emissao;

import br.com.ararati.entity.AbstractEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;

/**
 * Informações Adicionais da NF-e
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "informacoes_adicionais")
public class InformacoesAdicionais extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "dados_nfe_id", nullable = false)
    private DadosNFe dadosNFe;

    // Informações Adicionais de Interesse do Fisco
    @Length(min = 1, max = 2000, message = "Informações do Fisco deve conter entre {min} e {max} caracteres")
    @Column(length = 2000, nullable = true)
    private String infAdFisco;
    // Informações Complementares de interesse do Contribuinte
    @Length(min = 1, max = 2000, message = "Informações do Contribuinte deve conter entre {min} e {max} caracteres")
    @Column(length = 2000, nullable = true)
    private String infCpl;

    @OneToMany(mappedBy = "informacoesAdicionais", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<InformacoesAdicionaisContribuinte> informacoesContribuinte;

    @OneToMany(mappedBy = "informacoesAdicionais", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<InformacoesAdicionaisFisco> informacoesFisco;

    @OneToMany(mappedBy = "informacoesAdicionais", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<InformacoesAdicionaisProcessoReferenciado> informacoesProcessoReferenciado;

    public InformacoesAdicionais() {
        this.informacoesContribuinte = new ArrayList<>();
        this.informacoesFisco = new ArrayList<>();
        this.informacoesProcessoReferenciado = new ArrayList<>();
    }

    public DadosNFe getDadosNFe() {
        return dadosNFe;
    }

    public void setDadosNFe(DadosNFe dadosNFe) {
        this.dadosNFe = dadosNFe;
    }

    public String getInfAdFisco() {
        return infAdFisco;
    }

    public void setInfAdFisco(String infAdFisco) {
        this.infAdFisco = infAdFisco;
    }

    public String getInfCpl() {
        return infCpl;
    }

    public void setInfCpl(String infCpl) {
        this.infCpl = infCpl;
    }

    public List<InformacoesAdicionaisContribuinte> getInformacoesContribuinte() {
        return informacoesContribuinte;
    }

    public void setInformacoesContribuinte(List<InformacoesAdicionaisContribuinte> informacoesContribuinte) {
        this.informacoesContribuinte = informacoesContribuinte;
    }

    public List<InformacoesAdicionaisFisco> getInformacoesFisco() {
        return informacoesFisco;
    }

    public void setInformacoesFisco(List<InformacoesAdicionaisFisco> informacoesFisco) {
        this.informacoesFisco = informacoesFisco;
    }

    public List<InformacoesAdicionaisProcessoReferenciado> getInformacoesProcessoReferenciado() {
        return informacoesProcessoReferenciado;
    }

    public void setInformacoesProcessoReferenciado(List<InformacoesAdicionaisProcessoReferenciado> informacoesProcessoReferenciado) {
        this.informacoesProcessoReferenciado = informacoesProcessoReferenciado;
    }

}
