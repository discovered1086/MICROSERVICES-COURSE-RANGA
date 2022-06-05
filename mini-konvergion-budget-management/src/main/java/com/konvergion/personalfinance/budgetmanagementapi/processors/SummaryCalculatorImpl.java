package com.konvergion.personalfinance.budgetmanagementapi.processors;

import com.konvergion.personalfinance.budgetmanagementapi.model.entities.BudgetEstimateEntity;

import java.math.BigDecimal;

public interface SummaryCalculatorImpl<T extends BudgetEstimateEntity> {

    void updateEstimateSummary(T budgetEstimate, BigDecimal amount);

    void updateActualSummary(T budgetEstimate, BigDecimal amount);
}
