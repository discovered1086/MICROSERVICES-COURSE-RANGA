package com.konvergion.personalfinance.budgetmanagementapi.service;



import com.konvergion.personalfinance.budgetmanagementapi.model.dto.ExpensePlanItemDTO;

import java.util.List;

public interface IExpensePlanService {

	ExpensePlanItemDTO addExpensePlan(ExpensePlanItemDTO expensePlanItemDTO, String customerId);
	
	void deleteExpensePlanItem(String itemId);
	
	List<ExpensePlanItemDTO> getAllExpensePlanItems(String customerId);
	
	ExpensePlanItemDTO getSingleExpensePlanItem(String planItemId);
}
