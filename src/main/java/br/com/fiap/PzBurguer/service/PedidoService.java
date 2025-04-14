package br.com.fiap.PzBurguer.service;

import br.com.fiap.PzBurguer.dto.PedidoDto;
import br.com.fiap.PzBurguer.dto.responses.PedidosPendentesResponse;
import br.com.fiap.PzBurguer.model.Item;
import br.com.fiap.PzBurguer.model.ItemPedido;
import br.com.fiap.PzBurguer.model.Pedido;
import br.com.fiap.PzBurguer.model.StatusPedido;
import br.com.fiap.PzBurguer.repository.ItemPedidoRepository;
import br.com.fiap.PzBurguer.repository.ItemRepository;
import br.com.fiap.PzBurguer.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ItemRepository itemRepository;


    public Pedido criarPedido(PedidoDto dto) {
        List<ItemPedido> itens = dto.itens().stream().map(itemRequest -> {
            Item item = itemRepository.findById(itemRequest.itemId())
                    .orElseThrow(() -> new RuntimeException("Item não encontrado: " + itemRequest.itemId()));
            return new ItemPedido(item, itemRequest.quantidade());
        }).toList();

        Pedido pedido = new Pedido(
                dto.usuario(),
                dto.statusPedido(),
                dto.endereco(),
                dto.observacoes(),
                itens
        );

        for (ItemPedido item : itens) {
            item.setPedido(pedido); // relacionamento reverso
        }

        return pedidoRepository.save(pedido);
    }



    public List<PedidosPendentesResponse> listarPedidosPendentes() {
        List<Pedido> pedidos = pedidoRepository.findByStatus(StatusPedido.SOLICITADO);

        return pedidos.stream().map(pedido -> new PedidosPendentesResponse(
                pedido.getUsuario().getId(),
                pedido.getUsuario().getNome(),
                pedido.getItens().stream()
                        .collect(Collectors.toMap(
                                itemPedido -> itemPedido.getItem().getNome(),
                                ItemPedido::getQuantidade
                        )),
                pedido.getValorTotal(),
                pedido.getStatus().name(),
                pedido.getEnderecoEntrega(),
                pedido.getObservacoes()
        )).toList();
    }






}
