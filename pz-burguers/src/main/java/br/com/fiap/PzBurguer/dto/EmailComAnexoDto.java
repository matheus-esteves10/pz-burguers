package br.com.fiap.PzBurguer.dto;

import lombok.Data;

@Data
public class EmailComAnexoDto {
    private String emailTo;
    private String subject;
    private String text;
    private byte[] anexo;
    private String nomeArquivo;

}
