package br.com.mscustomer.controller.response;

import br.com.mscustomer.enums.Gender;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CustomerResponse(String cpf,
                               String firstName,
                               String lastName,
                               Gender gender,
                               LocalDate birthDate,
                               String email,
                               AddressResponse address){
}
