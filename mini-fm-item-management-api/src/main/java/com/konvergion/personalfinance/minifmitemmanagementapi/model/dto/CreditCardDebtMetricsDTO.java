package com.konvergion.personalfinance.minifmitemmanagementapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardDebtMetricsDTO extends DebtMetricsDTO {
    private BigDecimal totalOpenDebt;

    private BigDecimal totalRevolvingDebt;

    private String debtType;

    private BigDecimal totalCreditUtilization;

    @Builder
    public CreditCardDebtMetricsDTO(BigDecimal totalDebt, BigDecimal totalOpenDebt,
                                    BigDecimal totalRevolvingDebt, String debtType, BigDecimal totalCreditUtilization) {
        super(totalDebt);
        this.totalOpenDebt = totalOpenDebt;
        this.totalRevolvingDebt = totalRevolvingDebt;
        this.debtType = debtType;
        this.totalCreditUtilization = totalCreditUtilization;
    }
}
