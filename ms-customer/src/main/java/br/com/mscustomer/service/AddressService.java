package br.com.mscustomer.service;

import br.com.mscustomer.controller.request.AddressRequest;
import br.com.mscustomer.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static br.com.mscustomer.builder.Builder.*;
import static br.com.mscustomer.utils.ValidatorUtils.addressValidation;

@Service
@AllArgsConstructor
public class AddressService {

    private static final String MSG_ERROR ="ERRO DURANTE PROCESSAMENTO";
    private final AddressRepository addressRepository;

    public ResponseEntity<?> createAddress(AddressRequest request) {
        var errorList=addressValidation(request);
        try{
            if(errorList.isEmpty()){
                addressRepository.save(toAddressEntity(request));
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }}catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createMessage(MSG_ERROR));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorList);

    }

    public ResponseEntity<?> updateAddress() {
        return null;
    }

    public ResponseEntity<?> deleteAddress() {
        return null;
    }
}
