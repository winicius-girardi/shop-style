package br.com.mscustomer.entity;


import br.com.mscustomer.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class Customer {

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="CPF",nullable=false,unique=true,length=11)
    private String cpf;

    @Column(name = "FIRST_NAME",nullable = false,length = 100)
    private String firstName;

    @Column(name = "LAST_NAME",nullable = false,length = 100)
    private String lastName;

    @Column(name="SEX",nullable = false,length = 10)
    private Gender gender;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="BIRTHDATE",nullable = false)
    private LocalDateTime birthdate;

}
