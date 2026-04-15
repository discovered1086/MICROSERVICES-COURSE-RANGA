package com.konvergion.personalfinance.minifmitemmanagementapi.dao;

import com.konvergion.personalfinance.minifmitemmanagementapi.model.CreditAccountDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditAccountBalanceRepository extends JpaRepository<CreditAccountDetailsEntity, String> {


}
