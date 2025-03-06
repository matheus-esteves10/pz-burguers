package br.com.fiap.PzBurguer.model;


public class ItemPedido {
    private Long id;
    private Item item;
    private int quantidade;


    public ItemPedido() {}

    public ItemPedido(Long id, Item item, int quantidade) {
        this.id = id;
        this.item = item;
        this.quantidade = quantidade;
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}

