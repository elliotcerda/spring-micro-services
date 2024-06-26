package com.elliot.microservices.apigateway;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class ApiGatewayConfig {
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p -> p.path("/get")
                        .filters(f -> f.addRequestHeader("MyHeadr", "MyURI")
                                .addRequestParameter("Param","MyParam"))
                        .uri("http://httpbin.org:80"))
                .route(p-> p.path("/currency-exchange/**")
                        .uri("lb://CURRENCY-EXCHANGE-SERVICE"))
                .route(p-> p.path("/v2/currency-converter/**")
                        .uri("lb://CURRENCY-CONVERSION-SERVICE"))
                .route(p-> p.path("/currency-converter/**")
                        .uri("lb://CURRENCY-CONVERSION-SERVICE"))
                .route(p-> p.path("/currency-converter-new/**")
                        .filters(f -> f.rewritePath("/currency-converter-new/(?<segment>.*)",
                                "/v2/currency-converter/${segment}"))
                        .uri("lb://CURRENCY-CONVERSION-SERVICE"))
                .build();
    }
}
