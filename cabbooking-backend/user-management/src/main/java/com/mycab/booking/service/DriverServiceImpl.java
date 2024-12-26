package com.mycab.booking.service;

import com.mycab.booking.dto.DriverDto;
import com.mycab.booking.entity.Driver;
import com.mycab.booking.exception.InvalidInputException;
import com.mycab.booking.exception.ResourceAlreadyExistsException;
import com.mycab.booking.exception.ResourceNotFoundException;
import com.mycab.booking.mapper.DriverMapper;
import com.mycab.booking.repository.DriverRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService
{
    @Autowired
    DriverRepo driverRepo;


    @Override
    public DriverDto addDriver(DriverDto driverDto)
    {
        if(isMailIdPresent(driverDto.getEmailId()))
        {
            throw new ResourceAlreadyExistsException(driverDto.getEmailId()+" email id already exist");
        }

        Driver driver = driverRepo.save(DriverMapper.getDriver(driverDto));
        return DriverMapper.getDriverDto(driver);
    }

    @Override
    public DriverDto getDrivers(long driverId)
    {
        Driver driver = driverRepo.findById(driverId).orElseThrow(()->{
            throw new ResourceNotFoundException("Invalid driver id ="+driverId);
        });

        return DriverMapper.getDriverDto(driver);
    }

    @Override
    public List<DriverDto> getDrivers(int page, int limit)
    {
        if(page<0)
        {
            throw new InvalidInputException("Page value should be greater than or equal to 0 and value ="+page);
        }


        Pageable limitedPageable = PageRequest.of(page,limit);
        Page<Driver> drivers = driverRepo.findAll(limitedPageable);

        return drivers.map((driver)->{
            return DriverMapper.getDriverDto(driver);
        }).toList();
    }

    @Override
    public boolean isMailIdPresent(String mailId)
    {
        return driverRepo.findByemailId(mailId).isPresent();
    }

    @Override
    public DriverDto updateDriver(long driverId, DriverDto driverDto)
    {

        Driver driver = driverRepo.findById(driverId).orElseThrow(()->{
            throw new ResourceNotFoundException("Invalid driver id ="+driverId);
        });

        driver.setName(driverDto.getName());
        driver.setAge(driverDto.getAge());
        driver.setGender(driverDto.getGender());
        driver.setEmailId(driverDto.getEmailId());
        driver.setMobileNo(driverDto.getMobileNo());

        return DriverMapper.getDriverDto(driverRepo.save(driver));
    }

    @Override
    public void deleteDriver(long driverId)
    {
        Driver driver = driverRepo.findById(driverId).orElseThrow(()->{
            throw new ResourceNotFoundException("Invalid driver id ="+driverId);
        });

        driverRepo.deleteById(driverId);
    }
}
