package br.com.fiap.PzBurguer.config;

import br.com.fiap.PzBurguer.model.Item;
import br.com.fiap.PzBurguer.model.TipoItem;
import br.com.fiap.PzBurguer.repository.ItemRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DatabaseSeeder {

    @Autowired
    private ItemRepository itemRepository;

    @PostConstruct
    public void init(){
        var itens = List.of(
                Item.builder().nome("Itaquera").precoUnitario(35.0).tipoItem(TipoItem.LANCHE).build(),
                Item.builder().nome("Morumbi").precoUnitario(39.90).tipoItem(TipoItem.LANCHE).build(),
                Item.builder().nome("Santana").precoUnitario(39.90).tipoItem(TipoItem.LANCHE).build(),
                Item.builder().nome("Paulista").precoUnitario(39.90).tipoItem(TipoItem.LANCHE).build(),
                Item.builder().nome("Obelisco").precoUnitario(70.0).tipoItem(TipoItem.LANCHE).build(),
                Item.builder().nome("Catupa Burguer").precoUnitario(44.90).tipoItem(TipoItem.LANCHE).build(),
                Item.builder().nome("Paraíso").precoUnitario(52.90).tipoItem(TipoItem.LANCHE).build(),
                Item.builder().nome("Coca Cola").precoUnitario(7.00).tipoItem(TipoItem.BEBIDA).build(),
                Item.builder().nome("Guaraná").precoUnitario(7.00).tipoItem(TipoItem.BEBIDA).build(),
                Item.builder().nome("Água sem gás").precoUnitario(4.00).tipoItem(TipoItem.BEBIDA).build()


        );
        itemRepository.saveAll(itens);
    }



}
