package com.elliot.microservices.currencyconversionservice;

import com.elliot.microservices.currencyconversionservice.config.ServerInstanceConfiguration;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "currency-exchange-service",
//        url = "localhost:8000")
@FeignClient(name = "currency-exchange-service")
//@LoadBalancerClient( name = "currency-exchange-service",
//        configuration = ServerInstanceConfiguration.class)
public interface CurrencyExchangeServiceProxy {
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from,
                                                        @PathVariable("to") String to);
}
