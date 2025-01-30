package br.com.mscustomer.builder;

import br.com.mscustomer.controller.request.AddressCreateRequest;
import br.com.mscustomer.controller.request.CustomerRequest;
import br.com.mscustomer.controller.response.AddressResponse;
import br.com.mscustomer.controller.response.CustomerResponse;
import br.com.mscustomer.entity.Address;
import br.com.mscustomer.entity.Customer;
import br.com.mscustomer.enums.Gender;
import br.com.mscustomer.enums.State;
import br.com.mscustomer.exception.response.ErrorField;
import br.com.mscustomer.exception.response.MessageResponse;
import br.com.mscustomer.model.CustomerAddress;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Builder {


    public static ErrorField createErrorField(String field, String message){
        return ErrorField.builder()
                .field(field)
                .message(message)
                .build();
    }

    public static Customer toCustomerEntity(CustomerRequest customerRequest){
        return Customer.builder()
                .cpf(customerRequest.cpf().replace(".","").replace("-",""))
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .gender(parseGender(customerRequest.sex()))
                .birthdate(formatBirthDate(customerRequest.birthdate()))
                .active(customerRequest.active())
                .email(customerRequest.email())
                .password(cipherPassword(customerRequest.password()))
                .build();
    }

    public static Address toAddressEntity(AddressCreateRequest addressRequest){
        return Address.builder()
                .city(addressRequest.city())
                .cep(addressRequest.cep().replace("-",""))
                .complement(addressRequest.complement())
                .district(addressRequest.district())
                .number(addressRequest.number())
                .street(addressRequest.street())
                .state(State.valueOf(addressRequest.state()))
                .customerId(addressRequest.customerId())
                .state(State.valueOf(addressRequest.state()))
                .build();
    }

    public  static CustomerResponse toCustomerResponse(CustomerAddress customer){
        return CustomerResponse.builder()
                .address(toAddressResponse(customer.city(),customer.cep(),
                        customer.complement(),customer.number(),customer.street(),customer.state())
                )
                .gender(Gender.valueOf(customer.gender()))
                .birthDate(customer.birthDate())
                .firstName(customer.firstName())
                .lastName(customer.lastName())
                .cpf(customer.cpf())
                .email(customer.email())
                .build();
    }

    public static AddressResponse toAddressResponse(String city, String cep, String complement, String number, String street, String state){
        return AddressResponse.builder()
                .city(city)
                .number(number)
                .street(street)
                .state(State.valueOf(state))
                .cep(cep)
                .complement(complement)
                .build();
    }

    public static MessageResponse createMessage(String message){
        return MessageResponse.builder()
                .message(message)
                .build();
    }

    public static Gender parseGender(String gender){
        try{
            return Gender.valueOf(gender);
        }catch(IllegalArgumentException e){
            return Gender.NOT_SPECIFIED;
        }
    }

    private static LocalDate formatBirthDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter) ;
    }

    public static String cipherPassword(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }


}
