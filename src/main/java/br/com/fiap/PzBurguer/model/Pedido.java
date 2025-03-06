package br.com.fiap.PzBurguer.model;

import java.math.BigDecimal;
import java.util.List;

public class Pedido {
    private Long id;
    private BigDecimal valorTotal;
    private StatusPedido status;
    private String enderecoEntrega;
    private String observacoes;
    private Usuario usuario; // Relacionamento direto com Usuario
    private List<ItemPedido> itens;
}
