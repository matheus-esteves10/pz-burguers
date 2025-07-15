package br.com.PzBurguer.emailMs.consumer;

import br.com.PzBurguer.emailMs.dto.EmailComAnexoDto;
import br.com.PzBurguer.emailMs.dto.EmailDto;
import br.com.PzBurguer.emailMs.model.EmailModel;
import br.com.PzBurguer.emailMs.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    private final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${broker.queue.email.name}")
    public void listenEmailQueue(@Payload EmailDto emailDto) {

        var emailModel = new EmailModel();

        BeanUtils.copyProperties(emailDto, emailModel);

        emailService.sendWelcome(emailModel);
    }

    @RabbitListener(queues = "${broker.queue.email.nota-fiscal}")
    public void listenNotaFiscalQueue(@Payload EmailComAnexoDto emailDto) {

        var emailModel = new EmailModel();

        BeanUtils.copyProperties(emailDto, emailModel);

        emailService.sendNotaFiscal(emailModel);
    }
}
