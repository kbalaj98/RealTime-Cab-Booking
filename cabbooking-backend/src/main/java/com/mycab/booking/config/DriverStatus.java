package com.mycab.booking.config;

public enum DriverStatus
{
    UNAVAILABLE(0),//break
    AVAILABLE(1),//ready to pick
    ON_TRIP(2),//already in ride
    OFFLINE(3),//leave
    WAITING_FOR_RIDER(4),//arrived at customer location
    INACTIVE(5);//driver account temp blocked

    int status;

    DriverStatus(int status)
    {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
