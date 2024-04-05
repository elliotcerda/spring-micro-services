package com.elliot.microservices.currencyconversionservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;

@RestController
public class CurrencyConversionController {
    private Logger logger = LoggerFactory.getLogger(CurrencyConversionController.class);

    @Autowired
    private CurrencyExchangeServiceProxy currencyExchangeServiceProxy;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrncy(@PathVariable String from,
                                                 @PathVariable String to,
                                                 @PathVariable BigDecimal quantity) throws Exception {
        logger.error("Error");
        logger.info("Info");

        Map<String, String> uriVariables = new HashMap();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        //Use spring RestTemplate
        ResponseEntity<CurrencyConversionBean> responseEntity = restTemplate
                .getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversionBean.class, uriVariables);
        if (responseEntity.getStatusCode().is2xxSuccessful()){
            CurrencyConversionBean response = responseEntity.getBody();
            return new CurrencyConversionBean(response.getId(), from, to,
                    response.getConversionMultiple(), quantity,
                    response.getConversionMultiple().multiply(quantity), response.getPort());
        }else {
            throw new Exception(responseEntity.toString());
        }
    }

    @GetMapping("/v2/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrncyV2(@PathVariable String from,
                                                 @PathVariable String to,
                                                 @PathVariable BigDecimal quantity) throws Exception {

        //Use FEIGN
        CurrencyConversionBean response = currencyExchangeServiceProxy.retrieveExchangeValue(from, to);
        response.setQuantity(quantity);
        response.setTotalCalculatedAmount(response.getConversionMultiple().multiply(quantity));
        return response;
    }
}
