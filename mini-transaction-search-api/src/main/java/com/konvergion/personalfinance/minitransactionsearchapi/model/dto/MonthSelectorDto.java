package com.konvergion.personalfinance.minitransactionsearchapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MonthSelectorDto {
    private String selectedMonth;
    private String selectedYear;
}