package br.com.fiap.PzBurguer.service;

import br.com.fiap.PzBurguer.dto.ItemDto;
import br.com.fiap.PzBurguer.model.Item;
import br.com.fiap.PzBurguer.model.TipoItem;
import br.com.fiap.PzBurguer.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


import jakarta.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item cadastrarItem(ItemDto dto){
        Item item = new Item();
        item.setNome(dto.nome());
        item.setPrecoUnitario(dto.precoUnitario());
        item.setTipoItem(dto.tipoItem());

        return itemRepository.save(item);
    }



    public Page<ItemDto> listarItens(String nome, TipoItem tipoItem, Pageable pageable) {
        Specification<Item> spec = Specification.where(null); //Cria uma Specification vazia (sem filtro nenhum ainda).

        if (nome != null && !nome.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) -> //adiciona um filtro para buscar itens que contenham o nome informado
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%")
            );
        }

        if (tipoItem != null) {
            spec = spec.and((root, query, criteriaBuilder) -> //adiciona outro filtro para buscar apenas itens com aquele tipoItem
                    criteriaBuilder.equal(root.get("tipoItem"), tipoItem)
            );
        }

        return itemRepository.findAll(spec, pageable)
                .map(item -> new ItemDto(item.getNome(), item.getPrecoUnitario(), item.getTipoItem()));
    }
}





