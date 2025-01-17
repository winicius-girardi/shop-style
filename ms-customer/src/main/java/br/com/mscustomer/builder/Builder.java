package br.com.mscustomer.builder;

import br.com.mscustomer.controller.response.ErrorField;

public class Builder {


    public static ErrorField createErrorField(String field, String message){
        return ErrorField.builder()
                .field(field)
                .message(message)
                .build();
    }
}
