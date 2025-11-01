package com.studenti.studentiSpring.services;

import com.studenti.studentiSpring.dto.AddressDTO;

import java.util.List;


public interface AddressService {

    AddressDTO addAddress(AddressDTO dto);
    AddressDTO updateAddress(AddressDTO dto);
    void deleteAddress(AddressDTO dto);
    List<AddressDTO> getAddresses();
    AddressDTO getAddressById(Long id);


}
