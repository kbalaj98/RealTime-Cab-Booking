package com.mycab.realTimeCabBooking.mapper;

import com.mycab.realTimeCabBooking.Entity.Customer;
import com.mycab.realTimeCabBooking.dto.CustomerDto;

public class CustomerMapper
{
    public  static CustomerDto getCustomerDto(Customer customer)
    {
        return new CustomerDto(customer.getId(),customer.getName(), customer.getEmailId(), customer.getMobileNo());
    }

    public  static Customer getCustomer(CustomerDto customerDto)
    {
        return  new Customer(customerDto.getId(),customerDto.getName(),customerDto.getEmailId(),customerDto.getMobileNo());
    }
}
