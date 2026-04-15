package com.konvergion.personalfinance.minifmitemmanagementapi.dao;

import com.konvergion.personalfinance.minifmitemmanagementapi.model.FinancialAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FinancialAccountRepository extends JpaRepository<FinancialAccountEntity, String> {

    List<FinancialAccountEntity> findByCustomerId(String customerId);

    @Query(name = "debtAccountDetails", value = "SELECT a FROM FinancialAccountEntity a " +
            "WHERE a.customerId=:customerId " +
            "AND a.accountType.accountType='Credit'")
    List<FinancialAccountEntity> getDebtAccountDetails(@Param("customerId") String customerId);

    @Query(name = "depositoryAccountDetails", value = "SELECT a FROM FinancialAccountEntity a " +
            "WHERE a.customerId=:customerId " +
            "AND a.accountType.accountType ='Depository'")
    List<FinancialAccountEntity> getDepositoryAccountDetails(@Param("customerId") String customerId);

    @Query(name = "cashAccountDetails", value = "SELECT a FROM FinancialAccountEntity a " +
            "WHERE a.customerId=:customerId " +
            "AND a.accountType.accountType ='Wallet'")
    List<FinancialAccountEntity> getCashAccountDetails(@Param("customerId") String customerId);

    @Query(name = "vehicleAccountDetails", value = "SELECT a FROM FinancialAccountEntity a " +
            "WHERE a.customerId=:customerId " +
            "AND a.accountType.accountType ='Property' " +
            "AND a.accountType.accountSubType='Vehicle'")
    List<FinancialAccountEntity> getPropertyAccountDetails(@Param("customerId") String customerId);

    Optional<FinancialAccountEntity> findByExternalAccountId(String externalAccountId);


}
