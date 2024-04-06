package com.restful03.TI323.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção lançada quando uma entidade não é encontrada.
 * Esta exceção é geralmente lançada quando uma entidade específica não pode ser encontrada no sistema.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

    /**
     * Construtor para criar uma nova instância de EntityNotFoundException com uma mensagem de erro personalizada.
     *
     * @param message A mensagem de erro detalhando a entidade não encontrada.
     */
    public EntityNotFoundException(String message) {
        super(message);
    }
}
