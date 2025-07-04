package br.com.fiap.PzBurguer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record Credentials(
        @Email(message = "E-mail inválido")
        @NotBlank(message = "O e-mail é obrigatório")
        String username,

        @NotBlank(message = "A senha é obrigatória")
        String password
) {}
