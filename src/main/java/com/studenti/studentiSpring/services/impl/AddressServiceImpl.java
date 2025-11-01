package com.studenti.studentiSpring.services.impl;

import com.studenti.studentiSpring.dto.AddressDTO;
import com.studenti.studentiSpring.models.Address;
import com.studenti.studentiSpring.repositories.AddressRepository;
import com.studenti.studentiSpring.services.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

        private final AddressRepository repository;

        public AddressServiceImpl(AddressRepository repository) {
            this.repository = repository;
        }

        @Override
        public AddressDTO addAddress(AddressDTO dto) {
            Address address = new Address();
            address.setStreet(dto.getStreet());
            address.setCity(dto.getCity());
            address.setState(dto.getState());
            Address saved = repository.save(address);
            return new AddressDTO(saved.getId(), saved.getStreet(), saved.getCity(), saved.getState());
        }

        @Override
        public AddressDTO updateAddress(AddressDTO dto) {
            Optional<Address> optionalAddress = repository.findById(dto.getId());
            if (optionalAddress.isEmpty()) {
                throw new RuntimeException("Address not found with id: " + dto.getId());
            }

            Address address = optionalAddress.get();
            address.setStreet(dto.getStreet());
            address.setCity(dto.getCity());
            address.setState(dto.getState());
            Address updated = repository.save(address);
            return new AddressDTO(updated.getId(), updated.getStreet(), updated.getCity(), updated.getState());
        }

        @Override
        public void deleteAddress(AddressDTO dto) {
            if (!repository.existsById(dto.getId())) {
                throw new RuntimeException("Address not found with id: " + dto.getId());
            }
            repository.deleteById(dto.getId());
        }

        @Override
        public List<AddressDTO> getAddresses() {
            return repository.findAll().stream()
                    .map(a -> new AddressDTO(a.getId(), a.getStreet(),a.getCity(), a.getState()))
                    .collect(Collectors.toList());
        }

        @Override
        public AddressDTO getAddressById(Long id) {
            Address address = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Address not found with id: " + id));
            return new AddressDTO(address.getId(),address.getStreet(),address.getCity(),address.getState());
        }



}
