package com.konvergion.personalfinance.minifmitemmanagementapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepositoryAccountMetricsDTO extends AssetsMetricsDTO {
    private BigDecimal totalChecking;

    private BigDecimal totalSavings;

    private BigDecimal totalCashInHand;

    private BigDecimal totalCashSavings;


}
