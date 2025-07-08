package br.com.fiap.PzBurguer.producer;

import br.com.fiap.PzBurguer.dto.responses.EmailDto;
import br.com.fiap.PzBurguer.model.Usuario;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UsuarioProducer implements IProducer<Usuario> {

    private final RabbitTemplate rabbitTemplate;


    public UsuarioProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.name}")
    private String routingKey; //nome da fila deve ser o igual a routing key quando utilizamos o exchange padrão ("")

    @Override
    public void publishMessage(Usuario usuario) {
        var emailDto = new EmailDto();
        emailDto.setUserId(usuario.getId());
        emailDto.setEmailTo(usuario.getEmail());
        emailDto.setSubject("Cadastro realizado com sucesso!");
        emailDto.setText("Parabéns " + usuario.getNome() + "! Seu cadastro foi realizado com sucesso!");

        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }



}
