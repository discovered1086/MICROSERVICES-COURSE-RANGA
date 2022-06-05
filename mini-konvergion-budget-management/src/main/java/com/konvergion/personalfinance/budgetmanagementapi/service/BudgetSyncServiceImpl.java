package com.konvergion.personalfinance.budgetmanagementapi.service;

import com.konvergion.personalfinance.budgetmanagementapi.model.entities.BudgetEstimateEntity;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.BudgetEstimateItemEntity;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.RegularBudgetEstimateItemEntity;
import com.konvergion.personalfinance.budgetmanagementapi.processors.BudgetSummaryCalculatorFactory;
import com.konvergion.personalfinance.budgetmanagementapi.processors.IBudgetActualSummaryProcessor;
import com.konvergion.personalfinance.budgetmanagementapi.processors.IBudgetEstimateSummaryProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetSyncServiceImpl<E extends BudgetEstimateItemEntity> implements IBudgetSyncService<E> {

    @Autowired
    private BudgetSummaryCalculatorFactory summaryCalculatorFactory;

    @Override
    public void syncBudgetAmounts(String budgetId, String customerId, E budgetItem, Class<E> theClass) {
        IBudgetEstimateSummaryProcessor<E, BudgetEstimateEntity> processorClass = summaryCalculatorFactory.getEstimateProcessorClass(theClass);
        processorClass.updateBudgetEstimateSummary(budgetItem, budgetId, customerId);
        if (theClass.isAssignableFrom(RegularBudgetEstimateItemEntity.class)) {
            IBudgetActualSummaryProcessor<E, BudgetEstimateEntity> actualProcessorClass = summaryCalculatorFactory.getActualProcessorClass();
            actualProcessorClass.updateBudgetActualSummary(budgetItem, budgetId, customerId);
        }
    }
}
