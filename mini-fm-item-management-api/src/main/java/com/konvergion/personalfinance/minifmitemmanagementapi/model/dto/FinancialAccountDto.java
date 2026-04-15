package com.konvergion.personalfinance.minifmitemmanagementapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FinancialAccountDto implements Serializable {
    private String accountId;
    private String accountName;
    private String externalAccountId;
    private String accountType;
    private String accountSubType;
    private String accountNumber;
    private String accessToken;
    private String institutionName;
    private String accountStatus;
    private String accountLinkStatus;
}
