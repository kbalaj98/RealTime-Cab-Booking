package com.mycab.booking.controller;

import com.mycab.booking.dto.CustomerDto;
import com.mycab.booking.service.CustomerServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin("*")
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

        CustomerDto getCustomer = customerService.getCustomers(id);

        return ResponseEntity.ok(getCustomer);
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<CustomerDto>> getAllCustomer(@Param("start") int start, @Param("limit") int limit)
    {
        logger.info("get all customer");

        List<CustomerDto> resultList = customerService.getCustomers(start,limit);

        return new ResponseEntity<>(resultList,HttpStatus.OK);
    }

    @PutMapping("/updateCustomer/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable("id") long cus_id,@RequestBody CustomerDto customerDto)
    {
        logger.info("updating customer ");

        CustomerDto updatedCustomer = customerService.updateCustomer(cus_id,customerDto);

        return new ResponseEntity<>(updatedCustomer,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Object>> deleteCustomer(@PathVariable("id") long cusId)
    {
        Map<String,Object> res = new HashMap<>();

        customerService.deleteCustomer(cusId);

       res.put("Status","SUCCESS");
       res.put("id",cusId);

       return  new ResponseEntity<>(res,HttpStatus.OK);
    }

}
