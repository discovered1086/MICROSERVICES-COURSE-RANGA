package com.konvergion.personalfinance.minifmitemmanagementapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetsMetricsDTO implements Serializable {
    private BigDecimal totalAssets;
}
