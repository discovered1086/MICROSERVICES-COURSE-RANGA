package com.konvergion.personalfinance.budgetmanagementapi.processors;

import com.konvergion.personalfinance.budgetmanagementapi.model.entities.BudgetEstimateEntity;

import java.math.BigDecimal;

public class ActualSummaryCalculatorImpl<T extends BudgetEstimateEntity> extends SummaryCalculator<T> {

    @Override
    void updateBudgetSummary(T budgetEntity, BigDecimal amount) {
        summaryCalculatorImpl.updateActualSummary(budgetEntity, amount);
    }
}
