package com.konvergion.personalfinance.budgetmanagementapi.model.dto;

import lombok.AllArgsConstructor;

import java.util.EnumSet;

@AllArgsConstructor
public enum BudgetType {
    REGULAR("regular"),
    CALCULATED("calculated");

    private final String displayValue;

    public static BudgetType getBudgetTypeEnum(String inputValue) {
        return EnumSet.allOf(BudgetType.class).stream()
                .filter(budgetType -> budgetType.displayValue.equals(inputValue))
                .findAny().orElse(BudgetType.REGULAR);
    }

    public static String getBudgetTypeValue(BudgetType budgetType) {
        return budgetType.displayValue;
    }
}
