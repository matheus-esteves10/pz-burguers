package br.com.fiap.PzBurguer.exceptions;

public class UnauthorizedException extends RuntimeException{

    public UnauthorizedException(String message) {
        super(message);
    }
}
