package com.bdg.airport_management_system_spring_boot;

import com.bdg.airport_management_system_spring_boot.model.AddressMod;
import com.bdg.airport_management_system_spring_boot.service.impl.AddressServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AirportManagementSystemSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirportManagementSystemSpringBootApplication.class, args);

       // AddressMod addressMod = new AddressMod();

        //System.out.println(AddressServiceImpl.class);
    }
}