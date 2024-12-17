package com.mycab.booking.mapper;

import com.mycab.booking.entity.Customer;
import com.mycab.booking.dto.CustomerDto;

public class CustomerMapper
{
    public  static CustomerDto getCustomerDto(Customer customer)
    {
        return new CustomerDto(customer.getCustomerId(),customer.getName(), customer.getEmailId(),customer.getGender(), customer.getAge(), customer.getMobileNo(),customer.getCreatedTime());
    }

    public  static Customer getCustomer(CustomerDto customerDto)
    {
        return  new Customer(customerDto.getCustomerId(),customerDto.getName(),customerDto.getEmailId(),customerDto.getGender(),customerDto.getAge(),customerDto.getMobileNo(),customerDto.getCreatedTime());
    }
}
