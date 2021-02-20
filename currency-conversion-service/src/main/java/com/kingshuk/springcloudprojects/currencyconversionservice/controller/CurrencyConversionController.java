package com.kingshuk.springcloudprojects.currencyconversionservice.controller;

import com.kingshuk.springcloudprojects.currencyconversionservice.model.CurrencyConversionDTO;
import com.kingshuk.springcloudprojects.currencyconversionservice.model.ExchangeValue;
import com.kingshuk.springcloudprojects.currencyconversionservice.util.ApplicationConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Slf4j
public class CurrencyConversionController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ICurrencyExchangeProxy proxy;

    @GetMapping("/currency-convertor/from/{fromCurrency}/to/{toCurrency}/{amount}")
    public CurrencyConversionDTO getConvertedAmount(
            @PathVariable String fromCurrency,
            @PathVariable String toCurrency,
            @PathVariable BigDecimal amount) {
//        String url = UriComponentsBuilder.fromHttpUrl(ApplicationConstants.EXCHANGE_URL)
//                .buildAndExpand(fromCurrency, toCurrency)
//                .toUriString();

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("fromCurrency", fromCurrency);
        uriVariables.put("toCurrency", toCurrency);

        final String url = UriComponentsBuilder.fromHttpUrl(ApplicationConstants.EXCHANGE_URL)
                .buildAndExpand(uriVariables)
                .toUriString();

        log.info("The currency exchange URL is: " + url);

        ResponseEntity<ExchangeValue> entity = restTemplate.getForEntity(url, ExchangeValue.class);

        if (Objects.nonNull(entity.getBody())) {
            ExchangeValue exchangeValue = entity.getBody();
            return CurrencyConversionDTO.builder()
                    .fromAmount(amount)
                    .exchangeValue(exchangeValue)
                    .toAmount(amount.multiply(Objects.nonNull(exchangeValue) ? exchangeValue.getExchangeRate() : BigDecimal.ONE))
                    .build();
        }

        return new CurrencyConversionDTO();
    }


    @GetMapping("/currency-convertor-feign/from/{fromCurrency}/to/{toCurrency}/{amount}")
    public CurrencyConversionDTO getConvertedAmountUsingFeign(
            @PathVariable String fromCurrency,
            @PathVariable String toCurrency,
            @PathVariable BigDecimal amount) {

        ExchangeValue exchangeValue = proxy.getCurrencyExchangeRate(fromCurrency, toCurrency);

        return CurrencyConversionDTO.builder()
                .fromAmount(amount)
                .exchangeValue(exchangeValue)
                .toAmount(amount.multiply(Objects.nonNull(exchangeValue) ? exchangeValue.getExchangeRate() : BigDecimal.ONE))
                .build();

    }
}
