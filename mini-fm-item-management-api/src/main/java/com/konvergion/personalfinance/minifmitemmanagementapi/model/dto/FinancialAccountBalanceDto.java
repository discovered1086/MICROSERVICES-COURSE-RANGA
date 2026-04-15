package com.konvergion.personalfinance.minifmitemmanagementapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FinancialAccountBalanceDto implements Serializable {
    private String accountId;
    private String externalAccountId;
    private String accountName;
    private String accountType;
    private String accountSubType;
    private BigDecimal availableBalance;
    private BigDecimal currentBalance;
    private BigDecimal creditLimit;
}
