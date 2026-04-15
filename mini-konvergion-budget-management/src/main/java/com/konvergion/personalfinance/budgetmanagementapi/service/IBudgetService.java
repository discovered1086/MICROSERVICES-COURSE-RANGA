package com.konvergion.personalfinance.budgetmanagementapi.service;

import com.konvergion.personalfinance.budgetmanagementapi.model.dto.BudgetEstimateDto;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.BudgetEstimateItemDto;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.BudgetListingDto;

import java.util.List;

public interface IBudgetService {

    void addBudget(BudgetEstimateDto estimateDto, String customerId);

    List<BudgetListingDto> getBudgetPeriodsForCustomer(String customerId);

    List<BudgetEstimateDto> getAllBudgetsForCustomer(String customerId);

    BudgetEstimateDto getSingleBudget(String budgetId);

    List<BudgetEstimateItemDto> getAllBudgetItemsForBudget(String budgetId);

    void addBudgetItem(BudgetEstimateItemDto estimateItemDto, String budgetId, String customerId);
}
