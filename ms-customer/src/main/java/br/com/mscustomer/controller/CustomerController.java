package br.com.mscustomer.controller;

import br.com.mscustomer.controller.request.CustomerRequest;
import br.com.mscustomer.service.AddressService;
import br.com.mscustomer.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class CustomerController {

    private final AddressService addressService;
    private final CustomerService customerService;

    @PostMapping("/v1/login")
    public ResponseEntity<?> login(){
        return null;
    }

    @PostMapping("/v1/customers")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerRequest request){
        return customerService.createCustomer(request);
    }

    @GetMapping("/v1/customers/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable Long id){
        return customerService.getCustomer();
    }

    @PutMapping("/v1/customers/{id}")
    public ResponseEntity<?> updateCustomerData(@PathVariable Long id){
        return customerService.updateCustomerData();
    }

    @PutMapping("/v1/customers/{id}/password")
    public ResponseEntity<?> updateCustomerPassword(@PathVariable Long id){
        return customerService.updateCustomerPassword();
    }

    @PostMapping("/v1/address}")
    public ResponseEntity<?> createAddress(){
        return addressService.createAddress();
    }

    @PutMapping("/v1/address/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable Long id) {
        return addressService.updateAddress();
    }

    @DeleteMapping("/v1/address/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long id) {
        return addressService.deleteAddress();
    }






}
