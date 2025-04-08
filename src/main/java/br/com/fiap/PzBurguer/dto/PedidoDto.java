package br.com.fiap.PzBurguer.dto;

import br.com.fiap.PzBurguer.model.ItemPedido;
import br.com.fiap.PzBurguer.model.StatusPedido;

import java.util.List;

public record PedidoDto (Long idPedido,
                         Long idUsuario,
                         StatusPedido statusPedido,
                         String endereco,
                         String observacoes,
                         List<ItemPedido> itens
                         ) {
}
