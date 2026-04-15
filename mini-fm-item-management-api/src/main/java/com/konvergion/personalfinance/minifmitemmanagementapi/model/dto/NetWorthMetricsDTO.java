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
public class NetWorthMetricsDTO implements Serializable {

    private BigDecimal totalAssets;

    private BigDecimal totalLiabilities;

    private BigDecimal netWorth;
}
