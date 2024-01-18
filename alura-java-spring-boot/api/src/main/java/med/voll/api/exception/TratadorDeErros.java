package med.voll.api.exception;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

    private static final Logger logger = LoggerFactory.getLogger(TratadorDeErros.class);

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> tratarErro404(EntityNotFoundException ex) {
        logger.error("Entity not found: {}", ex.getMessage(), ex);
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> tratarErroRegraDeNegocio(MethodArgumentNotValidException ex) {
        logger.error("Validation error: {}", ex.getMessage(), ex);

        var erros = ex.getFieldErrors();
        var dadosErroValidacaoList = erros.stream().map(DadosErroValidacao::new).toList();
        return ResponseEntity.badRequest().body(dadosErroValidacaoList);
    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<Object> tratarErroRegraDeNegocio(ValidacaoException ex) {
        logger.error("Validation error: {}", ex.getMessage(), ex);

        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        logger.error("An unexpected error occurred: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
    }

    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}