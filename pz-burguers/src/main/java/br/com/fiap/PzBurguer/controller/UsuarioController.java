package br.com.fiap.PzBurguer.controller;

import br.com.fiap.PzBurguer.dto.CadastroDto;
import br.com.fiap.PzBurguer.dto.result.UsuarioCadastroResult;
import br.com.fiap.PzBurguer.dto.responses.UsuarioResponseDto;
import br.com.fiap.PzBurguer.exceptions.MensageriaException;
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
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @PostMapping()
    public ResponseEntity<UsuarioResponseDto> create(@RequestBody @Valid CadastroDto dto) throws MensageriaException {
        log.info("Cadastrando o usuário: " + dto.email());

        UsuarioCadastroResult result = usuarioService.cadastrarUsuario(dto);


        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioResponseDto.from(result.getUsuario()));
    }

}

