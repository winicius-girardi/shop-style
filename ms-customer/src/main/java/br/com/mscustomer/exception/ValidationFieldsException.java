package br.com.mscustomer.exception;

import br.com.mscustomer.exception.response.ErrorField;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ValidationFieldsException extends RuntimeException{
    private List<ErrorField> errors;
}
