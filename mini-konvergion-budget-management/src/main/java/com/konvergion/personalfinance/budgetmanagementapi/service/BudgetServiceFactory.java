package com.konvergion.personalfinance.budgetmanagementapi.service;

import com.konvergion.personalfinance.budgetmanagementapi.model.dto.BudgetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BudgetServiceFactory {

    @Autowired
    private CalculatedBudgetServiceImpl calculatedBudgetService;

    @Autowired
    private RegularBudgetServiceImpl regularBudgetService;

    public <T extends IBudgetService> T getServiceClass(String budgetType) {
        BudgetType budgetTypeEnum = BudgetType.getBudgetTypeEnum(budgetType);
        if (BudgetType.REGULAR.equals(budgetTypeEnum)) {
            return (T) regularBudgetService;
        } else {
            return (T) calculatedBudgetService;
        }
    }

    public <T extends IBudgetService> T getServiceClass(BudgetType budgetType) {
        if (BudgetType.REGULAR.equals(budgetType)) {
            return (T) regularBudgetService;
        } else {
            return (T) calculatedBudgetService;
        }
    }
}
