package com.konvergion.personalfinance.budgetmanagementapi.model.repository;



import com.konvergion.personalfinance.budgetmanagementapi.model.entities.ExpensePlanItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensePlanRepository extends JpaRepository<ExpensePlanItemEntity, String> {

}
