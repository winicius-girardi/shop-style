package br.com.mscustomer.entity;

import br.com.mscustomer.enums.State;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ADDRESS",schema = "MS_CUSTOMER_SCH")
@Entity
public class Address {

    @Column(name = "STATE", nullable = false)
    private State state;
    @Column(name="CITY",nullable = false)
    private String  city;
    @Column(name = "DISTRICT",nullable = false)
    private String  district;
    @Column(name = "STREET",nullable = false)
    private String  street;
    @Column(name = "NUMBER", nullable = false)
    private String  number;
    @Column(name = "CEP",nullable = false)
    private String  cep;
    @Column(name="COMPLEMENT")
    private String  complement;

    @Id
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID",nullable = false)
    private Customer  customer;



}
