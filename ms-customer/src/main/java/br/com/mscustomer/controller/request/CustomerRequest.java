package br.com.mscustomer.controller.request;

import lombok.Builder;

@Builder
public record CustomerRequest(String firstName,
                              String lastName,
                              String cpf,
                              String sex,
                              String birthdate,
                              String email,
                              String password,//receber criptografado
                              boolean  active){
}
