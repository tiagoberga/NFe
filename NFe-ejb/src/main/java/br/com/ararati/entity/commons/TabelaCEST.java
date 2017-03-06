package br.com.ararati.entity.commons;

import br.com.ararati.entity.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author tiago
 */
@Entity
@Table(schema = "public", name = "tabela_cest")
public class TabelaCEST extends AbstractEntity {

    @Column(length = 7, nullable = false)
    private String codigo;
    @Column(length = 8, nullable = false)
    private String ncm;
    @Column(length = 1000, nullable = false)
    private String descricao;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
