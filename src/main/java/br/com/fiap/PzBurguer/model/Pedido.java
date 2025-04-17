package br.com.fiap.PzBurguer.model;

import br.com.fiap.PzBurguer.dto.PedidoDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity

public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valorTotal;
    @Enumerated(EnumType.STRING)
    private StatusPedido status;
    private String enderecoEntrega;
    private String observacoes;
    private LocalDateTime dataPedido;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens;

    public Pedido(Usuario usuario, StatusPedido status, String endereco, String observacoes, List<ItemPedido> itens) {
        this.usuario = usuario;
        this.status = status == null ? StatusPedido.SOLICITADO : status;
        this.enderecoEntrega = endereco;
        this.observacoes = observacoes;
        this.itens = itens;
        this.valorTotal = BigDecimal.valueOf(calcularValorTotal(itens));
        this.dataPedido = LocalDateTime.now();
    }


    public Pedido() {
    }

    private Double calcularValorTotal(List<ItemPedido> itens) {
        return itens.stream()
                .mapToDouble(itemPedido -> itemPedido.getItem().getPrecoUnitario() * itemPedido.getQuantidade())
                .sum();
    }
}
