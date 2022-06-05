package com.konvergion.personalfinance.budgetmanagementapi.processors;

import com.konvergion.personalfinance.budgetmanagementapi.model.entities.BudgetEstimateEntity;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
public abstract class SummaryCalculator<T extends BudgetEstimateEntity> {

    protected SummaryCalculatorImpl<T> summaryCalculatorImpl;


    abstract void updateBudgetSummary(T budgetEntity, BigDecimal amount);


}
