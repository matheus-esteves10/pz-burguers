package br.com.fiap.PzBurguer.dto.responses;

import br.com.fiap.PzBurguer.model.Pedido;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

public record ResponsePedidoDto(
        Long id,
        String status,
        String enderecoEntrega,
        String observacoes,
        BigDecimal valorTotal,
        Map<String, Integer> itens
) {
    public ResponsePedidoDto(Pedido pedido) {
        this(
                pedido.getId(),
                pedido.getStatus().name(),
                pedido.getEnderecoEntrega(),
                pedido.getObservacoes(),
                pedido.getValorTotal(),
                pedido.getItens().stream()
                        .collect(Collectors.toMap(
                                item -> item.getItem().getNome(),
                                item -> item.getQuantidade()
                        ))
        );
    }
}

