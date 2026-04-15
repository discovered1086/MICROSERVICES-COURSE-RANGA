package com.konvergion.personalfinance.budgetmanagementapi.model.dto;

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
public class BudgetSummaryDto implements Serializable {
    private  String budgetEstimateId;
    private  BigDecimal totalIncome;
    private  BigDecimal totalDebitTransactions;
    private  BigDecimal totalCreditCardTransactions;
    private  BigDecimal totalExpenses;
    private  BigDecimal totalSavings;
}
