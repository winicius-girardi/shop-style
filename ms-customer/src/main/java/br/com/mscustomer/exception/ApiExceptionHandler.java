package br.com.mscustomer.exception;

import br.com.mscustomer.exception.response.ErrorField;
import br.com.mscustomer.exception.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ValidationFieldsException.class)
    public ResponseEntity<List<ErrorField>> validationException(ValidationFieldsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getErrors());
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<MessageResponse> databaseException(DatabaseException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
