package com.mycab.booking.service;

import com.mycab.booking.dto.CustomerDto;

import java.util.List;

public interface CustomerService
{
    CustomerDto addCustomer(CustomerDto customerDto);

    CustomerDto getCustomers(long cusId);

    List<CustomerDto> getCustomers(int start, int limit);

    boolean isMailIdPresent(String mailId);

    CustomerDto updateCustomer(long cusId, CustomerDto customerDto);

    void deleteCustomer(long cusId);
}
