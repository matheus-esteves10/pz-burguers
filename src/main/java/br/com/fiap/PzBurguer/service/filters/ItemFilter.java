package br.com.fiap.PzBurguer.service.filters;

import br.com.fiap.PzBurguer.model.TipoItem;

public record ItemFilter(
        String nome,
        TipoItem tipoItem
) {}

