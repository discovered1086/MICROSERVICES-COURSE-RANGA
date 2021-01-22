package com.kingshuk.springcloudprojects.currencyexchangeservice.model;

import lombok.*;

import java.math.BigDecimal;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class ExchangeValue {

    @NonNull
    private long id;

    @NonNull
    private String fromCurrency;

    @NonNull
    private String toCurrency;

    @NonNull
    private BigDecimal exchangeRate;

    private int port;
}
