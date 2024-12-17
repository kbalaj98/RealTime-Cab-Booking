package com.mycab.booking.service;

import com.mycab.booking.dto.CustomerDto;
import com.mycab.booking.entity.Customer;
import com.mycab.booking.exception.InvalidInputException;
import com.mycab.booking.exception.ResourceAlreadyExistsException;
import com.mycab.booking.exception.ResourceNotFoundException;
import com.mycab.booking.mapper.CustomerMapper;
import com.mycab.booking.repository.CustomerRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService
{
    //constructor injection applied here
    CustomerRepo customerRepo;

    @Override
    public CustomerDto addCustomer(CustomerDto customerDto)
    {
        if(isMailIdPresent(customerDto.getEmailId()))
        {
            throw new ResourceAlreadyExistsException(customerDto.getEmailId()+" email id already exist");
        }

        customerDto.setCreatedTime(System.currentTimeMillis());

        Customer customer = CustomerMapper.getCustomer(customerDto);

        return saveCustomer(customer);
    }


    @Override
    public CustomerDto getCustomers(long cus_id)
    {
        Customer customerRes = customerRepo.findById(cus_id).orElseThrow(()->
                new ResourceNotFoundException("Invalid customer id "+cus_id)

        );

        return CustomerMapper.getCustomerDto(customerRes);
    }

    @Override
    public List<CustomerDto> getCustomers(int start, int limit)
    {
        if(start<0)
        {
            throw new InvalidInputException("start value should to be >= 0");
        }

        Pageable limitedResult = PageRequest.of(start,limit);

        Page<Customer> customerPage = customerRepo.findAll(limitedResult);

        return customerPage.stream().map((customer) ->{
            return CustomerMapper.getCustomerDto(customer);
        }).toList();
    }

    @Override
    public boolean isMailIdPresent(String mailId)
    {
        return customerRepo.findByemailId(mailId).isPresent();
    }

    @Override
    public CustomerDto updateCustomer(long customer_id, CustomerDto updatedCustomerDto)
    {
        Optional<Customer> customerRes = customerRepo.findById(customer_id);

        if(!customerRes.isPresent())
        {
            throw  new ResourceNotFoundException("Customer id not found"+customer_id);
        }

        Customer customer = customerRes.get();
        customer.setName(updatedCustomerDto.getName());
        customer.setEmailId(updatedCustomerDto.getEmailId());
        customer.setAge(updatedCustomerDto.getAge());
        customer.setMobileNo(updatedCustomerDto.getMobileNo());

        return saveCustomer(customer);
    }

    @Override
    public void deleteCustomer(long cusId)
    {
        customerRepo.findById(cusId).orElseThrow(
                () -> new ResourceNotFoundException("Customer id not found "+cusId)
        );

        customerRepo.deleteById(cusId);
    }

    public CustomerDto saveCustomer(Customer customer)
    {
        return CustomerMapper.getCustomerDto(customerRepo.save(customer));
    }

}
