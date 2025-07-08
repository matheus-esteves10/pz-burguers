package br.com.fiap.PzBurguer.repository;

import br.com.fiap.PzBurguer.model.Pedido;
import br.com.fiap.PzBurguer.model.enums.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface PedidoRepository extends JpaRepository<Pedido, Long>, JpaSpecificationExecutor<Pedido> {


    List<Pedido> findByStatus(StatusPedido status);

}
