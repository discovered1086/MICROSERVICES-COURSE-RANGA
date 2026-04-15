package com.konvergion.personalfinance.budgetmanagementapi.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"transactionCount", "totalAmount","transactions"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchTransactionStandardResponseDTO {

    private int transactionCount;

    private Double totalDebitAmount;

    private Double totalCreditAmount;

}
