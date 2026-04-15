package com.konvergion.personalfinance.minitransactionsearchapi.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.serialization.ExpenseFrequencyTypeDeSerializer;
import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.serialization.ExpenseFrequencyTypeSerializer;
import com.konvergion.personalfinance.minitransactionsearchapi.model.entity.RecurringExpenseFrequencyEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecurringExpenseFrequencyDto implements Serializable {
    @JsonSerialize(using = ExpenseFrequencyTypeSerializer.class)
    @JsonDeserialize(using = ExpenseFrequencyTypeDeSerializer.class)
    private RecurringExpenseFrequencyEnum frequencyType;
    private Integer frequencyPeriod;
}
