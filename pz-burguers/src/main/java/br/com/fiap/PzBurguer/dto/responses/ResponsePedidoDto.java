package br.com.fiap.PzBurguer.dto.responses;

import br.com.fiap.PzBurguer.model.Pedido;
import br.com.fiap.PzBurguer.model.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

public record ResponsePedidoDto(
        Long id,
        String status,
        String enderecoEntrega,
        String observacoes,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime dataPedido,
        BigDecimal valorTotal,
        Map<String, Integer> itens,
        String nomeUser
) {

    public static ResponsePedidoDto from(Pedido pedido) {
        return new ResponsePedidoDto(
                pedido.getId(),
                pedido.getStatus().name(),
                pedido.getEnderecoEntrega(),
                pedido.getObservacoes(),
                pedido.getDataPedido(),
                pedido.getValorTotal(),
                pedido.getItens().stream()
                        .collect(Collectors.toMap(
                                item -> item.getItem().getNome(),
                                item -> item.getQuantidade()
                        )),
                pedido.getUsuario().getNome()
        );
    }

}


