package br.com.fiap.PzBurguer.service;

import br.com.fiap.PzBurguer.dto.CadastroDto;
import br.com.fiap.PzBurguer.dto.LoginDto;
import br.com.fiap.PzBurguer.exceptions.UnauthorizedException;
import br.com.fiap.PzBurguer.model.Usuario;
import br.com.fiap.PzBurguer.model.enums.UserRole;
import br.com.fiap.PzBurguer.producer.UsuarioProducer;
import br.com.fiap.PzBurguer.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UsuarioProducer usuarioProducer;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario cadastrarUsuario(CadastroDto dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setTelefone(dto.telefone());
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        usuario.setRole(UserRole.CLIENTE);
        repository.save(usuario);

        usuarioProducer.publishMessage(usuario);

        return usuario;
    }

}

