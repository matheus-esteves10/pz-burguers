package br.com.fiap.PzBurguer.dto;

import br.com.fiap.PzBurguer.model.enums.StatusPedido;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoDto (
                         StatusPedido statusPedido,
                         String endereco,
                         String observacoes,
                         LocalDateTime dataPedido,
                         List<ItemPedidoRequest> itens
                         ) {
}
