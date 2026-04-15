package com.konvergion.personalfinance.minitransactionsearchapi.dao;

import com.konvergion.personalfinance.minitransactionsearchapi.model.entity.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<CurrencyEntity, String> {
}
