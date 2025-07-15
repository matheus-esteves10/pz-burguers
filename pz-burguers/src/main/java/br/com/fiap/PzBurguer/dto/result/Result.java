package br.com.fiap.PzBurguer.dto.result;

public record Result<T>(T objeto, Exception exception) {

}
