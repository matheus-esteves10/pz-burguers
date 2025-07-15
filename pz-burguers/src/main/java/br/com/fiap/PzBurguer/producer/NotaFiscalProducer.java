package br.com.fiap.PzBurguer.producer;

import br.com.fiap.PzBurguer.dto.EmailComAnexoDto;
import br.com.fiap.PzBurguer.model.Pedido;
import br.com.fiap.PzBurguer.service.NotaFiscalService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class NotaFiscalProducer implements IProducer<EmailComAnexoDto> {

    private final RabbitTemplate rabbitTemplate;


    @Value("${broker.queue.email.nota-fiscal}")
    private String routingKey;

    public NotaFiscalProducer(RabbitTemplate rabbitTemplate, NotaFiscalService notaFiscalService) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publishMessage(EmailComAnexoDto emailComAnexoDto) {
        rabbitTemplate.convertAndSend("", routingKey, emailComAnexoDto);
    }

}
