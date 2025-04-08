package br.com.fiap.PzBurguer.controller;

import br.com.fiap.PzBurguer.dto.CadastroDto;
import br.com.fiap.PzBurguer.dto.LoginDto;
import br.com.fiap.PzBurguer.model.Usuario;
import br.com.fiap.PzBurguer.repository.CadastroRepository;
import br.com.fiap.PzBurguer.service.UsuarioService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CadastroRepository repository;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> create(@RequestBody @Valid CadastroDto dto) {
        log.info("Cadastrando o usuário: " + dto.email());
        Usuario usuario = usuarioService.criarUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        log.info("Tentativa de login do usuário: " + loginDto.email());
        Usuario usuario = repository.findByEmail(loginDto.email());

        if (usuario == null || !usuario.getSenha().equals(loginDto.senha())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Email ou senha inválidos");
        }

        return ResponseEntity.ok("usuario autorizado");
    }
}

