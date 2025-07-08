package br.com.fiap.PzBurguer.service;

import br.com.fiap.PzBurguer.dto.CadastroDto;
import br.com.fiap.PzBurguer.dto.result.Result;
import br.com.fiap.PzBurguer.model.Usuario;
import br.com.fiap.PzBurguer.model.enums.UserRole;
import br.com.fiap.PzBurguer.producer.UsuarioProducer;
import br.com.fiap.PzBurguer.repository.UsuarioRepository;
import br.com.fiap.PzBurguer.service.utilities.MensageriaUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UsuarioProducer usuarioProducer;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final Logger log = LoggerFactory.getLogger(getClass());

    public Result<Usuario> cadastrarUsuario(CadastroDto dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setTelefone(dto.telefone());
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        usuario.setRole(UserRole.CLIENTE);

        repository.save(usuario);

        return MensageriaUtilities.enviarMensagemComFallback(usuarioProducer, usuario);
    }


}

