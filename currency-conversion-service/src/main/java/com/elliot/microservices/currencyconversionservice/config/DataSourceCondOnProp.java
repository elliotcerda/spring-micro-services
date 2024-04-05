package com.elliot.microservices.currencyconversionservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
//Will be init only if property is available with the given value
@ConditionalOnProperty(name = "database", havingValue = "mysql")
public class DataSourceCondOnProp {

    @Autowired
    private Environment environment;
    static {
        System.out.println("DataSourceCondOnProp being statically initialized");
    }

    public DataSourceCondOnProp(){
        System.out.println("DataSourceCondOnProp being initialized because of prop mysql");
    }
}
