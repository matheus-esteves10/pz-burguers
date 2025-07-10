package br.com.fiap.PzBurguer.consumer;

import br.com.fiap.PzBurguer.dto.listener.PagamentoStatusDto;
import br.com.fiap.PzBurguer.service.PedidoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class PedidoStatusConsumer {

    private final PedidoService pedidoService;

    public PedidoStatusConsumer(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }


    @RabbitListener(queues = "${broker.queue.payment.response}")
    public void listenPagamentoStatus(@Payload PagamentoStatusDto dto) {

        pedidoService.atualizarStatusPagamento(dto.idPedido(), dto.status());
    }
}
