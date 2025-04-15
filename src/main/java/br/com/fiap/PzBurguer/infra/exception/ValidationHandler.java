package br.com.fiap.PzBurguer.infra.exception;

import br.com.fiap.PzBurguer.exceptions.InvalidCancelException;
import br.com.fiap.PzBurguer.exceptions.OrderNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ValidationHandler {

    record ValidationError(String field, String message){
        public ValidationError(FieldError fieldError){
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ValidationError> handle(MethodArgumentNotValidException e){
        return e.getFieldErrors()
                .stream()
                .map(ValidationError::new)//reference method
                .toList();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> handleDataIntegrityViolation(DataIntegrityViolationException e) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Violação de integridade de dados");
        error.put("message", e.getLocalizedMessage());
        return error;
    }

    @ExceptionHandler(InvalidCancelException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleInvalidCancel(InvalidCancelException e) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Pedido não pode ser cancelado");
        error.put("message", e.getMessage());
        return error;
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleOrderNotFound(OrderNotFoundException e) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Pedido nao encontrado");
        error.put("message", e.getMessage());
        return error;
    }

}
