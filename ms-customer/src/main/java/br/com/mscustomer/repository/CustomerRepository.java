package br.com.mscustomer.repository;

import br.com.mscustomer.entity.Customer;
import br.com.mscustomer.model.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

    @Query(value = """
    SELECT c.cpf, c.first_name, c.last_name, c.sex, c.birthdate, c.email,
           a.state, a.street, a.city, a.number, a.cep, a.complement
    FROM ms_customer_sch.customer c
    INNER JOIN ms_customer_sch.address a ON c.id = a.customer_id
    WHERE c.id = :id
    """, nativeQuery = true)
    CustomerAddress findCustomerDataById(@Param("id") Long id);

}
