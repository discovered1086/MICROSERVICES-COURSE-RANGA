package com.kingshuk.springcloudprojects.currencyexchangeservice.controller;

import com.kingshuk.springcloudprojects.currencyexchangeservice.dao.ExchangeValueRepository;
import com.kingshuk.springcloudprojects.currencyexchangeservice.model.ExchangeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private ExchangeValueRepository exchangeValueRepository;

    @GetMapping("/from/{fromCurrency}/to/{toCurrency}")
    public ExchangeValue getCurrencyExchangeRate(
            @PathVariable String fromCurrency,
            @PathVariable String toCurrency) {
        ExchangeValue exchangevalue = exchangeValueRepository.findByFromCurrencyAndToCurrency(fromCurrency, toCurrency);
        exchangevalue.setPort(Integer.parseInt(
                Objects.requireNonNull(
                        environment.getProperty("local.server.port"))));
        return exchangevalue;
    }
}
