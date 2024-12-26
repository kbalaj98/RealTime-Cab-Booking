package com.mycab.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"com.mycab.booking","com.mycab.common"})
public class RealTimeCabBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealTimeCabBookingApplication.class, args);
	}

}
