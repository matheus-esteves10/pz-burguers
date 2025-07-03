package br.com.fiap.PzBurguer.dto;

import br.com.fiap.PzBurguer.model.TipoItem;

public record ItemDto(String nome,
                      Double precoUnitario,
                      TipoItem tipoItem
                      ) {
}
