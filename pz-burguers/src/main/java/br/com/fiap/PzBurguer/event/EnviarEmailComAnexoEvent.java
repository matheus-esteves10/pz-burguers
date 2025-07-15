package br.com.fiap.PzBurguer.event;

import br.com.fiap.PzBurguer.dto.EmailComAnexoDto;

public class EnviarEmailComAnexoEvent {
    private final EmailComAnexoDto emailComAnexoDto;

    public EnviarEmailComAnexoEvent(EmailComAnexoDto emailComAnexoDto) {
        this.emailComAnexoDto = emailComAnexoDto;
    }

    public EmailComAnexoDto getEmailComAnexoDto() {
        return emailComAnexoDto;
    }
}
