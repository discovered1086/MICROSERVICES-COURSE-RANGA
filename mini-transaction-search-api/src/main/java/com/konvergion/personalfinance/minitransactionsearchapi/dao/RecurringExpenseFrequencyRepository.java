package com.konvergion.personalfinance.minitransactionsearchapi.dao;

import com.konvergion.personalfinance.minitransactionsearchapi.model.entity.RecurringExpenseFrequencyEntity;
import com.konvergion.personalfinance.minitransactionsearchapi.model.entity.RecurringExpenseFrequencyEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecurringExpenseFrequencyRepository extends JpaRepository<RecurringExpenseFrequencyEntity, String> {

    Optional<RecurringExpenseFrequencyEntity> findByFrequencyPeriodAndFrequencyType(Integer period, RecurringExpenseFrequencyEnum type);
}