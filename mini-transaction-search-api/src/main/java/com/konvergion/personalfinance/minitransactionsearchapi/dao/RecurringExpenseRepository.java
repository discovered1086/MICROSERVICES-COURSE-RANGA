package com.konvergion.personalfinance.minitransactionsearchapi.dao;

import com.konvergion.personalfinance.minitransactionsearchapi.model.entity.RecurringExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecurringExpenseRepository extends JpaRepository<RecurringExpenseEntity, String> {

    List<RecurringExpenseEntity> findByCustomerId(String customerId);
}