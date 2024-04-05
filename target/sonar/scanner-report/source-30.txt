package com.elliot.microservices.currencyexchangeservice;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {
    private static int a = 0;
    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
    @GetMapping("/sample-api")
    @Retry(name = "sample-api", fallbackMethod = "fallbackMethod")
    public String sampleApiRetry(){
        logger.info("sample api call received");
        ResponseEntity<String> forEntity = new RestTemplate()
                .getForEntity("http://locahost:8080/dummy",
                String.class);
        return forEntity.getBody();
    }

    @GetMapping("/sample-api-circuit-breaker")
    @CircuitBreaker(name = "default", fallbackMethod = "fallbackMethod")
    public String sampleApi(){
        logger.info("sample-api-circuit-breaker call received = {}",++a);
        ResponseEntity<String> forEntity = new RestTemplate()
                .getForEntity("http://locahost:8080/dummy",
                        String.class);
        return forEntity.getBody();
    }

    @GetMapping("/sample-api-rate-limiting")
    @RateLimiter(name = "sample-api", fallbackMethod = "fallbackMethod")
    public String sampleApiRateLimit(){
        logger.info("sample-api-rate-limiting call received = {}",++a);
//        ResponseEntity<String> forEntity = new RestTemplate()
//                .getForEntity("http://locahost:8080/dummy",
//                        String.class);
//        return forEntity.getBody();
        return "sample-api-reate-limiting";
    }

    public String fallbackMethod(Exception e){
        logger.error("Error -> {}", e.getMessage());
        return "Hi I'm the fallback method";
    }
}
