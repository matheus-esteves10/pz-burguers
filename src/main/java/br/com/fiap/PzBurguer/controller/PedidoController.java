package br.com.fiap.PzBurguer.controller;

import br.com.fiap.PzBurguer.model.Pedido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private Logger log = LoggerFactory.getLogger(getClass());
    List<Pedido> repository = new ArrayList<Pedido>();

    @GetMapping()
    public List<Pedido> index() {
        return repository;
    }

    @GetMapping("/{id}")
    public Pedido getPedidoById(@PathVariable Long id) {
        return getPedido(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Pedido> create(@RequestBody Pedido pedido) {
        log.info("Criando pedido de ID " + pedido.getId());
        repository.add(pedido);
        return ResponseEntity.status(201).body(pedido);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando pedido de ID " + id);
        repository.remove(getPedido(id));
    }

    @PutMapping("/{id}")
    public Pedido update(@PathVariable Long id, @RequestBody Pedido pedido) {
        log.info("Atualizando o pedido " + id);

        repository.remove(getPedido(id));
        pedido.setId(id);
        repository.add(pedido);

        return pedido;
    }

    private Pedido getPedido(Long id) {
        return repository
                .stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                );
    }
}

