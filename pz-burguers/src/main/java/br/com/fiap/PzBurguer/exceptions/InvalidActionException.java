package br.com.fiap.PzBurguer.exceptions;

public class InvalidActionException extends RuntimeException {
    public InvalidActionException(String s) {
        super(s);
    }
}
