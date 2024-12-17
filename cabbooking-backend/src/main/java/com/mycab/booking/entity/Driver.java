package com.mycab.booking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Driver
{
    @Id
    @GeneratedValue
    long driver_id;

    @Column(nullable = false)
    String name;

    @Column(unique = true,nullable = false)
    String emailId;

    String gender;

    int age;

    @Column(nullable = false)
    long mobileNo;

    String carModel;

    @Column(nullable = false)
    int seatingCapacity;
}
