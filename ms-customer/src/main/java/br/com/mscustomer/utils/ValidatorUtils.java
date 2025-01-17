package br.com.mscustomer.utils;

import br.com.mscustomer.controller.request.CustomerRequest;
import br.com.mscustomer.controller.response.ErrorField;

import java.util.ArrayList;
import java.util.List;

import static br.com.mscustomer.builder.Builder.createErrorField;

public class ValidatorUtils{

    public static List<ErrorField> customerValidation(CustomerRequest customer){

        List<ErrorField> errors = new ArrayList<>();

        if(customer.cpf().length()!=11||customer.cpf().matches("[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{3}")){
            errors.add(createErrorField("CPF","CPF INFORMADO NÃO ESTÁ NO FORMATO XXX.XXX.XXX-XX"));
        }
        if(customer.email().matches("")){

        }


        return null;
    }

}
