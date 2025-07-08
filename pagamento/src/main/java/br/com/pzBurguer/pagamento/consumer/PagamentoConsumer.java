package br.com.pzBurguer.pagamento.consumer;

import br.com.pzBurguer.pagamento.PagamentoService;
import br.com.pzBurguer.pagamento.dto.PagamentoDto;
import br.com.pzBurguer.pagamento.model.Pagamento;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class PagamentoConsumer {

    private final PagamentoService pagamentoService;

    public PagamentoConsumer(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @RabbitListener(queues = "${broker.queue.payment.name}")
    public void listenPaymentQueue(@Payload PagamentoDto pagamentoDto) {

        var pagamentoModel = new Pagamento();

        BeanUtils.copyProperties(pagamentoDto, pagamentoModel);

        pagamentoService.sendPayment(pagamentoModel);
    }
}
