package com.kingshuk.springcloudprojects.currencyconversionservice.controller;

import com.kingshuk.springcloudprojects.currencyconversionservice.model.ExchangeValue;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "currency-exchange-service",
        url = "localhost:8001")
public interface ICurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{fromCurrency}/to/{toCurrency}")
    ExchangeValue getCurrencyExchangeRate(
            @PathVariable String fromCurrency,
            @PathVariable String toCurrency);
}
