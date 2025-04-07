package br.com.fiap.PzBurguer.model;


public class ItemPedido {
    private Long id;
    private Item item;



    public ItemPedido() {}

    public ItemPedido(Long id, Item item) {
        this.id = id;
        this.item = item;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

}

