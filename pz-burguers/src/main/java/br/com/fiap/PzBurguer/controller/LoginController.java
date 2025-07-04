package br.com.fiap.PzBurguer.controller;

import br.com.fiap.PzBurguer.dto.Credentials;
import br.com.fiap.PzBurguer.dto.Token;
import br.com.fiap.PzBurguer.model.Usuario;
import br.com.fiap.PzBurguer.service.AuthService;
import br.com.fiap.PzBurguer.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Operation(summary = "Autenticar usuário", description = "Retorna um token JWT válido se as credenciais estiverem corretas",
              responses = {
        @ApiResponse(responseCode = "200", description = "Token JWT", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "401", description = "Credenciais inválidas", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "404", description = "Username não encontrado", content = @Content(mediaType = "application/json"))
    })
    @PostMapping
    public Token login(@RequestBody @Valid Credentials credentials) {
        Usuario user = (Usuario) authService.loadUserByUsername(credentials.username());

        if (!passwordEncoder.matches(credentials.password(), user.getSenha())) {
            throw new BadCredentialsException("Senha incorreta");
        }

        String jwt = tokenService.createToken(user);

        return new Token(jwt);
    }

}
