package br.com.fiap.PzBurguer.repository;

import br.com.fiap.PzBurguer.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
