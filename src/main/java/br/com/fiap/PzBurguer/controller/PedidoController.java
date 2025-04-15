package br.com.fiap.PzBurguer.controller;

import br.com.fiap.PzBurguer.dto.PedidoCancelamentoDto;
import br.com.fiap.PzBurguer.dto.PedidoDto;
import br.com.fiap.PzBurguer.dto.responses.PedidosPendentesResponse;
import br.com.fiap.PzBurguer.dto.responses.ResponsePedidoDto;
import br.com.fiap.PzBurguer.model.ItemPedido;
import br.com.fiap.PzBurguer.model.Pedido;
import br.com.fiap.PzBurguer.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final Logger log = LoggerFactory.getLogger(getClass());

   @Autowired
   private PedidoService pedidoService;

   @PostMapping
   @Transactional
   @Operation(summary = "Cadastrar pedido", responses = {@ApiResponse(responseCode = "201")})
   public ResponseEntity<ResponsePedidoDto> create(@RequestBody @Valid PedidoDto dto) {
       log.info("Cadastrando o pedido: " + dto.usuario().getId());
       Pedido pedido = pedidoService.criarPedido(dto);
       return ResponseEntity.status(HttpStatus.CREATED).body(new ResponsePedidoDto(pedido));
   }

    @GetMapping("/pendentes")
    @Operation(summary = "Listar pedidos pendentes", responses = {@ApiResponse(responseCode = "200")})
    public ResponseEntity<List<PedidosPendentesResponse>> listarPedidosPendentes() {
        List<PedidosPendentesResponse> pedidosPendentes = pedidoService.listarPedidosPendentes();
        return ResponseEntity.ok(pedidosPendentes);
    }

    @DeleteMapping("/cancelar")
    public ResponseEntity<Pedido> cancelarPedido(@RequestBody PedidoCancelamentoDto dto) {
        Optional<Pedido> pedidoCancelado = pedidoService.alterarPedido(dto);
        return ResponseEntity.noContent().build();
    }

}
