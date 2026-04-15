package com.konvergion.personalfinance.minitransactionsearchapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountTransactionDto {
    private String transactionId;

    private String transactionDate;

    private String postedDate;

    private String transactionName;

    private String accountId;

    private BigDecimal transactionAmount;

    private String transactionDescription;

    private String transactionCategory;
}
