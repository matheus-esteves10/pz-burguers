package br.com.fiap.PzBurguer.service.utilities;

import br.com.fiap.PzBurguer.dto.result.Result;
import br.com.fiap.PzBurguer.exceptions.MensageriaException;
import br.com.fiap.PzBurguer.producer.IProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class MensageriaUtilities {

    private static final Logger log = LoggerFactory.getLogger(MensageriaUtilities.class);

    private MensageriaUtilities() {} // impede instanciamento

    public static <T> Result<T> enviarMensagemComFallback(IProducer<T> publisher, T mensagem) {
        Exception exception = null;
        try {
            publisher.publishMessage(mensagem);
        } catch (Exception e) {
            exception = new MensageriaException();
            log.warn("Falha ao enviar mensagem para RabbitMQ. Cadastro seguirá normalmente.");
        }
        return new Result<>(mensagem, exception);
    }
}
