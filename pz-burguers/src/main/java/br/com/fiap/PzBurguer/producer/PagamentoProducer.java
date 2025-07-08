package br.com.fiap.PzBurguer.producer;

import br.com.fiap.PzBurguer.dto.PagamentoDto;
import br.com.fiap.PzBurguer.model.Pedido;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PagamentoProducer implements IProducer<Pedido>{

    private final RabbitTemplate rabbitTemplate;

    public PagamentoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.payment.name}")
    private String routingKey;


    @Override
    public void publishMessage(Pedido message) {
        var pagamentoDto = new PagamentoDto();

        pagamentoDto.setIdPedido(message.getId());
        pagamentoDto.setValorTotal(message.getValorTotal());

        rabbitTemplate.convertAndSend("", routingKey, pagamentoDto);
    }
}
