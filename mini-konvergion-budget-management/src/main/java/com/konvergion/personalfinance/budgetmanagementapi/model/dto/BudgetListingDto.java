package com.konvergion.personalfinance.budgetmanagementapi.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.serialization.DateDeSerializer;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.serialization.DateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BudgetListingDto implements Serializable {
    private String budgetEstimateId;

    private String budgetName;

    @JsonDeserialize(using = DateDeSerializer.class)
    @JsonSerialize(using = DateSerializer.class)
    private LocalDate budgetPeriodStartDate;

    @JsonDeserialize(using = DateDeSerializer.class)
    @JsonSerialize(using = DateSerializer.class)
    private LocalDate budgetPeriodEndDate;
}
