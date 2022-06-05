package com.konvergion.personalfinance.budgetmanagementapi.processors;

import com.konvergion.personalfinance.budgetmanagementapi.model.entities.BudgetEstimateEntity;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.BudgetEstimateItemEntity;

public interface IBudgetActualSummaryProcessor<E extends BudgetEstimateItemEntity, T extends BudgetEstimateEntity> extends IBudgetSummaryProcessor<E,T>{

    void updateBudgetActualSummary(E budgetItem, String budgetId, String customerId);
}
