package com.mycab.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@RedisHash("CustomerDto")
public class CustomerDto implements Serializable
{
    long customerId;
    String name;
    String emailId;
    String gender;
    int age;
    long mobileNo;
    long createdTime;
}
