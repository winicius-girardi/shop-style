package br.com.mscustomer.service;

import br.com.mscustomer.controller.request.AddressCreateRequest;
import br.com.mscustomer.controller.request.AddressUpdateRequest;
import br.com.mscustomer.entity.Address;
import br.com.mscustomer.enums.State;
import br.com.mscustomer.exception.DatabaseException;
import br.com.mscustomer.exception.ValidationFieldsException;
import br.com.mscustomer.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static br.com.mscustomer.builder.Builder.createMessage;
import static br.com.mscustomer.builder.Builder.toAddressEntity;
import static br.com.mscustomer.utils.ConstantsUtils.MSG_ERROR;
import static br.com.mscustomer.utils.ValidatorUtils.*;

@Service
@AllArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public ResponseEntity<Void> createAddress(final AddressCreateRequest request) {
        var errorList=addressValidation(request);
        try{
            if(errorList.isEmpty()){
                addressRepository.save(toAddressEntity(request));
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }}catch (Exception e){
            throw new DatabaseException(createMessage(MSG_ERROR));
        }
        throw new ValidationFieldsException(errorList);

    }

    public ResponseEntity<Void> updateAddress(final Long id,final AddressUpdateRequest request){

        Address existingAddress=addressRepository.findById(id).orElseThrow(
                () -> new DatabaseException(createMessage(String.format("Não foi encontrado endereço para id '%d'",id))));

        if (request.state() != null && isValidState(request.state()))
            existingAddress.setState(State.valueOf(request.state()));

        if (request.city() != null)
            existingAddress.setCity(request.city());

        if (request.district() != null)
            existingAddress.setDistrict(request.district());

        if (request.street() != null)
            existingAddress.setStreet(request.street());

        if (request.number() != null&& isValidNumber(request.number()))
            existingAddress.setNumber(request.number());

        if (request.cep() != null && isValidCep(request.cep()))
            existingAddress.setCep(request.cep().replace("-",""));

        if (request.complement() != null)
            existingAddress.setComplement(request.complement());

        addressRepository.save(existingAddress);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity<Void> deleteAddress(final Long id) {
        addressRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
