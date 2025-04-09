package br.com.fiap.PzBurguer.model;

import br.com.fiap.PzBurguer.dto.PedidoDto;

import java.math.BigDecimal;
import java.util.List;

public class Pedido {
    private Long id;
    private BigDecimal valorTotal;
    private StatusPedido status;
    private String enderecoEntrega;
    private String observacoes;
    private Long idUsuario;
    private String endereco;
    private List<ItemPedido> itens;

    public Pedido(PedidoDto dto) {
        this.id = dto.idPedido();
        this.idUsuario = dto.idUsuario();
        this.status = dto.statusPedido() == null ? StatusPedido.SOLICITADO : dto.statusPedido();
        this.enderecoEntrega = dto.endereco();
        this.observacoes = dto.observacoes();
        this.itens = dto.itens();
        this.valorTotal = BigDecimal.valueOf(calcularValorTotal(dto.itens()));
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

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    private Double calcularValorTotal(List<ItemPedido> itens) {
        return itens.stream()
                .mapToDouble(itemPedido -> itemPedido.getItem().getPrecoUnitario() * itemPedido.getQuantidade())
                .sum();
    }
}
