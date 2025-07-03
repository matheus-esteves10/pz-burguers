package br.com.fiap.PzBurguer.exceptions;

public class InvalidCancelException extends RuntimeException {
    public InvalidCancelException(String s) {
        super(s);
    }
}
