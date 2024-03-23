package com.restful.todomanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção lançada quando um recurso não é encontrado.
 * Esta exceção é anotada com @ResponseStatus(HttpStatus.NOT_FOUND),
 * indicando que, quando lançada, resultará em uma resposta HTTP 404 (Not Found).
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Construtor que recebe uma mensagem para a exceção.
     *
     * @param message A mensagem descritiva da exceção.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}