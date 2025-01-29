package br.com.mscustomer.service;

import br.com.mscustomer.controller.request.CustomerRequest;
import br.com.mscustomer.controller.request.PasswordChangeRequest;
import br.com.mscustomer.exception.DatabaseException;
import br.com.mscustomer.exception.ValidationFieldsException;
import br.com.mscustomer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static br.com.mscustomer.builder.Builder.*;
import static br.com.mscustomer.utils.ConstantsUtils.MSG_ERROR;
import static br.com.mscustomer.utils.ValidatorUtils.customerValidation;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public ResponseEntity<?> updateCustomerData() {
        return null;
    }

    public ResponseEntity<Void> createCustomer(CustomerRequest request){
        var errorList = customerValidation(request);
        try{
        if(errorList.isEmpty()){
            customerRepository.save(toCustomerEntity(request));
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }}catch (Exception e){
            throw new DatabaseException(createMessage(MSG_ERROR));
        }
        throw new ValidationFieldsException(errorList);

    }

    public ResponseEntity<?> getCustomer() {
        return null;
    }

    public ResponseEntity<Void> updateCustomerPassword(final long id,final PasswordChangeRequest request) {
        var customer = customerRepository.findById(id).orElseThrow(
                () -> new DatabaseException(createMessage(String.format("Não foi encontrado cliente para id '%d'",id))));
        customer.setPassword(cipherPassword(request.newPassword()));
        customerRepository.save(customer);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
