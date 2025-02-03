package br.com.ms_catalog.exception

import br.com.ms_catalog.controller.response.ErrorField
import br.com.ms_catalog.controller.response.MessageResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApiExceptionHandler {

    @ExceptionHandler(ValidationException::class)
    fun handleValidationException(ex: ValidationException):ResponseEntity<List<ErrorField>>{
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.errors)
    }

    @ExceptionHandler(DatabaseException::class)
    fun handleDatabaseException(ex: DatabaseException):ResponseEntity<MessageResponse>{
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(MessageResponse(ex.message!!))
    }

}
