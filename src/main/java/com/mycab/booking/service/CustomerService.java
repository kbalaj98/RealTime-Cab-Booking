package com.mycab.booking.service;

import com.mycab.booking.dto.CustomerDto;

import java.util.List;

public interface CustomerService
{
    CustomerDto addCustomer(CustomerDto customerDto);

    CustomerDto getCustomer(long cusId);

    List<CustomerDto> getCustomer(int start,int limit);

    boolean isMailIdPresent(String mailId);
}
