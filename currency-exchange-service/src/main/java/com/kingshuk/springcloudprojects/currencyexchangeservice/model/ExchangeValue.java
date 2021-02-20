package com.kingshuk.springcloudprojects.currencyexchangeservice.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name = "EXCHANGE_VALUES")
public class ExchangeValue {

    @NonNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NonNull
    @Column(name = "FROM_CURRENCY")
    private String fromCurrency;

    @NonNull
    @Column(name = "TO_CURRENCY")
    private String toCurrency;

    @NonNull
    @Column(name = "EXCHANGE_RATE", columnDefinition = "NUMBER(10,2)")
    private BigDecimal exchangeRate;

    private transient int port;
}
