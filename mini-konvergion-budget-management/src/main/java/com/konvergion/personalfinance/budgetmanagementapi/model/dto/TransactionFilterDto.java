package com.konvergion.personalfinance.budgetmanagementapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionFilterDto {

    private String transactionType;

    private String description;

    private String category;

    private CustomDatePickerDto datePicker;
}
