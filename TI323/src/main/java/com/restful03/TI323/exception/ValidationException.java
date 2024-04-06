package com.restful03.TI323.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção lançada quando ocorre uma validação de entrada inválida.
 * Esta exceção é geralmente lançada quando os dados de entrada não atendem aos critérios de validação esperados.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException {

    /**
     * Construtor para criar uma nova instância de ValidacaoException com uma mensagem de erro personalizada.
     *
     * @param message A mensagem de erro detalhando a natureza do problema.
     */
    public ValidationException(String message) {
        super(message);
    }
}
