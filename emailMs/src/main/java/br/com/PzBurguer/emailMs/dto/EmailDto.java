package br.com.PzBurguer.emailMs.dto;

public record EmailDto(Long userId,
                       String emailTo,
                       String subject,
                       String text) {
}
