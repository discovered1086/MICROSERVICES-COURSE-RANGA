package com.kingshuk.springcloudprojects.currencyexchangeservice.controller;

import com.kingshuk.springcloudprojects.currencyexchangeservice.model.ExchangeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Objects;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @GetMapping("/from/{fromCurrency}/to/{toCurrency}")
    public ExchangeValue getCurrencyExchangeRate(
            @PathVariable String fromCurrency,
            @PathVariable String toCurrency) {
        return ExchangeValue.builder()
                .id(1000)
                .exchangeRate(BigDecimal.valueOf(65))
                .fromCurrency(fromCurrency)
                .toCurrency(toCurrency)
                .port(Integer.parseInt(
                        Objects.requireNonNull(
                                environment.getProperty("local.server.port"))))
                .build();
    }
}
