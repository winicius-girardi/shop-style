package br.com.mscustomer.controller;

import br.com.mscustomer.controller.request.AddressRequest;
import br.com.mscustomer.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/v1/address")
    public ResponseEntity<?> createAddress(@RequestBody AddressRequest request){
        return addressService.createAddress(request);
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
