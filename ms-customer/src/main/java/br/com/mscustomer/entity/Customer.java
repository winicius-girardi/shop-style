package br.com.mscustomer.entity;

import br.com.mscustomer.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CUSTOMER",schema = "MS_CUSTOMER_SCH")
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name="CPF",nullable=false,unique=true,length=11)
    private String cpf;

    @Column(name = "FIRST_NAME",nullable = false,length = 100)
    private String firstName;

    @Column(name = "LAST_NAME",nullable = false,length = 100)
    private String lastName;

    @Column(name="SEX",nullable = false,length = 10)
    private Gender gender;

    @Column(name="BIRTHDATE",nullable = false)
    private LocalDate birthdate;

}
