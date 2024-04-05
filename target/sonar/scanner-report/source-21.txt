package com.elliot.microservices.currencyconversionservice.config;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
//Will be init only if profile is prod
@Profile("prod")
public class DataSource {
    static {
        System.out.println("DataSource being statically initialized");
    }

    public DataSource(){
        System.out.println("DataSource being initialized");
    }
}
