package com.konvergion.personalfinance.budgetmanagementapi.processors;

import com.konvergion.personalfinance.budgetmanagementapi.model.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BudgetSummaryCalculatorFactory {

    @Autowired
    private IBudgetEstimateSummaryProcessor<RegularBudgetEstimateItemEntity, RegularBudgetEstimateEntity> regularProcessorClass;

    @Autowired
    private IBudgetEstimateSummaryProcessor<CalculationBudgetEstimateItemEntity, CalculationBudgetEstimateEntity> calculatedProcessorClass;

    @Autowired
    private IBudgetActualSummaryProcessor<RegularBudgetEstimateItemEntity, RegularBudgetEstimateEntity> actualSummaryProcessor;


    public <E extends BudgetEstimateEntity, T extends BudgetEstimateItemEntity> IBudgetEstimateSummaryProcessor<T,E> getEstimateProcessorClass(Class<T> eClass){
        if(eClass.isAssignableFrom(RegularBudgetEstimateItemEntity.class)){
            return (IBudgetEstimateSummaryProcessor<T, E>) regularProcessorClass;
        }else{
            return (IBudgetEstimateSummaryProcessor<T, E>) calculatedProcessorClass;
        }
    }

    public <E extends BudgetEstimateEntity, T extends BudgetEstimateItemEntity> IBudgetActualSummaryProcessor<T,E> getActualProcessorClass(){
        return (IBudgetActualSummaryProcessor<T, E>) actualSummaryProcessor;
    }
}
