package com.mycab.booking.service;

import com.mycab.booking.dto.DriverDto;

import java.util.List;

public interface DriverService
{
    DriverDto addDriver(DriverDto driverDto);

    DriverDto getDrivers(long cusId);

    List<DriverDto> getDrivers(int page, int limit);

    boolean isMailIdPresent(String mailId);

    DriverDto updateDriver(long cusId, DriverDto driverDto);

    void deleteDriver(long driverId);
}
