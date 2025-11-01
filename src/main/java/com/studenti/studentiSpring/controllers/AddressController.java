package com.studenti.studentiSpring.controllers;

import com.studenti.studentiSpring.dto.AddressDTO;
import com.studenti.studentiSpring.services.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AddressDTO> create(@Validated @RequestBody AddressDTO dto) {
        return new ResponseEntity<>(service.addAddress(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AddressDTO>> getAll() {
        return ResponseEntity.ok(service.getAddresses());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> update(@PathVariable Long id, @Validated @RequestBody AddressDTO dto) {
        dto.setId(id);
        AddressDTO updated = service.updateAddress(dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        AddressDTO dto = new AddressDTO();
        dto.setId(id);
        service.deleteAddress(dto);
        return ResponseEntity.noContent().build();
    }



    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAddressById(id));
    }
}
