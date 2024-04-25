package com.restful03.TI323.handler;

import org.springframework.validation.FieldError;

import java.time.LocalDateTime;

/**
 * Esta classe é um registro que representa os detalhes de um erro.
 * Ela contém informações como o timestamp do erro, o campo onde ocorreu o erro,
 * os detalhes do erro e o tipo de erro.
 */
public record ErrorDetails(
        LocalDateTime timestamp, // A data e hora em que o erro ocorreu.
        String field, // O campo onde o erro ocorreu.
        String details, // Os detalhes do erro.
        String error) { // O tipo de erro.

    /**
     * Este é um construtor que cria um objeto ErrorDetails a partir de um objeto FieldError.
     * Ele extrai as informações necessárias do objeto FieldError e as usa para inicializar o objeto ErrorDetails.
     *
     * @param erro O objeto FieldError do qual as informações são extraídas.
     */
    public ErrorDetails(FieldError erro) {
        this(
                LocalDateTime.now(), // A data e hora atuais.
                erro.getField(), // O campo onde o erro ocorreu.
                erro.getDefaultMessage(), // A mensagem de erro padrão.
                erro.getCode() // O código de erro.
        );
    }
}
