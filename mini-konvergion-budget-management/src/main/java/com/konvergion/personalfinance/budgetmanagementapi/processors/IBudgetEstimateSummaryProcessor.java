package com.konvergion.personalfinance.budgetmanagementapi.processors;

import com.konvergion.personalfinance.budgetmanagementapi.model.entities.BudgetEstimateEntity;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.BudgetEstimateItemEntity;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.RegularBudgetEstimateEntity;

public interface IBudgetEstimateSummaryProcessor<E extends BudgetEstimateItemEntity, T extends BudgetEstimateEntity> extends IBudgetSummaryProcessor<E,T>{

    void updateBudgetEstimateSummary(E budgetItem, String budgetId, String customerId);
}
