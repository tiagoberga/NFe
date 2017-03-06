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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * Grupo Lacres
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "transporte_lacre_nfe")
public class TransporteLacre extends AbstractEntity {

    @ManyToOne
    @NotNull(message = "Empresa Emitente é obrigatório")
    @JoinColumn(name = "emitente_id", nullable = false)
    private Emitente emitente;

    @ManyToOne
    @JoinColumn(name = "transporte_volume_id", nullable = false)
    private TransporteVolume transporteVolume;

    // Número dos Lacres
    @Length(min = 1, max = 15, message = "Número dos Lacres deve conter entre {min} e {max} caracteres")
    @Column(length = 60, nullable = true)
    private String nlacre;

    public Emitente getEmitente() {
        return emitente;
    }

    public void setEmitente(Emitente emitente) {
        this.emitente = emitente;
    }

    public TransporteVolume getTransporteVolume() {
        return transporteVolume;
    }

    public void setTransporteVolume(TransporteVolume transporteVolume) {
        this.transporteVolume = transporteVolume;
    }

    public String getNlacre() {
        return nlacre;
    }

    public void setNlacre(String nlacre) {
        this.nlacre = nlacre;
    }

}
