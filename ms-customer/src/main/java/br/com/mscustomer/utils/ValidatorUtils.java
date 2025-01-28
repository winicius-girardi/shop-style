package br.com.mscustomer.utils;

import br.com.mscustomer.controller.request.AddressRequest;
import br.com.mscustomer.controller.request.CustomerRequest;
import br.com.mscustomer.controller.response.ErrorField;
import br.com.mscustomer.enums.Gender;
import br.com.mscustomer.enums.State;

import java.util.ArrayList;
import java.util.List;

import static br.com.mscustomer.builder.Builder.createErrorField;

public class ValidatorUtils{

    public static List<ErrorField> customerValidation(CustomerRequest customer){

        List<ErrorField> errors = new ArrayList<>();

        if(!customer.cpf().matches("[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}"))
            errors.add(createErrorField("CPF","CPF INFORMADO NÃO ESTÁ NO FORMATO XXX.XXX.XXX-XX"));

        if(customer.firstName().replace(" ","").length()<3||customer.firstName().contains("[0-9]"))
            errors.add(createErrorField("FIRSTNAME","NOME INFORADO DEVE TER PELO MENOS 3 CARACTERES VÁLIDOS"));

        if(customer.lastName().replace(" ","").length()<3||customer.lastName().contains("[0-9]"))
            errors.add(createErrorField("LASTNAME","SOBRENOME INFORADO DEVE TER PELO MENOS 3 CARACTERES VÁLIDOS"));

        if(!customer.email().matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"))
            errors.add(createErrorField("EMAIL","EMAIL INFORADO DEVE TER FORMATO VÁLIDO"));

        if(customer.password().replace(" ","").length()<6)
            errors.add(createErrorField("PASSWORD","SENHA INFORMADA DEVE TER PELO MENOS 6 CARACTERES VÁLIDOS."));

        if(!customer.birthdate().matches("^\\d{4}-\\d{2}-\\d{2}$"))
            errors.add(createErrorField("BIRTHDATE","DATA DE NASCIMENTO PRECISA ESTAR NO FORMATO 'ANO-MES-DIA'."));

        return errors;

    }

    public  static List<ErrorField> addressValidation(AddressRequest address){

        List<ErrorField> errors = new ArrayList<>();

        if(!address.cep().matches("^\\d{5}-\\d{3}$"))
            errors.add(createErrorField("ADDRESS","CEP PRECISA SER NO FORMATO XXXXX-XXX"));

        if(address.city().isBlank())
            errors.add(createErrorField("CITY","CIDADE PRECISA SER INFORMADA"));

        if(address.state().isBlank())
            errors.add(createErrorField("STATE","ESTADO PRECISA SER INFOMARDO"));

        if(address.number().matches("^\\d$"))
            errors.add(createErrorField("NUMBER","CAMPO NUMERO SÓ PODE CONTER NUMEROS"));

        if(address.customerId().describeConstable().isEmpty())
            errors.add(createErrorField("CUSTOMERID","CUSTOMERID PRECISA SER INFORMADO"));

        if(address.district().isBlank())
            errors.add(createErrorField("DISTRICT","DISTRITO PRECISA SER INFORMADO"));

        if(address.street().isBlank())
            errors.add(createErrorField("STREET","RUA PRECISA SER INFORMADA"));

        try{
            State.valueOf(address.state());
        }catch(Exception e){
            errors.add(createErrorField("STATE","ESTADO INFORMADO É INVÁLIDO"));
        }


        return errors;
    }

}
