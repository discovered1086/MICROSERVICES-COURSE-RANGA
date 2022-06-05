package com.konvergion.personalfinance.budgetmanagementapi.processors;

import com.konvergion.personalfinance.budgetmanagementapi.model.BudgetSummaryType;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.BudgetEstimateEntity;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.BudgetSummaryEntity;

import java.math.BigDecimal;
import java.util.Objects;

public class SavingsSummaryCalculatorImpl<T extends BudgetEstimateEntity> implements SummaryCalculatorImpl<T>{

    @Override
    public void updateEstimateSummary(T budgetEstimate, BigDecimal amount) {
        BudgetSummaryEntity budgetSummary = budgetEstimate.getBudgetSummaries().get(BudgetSummaryType.ESTIMATE);
        if (Objects.isNull(budgetSummary)) {
            budgetSummary = BudgetSummaryEntity.builder().build();
            budgetSummary.setTotalSavings(amount);
            budgetEstimate.getBudgetSummaries().put(BudgetSummaryType.ESTIMATE, budgetSummary);
        } else {
            budgetSummary.setTotalSavings(Objects.isNull(budgetSummary.getTotalSavings())? amount: budgetSummary.getTotalSavings().add(amount));
        }
    }

    @Override
    public void updateActualSummary(T budgetEstimate, BigDecimal amount) {
        BudgetSummaryEntity budgetSummary = budgetEstimate.getBudgetSummaries().get(BudgetSummaryType.ACTUAL);
        if (Objects.isNull(budgetSummary)) {
            budgetSummary = BudgetSummaryEntity.builder().build();
            budgetSummary.setTotalSavings(amount);
            budgetEstimate.getBudgetSummaries().put(BudgetSummaryType.ACTUAL, budgetSummary);
        } else {
            budgetSummary.setTotalSavings(Objects.isNull(budgetSummary.getTotalSavings())? amount: budgetSummary.getTotalSavings().add(amount));
        }
    }
}
