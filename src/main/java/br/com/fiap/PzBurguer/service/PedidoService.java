package br.com.fiap.PzBurguer.service;

import br.com.fiap.PzBurguer.dto.PedidoCancelamentoDto;
import br.com.fiap.PzBurguer.dto.PedidoDto;
import br.com.fiap.PzBurguer.dto.responses.PedidosPendentesResponse;
import br.com.fiap.PzBurguer.dto.responses.ResponsePedidoDto;
import br.com.fiap.PzBurguer.exceptions.InvalidCancelException;
import br.com.fiap.PzBurguer.exceptions.OrderNotFoundException;
import br.com.fiap.PzBurguer.model.Item;
import br.com.fiap.PzBurguer.model.ItemPedido;
import br.com.fiap.PzBurguer.model.Pedido;
import br.com.fiap.PzBurguer.model.StatusPedido;
import br.com.fiap.PzBurguer.repository.ItemPedidoRepository;
import br.com.fiap.PzBurguer.repository.ItemRepository;
import br.com.fiap.PzBurguer.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
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
                    .orElseThrow(() -> new OrderNotFoundException("Pedido de id " + itemRequest.itemId() + " não encontrado"));
            return new ItemPedido(item, itemRequest.quantidade());
        }).toList();

        Pedido pedido = new Pedido(
                dto.usuario(),
                StatusPedido.SOLICITADO,
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

    public Optional<Pedido> cancelarPedido(PedidoCancelamentoDto dto) {
        Pedido pedido = pedidoRepository.findById(dto.idPedido())
                .orElseThrow(() -> new OrderNotFoundException("Pedido de id " + dto.idPedido() + " não encontrado"));

        if (pedido.getStatus() != StatusPedido.SOLICITADO) {
            throw new InvalidCancelException("Pedido com status diferente de SOLICITADO não pode ser cancelado");
        }

        pedido.setStatus(StatusPedido.CANCELADO);
        return Optional.of(pedidoRepository.save(pedido));
    }

    public Page<ResponsePedidoDto> listarPedidosPorPeriodo(LocalDate dataInicio, LocalDate dataFim, Pageable pageable) {
        Specification<Pedido> spec = Specification.where((root, query, cb) ->
                cb.between(root.get("dataPedido"),
                        dataInicio.atStartOfDay(),
                        dataFim.atTime(LocalTime.MAX))
        );

        return pedidoRepository.findAll(spec, pageable)
                .map(ResponsePedidoDto::new);
    }








}
