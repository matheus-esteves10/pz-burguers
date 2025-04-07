package br.com.fiap.PzBurguer.controller;

import br.com.fiap.PzBurguer.model.Pedido;
import br.com.fiap.PzBurguer.repository.PedidoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final PedidoRepository repository;

    public PedidoController(PedidoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Pedido> index() {
        log.info("Retornando todos os pedidos ");
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Pedido getPedidoById(@PathVariable Long id) {
        log.info("Retornando pedido de ID " + id);
        return repository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Pedido> create(@RequestBody Pedido pedido) {
        log.info("Criando pedido de ID " + pedido.getId());
        Pedido created = repository.save(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando pedido de ID " + id);
        repository.delete(id);
    }

    @PutMapping("/{id}")
    public Pedido update(@PathVariable Long id, @RequestBody Pedido pedido) {
        log.info("Atualizando o pedido " + id);
        return repository.update(id, pedido);
    }
}
