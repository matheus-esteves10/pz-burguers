package br.com.PzBurguer.emailMs.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailModel {

    private Long userId;

    private String emailFrom;

    private String emailTo;

    private String subject;

    private String text;

    private String nomeArquivo;

    private byte[] anexo;
}
