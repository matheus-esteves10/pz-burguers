package br.com.fiap.PzBurguer.controller;

import br.com.fiap.PzBurguer.dto.CadastroDto;
import br.com.fiap.PzBurguer.dto.LoginDto;
import br.com.fiap.PzBurguer.exceptions.UnauthorizedException;
import br.com.fiap.PzBurguer.model.Usuario;
import br.com.fiap.PzBurguer.repository.CadastroRepository;
import br.com.fiap.PzBurguer.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(
            summary = "Cadastrar usuário",
            responses = {
                    @ApiResponse(responseCode = "201"),
                    @ApiResponse(responseCode = "400"),
                    @ApiResponse(responseCode = "409")
            }
    )
    public ResponseEntity<Usuario> create(@RequestBody @Valid CadastroDto dto) {
        log.info("Cadastrando o usuário: " + dto.email());
        Usuario usuario = usuarioService.cadastrarUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }


    @PostMapping("/login")
    @Operation(
            summary = "Cadastrar usuário",
            responses = {
                    @ApiResponse(responseCode = "201"),
                    @ApiResponse(responseCode = "401")
            }
    )
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        try {
            usuarioService.login(loginDto);
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

        return ResponseEntity.ok("usuario autorizado");
    }
}

