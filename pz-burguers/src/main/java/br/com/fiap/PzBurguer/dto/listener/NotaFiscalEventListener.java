package br.com.fiap.PzBurguer.dto.listener;

import br.com.fiap.PzBurguer.event.EnviarEmailComAnexoEvent;
import br.com.fiap.PzBurguer.producer.NotaFiscalProducer;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotaFiscalEventListener {

    private final NotaFiscalProducer notaFiscalProducer;

    public NotaFiscalEventListener(NotaFiscalProducer notaFiscalProducer) {
        this.notaFiscalProducer = notaFiscalProducer;
    }

    @EventListener
    public void onEnviarEmailComAnexoEvent(EnviarEmailComAnexoEvent event) {
        notaFiscalProducer.publishMessage(event.getEmailComAnexoDto());
    }
}
