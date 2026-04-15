package com.konvergion.personalfinance.minifmitemmanagementapi.web;

import com.konvergion.personalfinance.minifmitemmanagementapi.model.dto.*;
import com.konvergion.personalfinance.minifmitemmanagementapi.service.IAccountMetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AccountMetricsRequestProcessorImpl implements IAccountMetricsRequestProcessor {

    @Autowired
    private IAccountMetricsService accountMetricsService;

    @Override
    public AccountMetricsResponseDTO getAccountBalances(String customerId) {
        DebtMetricsResponseDTO<DebtMetricsDTO> debtMetricsInformation = accountMetricsService.getDebtMetricsInformation(customerId);
        DepositoryAccountMetricsDTO cashAccountMetrics = getCashMetrics(customerId);
        return AccountMetricsResponseDTO.builder()
                .debtMetricsDTO(debtMetricsInformation)
                .netWorthMetricsDTO(getNetWorth(customerId))
                .cashMetricsDTO(cashAccountMetrics)
                .netCash(cashAccountMetrics.getTotalChecking().subtract(debtMetricsInformation.getTotalDebt()))
                .build();
    }

    @Override
    public NetWorthMetricsDTO getNetWorth(String customerId) {
        BigDecimal totalDebt = accountMetricsService.getDebtMetricsInformation(customerId).getTotalDebt();
        DepositoryAccountMetricsDTO cashAccountMetrics = accountMetricsService.getCashAccountMetrics(customerId);
        PropertyAccountMetricsDTO propertyMetrics = accountMetricsService.getPropertyMetrics(customerId);
        NetWorthMetricsDTO metricsDTO = NetWorthMetricsDTO.builder()
                .totalAssets(cashAccountMetrics.getTotalAssets().add(propertyMetrics.getTotalAssets()))
                .totalLiabilities(totalDebt)
                .build();

        metricsDTO.setNetWorth(metricsDTO.getTotalAssets().subtract(metricsDTO.getTotalLiabilities()));
        return metricsDTO;
    }

    @Override
    public DepositoryAccountMetricsDTO getCashMetrics(String customerId) {
        return accountMetricsService.getCashAccountMetrics(customerId);
    }
}
