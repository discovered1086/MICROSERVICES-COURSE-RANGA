package com.konvergion.personalfinance.budgetmanagementapi.model.repository;

import com.konvergion.personalfinance.budgetmanagementapi.model.entities.BudgetEstimateEntity;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.BudgetEstimateItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BudgetEstimateItemRepository<T extends BudgetEstimateItemEntity> extends JpaRepository<T, String> {
}