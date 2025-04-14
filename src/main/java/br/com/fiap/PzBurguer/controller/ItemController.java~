package br.com.fiap.PzBurguer.controller;

import br.com.fiap.PzBurguer.dto.ItemDto;
import br.com.fiap.PzBurguer.model.Item;

import br.com.fiap.PzBurguer.repository.ItemRepository;
import br.com.fiap.PzBurguer.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService service;

    @Autowired
    private ItemRepository repository;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @PostMapping()
    @CacheEvict(value = "categories", allEntries = true)
    @Operation(
            summary = "Cadastrar item",
            responses = {
                    @ApiResponse(responseCode = "201"),
                    @ApiResponse(responseCode = "404")
            }
    )
    public ResponseEntity<Item> create(@RequestBody @Valid ItemDto dto) {
        log.info("Cadastrando o item: " + dto.nome());
        Item item = service.cadastrarItem(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    @GetMapping
    @Cacheable
    @Operation(
            summary = "Cadastrar usuário",
            responses = {
                    @ApiResponse(responseCode = "200")
            }
    )
    public List<Item> getItens(){
        return repository.findAll();
    }

}
