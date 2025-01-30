package br.com.mscustomer.controller.response;

import br.com.mscustomer.enums.State;
import lombok.Builder;

@Builder
public record AddressResponse(State state,
                              String street,
                              String city,
                              String number,
                              String cep,
                              String complement){
}
