package br.com.pzBurguer.pagamento.dto;

import br.com.pzBurguer.pagamento.model.enums.StatusPagamento;

public record PagamentoStatusDto(Long idPedido, StatusPagamento status) {
}
