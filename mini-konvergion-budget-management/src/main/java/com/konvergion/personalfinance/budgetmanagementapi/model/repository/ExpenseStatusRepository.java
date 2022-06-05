package com.konvergion.personalfinance.budgetmanagementapi.model.repository;

import com.konvergion.personalfinance.budgetmanagementapi.model.entities.ExpenseStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseStatusRepository extends JpaRepository<ExpenseStatusEntity, Integer> {

    ExpenseStatusEntity findExpenseStatusEntityByStatusValueLike(String statusValueText);
}
