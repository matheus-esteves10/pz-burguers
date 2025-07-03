package br.com.fiap.PzBurguer.dto.responses;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public record PedidosPendentesResponse(
        Long idCliente,
        String nomeCliente,
        Map<String, Integer> itens,
        BigDecimal valorTotal,
        String statusPedido,
        String enderecoEntrega,
        String observacoes
) {
}
