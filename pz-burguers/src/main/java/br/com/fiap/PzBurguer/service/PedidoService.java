package br.com.fiap.PzBurguer.service;

import br.com.fiap.PzBurguer.dto.PedidoDto;
import br.com.fiap.PzBurguer.dto.responses.PedidosPendentesResponse;
import br.com.fiap.PzBurguer.dto.responses.ResponsePedidoDto;
import br.com.fiap.PzBurguer.dto.result.Result;
import br.com.fiap.PzBurguer.exceptions.InvalidCancelException;
import br.com.fiap.PzBurguer.exceptions.OrderNotFoundException;
import br.com.fiap.PzBurguer.model.*;
import br.com.fiap.PzBurguer.model.enums.StatusPagamento;
import br.com.fiap.PzBurguer.model.enums.StatusPedido;
import br.com.fiap.PzBurguer.model.enums.UserRole;
import br.com.fiap.PzBurguer.producer.PagamentoProducer;
import br.com.fiap.PzBurguer.repository.ItemRepository;
import br.com.fiap.PzBurguer.repository.PedidoRepository;
import br.com.fiap.PzBurguer.service.utilities.MensageriaUtilities;
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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;


    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PagamentoProducer pagamentoProducer;

    @Transactional
    public Result<Pedido> criarPedido(PedidoDto dto, Usuario usuario) {
        List<ItemPedido> itens = dto.itens().stream().map(itemRequest -> {
            Item item = itemRepository.findById(itemRequest.itemId())
                    .orElseThrow(() -> new OrderNotFoundException("Pedido de id " + itemRequest.itemId() + " não encontrado"));
            return new ItemPedido(item, itemRequest.quantidade());
        }).toList();

        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedido.SOLICITADO);
        pedido.setStatusPagamento(StatusPagamento.PENDENTE);
        pedido.setEnderecoEntrega(dto.endereco());
        pedido.setObservacoes(dto.observacoes());
        pedido.setItens(itens);
        pedido.setUsuario(usuario);
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setValorTotal(Pedido.calcularValorTotal(itens));


        for (ItemPedido item : itens) {
            item.setPedido(pedido); // relacionamento reverso
        }

        pedidoRepository.save(pedido);

        return MensageriaUtilities.enviarMensagemComFallback(pagamentoProducer, pedido);
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

    public Optional<Pedido> cancelarPedido(Long idPedido, Usuario usuario) {
        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new OrderNotFoundException("Pedido de id " + idPedido + " não encontrado"));

        if (pedido.getStatus() != StatusPedido.SOLICITADO) {
            throw new InvalidCancelException("Pedido com status diferente de SOLICITADO não pode ser cancelado");
        }

        checaPedido(pedido.getUsuario().getId(), usuario);



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
                .map(ResponsePedidoDto::from);
    }

    private void checaPedido(Long idUserPedido, Usuario usuario) {
        if (usuario.getRole() == UserRole.RESTAURANTE){
            return;
        }
        if (!Objects.equals(idUserPedido, usuario.getId())){
            throw new InvalidCancelException("O usuário que quer cancelar o pedido deve ser o mesmo que realizou o mesmo");
        }
    }

}
