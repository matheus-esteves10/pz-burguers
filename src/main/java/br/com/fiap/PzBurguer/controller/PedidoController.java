package br.com.fiap.PzBurguer.controller;

import br.com.fiap.PzBurguer.dto.PedidoDto;
import br.com.fiap.PzBurguer.model.Pedido;
import br.com.fiap.PzBurguer.repository.PedidoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
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
    @Operation(
            summary = "Exibir todos os pedidos",
            responses = {
                    @ApiResponse(responseCode = "200")
            }
    )
    public List<Pedido> index() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Exibir pedido por id",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "404")
            }
    )
    public Pedido getPedidoById(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Criar pedido",
            responses = {
                    @ApiResponse(responseCode = "201"),
                    @ApiResponse(responseCode = "400")
            }
    )
    public ResponseEntity<Pedido> create(@RequestBody @Valid PedidoDto dto) {
        log.info("Criando pedido de ID " + dto.idPedido());
        Pedido pedido = new Pedido(dto);
        Pedido created = repository.save(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Deletar pedido por id",
            responses = {
                    @ApiResponse(responseCode = "204"),
                    @ApiResponse(responseCode = "404")
            }
    )
    public void destroy(@PathVariable Long id) {
        log.info("Apagando pedido de ID " + id);
        repository.delete(id);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Editar pedido por id",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "404")
            }
    )
    public Pedido update(@PathVariable Long id, @RequestBody @Valid PedidoDto dto) {
        log.info("Atualizando o pedido " + id);
        Pedido pedido = new Pedido(dto);
        return repository.update(id, pedido);
    }
}
