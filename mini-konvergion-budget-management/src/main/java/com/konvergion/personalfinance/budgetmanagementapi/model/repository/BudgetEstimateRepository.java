package com.konvergion.personalfinance.budgetmanagementapi.model.repository;

import com.konvergion.personalfinance.budgetmanagementapi.model.entities.BudgetEstimateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BudgetEstimateRepository<T extends BudgetEstimateEntity> extends JpaRepository<T, String> {


    List<T> findByCustomerId(String customerId);

    T findByBudgetEstimateId(String budgetId);
}