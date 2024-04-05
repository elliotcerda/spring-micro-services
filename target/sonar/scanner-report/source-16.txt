package com.elliot.microservices.currencyconversionservice.config;

import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

public class InstanceSupplier implements ServiceInstanceListSupplier {
    private final String serviceId;

    public InstanceSupplier(String serviceId){
        this.serviceId = serviceId;
    }
    @Override
    public String getServiceId() {
        return this.serviceId;
    }

    @Override
    public Flux<List<ServiceInstance>> get() {
        return Flux.just(Arrays
                .asList(new DefaultServiceInstance(serviceId + "1", serviceId, "localhost", 8000, false),
                new DefaultServiceInstance(serviceId + "2", serviceId, "localhost", 8001, false)));
    }
}
