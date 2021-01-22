package com.kingshuk.springcloudprojects.currencyexchangeservice.dao;

import com.kingshuk.springcloudprojects.currencyexchangeservice.model.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {

    ExchangeValue findByFromCurrencyAndToCurrency(String fromCurrency, String toCurrency);
}
