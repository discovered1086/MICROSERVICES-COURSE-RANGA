package com.konvergion.personalfinance.budgetmanagementapi.processors;

import com.konvergion.personalfinance.budgetmanagementapi.model.BudgetSummaryType;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.BudgetEstimateEntity;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.BudgetSummaryEntity;

import java.math.BigDecimal;
import java.util.Objects;

public class AccountDebitSummaryCalculatorImpl<T extends BudgetEstimateEntity> implements SummaryCalculatorImpl<T>{

    @Override
    public void updateEstimateSummary(T budgetEstimate, BigDecimal amount) {
        BudgetSummaryEntity budgetSummary = budgetEstimate.getBudgetSummaries().get(BudgetSummaryType.ESTIMATE);
        if (Objects.isNull(budgetSummary)) {
            budgetSummary = BudgetSummaryEntity.builder().build();
            budgetSummary.setTotalDebitTransactions(amount);
            budgetSummary.setTotalExpenses(amount);
            budgetEstimate.getBudgetSummaries().put(BudgetSummaryType.ESTIMATE, budgetSummary);
        } else {
            budgetSummary.setTotalDebitTransactions(Objects.isNull(
                    budgetSummary.getTotalDebitTransactions()) ? amount : budgetSummary.getTotalDebitTransactions().add(amount));
            budgetSummary.setTotalExpenses(Objects.isNull(budgetSummary.getTotalExpenses())? amount: budgetSummary.getTotalExpenses().add(amount));
        }
    }

    @Override
    public void updateActualSummary(T budgetEstimate, BigDecimal amount) {
        BudgetSummaryEntity budgetSummary = budgetEstimate.getBudgetSummaries().get(BudgetSummaryType.ACTUAL);
        if (Objects.isNull(budgetSummary)) {
            budgetSummary = BudgetSummaryEntity.builder().build();
            budgetSummary.setTotalDebitTransactions(amount);
            budgetSummary.setTotalExpenses(amount);
            budgetEstimate.getBudgetSummaries().put(BudgetSummaryType.ACTUAL, budgetSummary);
        } else {
            budgetSummary.setTotalDebitTransactions(Objects.isNull(
                    budgetSummary.getTotalDebitTransactions()) ? amount : budgetSummary.getTotalDebitTransactions().add(amount));
            budgetSummary.setTotalExpenses(Objects.isNull(budgetSummary.getTotalExpenses())? amount: budgetSummary.getTotalExpenses().add(amount));
        }
    }
}
