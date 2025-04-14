package br.com.fiap.PzBurguer.dto.responses;

import br.com.fiap.PzBurguer.model.Pedido;

import java.math.BigDecimal;

public record ResponsePedidoDto(Long id, String status, String enderecoEntrega, String observacoes, BigDecimal valorTotal) {

    public ResponsePedidoDto(Pedido pedido) {
        this(pedido.getId(), pedido.getStatus().name(), pedido.getEnderecoEntrega(), pedido.getObservacoes(), pedido.getValorTotal());
    }
}
