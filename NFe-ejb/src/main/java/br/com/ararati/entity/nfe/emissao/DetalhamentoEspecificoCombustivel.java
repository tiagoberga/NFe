/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.nfe.emissao;

import br.com.ararati.entity.AbstractEntity;
import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * Identificação do Destinatário da Nota Fiscal eletrônica
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "detalhamento_especifico_combustivel")
public class DetalhamentoEspecificoCombustivel extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "detalhamento_produto_servico_id", nullable = false)
    private DetalhamentoProdutoServico detalhamentoProdutoServico;

    // Código de produto da ANP
    @NotNull(message = "Código de produto da ANP é obrigatório")
    @Column(length = 9, nullable = false)
    private String cprodanp;

    /**
     * Descrição do produto conforme ANP, utilizar a descrição de produtos do
     * Sistema de Informações de Movimentação de Produtos - SIMP
     * (http://www.anp.gov.br/simp/).
     */
    @NotNull(message = "Descrição ANP é obrigatório")
    @Length(min = 2, max = 95, message = "Descrição ANP deve conter entre {min} e {max} caracteres")
    @Column(length = 95, nullable = false)
    private String descanp;

    /**
     * Percentual do GLP derivado do petróleo no produto GLP
     * (cProdANP=210203001), informar em número decimal o percentual do GLP
     * derivado de petróleo no produto GLP. Valores de 0 a 1.
     */
    @DecimalMin(value = "0.0000", message = "Percentual do GLP - Valor mínimo permitido: {value}")
    @DecimalMax(value = "1.0000", message = "Percentual do GLP - Valor máximo permitido: {value}")
    @Column(precision = 5, scale = 4)
    private BigDecimal pglp;

    /**
     * Percentual de Gás Natural Nacional – GLGNn para o produto GLP
     * (cProdANP=210203001), informar em número decimal o percentual do Gás
     * Natural Nacional – GLGNn para o produto GLP. Valores de 0 a 1.
     */
    @DecimalMin(value = "0.0000", message = "Percentual de Gás Natural Nacional  - Valor mínimo permitido: {value}")
    @DecimalMax(value = "1.0000", message = "Percentual de Gás Natural Nacional  - Valor máximo permitido: {value}")
    @Column(precision = 5, scale = 4)
    private BigDecimal pgnn;

    /**
     * Percentual de Gás Natural Importado – GLGNi para o produto GLP
     * (cProdANP=210203001), Informar em número decimal o percentual do Gás
     * Natural Importado – GLGNi para o produto GLP. Valores de 0 a 1.
     */
    @DecimalMin(value = "0.0000", message = "Percentual de Gás Natural Importado - Valor mínimo permitido: {value}")
    @DecimalMax(value = "1.0000", message = "Percentual de Gás Natural Importado - Valor máximo permitido: {value}")
    @Column(precision = 5, scale = 4)
    private BigDecimal pgni;

    /**
     * Valor de partida (cProdANP=210203001), deve ser informado neste campo o
     * valor por quilograma sem ICMS.
     */
    @DecimalMin(value = "0.00")
    @Column(precision = 15, scale = 2)
    private BigDecimal vpart;

    /**
     * Código de autorização / registro do CODIF , Informar apenas quando a UF
     * utilizar o CODIF (Sistema de Controle do Diferimento do Imposto nas
     * Operações com AEAC - Álcool Etílico Anidro Combustível).
     */
    @Length(min = 1, max = 21, message = "CODIF deve conter entre {min} e {max} caracteres")
    @Column(length = 21)
    private String codif;

    /**
     * Quantidade de combustível faturada à temperatura ambiente, informar
     * quando a quantidade faturada informada no campo "prod/qCom" (id:I10)
     * tiver sido ajustada para uma temperatura diferente da ambiente.
     */
    @DecimalMin(value = "0.0000")
    @Column(precision = 15, scale = 4)
    private BigDecimal qtemp;

    /**
     * Sigla da UF de consumo, informar a UF de consumo. Informar "EX" para
     * Exterior.
     */
    @Column(length = 2, nullable = false)
    private String ufcons;

    @Valid
    @OneToOne(mappedBy = "detalhamentoEspecificoCombustivel", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private DetalhamentoEspecificoCombustivelCIDE cide;

    public DetalhamentoProdutoServico getDetalhamentoProdutoServico() {
        return detalhamentoProdutoServico;
    }

    public void setDetalhamentoProdutoServico(DetalhamentoProdutoServico detalhamentoProdutoServico) {
        this.detalhamentoProdutoServico = detalhamentoProdutoServico;
    }

    public String getCprodanp() {
        return cprodanp;
    }

    public void setCprodanp(String cprodanp) {
        this.cprodanp = cprodanp;
    }

    public String getDescanp() {
        return descanp;
    }

    public void setDescanp(String descanp) {
        this.descanp = descanp;
    }

    public BigDecimal getPglp() {
        return pglp;
    }

    public void setPglp(BigDecimal pglp) {
        this.pglp = pglp;
    }

    public BigDecimal getPgnn() {
        return pgnn;
    }

    public void setPgnn(BigDecimal pgnn) {
        this.pgnn = pgnn;
    }

    public BigDecimal getPgni() {
        return pgni;
    }

    public void setPgni(BigDecimal pgni) {
        this.pgni = pgni;
    }

    public BigDecimal getVpart() {
        return vpart;
    }

    public void setVpart(BigDecimal vpart) {
        this.vpart = vpart;
    }

    public String getCodif() {
        return codif;
    }

    public void setCodif(String codif) {
        this.codif = codif;
    }

    public BigDecimal getQtemp() {
        return qtemp;
    }

    public void setQtemp(BigDecimal qtemp) {
        this.qtemp = qtemp;
    }

    public String getUfcons() {
        return ufcons;
    }

    public void setUfcons(String ufcons) {
        this.ufcons = ufcons;
    }

    public DetalhamentoEspecificoCombustivelCIDE getCide() {
        return cide;
    }

    public void setCide(DetalhamentoEspecificoCombustivelCIDE cide) {
        this.cide = cide;
    }

}
