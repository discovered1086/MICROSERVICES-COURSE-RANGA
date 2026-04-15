package com.konvergion.personalfinance.minifmitemmanagementapi.model.dto;

import com.konvergion.personalfinance.minifmitemmanagementapi.model.dto.DebtMetricsDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DebtMetricsResponseDTO<T extends DebtMetricsDTO> implements Serializable {
    private BigDecimal totalDebt;

    private List<T> debtMetrics;

}
