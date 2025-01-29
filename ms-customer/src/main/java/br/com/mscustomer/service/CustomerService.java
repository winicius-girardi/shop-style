package br.com.mscustomer.service;

import br.com.mscustomer.controller.request.CustomerRequest;
import br.com.mscustomer.exception.DatabaseException;
import br.com.mscustomer.exception.ValidationFieldsException;
import br.com.mscustomer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static br.com.mscustomer.builder.Builder.createMessage;
import static br.com.mscustomer.builder.Builder.toCustomerEntity;
import static br.com.mscustomer.utils.StringConstants.MSG_ERROR;
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

    public ResponseEntity<?> updateCustomerPassword() {
        return null;
    }
}
