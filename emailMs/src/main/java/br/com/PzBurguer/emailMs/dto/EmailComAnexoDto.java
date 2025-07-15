package br.com.PzBurguer.emailMs.dto;

public record EmailComAnexoDto(
        String emailTo,
        String subject,
        String text,
        String nomeArquivo,
        byte[] anexo
) {}

