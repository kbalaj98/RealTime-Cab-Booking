package com.mycab.booking.mapper;

import com.mycab.booking.dto.DriverDto;
import com.mycab.booking.entity.Driver;

public class DriverMapper
{
    public static DriverDto getDriverDto(Driver driver)
    {
        return  new DriverDto(driver.getDriver_id(),driver.getName(),driver.getEmailId(),driver.getGender(),driver.getAge(),driver.getMobileNo());
    }

    public static  Driver getDriver(DriverDto driverDto)
    {
        return new Driver(driverDto.getDriver_id(),driverDto.getName(),driverDto.getEmailId(),driverDto.getGender(),driverDto.getAge(),driverDto.getMobileNo());
    }
}
