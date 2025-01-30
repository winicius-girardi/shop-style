package br.com.mscustomer.model;

import java.time.LocalDate;

public record CustomerAddress(String cpf,
                              String firstName,
                              String lastName,
                              String gender,
                              LocalDate birthDate,
                              String email,
                              String state,
                              String street,
                              String city,
                              String number,
                              String cep,
                              String complement){
}
