package com.mycab.realTimeCabBooking.dto;

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
    long id;
    String name;
    String emailId;
    long mobileNo;
}
