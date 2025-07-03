package br.com.fiap.PzBurguer.repository;

import br.com.fiap.PzBurguer.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CadastroRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);
}
