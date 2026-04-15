package com.konvergion.personalfinance.budgetmanagementapi.web;

import com.konvergion.personalfinance.budgetmanagementapi.model.dto.BudgetEstimateDto;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.BudgetEstimateItemDto;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.BudgetListingDto;

import java.util.List;

public interface IBudgetRequestProcessor {

    void addBudget(BudgetEstimateDto estimateDto, String customerId);

    List<BudgetListingDto> getBudgetListing(String customerId);

    List<BudgetEstimateDto> getAllBudgetsForCustomer(String customerId);

    BudgetEstimateDto getSingleBudget(String budgetId);

    List<BudgetEstimateItemDto> getAllBudgetItemsForBudget(String budgetId);

    void addBudgetItem(BudgetEstimateItemDto estimateItemDto, String budgetId, String customerId);
}
