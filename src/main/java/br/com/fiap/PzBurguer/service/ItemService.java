package br.com.fiap.PzBurguer.service;

import br.com.fiap.PzBurguer.dto.ItemDto;
import br.com.fiap.PzBurguer.model.Item;
import br.com.fiap.PzBurguer.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    public Item cadastrarItem(ItemDto dto){
        Item item = new Item();
        item.setNome(dto.nome());
        item.setPrecoUnitario(dto.precoUnitario());

        return repository.save(item);
    }

}
