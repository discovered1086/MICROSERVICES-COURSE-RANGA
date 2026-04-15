package com.konvergion.personalfinance.minitransactionsearchapi.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.RecurringExpenseFrequencyDto;
import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.serialization.DateSerializer;
import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.serialization.ExpenseFrequencyTypeDeSerializer;
import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.serialization.ExpenseFrequencyTypeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecurringExpenseDto implements Serializable {
    private String expenseId;

    @JsonSerialize(using = DateSerializer.class)
    private LocalDate nextExpenseDate;

    private String expenseName;
    private BigDecimal expenseAmount;
    private String expenseDescription;

    private RecurringExpenseFrequencyDto frequency;
    private String expenseCategory;
    private String customerId;
}
