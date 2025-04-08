package br.com.fiap.PzBurguer.service;

import br.com.fiap.PzBurguer.dto.CadastroDto;
import br.com.fiap.PzBurguer.model.Usuario;
import br.com.fiap.PzBurguer.repository.CadastroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private CadastroRepository repository;

    public Usuario criarUsuario(CadastroDto dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setTelefone(dto.telefone());
        usuario.setSenha(dto.senha());
        return repository.save(usuario);
    }
}

