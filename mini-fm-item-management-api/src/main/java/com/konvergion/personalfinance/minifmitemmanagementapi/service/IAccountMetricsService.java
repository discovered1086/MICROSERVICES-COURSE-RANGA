package com.konvergion.personalfinance.minifmitemmanagementapi.service;

import com.konvergion.personalfinance.minifmitemmanagementapi.model.dto.*;

import java.util.List;

public interface IAccountMetricsService {

    <T extends DebtMetricsDTO> DebtMetricsResponseDTO<T> getDebtMetricsInformation(String customerId);

    DepositoryAccountMetricsDTO getCashAccountMetrics(String customerId);

    PropertyAccountMetricsDTO getPropertyMetrics(String customerId);

    void updateAccountBalance(List<FinancialAccountBalanceDto> balanceDtos);

}
