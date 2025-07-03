package br.com.fiap.PzBurguer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Entity
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O campo não pode ser em branco")
    private String nome;
    @Min(value = 0, message = "o valor deve ser maior que 0")
    private Double precoUnitario;
    @Enumerated(EnumType.STRING)
    private TipoItem tipoItem;


    public Item() {
    }

    public Item(Long id, String nome, Double precoUnitario, TipoItem tipoItem) {
        this.id = id;
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.tipoItem = tipoItem;
    }
}
