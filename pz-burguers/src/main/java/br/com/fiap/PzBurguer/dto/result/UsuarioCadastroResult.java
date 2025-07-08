package br.com.fiap.PzBurguer.dto.result;

import br.com.fiap.PzBurguer.model.Usuario;

public class UsuarioCadastroResult {
    private final Usuario usuario;
    private final Exception exception;

    public UsuarioCadastroResult(Usuario usuario, Exception exception) {
        this.usuario = usuario;
        this.exception = exception;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Exception getException() {
        return exception;
    }
}
