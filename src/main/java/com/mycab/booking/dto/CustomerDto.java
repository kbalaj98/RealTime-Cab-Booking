package com.mycab.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerDto
{
    long customerId;
    String name;
    String emailId;
    String gender;
    int age;
    long mobileNo;
    long createdTime;
}
