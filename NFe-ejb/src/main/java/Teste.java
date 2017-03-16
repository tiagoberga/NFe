
import br.com.ararati.entity.nfe.emissao.DetalhamentoProdutoServico;
import br.com.ararati.enums.N.NFeTipoSituacaoTributariaICMS;
import java.math.BigDecimal;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ararati1
 */
public class Teste {

    public static void main(String[] args) {
        DetalhamentoProdutoServico dps = new DetalhamentoProdutoServico();
        dps.setCsticms(NFeTipoSituacaoTributariaICMS.COD00);
        dps.setPredbcicms(BigDecimal.ZERO);
        dps.setPredbcicmsst(BigDecimal.ZERO);
    }
    
}
