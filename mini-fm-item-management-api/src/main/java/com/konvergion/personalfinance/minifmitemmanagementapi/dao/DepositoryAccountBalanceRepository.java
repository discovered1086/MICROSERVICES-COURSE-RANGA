package com.konvergion.personalfinance.minifmitemmanagementapi.dao;

import com.konvergion.personalfinance.minifmitemmanagementapi.model.DepositoryAccountDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositoryAccountBalanceRepository extends JpaRepository<DepositoryAccountDetailsEntity, String> {


}
