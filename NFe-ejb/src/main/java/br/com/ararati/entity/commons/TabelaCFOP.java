package br.com.ararati.entity.commons;

import br.com.ararati.entity.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author tiago
 */
@Entity
@Table(schema = "public", name = "tabela_cfop")
public class TabelaCFOP extends AbstractEntity {

    @Column(length = 4, nullable = false)
    private String codigo;
    @Column(length = 1000, nullable = false)
    private String descricao;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    
}
