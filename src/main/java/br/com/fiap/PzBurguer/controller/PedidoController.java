package br.com.fiap.PzBurguer.controller;

import br.com.fiap.PzBurguer.model.Pedido;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    List<Pedido> repository = new ArrayList<Pedido>();

    @GetMapping()
    public List<Pedido> getPedidos() {
        return repository;
    }

    @PostMapping
    public ResponseEntity<Pedido> create(@RequestBody Pedido pedido) {
        System.out.println("Criando novo pedido...");
        repository.add(pedido);
        return ResponseEntity.status(201).body(pedido);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Long id) {
        System.out.println("Buscando " + id);
        var category = repository.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();

        if(category.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(category.get());
    }
}

