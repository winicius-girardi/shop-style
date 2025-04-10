package br.com.mscustomer.exception;


import br.com.mscustomer.exception.response.MessageResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DatabaseException extends RuntimeException {
    private MessageResponse details;
}
