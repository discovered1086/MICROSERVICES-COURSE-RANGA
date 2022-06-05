package com.konvergion.personalfinance.minitransactionsearchapi.model.dto;

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
public class RecurringExpenseResponseDTO implements Serializable {
    private BigDecimal totalYearlyExpenses;

    private BigDecimal totalMonthlyExpenses;

    private List<RecurringExpenseDto> recurringExpenses;

}
