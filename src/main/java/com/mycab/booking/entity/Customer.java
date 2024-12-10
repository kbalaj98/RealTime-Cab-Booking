package com.mycab.realTimeCabBooking.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Customer
{
    @Id
    @GeneratedValue
    long id;

    @Column(nullable = false)
    String name;
    @Column(unique = true,nullable = false)
    String emailId;
    @Column(nullable = false)
    long mobileNo;
}
