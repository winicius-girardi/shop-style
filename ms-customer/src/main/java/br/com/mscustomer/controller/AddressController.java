package br.com.mscustomer.controller;

import br.com.mscustomer.controller.request.AddressCreateRequest;
import br.com.mscustomer.controller.request.AddressUpdateRequest;
import br.com.mscustomer.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/v1/address")
    public ResponseEntity<Void> createAddress(@RequestBody final AddressCreateRequest request){
        return addressService.createAddress(request);
    }

    @PutMapping("/v1/address/{id}")
    public ResponseEntity<Void> updateAddress(@PathVariable final Long id,@RequestBody final AddressUpdateRequest request) {
        return addressService.updateAddress(id,request);
    }

    @DeleteMapping("/v1/address/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable final Long id) {
        return addressService.deleteAddress(id);
    }

}
