package com.mycab.booking.controller;

import com.mycab.booking.dto.DriverDto;
import com.mycab.booking.service.DriverService;
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


@RestController
@RequestMapping("/api/driver")
public class DriverController
{
    Logger logger = LoggerFactory.getLogger(DriverController.class);

    @Autowired
    DriverService driverService;

    @GetMapping("/{id}")
    public ResponseEntity<DriverDto> getDriver(@PathVariable("id") long driverId)
    {
        logger.info("get  driver="+driverId);

        DriverDto response  =  driverService.getDrivers(driverId);
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/getDrivers")
    public ResponseEntity<List<DriverDto>> getDrivers(@Param("page") int page,@Param("limit") int limit)
    {
        logger.info("get all  driver");

        List<DriverDto> driversDto = driverService.getDrivers(page,limit);
        return new ResponseEntity<>(driversDto,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DriverDto> addDriver(@RequestBody  DriverDto driverDto)
    {
        logger.info("add driver");

        return new ResponseEntity<>(driverService.addDriver(driverDto),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DriverDto> updateDriver(@PathVariable("id") long driverId,@RequestBody  DriverDto driverDto)
    {
        logger.info("updating driver");

        return new ResponseEntity<>(driverService.updateDriver(driverId,driverDto),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Object>> deleteDriver(@PathVariable("id") long driverId)
    {
        logger.info("deleting driver"+driverId);

        driverService.deleteDriver(driverId);
        Map<String, Object> res = new HashMap<>();
        res.put("Status","SUCCESS");
        res.put("driverId",driverId);

        return new ResponseEntity<>(res,HttpStatus.OK);
    }



}
