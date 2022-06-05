package com.konvergion.personalfinance.minifmitemmanagementapi.web;

import com.konvergion.personalfinance.minifmitemmanagementapi.model.dto.AccountMetricsResponseDTO;
import com.konvergion.personalfinance.minifmitemmanagementapi.model.dto.DepositoryAccountMetricsDTO;
import com.konvergion.personalfinance.minifmitemmanagementapi.model.dto.NetWorthMetricsDTO;

public interface IAccountMetricsRequestProcessor {

    AccountMetricsResponseDTO getAccountBalances(String customerId);

    NetWorthMetricsDTO getNetWorth(String customerId);

    DepositoryAccountMetricsDTO getCashMetrics(String customerId);
}
