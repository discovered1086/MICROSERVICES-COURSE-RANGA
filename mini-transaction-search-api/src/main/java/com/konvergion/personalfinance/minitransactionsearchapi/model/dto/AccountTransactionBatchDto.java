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
public class AccountTransactionBatchDto {

    private String transactionDate;

    private String postedDate;

    private String accountName;

    private String accountNumber;

    private String institutionName;

    private String transactionName;

    private String accountType;

    private BigDecimal transactionAmount;

    private String transactionDescription;

    private String transactionCategory;
}
