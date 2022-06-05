package com.konvergion.personalfinance.budgetmanagementapi.processors;

import com.konvergion.personalfinance.budgetmanagementapi.model.entities.BudgetEstimateEntity;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.BudgetEstimateItemEntity;

public interface IBudgetSummaryProcessor<E extends BudgetEstimateItemEntity, T extends BudgetEstimateEntity> {

    default SummaryCalculatorImpl<T> getImplementationClass(String typeOfTransaction) {
        switch (typeOfTransaction) {
            case "Account Credit Transaction":
                return new AccountCreditSummaryCalculatorImpl<>();
            case "Savings Transaction":
                return new SavingsSummaryCalculatorImpl<>();
            case "Account Debit Transaction":
                return new AccountDebitSummaryCalculatorImpl<>();
            default:
                return new CreditCardSummaryCalculatorImpl<>();
        }
    }
}
