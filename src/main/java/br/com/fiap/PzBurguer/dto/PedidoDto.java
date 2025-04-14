package br.com.fiap.PzBurguer.dto;

import br.com.fiap.PzBurguer.model.ItemPedido;
import br.com.fiap.PzBurguer.model.StatusPedido;
import br.com.fiap.PzBurguer.model.Usuario;

import java.util.List;

public record PedidoDto (
                         StatusPedido statusPedido,
                         String endereco,
                         String observacoes,
                         Usuario usuario,
                         List<ItemPedido> itens
                         ) {
}
