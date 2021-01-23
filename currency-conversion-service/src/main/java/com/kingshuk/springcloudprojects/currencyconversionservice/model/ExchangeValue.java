package com.kingshuk.springcloudprojects.currencyconversionservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.math.BigDecimal;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class ExchangeValue {

    @NonNull
    @JsonIgnore
    private long id;

    @NonNull
    private String fromCurrency;

    @NonNull
    private String toCurrency;

    @NonNull
    private BigDecimal exchangeRate;

    @JsonIgnore
    private int port;
}
