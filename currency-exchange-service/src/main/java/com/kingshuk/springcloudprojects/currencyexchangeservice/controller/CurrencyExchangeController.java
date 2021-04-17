package com.kingshuk.springcloudprojects.currencyexchangeservice.controller;

import com.kingshuk.springcloudprojects.currencyexchangeservice.dao.ExchangeValueRepository;
import com.kingshuk.springcloudprojects.currencyexchangeservice.model.ExchangeValue;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Objects;

@RestController
//@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

    private final Logger logger= LoggerFactory.getLogger(CurrencyExchangeController.class);

    @Autowired
    private Environment environment;

    @Autowired
    private ExchangeValueRepository exchangeValueRepository;

    @GetMapping("/currency-exchange/from/{fromCurrency}/to/{toCurrency}")
    //@RateLimiter(name="default", fallbackMethod = "defaultCurrencyExchange")
    @RateLimiter(name="default")
    public ExchangeValue getCurrencyExchangeRate(
            @PathVariable String fromCurrency,
            @PathVariable String toCurrency) {
        ExchangeValue exchangevalue = exchangeValueRepository.findByFromCurrencyAndToCurrency(fromCurrency, toCurrency);
        exchangevalue.setPort(Integer.parseInt(
                Objects.requireNonNull(
                        environment.getProperty("local.server.port"))));
        return exchangevalue;
    }

    @GetMapping("/currency-exchange-resilience4j-retry/from/{fromCurrency}/to/{toCurrency}")
    //@Retry(name="currency-exchange", fallbackMethod = "defaultCurrencyExchange")
    //@Retry(name="currency-exchange")
    @CircuitBreaker(name="default", fallbackMethod = "defaultCurrencyExchange")
    public ExchangeValue getCurrencyExchangeRateRetry(
            @PathVariable String fromCurrency,
            @PathVariable String toCurrency) {
        logger.info("Currency exchange service retry method executed");
        ExchangeValue exchangevalue = exchangeValueRepository.findByFromCurrencyAndToCurrency(fromCurrency, toCurrency);
        exchangevalue.setPort(Integer.parseInt(
                Objects.requireNonNull(
                        environment.getProperty("local.server.port"))));
        throw new RuntimeException("Something went wrong");
    }

    @SuppressWarnings({"unused", "java:S1144"})
    private ExchangeValue defaultCurrencyExchange(RuntimeException exception){
        logger.info("Fallback method has been called");
        return ExchangeValue.builder()
                .port(Integer.parseInt(
                        Objects.requireNonNull(
                                environment.getProperty("local.server.port"))))
                .fromCurrency("USD")
                .id(1000)
                .toCurrency("INR")
                .exchangeRate(BigDecimal.valueOf(60.31))
                .build();
    }
}
