package br.com.mscustomer.utils;

import br.com.mscustomer.controller.request.CustomerRequest;
import br.com.mscustomer.controller.response.ErrorField;
import br.com.mscustomer.enums.Gender;

import java.util.ArrayList;
import java.util.List;

import static br.com.mscustomer.builder.Builder.createErrorField;

public class ValidatorUtils{

    public static List<ErrorField> customerValidation(CustomerRequest customer){

        List<ErrorField> errors = new ArrayList<>();

        if(customer.cpf().length()!=11||customer.cpf().matches("[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{3}"))
            errors.add(createErrorField("CPF","CPF INFORMADO NÃO ESTÁ NO FORMATO XXX.XXX.XXX-XX"));

        if(customer.firstName().replace(" ","").length()<3||customer.firstName().contains("[0-9]"))
            errors.add(createErrorField("FIRSTNAME","NOME INFORADO DEVE TER PELO MENOS 3 CARACTERES VÁLIDOS"));

        if(customer.lastName().replace(" ","").length()<3||customer.lastName().contains("[0-9]"))
            errors.add(createErrorField("LASTNAME","SOBRENOME INFORADO DEVE TER PELO MENOS 3 CARACTERES VÁLIDOS"));

        if(!customer.email().matches("^[\\\\w._%+-]+@[\\\\w.-]+\\\\.[a-zA-Z]{2,}$"))
            errors.add(createErrorField("EMAIL","EMAIL INFORADO DEVE TER FORMATO VÁLIDO"));

        if(customer.password().replace(" ","").length()<6)
            errors.add(createErrorField("PASSWORD","SENHA INFORMADA DEVE TER PELO MENOS 6 CARACTERES VÁLIDOS."));

        if(!customer.birthdate().matches("^\\d{4}-\\d{2}-\\d{2}$"))
            errors.add(createErrorField("BIRTHDATE","DATA DE NASCIMENTO PRECISA ESTAR NO FORMATO 'ANO-MES-DIA'."));



            return errors;

    }

}
