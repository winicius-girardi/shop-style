package br.com.mscustomer.controller.request;

import lombok.Builder;

@Builder
public record AddressCreateRequest(
        String state,
        String city,
        String district,
        String street,
        String number,
        String cep,
        String complement,
        Long customerId
        ){
}
