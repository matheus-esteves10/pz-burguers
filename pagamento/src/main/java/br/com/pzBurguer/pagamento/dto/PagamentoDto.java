package br.com.pzBurguer.pagamento.dto;

import java.math.BigDecimal;

public record PagamentoDto(BigDecimal valorTotal, Long idPedido) {
}
