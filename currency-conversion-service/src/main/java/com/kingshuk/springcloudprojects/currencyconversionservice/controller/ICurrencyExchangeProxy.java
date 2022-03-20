package com.kingshuk.springcloudprojects.currencyconversionservice.controller;

import com.kingshuk.springcloudprojects.currencyconversionservice.model.ExchangeValue;
<<<<<<< HEAD
=======
import org.springframework.cloud.netflix.ribbon.RibbonClient;
>>>>>>> origin/main
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "currency-exchange-service",url = "localhost:8001")
@FeignClient(name = "microservice-api-gateway")
@RibbonClient(name = "currency-exchange-service")
public interface ICurrencyExchangeProxy {

    @GetMapping("/currency-exchange-service/currency-exchange/from/{fromCurrency}/to/{toCurrency}")
@FeignClient(name = "currency-exchange-service")
public interface ICurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{fromCurrency}/to/{toCurrency}")
    ExchangeValue getCurrencyExchangeRate(
            @PathVariable String fromCurrency,
            @PathVariable String toCurrency);
}
