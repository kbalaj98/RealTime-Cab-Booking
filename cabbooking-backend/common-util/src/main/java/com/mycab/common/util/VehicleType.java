package com.mycab.common.util;

public enum VehicleType
{
    BIKE(1),
    AUTO(2),
    CAR(3);

    int type;
     VehicleType(int type)
    {
        this.type = type;
    }

    public int getValue()
    {
        return type;
    }

}
