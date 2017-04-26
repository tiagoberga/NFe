/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.nfe.emissao;

import br.com.ararati.entity.cadastros.*;
import br.com.ararati.entity.AbstractEntity;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * Informações do Registro de Aquisição de Cana
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "aquisicao_cana_deducao")
public class AquisicaoCanaDeducao extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "aquisicao_cana_id", nullable = false)
    private AquisicaoCana aquisicaoCana;

    // Descrição da Dedução
    @NotNull(message = "Dedução é obrigatório")
    @Length(min = 1, max = 60, message = "Dedução do Contribuinte deve conter entre {min} e {max} caracteres")
    @Column(length = 60, nullable = false)
    private String xded;
    // Valor da Dedução
    @NotNull(message = "Valor da Dedução é obrigatório")
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2, nullable = true)
    private BigDecimal vded;

    public AquisicaoCana getAquisicaoCana() {
        return aquisicaoCana;
    }

    public void setAquisicaoCana(AquisicaoCana aquisicaoCana) {
        this.aquisicaoCana = aquisicaoCana;
    }

    public String getXded() {
        return xded;
    }

    public void setXded(String xded) {
        this.xded = xded;
    }

    public BigDecimal getVded() {
        return vded;
    }

    public void setVded(BigDecimal vded) {
        this.vded = vded;
    }
    
    
}
