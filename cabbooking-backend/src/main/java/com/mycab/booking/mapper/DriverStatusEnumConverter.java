package com.mycab.booking.mapper;

import com.mycab.booking.config.DriverStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class DriverStatusEnumConverter implements AttributeConverter<DriverStatus,Integer> {
    @Override
    public Integer convertToDatabaseColumn(DriverStatus driverStatusEnum)
    {
        if(driverStatusEnum==null)
        {
            return null;
        }
        return driverStatusEnum.getStatus();
    }

    @Override
    public DriverStatus convertToEntityAttribute(Integer integer)
    {
        return Stream.of(DriverStatus.values())
                .filter((driverStatusEnum -> driverStatusEnum.getStatus() ==integer))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}
