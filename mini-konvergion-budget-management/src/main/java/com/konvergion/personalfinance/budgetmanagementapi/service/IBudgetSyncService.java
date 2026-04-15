package com.konvergion.personalfinance.budgetmanagementapi.service;

import com.konvergion.personalfinance.budgetmanagementapi.model.entities.BudgetEstimateItemEntity;

public interface IBudgetSyncService<E extends BudgetEstimateItemEntity> {

    void syncBudgetAmounts(String budgetId, String customerId, E budgetItem, Class<E> theClass);
}
