package com.mycab.common.mapper;

import com.mycab.common.util.VehicleType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class VehicleTypeEnumConverter implements AttributeConverter<VehicleType,Integer>
{
    @Override
    public Integer convertToDatabaseColumn(VehicleType vechicleTypeEnum)
    {
        if(vechicleTypeEnum==null)
        {
            return null;
        }
        return vechicleTypeEnum.getValue();
    }

    @Override
    public VehicleType convertToEntityAttribute(Integer integer)
    {
        return Stream.of(VehicleType.values())
                .filter((vehile)-> vehile.getValue()==integer)
                .findFirst()
                .orElseThrow(IllegalAccessError::new);

    }
}
