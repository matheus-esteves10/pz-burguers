package br.com.fiap.PzBurguer.dto.responses;

import br.com.fiap.PzBurguer.model.Usuario;
import br.com.fiap.PzBurguer.model.enums.UserRole;

public record UsuarioResponseDto(Long id, String nome, String email, String telefone, UserRole role) {

    public static UsuarioResponseDto from(Usuario usuario) {
        return new UsuarioResponseDto(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getRole()
        );
    }
}
