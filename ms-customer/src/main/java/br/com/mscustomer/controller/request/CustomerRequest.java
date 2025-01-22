package br.com.mscustomer.controller.request;

import lombok.Builder;

@Builder
public record CustomerRequest(String firstName,
                              String lastName,
                              String cpf,
                              String sex,
                              String birthdate,
                              String email,
                              String password,//ñ vem cifrado, criptografar posteriormente
                              boolean  active){
}
