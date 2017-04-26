/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.nfe.emissao;

import br.com.ararati.entity.cadastros.*;
import br.com.ararati.entity.AbstractEntity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * Grupo Volumes
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "transporte_volume")
public class TransporteVolume extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "transporte_nfe_id", nullable = false)
    private TransporteNFe transporteNFe;

    // Quantidade de volumes transportados
    @Length(min = 1, max = 15, message = "Quantidade de Volumes deve conter entre {min} e {max} caracteres")
    @Column(length = 15, nullable = true)
    private String qvol;
    // Espécie dos volumes transportados
    @Length(min = 1, max = 15, message = "Espécie dos Volumes deve conter entre {min} e {max} caracteres")
    @Column(length = 60, nullable = true)
    private String esp;
    // Marca dos volumes transportados
    @Length(min = 1, max = 15, message = "Marca dos Volumes deve conter entre {min} e {max} caracteres")
    @Column(length = 60, nullable = true)
    private String marca;
    // Numeração dos volumes transportados 
    @Length(min = 1, max = 15, message = "Numeração dos Volumes deve conter entre {min} e {max} caracteres")
    @Column(length = 60, nullable = true)
    private String nvol;
    // Peso Liquido (em kg) 
    @DecimalMin(value = "0.000")
    @Column(precision = 15, scale = 3, nullable = true)
    private BigDecimal pesol;
    // Peso Bruto (em kg) 
    @DecimalMin(value = "0.000")
    @Column(precision = 15, scale = 3, nullable = true)
    private BigDecimal pesob;

    /* Grupo Lacres */
    @OneToMany(mappedBy = "transporteVolume", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<TransporteLacre> lacres;

    public TransporteVolume() {
        this.lacres = new ArrayList<>();
    }

    public void addTransporteLacre(TransporteLacre item) {
        this.lacres.add(item);
        item.setTransporteVolume(this);
    }

    public TransporteNFe getTransporteNFe() {
        return transporteNFe;
    }

    public void setTransporteNFe(TransporteNFe transporteNFe) {
        this.transporteNFe = transporteNFe;
    }

    public String getQvol() {
        return qvol;
    }

    public void setQvol(String qvol) {
        this.qvol = qvol;
    }

    public String getEsp() {
        return esp;
    }

    public void setEsp(String esp) {
        this.esp = esp;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNvol() {
        return nvol;
    }

    public void setNvol(String nvol) {
        this.nvol = nvol;
    }

    public BigDecimal getPesol() {
        return pesol;
    }

    public void setPesol(BigDecimal pesol) {
        this.pesol = pesol;
    }

    public BigDecimal getPesob() {
        return pesob;
    }

    public void setPesob(BigDecimal pesob) {
        this.pesob = pesob;
    }

    public List<TransporteLacre> getLacres() {
        return lacres;
    }

    public void setLacres(List<TransporteLacre> lacres) {
        this.lacres = lacres;
    }

}
