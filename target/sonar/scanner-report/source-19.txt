package com.elliot.microservices.currencyconversionservice.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

@Configuration
//Init only if there is a bean of DataSource.class
@ConditionalOnBean(DataSource.class)
public class DataSourceConfig {
    static {
        System.out.println("DataSourceConfig being statically initialized");
    }

    public DataSourceConfig(){
        System.out.println("DataSourceConfig being initialized");
    }
}
