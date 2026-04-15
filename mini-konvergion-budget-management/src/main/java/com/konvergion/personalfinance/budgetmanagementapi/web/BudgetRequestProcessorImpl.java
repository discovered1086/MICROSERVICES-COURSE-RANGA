package com.konvergion.personalfinance.budgetmanagementapi.web;

import com.konvergion.personalfinance.budgetmanagementapi.model.dto.BudgetEstimateDto;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.BudgetEstimateItemDto;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.BudgetListingDto;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.BudgetType;
import com.konvergion.personalfinance.budgetmanagementapi.service.BudgetServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class BudgetRequestProcessorImpl implements IBudgetRequestProcessor {

    @Autowired
    private BudgetServiceFactory serviceFactory;

    @Override
    public void addBudget(BudgetEstimateDto estimateDto, String customerId) {
        serviceFactory.getServiceClass(estimateDto.getBudgetType()).addBudget(estimateDto, customerId);
    }

    @Override
    public List<BudgetListingDto> getBudgetListing(String customerId) {
        List<BudgetListingDto> allBudgets = new ArrayList<>();
        allBudgets.addAll(serviceFactory.getServiceClass(BudgetType.getBudgetTypeValue(BudgetType.CALCULATED)).getBudgetPeriodsForCustomer(customerId));
        allBudgets.addAll(serviceFactory.getServiceClass(BudgetType.getBudgetTypeValue(BudgetType.REGULAR)).getBudgetPeriodsForCustomer(customerId));
        return allBudgets;
    }

    @Override
    public List<BudgetEstimateDto> getAllBudgetsForCustomer(String customerId) {
        List<BudgetEstimateDto> allBudgets = new ArrayList<>();
        allBudgets.addAll(serviceFactory.getServiceClass(BudgetType.getBudgetTypeValue(BudgetType.CALCULATED)).getAllBudgetsForCustomer(customerId));
        allBudgets.addAll(serviceFactory.getServiceClass(BudgetType.getBudgetTypeValue(BudgetType.REGULAR)).getAllBudgetsForCustomer(customerId));
        return allBudgets;
    }

    @Override
    public BudgetEstimateDto getSingleBudget(String budgetId) {
        BudgetEstimateDto singleBudget = serviceFactory.getServiceClass(BudgetType.getBudgetTypeValue(BudgetType.CALCULATED)).getSingleBudget(budgetId);
        if (Objects.isNull(singleBudget)) {
            singleBudget = serviceFactory.getServiceClass(BudgetType.getBudgetTypeValue(BudgetType.REGULAR)).getSingleBudget(budgetId);
        }
        return singleBudget;
    }

    @Override
    public List<BudgetEstimateItemDto> getAllBudgetItemsForBudget(String budgetId) {
        BudgetEstimateDto singleBudget = serviceFactory.getServiceClass(BudgetType.getBudgetTypeValue(BudgetType.CALCULATED)).getSingleBudget(budgetId);
        if (Objects.isNull(singleBudget)) {
            return serviceFactory.getServiceClass(BudgetType.getBudgetTypeValue(BudgetType.REGULAR)).getAllBudgetItemsForBudget(budgetId);
        } else {
            return serviceFactory.getServiceClass(BudgetType.getBudgetTypeValue(BudgetType.CALCULATED)).getAllBudgetItemsForBudget(budgetId);
        }
    }

    @Override
    public void addBudgetItem(BudgetEstimateItemDto estimateItemDto, String budgetId,
                              String customerId) {
        BudgetEstimateDto singleBudget = serviceFactory.getServiceClass(BudgetType.getBudgetTypeValue(BudgetType.CALCULATED)).getSingleBudget(budgetId);
        if (Objects.isNull(singleBudget)) {
            serviceFactory.getServiceClass(BudgetType.getBudgetTypeValue(BudgetType.REGULAR)).addBudgetItem(estimateItemDto, budgetId, customerId);
        } else {
            serviceFactory.getServiceClass(BudgetType.getBudgetTypeValue(BudgetType.CALCULATED)).addBudgetItem(estimateItemDto, budgetId, customerId);
        }
    }
}
