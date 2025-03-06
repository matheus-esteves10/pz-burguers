package br.com.fiap.PzBurguer.model;

import java.math.BigDecimal;
import java.util.List;

public class Pedido {
    private Long id;
    private BigDecimal valorTotal;
    private StatusPedido status;
    private String enderecoEntrega;
    private String observacoes;
    private Usuario usuario;
    private List<ItemPedido> itens;

    public Pedido(Long id, BigDecimal valorTotal, StatusPedido status, String enderecoEntrega, String observacoes, Usuario usuario, List<ItemPedido> itens) {
        this.id = id;
        this.valorTotal = valorTotal;
        this.status = status;
        this.enderecoEntrega = enderecoEntrega;
        this.observacoes = observacoes;
        this.usuario = usuario;
        this.itens = itens;
    }

    public Pedido() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public String getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(String enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }
}
