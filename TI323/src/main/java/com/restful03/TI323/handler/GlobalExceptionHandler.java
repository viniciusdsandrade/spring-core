package com.restful03.TI323.handler;

import com.restful03.TI323.exception.DuplicateEntryException;
import com.restful03.TI323.exception.ValidationException;
import com.restful03.TI323.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Esta classe é responsável por manipular globalmente as exceções lançadas pelos controladores REST.
 */
@RestControllerAdvice(basePackages = "com.restful03.TI323.controller")
public class GlobalExceptionHandler {

    /**
     * Este método manipula todas as exceções não especificadas que são lançadas globalmente.
     *
     * @param exception  A exceção global lançada.
     * @param webRequest O objeto WebRequest que contém detalhes sobre a solicitação da web.
     * @return Uma ResponseEntity que contém os detalhes do erro e o status HTTP 500 (Internal Server Error).
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception,
                                                              WebRequest webRequest) {

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(), // A data e hora atuais.
                exception.getMessage(), // A mensagem de erro da exceção.
                webRequest.getDescription(false), // A descrição da solicitação da web.
                "INTERNAL_SERVER_ERROR" // O tipo de erro.
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Este método manipula a exceção EntityNotFoundException que é lançada quando uma entidade não é encontrada.
     *
     * @param exception  A exceção EntityNotFoundException que foi lançada.
     * @param webRequest O objeto WebRequest que contém detalhes sobre a solicitação da web.
     * @return Uma ResponseEntity que contém os detalhes do erro e o status HTTP 404 (Not Found).
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<List<ErrorDetails>> handleResourceNotFoundException(EntityNotFoundException exception,
                                                                              WebRequest webRequest) {

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(), // A data e hora atuais.
                exception.getMessage(), // A mensagem de erro da exceção.
                webRequest.getDescription(false), // A descrição da solicitação da web.
                "RESOURCE_NOT_FOUND" // O tipo de erro.
        );

        return new ResponseEntity<>(List.of(errorDetails), HttpStatus.NOT_FOUND);
    }

    /**
     * Este método manipula a exceção DuplicateEntryException que é lançada quando uma entrada duplicada é encontrada.
     *
     * @param exception  A exceção DuplicateEntryException que foi lançada.
     * @param webRequest O objeto WebRequest que contém detalhes sobre a solicitação da web.
     * @return Uma ResponseEntity que contém os detalhes do erro e o status HTTP 409 (Conflict).
     */
    @ExceptionHandler(DuplicateEntryException.class)
    public ResponseEntity<List<ErrorDetails>> handleDuplicateEntryException(DuplicateEntryException exception,
                                                                            WebRequest webRequest) {
        // Cria um objeto ErrorDetails para encapsular os detalhes do erro.
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(), // A data e hora atuais.
                exception.getMessage(), // A mensagem de erro da exceção.
                webRequest.getDescription(false), // A descrição da solicitação da web.
                "DUPLICATE_ENTRY" // O tipo de erro.
        );

        // Retorna uma ResponseEntity contendo a lista de ErrorDetails e o status HTTP 409 (Conflict).
        return ResponseEntity.status(HttpStatus.CONFLICT).body(List.of(errorDetails));
    }

    /**
     * Este método manipula a exceção ValidationException que é lançada quando ocorre um erro de validação.
     *
     * @param exception  A exceção ValidationException que foi lançada.
     * @param webRequest O objeto WebRequest que contém detalhes sobre a solicitação da web.
     * @return Uma ResponseEntity que contém os detalhes do erro e o status HTTP 400 (Bad Request).
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<List<ErrorDetails>> handleValidacaoException(ValidationException exception,
                                                                       WebRequest webRequest) {

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(), // A data e hora atuais.
                exception.getMessage(), // A mensagem de erro da exceção.
                webRequest.getDescription(false), // A descrição da solicitação da web.
                "VALIDATION_ERROR" // O tipo de erro.
        );

        return new ResponseEntity<>(List.of(errorDetails), HttpStatus.BAD_REQUEST);
    }

    /**
     * Este método manipula a exceção MethodArgumentNotValidException que é lançada quando ocorre uma violação de integridade dos dados.
     *
     * @param exception A exceção MethodArgumentNotValidException que foi lançada.
     * @return Uma ResponseEntity que contém os detalhes do erro e o status HTTP 400 (Bad Request).
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorDetails>> handleDataIntegrityViolationException(MethodArgumentNotValidException exception) {
        // Obtém a lista de erros de campo da exceção.
        var errors = exception.getFieldErrors();

        // Mapeia os erros de campo para objetos ErrorDetails e os coleta em uma lista.
        List<ErrorDetails> errorDetailsList = errors.stream()
                .map(ErrorDetails::new)
                .collect(Collectors.toList());

        // Retorna uma ResponseEntity contendo a lista de ErrorDetails e o status HTTP 400 (Bad Request).
        return new ResponseEntity<>(errorDetailsList, HttpStatus.BAD_REQUEST);
    }
}