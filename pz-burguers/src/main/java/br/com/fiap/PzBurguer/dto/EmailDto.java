package br.com.fiap.PzBurguer.dto;

import lombok.Data;


@Data
public class EmailDto {

    private Long userId;
    private String emailTo;
    private String subject;
    private String text;
}
