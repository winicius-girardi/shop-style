package br.com.mscustomer.builder;

import br.com.mscustomer.controller.request.CustomerRequest;
import br.com.mscustomer.controller.response.ErrorField;
import br.com.mscustomer.controller.response.MessageResponse;
import br.com.mscustomer.entity.Customer;
import br.com.mscustomer.enums.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Builder {


    public static ErrorField createErrorField(String field, String message){
        return ErrorField.builder()
                .field(field)
                .message(message)
                .build();
    }

    public static Customer toCustomerEntity(CustomerRequest customerRequest){
        return Customer.builder()
                .cpf(customerRequest.cpf())
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .gender(createGender(customerRequest.sex()))
                .birthdate(formatBirthDate(customerRequest.birthdate()))
                .build();
    }

    private static LocalDateTime formatBirthDate(String date){
        LocalDate localDate = LocalDate.parse(date);
        LocalDateTime localDateTime = localDate.atStartOfDay();
        return localDateTime.atOffset(ZoneOffset.UTC).toLocalDateTime();
    }

    public static Gender createGender(String gender){
        try{
            return Gender.valueOf(gender);
        }catch(IllegalArgumentException e){
            return Gender.NOT_SPECIFIED;
        }
    }
    public static MessageResponse createMessage(String message){
        return MessageResponse.builder()
                .message(message)
                .build();
    }

}
