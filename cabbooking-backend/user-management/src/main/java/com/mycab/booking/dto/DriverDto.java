package com.mycab.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DriverDto
{
    long driver_id;
    String name;
    String emailId;
    String gender;
    int age;
    long mobileNo;
}
