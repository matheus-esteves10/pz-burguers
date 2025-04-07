package br.com.fiap.PzBurguer.repository;

import br.com.fiap.PzBurguer.model.Pedido;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PedidoRepository {

    private final List<Pedido> pedidos = new ArrayList<>();

    public List<Pedido> findAll() {
        return pedidos;
    }

    public Pedido findById(Long id) {
        return pedidos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Pedido save(Pedido pedido) {
        pedidos.add(pedido);
        return pedido;
    }

    public void delete(Long id) {
        Pedido pedido = findById(id);
        pedidos.remove(pedido);
    }

    public Pedido update(Long id, Pedido pedidoAtualizado) {
        Pedido existente = findById(id);
        pedidos.remove(existente);
        pedidoAtualizado.setId(id);
        pedidos.add(pedidoAtualizado);
        return pedidoAtualizado;
    }
}
