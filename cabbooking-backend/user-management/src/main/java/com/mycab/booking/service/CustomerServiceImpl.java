package com.mycab.booking.service;

import com.mycab.booking.dto.CustomerDto;
import com.mycab.booking.entity.Customer;
import com.mycab.booking.exception.InvalidInputException;
import com.mycab.booking.exception.ResourceAlreadyExistsException;
import com.mycab.booking.exception.ResourceNotFoundException;
import com.mycab.booking.mapper.CustomerMapper;
import com.mycab.booking.repository.CustomerRepo;
import com.mycab.common.redis.service.RedisService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService
{

    public static Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    //constructor injection applied here
    CustomerRepo customerRepo;
    RedisService redisService;

    public static final String redisKey = "Customer:";

    @Override
    public CustomerDto addCustomer(CustomerDto customerDto)
    {
        if(isMailIdPresent(customerDto.getEmailId()))
        {
            throw new ResourceAlreadyExistsException(customerDto.getEmailId()+" email id already exist");
        }

        customerDto.setCreatedTime(System.currentTimeMillis());

        Customer customer = CustomerMapper.getCustomer(customerDto);


        return saveInDbAndRedis(customer);
    }


    @Override
    public CustomerDto getCustomers(long cus_id)
    {
        Object redisCachObj = redisService.getPoolSpecificObject(getPoolName(),getCustomerKey(cus_id));

        if(redisCachObj!=null)
        {
            try
            {
                logger.info("customer "+ cus_id +" data fetched from redis");
                CustomerDto customerDto = (CustomerDto) redisCachObj;
                return customerDto;
            }
            catch (Exception e)
            {
                logger.info("Exception while fetch customer key ="+ cus_id,e);
            }
        }

        logger.info("Fetch customer data fetched from db");

        Customer customerRes = customerRepo.findById(cus_id).orElseThrow(()->
                    new ResourceNotFoundException("Invalid customer id "+cus_id));


        return saveInDbAndRedis(customerRes);
    }

    @Override
    public List<CustomerDto> getCustomers(int page, int limit)
    {
        if(page<0)
        {
            throw new InvalidInputException("page value should to be >= 0");
        }

        Pageable limitedResult = PageRequest.of(page,limit);

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

        return saveInDbAndRedis(customer);
    }

    @Override
    public void deleteCustomer(long cusId)
    {
        customerRepo.findById(cusId).orElseThrow(
                () -> new ResourceNotFoundException("Customer id not found "+cusId)
        );

        customerRepo.deleteById(cusId);

        logger.info("Deleting data from redis="+getCustomerKey(cusId));
        redisService.deleteObject(getPoolName(),getCustomerKey(cusId));
    }

    public CustomerDto saveCustomer(Customer customer)
    {
        return CustomerMapper.getCustomerDto(customerRepo.save(customer));
    }

    public String getPoolName()
    {
        return "db_0";
    }

    private String getCustomerKey(long cus_id)
    {
        return redisKey+cus_id;
    }

    private CustomerDto saveInDbAndRedis(Customer customer)
    {
        //save in db
        CustomerDto customerDto1 = saveCustomer(customer);

        //save in redis
        redisService.addPoolSpecificObject(getPoolName(),getCustomerKey(customerDto1.getCustomerId()),customerDto1, Duration.ofMinutes(3));

        return customerDto1;
    }

}
