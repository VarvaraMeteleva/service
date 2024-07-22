package com.meteleva.microservice.repository;

import com.meteleva.microservice.entit.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRep extends JpaRepository<Customer, Integer> {
}
