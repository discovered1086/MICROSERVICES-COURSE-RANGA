package com.kingshuk.springcloudprojects.currencyconversionservice.model;

import lombok.*;

import java.math.BigDecimal;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class CurrencyConversionDTO {

    private long id;

    private ExchangeValue exchangeValue;

    @NonNull
    private BigDecimal fromAmount;

    private BigDecimal toAmount;

    private int port;
}
