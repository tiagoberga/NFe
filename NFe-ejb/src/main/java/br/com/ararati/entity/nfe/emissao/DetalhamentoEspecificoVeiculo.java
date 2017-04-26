/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.entity.nfe.emissao;

import br.com.ararati.entity.cadastros.*;
import br.com.ararati.entity.AbstractEntity;
import br.com.ararati.enums.J.NFeTipoVeiculosNovosCombustivel;
import br.com.ararati.enums.J.NFeTipoVeiculosNovosCondicao;
import br.com.ararati.enums.J.NFeTipoVeiculosNovosCondicaoVIN;
import br.com.ararati.enums.J.NFeTipoVeiculosNovosCor;
import br.com.ararati.enums.J.NFeTipoVeiculosNovosOperacao;
import br.com.ararati.enums.J.NFeTipoVeiculosNovosRestricao;
import br.com.ararati.enums.J.NFeTipoVeiculosNovosVeiculo;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * J01 - Detalhamento de Veículos novos.
 *
 * @author tiago
 */
@Entity
@Table(schema = "nfe", name = "detalhamento_especifico_veiculo")
public class DetalhamentoEspecificoVeiculo extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "detalhamento_produto_servico_id", nullable = false)
    private DetalhamentoProdutoServico detalhamentoProdutoServico;

    // Tipo da operação
    @NotNull(message = "Tipo da operação é obrigatório")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NFeTipoVeiculosNovosOperacao tpop;
    
    // Chassi do veículo
    @NotNull(message = "Chassi do veículo é obrigatório")
    @Column(length = 17, nullable = false)
    private String chassi;
    
    // Cor
    @NotNull(message = "Cor é obrigatório")
    @Length(min = 1, max = 4, message = "Cor deve conter entre {min} e {max} caracteres")
    @Column(length = 4, nullable = false)
    private String ccor;
    
    // Descrição da Cor
    @NotNull(message = "Descrição da Cor é obrigatório")
    @Length(min = 1, max = 40, message = "Descrição deve conter entre {min} e {max} caracteres")
    @Column(length = 40, nullable = false)
    private String xcor;
    
    // Potência Motor (CV)
    @NotNull(message = "Potência Motor (CV) é obrigatório")
    @Length(min = 1, max = 4, message = "Potência Motor (CV) deve conter entre {min} e {max} caracteres")
    @Column(length = 4, nullable = false)
    private String pot;
    
    // Cilindradas
    @NotNull(message = "Cilindradas é obrigatório")
    @Length(min = 1, max = 4, message = "Cilindrada deve conter entre {min} e {max} caracteres")
    @Column(length = 4, nullable = false)
    private String cilin;
    
    // Peso Liquido
    @NotNull(message = "Peso Liquido é obrigatório")
    @Length(min = 1, max = 9, message = "Peso Liquido deve conter entre {min} e {max} caracteres")
    @Column(precision = 9, scale = 4, nullable = false)
    @DecimalMin(value = "0")
    private BigDecimal pesol;
    
    // Peso Bruto
    @NotNull(message = "Peso Bruto é obrigatório")
    @Length(min = 1, max = 9, message = "Peso Bruto deve conter entre {min} e {max} caracteres")
    @Column(precision = 9, scale = 4, nullable = false)
    @DecimalMin(value = "0")
    private BigDecimal pesob;
    
    // Serial (série) 
    @NotNull(message = "Serial é obrigatório")
    @Length(min = 1, max = 9, message = "Serial deve conter entre {min} e {max} caracteres")
    @Column(length = 9, nullable = false)
    private String nserie;
    
    // Tipo de combustível 
    @NotNull(message = "Tipo de combustível é obrigatório")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NFeTipoVeiculosNovosCombustivel tpcomb;
    
    // Número de Motor
    @NotNull(message = "Número de Motor é obrigatório")
    @Column(length = 21, nullable = false)
    private String nmotor;
    
    // Capacidade Máxima de Tração
    @NotNull(message = "Capacidade Máxima de Tração é obrigatório")
    @Column(length = 9, nullable = false)
    private String cmt;
    
    // Distância entre eixos
    @NotNull(message = "Distância entre eixos é obrigatório")
    @Column(length = 4, nullable = false)
    private String dist;
    
    // Ano Modelo de Fabricação 
    @NotNull(message = "Ano Modelo de Fabricação é obrigatório")
    @Column(length = 4, nullable = false)
    private String anomod;
    
    // Ano de Fabricação
    @NotNull(message = "Ano de Fabricação é obrigatório")
    @Column(length = 4, nullable = false)
    private String anofab;
    
    // Tipo de Pintura
    @NotNull(message = "Tipo de Pintura é obrigatório")
    @Column(length = 1, nullable = false)
    private String tppint;
    
    // Tipo de Veículo
    @NotNull(message = "Tipo de Veículo é obrigatório")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NFeTipoVeiculosNovosVeiculo tpveic;
    
    // Espécie de Veículo 
    @NotNull(message = "Espécie de Veículo é obrigatório")
    @Column(length = 1, nullable = false)
    private String espveic;
    
    // Condição do VIN
    @NotNull(message = "Condição do VIN é obrigatório")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NFeTipoVeiculosNovosCondicaoVIN vin;
    
    // Condição do Veículo
    @NotNull(message = "Condição do Veículo é obrigatório")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NFeTipoVeiculosNovosCondicao condveic;
    
    // Código Marca Modelo
    @NotNull(message = "Código Modelo é obrigatório")
    @Column(length = 6, nullable = false)
    private String cmod;
    
    // Código da Cor
    @NotNull(message = "Código da Cor é obrigatório")
    @Column(nullable = false)
    private NFeTipoVeiculosNovosCor ccorDENATRAN;
    
    // Capacidade máxima de lotação
    @NotNull(message = "Capacidade lotação é obrigatório")
    @Column(length = 3, nullable = false)
    private String lota;
    
    // Restrição
    @NotNull(message = "Restrição é obrigatório")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NFeTipoVeiculosNovosRestricao tprest;

    public DetalhamentoProdutoServico getDetalhamentoProdutoServico() {
        return detalhamentoProdutoServico;
    }

    public void setDetalhamentoProdutoServico(DetalhamentoProdutoServico detalhamentoProdutoServico) {
        this.detalhamentoProdutoServico = detalhamentoProdutoServico;
    }

    public NFeTipoVeiculosNovosOperacao getTpop() {
        return tpop;
    }

    public void setTpop(NFeTipoVeiculosNovosOperacao tpop) {
        this.tpop = tpop;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public String getCcor() {
        return ccor;
    }

    public void setCcor(String ccor) {
        this.ccor = ccor;
    }

    public String getXcor() {
        return xcor;
    }

    public void setXcor(String xcor) {
        this.xcor = xcor;
    }

    public String getPot() {
        return pot;
    }

    public void setPot(String pot) {
        this.pot = pot;
    }

    public String getCilin() {
        return cilin;
    }

    public void setCilin(String cilin) {
        this.cilin = cilin;
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

    public String getNserie() {
        return nserie;
    }

    public void setNserie(String nserie) {
        this.nserie = nserie;
    }

    public NFeTipoVeiculosNovosCombustivel getTpcomb() {
        return tpcomb;
    }

    public void setTpcomb(NFeTipoVeiculosNovosCombustivel tpcomb) {
        this.tpcomb = tpcomb;
    }

    public String getNmotor() {
        return nmotor;
    }

    public void setNmotor(String nmotor) {
        this.nmotor = nmotor;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public String getAnomod() {
        return anomod;
    }

    public void setAnomod(String anomod) {
        this.anomod = anomod;
    }

    public String getAnofab() {
        return anofab;
    }

    public void setAnofab(String anofab) {
        this.anofab = anofab;
    }

    public String getTppint() {
        return tppint;
    }

    public void setTppint(String tppint) {
        this.tppint = tppint;
    }

    public NFeTipoVeiculosNovosVeiculo getTpveic() {
        return tpveic;
    }

    public void setTpveic(NFeTipoVeiculosNovosVeiculo tpveic) {
        this.tpveic = tpveic;
    }

    public String getEspveic() {
        return espveic;
    }

    public void setEspveic(String espveic) {
        this.espveic = espveic;
    }

    public NFeTipoVeiculosNovosCondicaoVIN getVin() {
        return vin;
    }

    public void setVin(NFeTipoVeiculosNovosCondicaoVIN vin) {
        this.vin = vin;
    }

    public NFeTipoVeiculosNovosCondicao getCondveic() {
        return condveic;
    }

    public void setCondveic(NFeTipoVeiculosNovosCondicao condveic) {
        this.condveic = condveic;
    }

    public String getCmod() {
        return cmod;
    }

    public void setCmod(String cmod) {
        this.cmod = cmod;
    }

    public NFeTipoVeiculosNovosCor getCcorDENATRAN() {
        return ccorDENATRAN;
    }

    public void setCcorDENATRAN(NFeTipoVeiculosNovosCor ccorDENATRAN) {
        this.ccorDENATRAN = ccorDENATRAN;
    }

    public String getLota() {
        return lota;
    }

    public void setLota(String lota) {
        this.lota = lota;
    }

    public NFeTipoVeiculosNovosRestricao getTprest() {
        return tprest;
    }

    public void setTprest(NFeTipoVeiculosNovosRestricao tprest) {
        this.tprest = tprest;
    }

}
