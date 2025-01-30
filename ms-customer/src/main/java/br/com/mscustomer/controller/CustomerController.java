package br.com.mscustomer.controller;

import br.com.mscustomer.controller.request.CustomerRequest;
import br.com.mscustomer.controller.request.CustomerUpdateRequest;
import br.com.mscustomer.controller.request.PasswordChangeRequest;
import br.com.mscustomer.controller.response.CustomerResponse;
import br.com.mscustomer.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/v1/login")
    public ResponseEntity<?> login(){
        return null;
    }

    @PostMapping("/v1/customer")
    public ResponseEntity<Void> createCustomer(@RequestBody final CustomerRequest request){
        return customerService.createCustomer(request);
    }

    @GetMapping("/v1/customers/{id}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable final Long id){
        return customerService.getCustomer(id);
    }

    @PutMapping("/v1/customers/{id}")
    public ResponseEntity<Void> updateCustomer(@PathVariable Long id, @RequestBody final CustomerUpdateRequest request){
        return customerService.updateCustomerData(request,id);
    }

    @PutMapping("/v1/customers/{id}/password")
    public ResponseEntity<Void> updateCustomerPassword(@PathVariable final Long id, final @RequestBody PasswordChangeRequest request){
        return customerService.updateCustomerPassword(id,request);
    }



}
