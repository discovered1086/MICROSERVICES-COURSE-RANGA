package com.konvergion.personalfinance.minifmitemmanagementapi.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum FinancialAccountStatus {
    OPEN("Open"),
    CLOSED("Closed");

    private final String displayValue;

    public static String getValue(FinancialAccountStatus status){
        return status.displayValue;
    }
}
