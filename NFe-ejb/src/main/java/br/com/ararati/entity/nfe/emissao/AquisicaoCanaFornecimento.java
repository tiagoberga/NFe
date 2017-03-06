/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.nfe.emissao;

import br.com.ararati.entity.cadastros.*;
import br.com.ararati.entity.AbstractEntity;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * Informações do Registro de Aquisição de Cana
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "aquisicao_cana_fornecimento")
public class AquisicaoCanaFornecimento extends AbstractEntity {

    @ManyToOne
    @NotNull(message = "Empresa Emitente é obrigatório")
    @JoinColumn(name = "emitente_id", nullable = false)
    private Emitente emitente;

    @ManyToOne
    @JoinColumn(name = "aquisicao_cana_id", nullable = false)
    private AquisicaoCana aquisicaoCana;

    // Dia
    @NotNull(message = "Dia é obrigatório")
    @Column(length = 2, nullable = false)
    private String dia;
    // Quantidade
    @NotNull(message = "Quantidade é obrigatório")
    @DecimalMin(value = "0.0000")
    @Column(precision = 11, scale = 4, nullable = true)
    private BigDecimal qtde;

    public Emitente getEmitente() {
        return emitente;
    }

    public void setEmitente(Emitente emitente) {
        this.emitente = emitente;
    }

    public AquisicaoCana getAquisicaoCana() {
        return aquisicaoCana;
    }

    public void setAquisicaoCana(AquisicaoCana aquisicaoCana) {
        this.aquisicaoCana = aquisicaoCana;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public BigDecimal getQtde() {
        return qtde;
    }

    public void setQtde(BigDecimal qtde) {
        this.qtde = qtde;
    }
    
    
}
