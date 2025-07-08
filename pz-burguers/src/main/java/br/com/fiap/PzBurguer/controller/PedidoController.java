package br.com.fiap.PzBurguer.controller;

import br.com.fiap.PzBurguer.dto.PedidoDto;
import br.com.fiap.PzBurguer.dto.responses.PedidosPendentesResponse;
import br.com.fiap.PzBurguer.dto.responses.ResponsePedidoDto;
import br.com.fiap.PzBurguer.dto.result.Result;
import br.com.fiap.PzBurguer.model.Pedido;
import br.com.fiap.PzBurguer.model.Usuario;
import br.com.fiap.PzBurguer.repository.PedidoRepository;
import br.com.fiap.PzBurguer.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final Logger log = LoggerFactory.getLogger(getClass());

   @Autowired
   private PedidoService pedidoService;

   @Autowired
   private PedidoRepository pedidoRepository;

   @PostMapping
   @Transactional
   @Operation(summary = "Cadastrar pedido", responses = {@ApiResponse(responseCode = "201"),
           @ApiResponse(responseCode = "404")
   })
   public ResponseEntity<ResponsePedidoDto> create(@RequestBody @Valid PedidoDto dto) {
       Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       Result<Pedido> result = pedidoService.criarPedido(dto, usuario);
       log.info("Cadastrando o pedido");

       return ResponseEntity.status(HttpStatus.CREATED).body(ResponsePedidoDto.from(result.objeto()));
   }

    @GetMapping("/restaurante/pendentes")
    @Operation(summary = "Listar pedidos pendentes", responses = {@ApiResponse(responseCode = "200")})
    public ResponseEntity<List<PedidosPendentesResponse>> listarPedidosPendentes() {
        List<PedidosPendentesResponse> pedidosPendentes = pedidoService.listarPedidosPendentes();
        return ResponseEntity.ok(pedidosPendentes);
    }

    @DeleteMapping("/cancelar/{idPedido}")
    @Operation(summary = "Cancelar pedido", responses = {@ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "404"),
            @ApiResponse(responseCode = "400")
    })
    public ResponseEntity<Pedido> cancelarPedido(@PathVariable Long idPedido) {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Pedido> pedidoCancelado = pedidoService.cancelarPedido(idPedido, usuario);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/restaurante/{id}")
    @Operation(summary = "Listar pedido", responses = {@ApiResponse(responseCode = "200"),
    @ApiResponse(responseCode = "404")
    })
    public ResponseEntity<ResponsePedidoDto> pedidoById(@PathVariable Long id) {
        try {
            Pedido pedido = pedidoRepository.findById(id).get();
            return ResponseEntity.ok(ResponsePedidoDto.from(pedido));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/restaurante/periodo")
    public Page<ResponsePedidoDto> listarPedidosPorPeriodo(
            @RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,
            Pageable pageable) {
        return pedidoService.listarPedidosPorPeriodo(dataInicio, dataFim, pageable);
    }

}


