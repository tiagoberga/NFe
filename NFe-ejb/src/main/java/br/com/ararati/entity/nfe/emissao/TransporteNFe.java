/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.nfe.emissao;

import br.com.ararati.entity.cadastros.*;
import br.com.ararati.entity.AbstractEntity;
import br.com.ararati.enums.X.NFeTipoModalidadeFrete;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * Informações do Transporte da NF-e
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "transporte_nfe")
public class TransporteNFe extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "dados_nfe_id", nullable = false)
    private DadosNFe dadosNFe;

    // Modalidade do frete
    @NotNull(message = "Modalidade do Frete é obrigatório")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NFeTipoModalidadeFrete modfrete;

    /* Grupo Transportador */
    // Documento do Transportador
    @Column(length = 14, nullable = true)
    private String documento;
    // Razão Social ou nome 
    @Column(length = 60, nullable = true)
    @Length(min = 1, max = 60, message = "Razão Social deve conter entre {min} e {max} caracteres")
    private String xnome;
    // Inscrição Estadual do Transportador
    @Column(length = 14, nullable = true)
    @Length(min = 1, max = 14, message = "Inscrição Estadual deve conter entre {min} e {max} caracteres")
    private String ie;
    // Endereço Completo
    @Column(length = 60, nullable = true)
    @Length(min = 1, max = 60, message = "Endereço Completo deve conter entre {min} e {max} caracteres")
    private String xender;
    // Nome do município 
    @Column(length = 60, nullable = true)
    @Length(min = 1, max = 60, message = "Nome do Município deve conter entre {min} e {max} caracteres")
    private String xmun;
    // Sigla da UF 
    @Column(length = 2, nullable = true)
    private String uf;

    // Identificação do vagão 
    @Column(length = 20, nullable = true)
    @Length(min = 1, max = 20, message = "Vagão deve conter entre {min} e {max} caracteres")
    private String vagao;
    // Identificação da balsa
    @Length(min = 1, max = 20, message = "Balsa deve conter entre {min} e {max} caracteres")
    @Column(length = 20, nullable = true)
    private String balsa;

    /* Grupo Retenção ICMS transporte */
    @OneToOne(mappedBy = "transporteNFe", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private TransporteRetencaoIcms retencaoIcms;

    /* Grupo Veículo Transporte */
    @OneToOne(mappedBy = "transporteNFe", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private TransporteVeiculo veiculo;

    /* Grupo Reboque */
    @OneToMany(mappedBy = "transporteNFe", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<TransporteReboque> reboques;

    /* Grupo Volumes */
    @OneToMany(mappedBy = "transporteNFe", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<TransporteVolume> volumes;

    public TransporteNFe() {
    }

    public TransporteNFe(Transportadora transportadora) {
        this.documento = transportadora.getDocumento();
        this.xnome = transportadora.getXnome();
        this.ie = transportadora.getIe();

        // ENDERECO
        if (transportadora.getEndereco() != null) {
            Endereco end = transportadora.getEndereco();
            StringBuilder lgr = new StringBuilder().append(end.getEndXlgr()).append(" ").append(end.getEndNro()).append(" ").append(end.getEndXbairro());
            this.xender = lgr.toString();
            this.xmun = end.getEndXmun();
            this.uf = end.getEndUf();
        }
    }

    @PostLoad
    public void init() {
        this.volumes = new ArrayList<>();
        this.reboques = new ArrayList<>();
    }

    public void setVeiculo(TransporteVeiculo item) {
        this.veiculo = item;
    }

    public void setRetencaoIcms(TransporteRetencaoIcms item) {
        this.retencaoIcms = item;
    }

    public void addTransporteReboque(TransporteReboque item) {
        this.reboques.add(item);
        item.setTransporteNFe(this);
    }

    public void addTransporteVolume(TransporteVolume item) {
        this.volumes.add(item);
        item.setTransporteNFe(this);
    }

    public DadosNFe getDadosNFe() {
        return dadosNFe;
    }

    public void setDadosNFe(DadosNFe dadosNFe) {
        this.dadosNFe = dadosNFe;
    }

    public NFeTipoModalidadeFrete getModfrete() {
        return modfrete;
    }

    public void setModfrete(NFeTipoModalidadeFrete modfrete) {
        this.modfrete = modfrete;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getXnome() {
        return xnome;
    }

    public void setXnome(String xnome) {
        this.xnome = xnome;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getXender() {
        return xender;
    }

    public void setXender(String xender) {
        this.xender = xender;
    }

    public String getXmun() {
        return xmun;
    }

    public void setXmun(String xmun) {
        this.xmun = xmun;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getVagao() {
        return vagao;
    }

    public void setVagao(String vagao) {
        this.vagao = vagao;
    }

    public String getBalsa() {
        return balsa;
    }

    public void setBalsa(String balsa) {
        this.balsa = balsa;
    }

    public TransporteRetencaoIcms getRetencaoIcms() {
        return retencaoIcms;
    }

    public TransporteVeiculo getVeiculo() {
        return veiculo;
    }

    public List<TransporteReboque> getReboques() {
        return reboques;
    }

    public void setReboques(List<TransporteReboque> reboques) {
        this.reboques = reboques;
    }

    public List<TransporteVolume> getVolumes() {
        return volumes;
    }

    public void setVolumes(List<TransporteVolume> volumes) {
        this.volumes = volumes;
    }

}
