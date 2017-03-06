/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.nfe.emissao;

import br.com.ararati.entity.cadastros.*;
import br.com.ararati.entity.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * Informações do Transporte da NF-e
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "transporte_veiculo")
public class TransporteVeiculo extends AbstractEntity {


    @ManyToOne
    @NotNull(message = "Empresa Emitente é obrigatório")
    @JoinColumn(name = "emitente_id", nullable = false)
    private Emitente emitente;

    @OneToOne
    @JoinColumn(name = "transporte_nfe_id", nullable = false)
    private TransporteNFe transporteNFe;
        
    // Placa do Veículo
    @NotNull(message = "Placa do Veículo é obrigatório")
    @Column(length = 7, nullable = false)
    private String placa;
    // Sigla da UF
    @NotNull(message = "Sigla da UF é obrigatório")
    @Column(length = 2, nullable = false)
    private String uf;
    // Registro Nacional de Transportador de Carga (ANTT)
    @NotNull(message = "ANTT é obrigatório")
    @Column(length = 20, nullable = true)
    @Length(min = 1, max = 20, message = "Número do Item deve conter entre {min} e {max} caracteres")
    private String rntc;

    public Emitente getEmitente() {
        return emitente;
    }

    public void setEmitente(Emitente emitente) {
        this.emitente = emitente;
    }

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
