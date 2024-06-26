package com.elliot.microservices.limitservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {

    @Autowired
    private Configuration configuration;


    @GetMapping("/limits")
    public LimitsConfiguration retrieveLimitsFromConfiguration(){
        return new LimitsConfiguration(configuration.getMinimun(), configuration.getMaximun());
    }
}
