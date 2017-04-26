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
 * Grupo Reboque
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "transporte_reboque")
public class TransporteReboque extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "transporte_nfe_id", nullable = false)
    private TransporteNFe transporteNFe;

    // Placa do Veículo 
    @Column(length = 7, nullable = true)
    private String placa;
    // Sigla da UF 
    @Column(length = 2, nullable = true)
    private String uf;
    // Registro Nacional de Transportador de Carga (ANTT)
    @Column(length = 20, nullable = true)
    @Length(min = 1, max = 20, message = "Número do Item deve conter entre {min} e {max} caracteres")
    private String rntc;

    public TransporteNFe getTransporteNFe() {
        return transporteNFe;
    }

    public void setTransporteNFe(TransporteNFe transporteNFe) {
        this.transporteNFe = transporteNFe;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getRntc() {
        return rntc;
    }

    public void setRntc(String rntc) {
        this.rntc = rntc;
    }

}
