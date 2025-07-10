package br.com.fiap.PzBurguer.dto.listener;

import br.com.fiap.PzBurguer.model.enums.StatusPagamento;

public record PagamentoStatusDto(Long idPedido, StatusPagamento status) {
}