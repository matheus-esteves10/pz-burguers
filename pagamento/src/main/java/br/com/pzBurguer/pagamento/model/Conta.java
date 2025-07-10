package br.com.pzBurguer.pagamento.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Conta {
    private Long idPedido;
    private BigDecimal saldo;
}

