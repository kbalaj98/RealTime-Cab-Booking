package com.mycab.booking.controller;

import com.mycab.booking.dto.CustomerDto;
import com.mycab.booking.exception.InvalidInputException;
import com.mycab.booking.service.CustomerServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/customer")
public class CustomerController
{
    public static  Logger logger = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    CustomerServiceImpl customerService;

    @PostMapping
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerDto customerDto)
    {
        CustomerDto addedCustomer = customerService.addCustomer(customerDto);

        logger.info("Customer {} added",addedCustomer.getCustomerId());


        return new ResponseEntity(addedCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("id") long id)
    {
        logger.info("get customer ...");

        CustomerDto getCustomer = customerService.getCustomer(id);

        return ResponseEntity.ok(getCustomer);
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<CustomerDto>> getAllCustomer(@Param("start") int start, @Param("limit") int limit)
    {
        logger.info("get all customer");

        List<CustomerDto> resultList = customerService.getCustomer(start,limit);

        return new ResponseEntity<>(resultList,HttpStatus.OK);
    }

}
