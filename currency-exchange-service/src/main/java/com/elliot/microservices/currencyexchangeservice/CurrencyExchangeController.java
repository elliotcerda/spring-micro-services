package com.elliot.microservices.currencyexchangeservice;

import com.elliot.microservices.currencyexchangeservice.jpa.entity.ExchangeValue;
import com.elliot.microservices.currencyexchangeservice.jpa.repo.ExchangeValueRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
    @Autowired
    private Environment environment;
    @Autowired
    private ExchangeValueRepo exchangeValueRepo;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrievExchangeValue(@PathVariable String from,
                                              @PathVariable String to){
        logger.error("Error");
        logger.info("Info");
        ExchangeValue value = exchangeValueRepo.findByFromAndTo(from, to);

//        ExchangeValue value = new ExchangeValue(1000L,"US", "INR",
//                BigDecimal.valueOf(65));
//        //Reading current port
         value.setPort(Integer.parseInt(
                 environment.getProperty("local.server.port")));
        return value;
    }
}
