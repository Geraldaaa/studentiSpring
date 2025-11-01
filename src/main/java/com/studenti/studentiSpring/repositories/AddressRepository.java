package com.studenti.studentiSpring.repositories;

import com.studenti.studentiSpring.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
