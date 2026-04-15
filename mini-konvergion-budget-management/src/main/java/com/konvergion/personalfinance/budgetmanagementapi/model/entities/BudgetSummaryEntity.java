package com.konvergion.personalfinance.budgetmanagementapi.model.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BudgetSummaryEntity implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 3099999947097457703L;

	@Column(name = "TOTAL_INCOME")
	private BigDecimal totalIncome;

	@Column(name = "TOTAL_DEBIT_TRANSACTIONS")
	private BigDecimal totalDebitTransactions;

	@Column(name = "TOTAL_CREDIT_TRANSACTIONS")
	private BigDecimal totalCreditCardTransactions;

	@Column(name = "TOTAL_EXPENSES")
	private BigDecimal totalExpenses;

	@Column(name = "TOTAL_SAVINGS")
	private BigDecimal totalSavings;
}
