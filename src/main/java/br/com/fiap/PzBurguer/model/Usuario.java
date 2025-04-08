package br.com.fiap.PzBurguer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank (message = "O nome deve ser preenchido")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nome;
    @Email
    private String email;
    @Pattern(regexp = "^[0-9]{11}$", message = "Telefone inválido")
    private String telefone;
    @Size (min = 5, max = 255, message = "A senha deve ter entre 5 e 255 caracteres")
    private String senha;

    //    private List<Pedido> pedidos; TODO: FUTURAMENTE DESCOMENTAR ESSA LINHA QUANDO A PARTE DE PEDIDOS POSSUIR UM DB

    public Usuario() {
    }

    public Usuario(Long id, String nome, String email, String telefone, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
    }

}
