package br.com.mscustomer.controller.request;

public record AddressUpdateRequest(String state,
                                   String city,
                                   String district,
                                   String street,
                                   String number,
                                   String cep,
                                   String complement){
}
