package br.com.fiap.PzBurguer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CadastroDto(
        @NotBlank @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
        String nome,
        @Email
        String email,
        @Pattern(regexp = "^[0-9]{11}$", message = "Telefone inválido")
        String telefone,
        @Size (min = 5, max = 255, message = "A senha deve ter entre 5 e 255 caracteres")
        String senha
) {}

