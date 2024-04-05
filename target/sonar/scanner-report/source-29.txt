package com.elliot.microservices.currencyexchangeservice.jpa.repo;

import com.elliot.microservices.currencyexchangeservice.jpa.entity.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeValueRepo
        extends JpaRepository<ExchangeValue, Long> {
    ExchangeValue findByFromAndTo(String from, String to);
}
