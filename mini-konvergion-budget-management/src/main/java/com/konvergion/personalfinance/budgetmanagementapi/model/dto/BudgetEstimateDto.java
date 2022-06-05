package com.konvergion.personalfinance.budgetmanagementapi.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.konvergion.personalfinance.budgetmanagementapi.model.BudgetSummaryType;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.serialization.DateDeSerializer;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.serialization.DateSerializer;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.serialization.OffsetDateDeSerializer;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.serialization.OffsetDateSerializer;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.serialization.BudgetTypeDeSerializer;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.serialization.BudgetTypeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BudgetEstimateDto implements Serializable {
    private String budgetEstimateId;

    private String budgetName;

    @JsonDeserialize(using = BudgetTypeDeSerializer.class)
    @JsonSerialize(using = BudgetTypeSerializer.class)
    @NotNull(message = "Budget type can't be null")
    private BudgetType budgetType;

    @JsonDeserialize(using = DateDeSerializer.class)
    @JsonSerialize(using = DateSerializer.class)
    private LocalDate budgetPeriodStartDate;

    @JsonDeserialize(using = DateDeSerializer.class)
    @JsonSerialize(using = DateSerializer.class)
    //@Future(message = "Budget estimate end date must be a future date")
    private LocalDate budgetPeriodEndDate;


    private List<BudgetEstimateItemDto> budgetEstimateItems;

    @JsonDeserialize(using = DateDeSerializer.class)
    @JsonSerialize(using = DateSerializer.class)
    //@FutureOrPresent(message = "Budget lockdown date should not be in the past")
    private LocalDate budgetEditLockDownDate;

    @JsonDeserialize(using = DateDeSerializer.class)
    @JsonSerialize(using = DateSerializer.class)
    //@FutureOrPresent(message = "Budget lockdown date should not be in the past")
    private LocalDate budgetEntryLockDownDate;

    @JsonDeserialize(using = OffsetDateDeSerializer.class)
    @JsonSerialize(using = OffsetDateSerializer.class)
    //@PastOrPresent(message = "Budget creation date should not be in the future")
    private OffsetDateTime budgetCreationDate;

    private Map<BudgetSummaryType,BudgetSummaryDto> budgetSummaries;
}
