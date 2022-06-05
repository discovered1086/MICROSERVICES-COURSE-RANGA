package com.konvergion.personalfinance.budgetmanagementapi.processors;

import com.konvergion.personalfinance.budgetmanagementapi.model.entities.CalculationBudgetEstimateEntity;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.CalculationBudgetEstimateItemEntity;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.RegularBudgetEstimateEntity;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.RegularBudgetEstimateItemEntity;
import com.konvergion.personalfinance.budgetmanagementapi.model.repository.CalculatedBudgetEstimateRepository;
import com.konvergion.personalfinance.budgetmanagementapi.model.repository.RegularBudgetEstimateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalculatedBudgetSummaryProcessorImpl implements IBudgetEstimateSummaryProcessor<CalculationBudgetEstimateItemEntity, CalculationBudgetEstimateEntity> {

    @Autowired
    private CalculatedBudgetEstimateRepository estimateRepository;

    @Override
    public void updateBudgetEstimateSummary(CalculationBudgetEstimateItemEntity budgetItem, String budgetId, String customerId) {
        CalculationBudgetEstimateEntity budgetEstimate = estimateRepository.findByBudgetEstimateId(budgetId);
        SummaryCalculator<CalculationBudgetEstimateEntity> summaryCalculator = new EstimateSummaryCalculatorImpl<>();
        summaryCalculator.setSummaryCalculatorImpl(getImplementationClass(budgetItem.getTypeOfTransaction()));
        summaryCalculator.updateBudgetSummary(budgetEstimate, budgetItem.getBudgetItemEstimateAmount());
        estimateRepository.save(budgetEstimate);
    }
}
