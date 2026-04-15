package com.konvergion.personalfinance.minifmitemmanagementapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.implementation.bind.annotation.Super;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyAccountMetricsDTO extends AssetsMetricsDTO {

    private BigDecimal totalValuation;

    @Builder
    public PropertyAccountMetricsDTO(BigDecimal totalAssets, BigDecimal totalValuation) {
        super(totalAssets);
        this.totalValuation = totalValuation;
    }
}
