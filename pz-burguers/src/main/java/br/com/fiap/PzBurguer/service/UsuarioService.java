package br.com.fiap.PzBurguer.service;

import br.com.fiap.PzBurguer.dto.CadastroDto;
import br.com.fiap.PzBurguer.dto.LoginDto;
import br.com.fiap.PzBurguer.exceptions.UnauthorizedException;
import br.com.fiap.PzBurguer.model.Usuario;
import br.com.fiap.PzBurguer.producer.UsuarioProducer;
import br.com.fiap.PzBurguer.repository.CadastroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private CadastroRepository repository;

    @Autowired
    private UsuarioProducer usuarioProducer;

    public Usuario cadastrarUsuario(CadastroDto dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setTelefone(dto.telefone());
        usuario.setSenha(dto.senha());
        repository.save(usuario);

        usuarioProducer.publishMessage(usuario);

        return usuario;
    }

    public Usuario login (LoginDto dto){
        Usuario usuario = repository.findByEmail(dto.email());

        if (usuario == null || !usuario.getSenha().equals(dto.senha())) {
            throw new UnauthorizedException("Email ou senha inválidos");
        }

        return usuario;
    }
}

