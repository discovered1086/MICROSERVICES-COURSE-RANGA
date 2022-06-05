package com.konvergion.personalfinance.minifmitemmanagementapi.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum FinancialAccountLinkStatus {
    CONNECTED("Connected"),
    RELINK_REQUIRED("Requires relink"),
    REMOVED("Removed"),
    NOT_LINKED("Not Linked");

    private final String displayValue;

    public static String getValue(FinancialAccountLinkStatus status){
        return status.displayValue;
    }
}
