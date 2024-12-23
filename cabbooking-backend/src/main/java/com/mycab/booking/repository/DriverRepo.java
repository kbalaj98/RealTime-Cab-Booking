package com.mycab.booking.repository;

import com.mycab.booking.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepo extends JpaRepository<Driver,Long>
{
    Optional<Driver>  findByemailId(String emailId);
}
