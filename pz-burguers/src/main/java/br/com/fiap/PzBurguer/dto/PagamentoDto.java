package br.com.fiap.PzBurguer.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PagamentoDto {
    private BigDecimal valorTotal;
    private Long idUser;
}
