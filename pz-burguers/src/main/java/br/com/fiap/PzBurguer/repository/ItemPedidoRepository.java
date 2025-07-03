package br.com.fiap.PzBurguer.repository;

import br.com.fiap.PzBurguer.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

    List<ItemPedido> findByPedidoIdIn(List<Long> ids);
}
