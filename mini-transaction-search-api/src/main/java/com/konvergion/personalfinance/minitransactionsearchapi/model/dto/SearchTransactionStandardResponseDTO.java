package com.konvergion.personalfinance.minitransactionsearchapi.model.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"transactionCount", "totalAmount","transactions"})
public class SearchTransactionStandardResponseDTO {

    private int transactionCount;

    private Double totalDebitAmount;

    private Double totalCreditAmount;

    private List<AccountTransactionDto> transactions;

}
