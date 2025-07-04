package br.com.fiap.PzBurguer.controller;

import br.com.fiap.PzBurguer.dto.CadastroDto;
import br.com.fiap.PzBurguer.dto.responses.UsuarioResponseDto;
import br.com.fiap.PzBurguer.model.Usuario;
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
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @PostMapping()
    @Operation(
            summary = "Cadastrar usuário",
            responses = {
                    @ApiResponse(responseCode = "201"),
                    @ApiResponse(responseCode = "400"),
                    @ApiResponse(responseCode = "409")
            }
    )
    public ResponseEntity<UsuarioResponseDto> create(@RequestBody @Valid CadastroDto dto) {
        log.info("Cadastrando o usuário: " + dto.email());
        Usuario usuario = usuarioService.cadastrarUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioResponseDto.from(usuario));
    }

}

