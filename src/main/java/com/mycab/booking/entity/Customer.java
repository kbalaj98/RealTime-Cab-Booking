package com.mycab.booking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Customer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long customerId;

    @Column(nullable = false)
    String name;

    @Column(unique = true,nullable = false)
    String emailId;

    String gender;

    int age;

    @Column(nullable = false)
    long mobileNo;

    long createdTime;
}
