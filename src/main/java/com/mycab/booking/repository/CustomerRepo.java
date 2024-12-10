package com.mycab.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mycab.booking.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {

    //findBy + <fieldName> to query field
    Optional<Customer> findByemailId(String emailId);
}
