package com.konvergion.personalfinance.minifmitemmanagementapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountMetricsResponseDTO implements Serializable {

    private DebtMetricsResponseDTO<?> debtMetricsDTO;

    private NetWorthMetricsDTO netWorthMetricsDTO;

    private DepositoryAccountMetricsDTO cashMetricsDTO;

    private BigDecimal netCash;

}
