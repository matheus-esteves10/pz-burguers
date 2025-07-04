package br.com.fiap.PzBurguer.service;

import br.com.fiap.PzBurguer.exceptions.UsuarioNotFoundException;
import br.com.fiap.PzBurguer.model.Usuario;
import br.com.fiap.PzBurguer.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UsuarioRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(
                () -> new UsuarioNotFoundException("Usuario não encontrado"));
    }

}