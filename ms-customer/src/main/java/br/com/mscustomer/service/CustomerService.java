package br.com.mscustomer.service;

import br.com.mscustomer.controller.request.CustomerRequest;
import br.com.mscustomer.controller.request.PasswordChangeRequest;
import br.com.mscustomer.controller.response.CustomerResponse;
import br.com.mscustomer.exception.DatabaseException;
import br.com.mscustomer.exception.ValidationFieldsException;
import br.com.mscustomer.model.CustomerAddress;
import br.com.mscustomer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.mscustomer.builder.Builder.*;
import static br.com.mscustomer.utils.ConstantsUtils.MSG_ERROR;
import static br.com.mscustomer.utils.ValidatorUtils.customerValidation;
import static br.com.mscustomer.utils.ValidatorUtils.isValidPassword;

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

    public ResponseEntity<CustomerResponse> getCustomer(final long id){
        try{
        CustomerAddress customer= customerRepository.findCustomerDataById(id);

        return ResponseEntity.status(HttpStatus.FOUND).body(toCustomerResponse(customer));
        }catch (Exception e){
            throw new DatabaseException(createMessage(MSG_ERROR));
        }
    }

    public ResponseEntity<Void> updateCustomerPassword(final long id,final PasswordChangeRequest request) {
        if(!isValidPassword(request.newPassword()))
            throw new ValidationFieldsException(List.of(createErrorField("PASSWORD","SENHA DEVE TER PELO MENOS 6 CARACTERES VÁLIDOS.")));
        var customer = customerRepository.findById(id).orElseThrow(
                () -> new DatabaseException(createMessage(String.format("Não foi encontrado cliente para id '%d'",id))));
        customer.setPassword(cipherPassword(request.newPassword()));
        customerRepository.save(customer);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
