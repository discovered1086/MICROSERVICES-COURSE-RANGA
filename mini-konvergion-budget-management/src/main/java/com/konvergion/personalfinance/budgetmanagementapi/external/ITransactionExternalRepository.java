package com.konvergion.personalfinance.budgetmanagementapi.external;

import com.konvergion.personalfinance.budgetmanagementapi.model.dto.SearchTransactionStandardResponseDTO;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.TransactionFilterDto;

public interface ITransactionExternalRepository {

    SearchTransactionStandardResponseDTO getTransactionForCategory(String customerId, TransactionFilterDto filterDto);
}
