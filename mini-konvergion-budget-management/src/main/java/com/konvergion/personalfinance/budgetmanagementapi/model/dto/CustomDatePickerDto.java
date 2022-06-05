package com.konvergion.personalfinance.budgetmanagementapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomDatePickerDto {
    private String beginDate;

    private String endDate;
}
