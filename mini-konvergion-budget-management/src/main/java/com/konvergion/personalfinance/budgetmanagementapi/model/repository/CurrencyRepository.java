package com.konvergion.personalfinance.budgetmanagementapi.model.repository;

import com.konvergion.personalfinance.budgetmanagementapi.model.entities.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<CurrencyEntity, String> {

    CurrencyEntity findByCurrencyCode(String currencyCode);
}
