package com.konvergion.personalfinance.budgetmanagementapi.model.repository;

import com.konvergion.personalfinance.budgetmanagementapi.model.entities.BudgetEstimateEntity;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.RegularBudgetEstimateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegularBudgetEstimateRepository extends BudgetEstimateRepository<RegularBudgetEstimateEntity> {

}