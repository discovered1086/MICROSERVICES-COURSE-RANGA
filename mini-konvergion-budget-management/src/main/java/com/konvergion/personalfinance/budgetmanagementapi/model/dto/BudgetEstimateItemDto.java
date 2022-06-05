package com.konvergion.personalfinance.budgetmanagementapi.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.serialization.OffsetDateDeSerializer;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.serialization.OffsetDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BudgetEstimateItemDto implements Serializable {
    private  String budgetItemId;

    private  String typeOfTransaction;

    @NotBlank(message = "Budget estimate description must be provided")
    private  String budgetItemDescription;

    @NotNull(message = "Transaction currency must have a value")
    private  String currencyCode;

    @Positive(message = "Budget item estimate amount must be greater than 0.00")
    private  BigDecimal budgetItemEstimateAmount;

    @PositiveOrZero(message = "Budget actual amount must be greater than 0.00")
    private  BigDecimal budgetItemActualAmount;

    private  String budgetEstimateItemStatus;

    @JsonDeserialize(using = OffsetDateDeSerializer.class)
    @JsonSerialize(using = OffsetDateSerializer.class)
    @FutureOrPresent(message = "Budget estimate entry date must not be in the past")
    private  OffsetDateTime budgetEstimateItemEntryDate;

    @NotNull(message = "Budget item category must not be empty")
    private  String budgetEstimateItemCategory;
}
