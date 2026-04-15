package com.konvergion.personalfinance.minifmitemmanagementapi.dao;

import com.konvergion.personalfinance.minifmitemmanagementapi.model.FinancialAccountTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FinancialAccountTypeRepository extends JpaRepository<FinancialAccountTypeEntity, String> {

    Optional<FinancialAccountTypeEntity> findFinancialAccountTypeEntityByAccountTypeAndAccountSubType(String accountType, String accountSubType);

}
