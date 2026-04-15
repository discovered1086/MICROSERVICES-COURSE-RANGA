package com.konvergion.personalfinance.minifmitemmanagementapi.service;

import com.konvergion.personalfinance.minifmitemmanagementapi.model.dto.AccountResponseDTO;
import com.konvergion.personalfinance.minifmitemmanagementapi.model.dto.FinancialAccountDto;

public interface IAccountManagementService {

    void addAccount(FinancialAccountDto accountDto, String customerId);

    AccountResponseDTO getAccountsForCustomer(String customerId);

    FinancialAccountDto getSingleAccount(String accountId);

}
