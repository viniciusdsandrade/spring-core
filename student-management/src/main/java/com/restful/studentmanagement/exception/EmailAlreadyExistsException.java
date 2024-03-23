package com.restful.studentmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p>Exceção lançada quando já existe um endereço de e-mail cadastrado.
 * <p>
 * <p>Esta exceção é anotada com {@code @ResponseStatus(HttpStatus.BAD_REQUEST)}, indicando
 * <p>que resultará em uma resposta HTTP 400 (Bad Request) quando lançada.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailAlreadyExistsException extends RuntimeException {

    /**
     * Construtor da exceção EmailAlreadyExistsException.
     *
     * @param message Mensagem de erro que descreve a razão da exceção.
     */
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
