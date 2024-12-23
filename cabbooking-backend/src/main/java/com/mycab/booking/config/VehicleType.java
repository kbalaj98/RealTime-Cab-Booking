package com.mycab.booking.config;

public enum VechicleType
{
    BIKE(1),
    AUTO(2),
    CAR(3);

    int type;
    VechicleType(int type)
    {
        this.type = type;
    }

    public int getValue()
    {
        return type;
    }

}
