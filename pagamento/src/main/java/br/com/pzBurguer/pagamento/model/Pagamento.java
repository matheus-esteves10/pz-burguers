package br.com.pzBurguer.pagamento.model;

import br.com.pzBurguer.pagamento.model.enums.StatusPagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento {
    private BigDecimal valorTotal;
    private StatusPagamento statusPagamento;
}
