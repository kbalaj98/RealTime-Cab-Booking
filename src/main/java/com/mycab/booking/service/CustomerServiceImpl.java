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

        return CustomerMapper.getCustomerDto(customerRepo.save(customer));
    }



    @Override
    public CustomerDto getCustomer(long cus_id)
    {
        Customer customerRes = customerRepo.findById(cus_id).orElseThrow(()->
                new ResourceNotFoundException("Invalid customer id "+cus_id)

        );

        return CustomerMapper.getCustomerDto(customerRes);
    }

    @Override
    public List<CustomerDto> getCustomer(int start, int limit)
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
}
