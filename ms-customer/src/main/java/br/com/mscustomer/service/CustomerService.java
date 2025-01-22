package br.com.mscustomer.service;

import br.com.mscustomer.controller.request.CustomerRequest;
import br.com.mscustomer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static br.com.mscustomer.builder.Builder.createMessage;
import static br.com.mscustomer.builder.Builder.toCustomerEntity;
import static br.com.mscustomer.utils.ValidatorUtils.customerValidation;

@Service
@AllArgsConstructor
public class CustomerService {

    private static final String MSG_ERROR ="ERRO DURANTE PROCESSAMENTO";
    private final CustomerRepository customerRepository;

    public ResponseEntity<?> updateCustomerData() {
        return null;
    }

    public ResponseEntity<?> createCustomer(CustomerRequest request){
        var errorList = customerValidation(request);
        try{
        if(errorList.isEmpty()){
            customerRepository.save(toCustomerEntity(request));
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }}catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createMessage(MSG_ERROR));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorList);

    }

    public ResponseEntity<?> getCustomer() {
        return null;
    }

    public ResponseEntity<?> updateCustomerPassword() {
        return null;
    }
}
