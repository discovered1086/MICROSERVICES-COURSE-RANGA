package com.konvergion.personalfinance.minitransactionsearchapi.model.dto;

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

    private String accountName;

    private MonthSelectorDto monthSelector;

    private CustomDatePickerDto datePicker;
}
