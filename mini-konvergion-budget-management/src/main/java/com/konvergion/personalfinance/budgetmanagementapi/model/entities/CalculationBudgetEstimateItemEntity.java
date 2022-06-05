package com.konvergion.personalfinance.budgetmanagementapi.model.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@Table(name = "CALCULATION_BUDGET_ESTIMATE_ITEM")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CalculationBudgetEstimateItemEntity extends BudgetEstimateItemEntity {

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "BDGT_ID", referencedColumnName = "BDGT_EST_ID")
	@ToString.Exclude
	private CalculationBudgetEstimateEntity budgetEstimate;

	@Builder
	public CalculationBudgetEstimateItemEntity(String budgetItemId, int version, String typeOfTransaction,
											   @NotBlank(message = "Budget estimate description must be provided") String budgetItemDescription,
											   @NotNull(message = "Transaction currency must have a value") CurrencyEntity budgetItemCurrency,
											   @Positive(message = "Budget item estimate amount must be greater than 0.00") BigDecimal budgetItemEstimateAmount,
											   @FutureOrPresent(message = "Budget estimate entry date must not be in the past") OffsetDateTime budgetEstimateItemEntryDate,
											   @NotNull(message = "Budget item category must not be empty") String budgetEstimateItemCategory,
											   CalculationBudgetEstimateEntity budgetEstimate) {
		super(budgetItemId, version, typeOfTransaction, budgetItemDescription, budgetItemCurrency, budgetItemEstimateAmount, budgetEstimateItemEntryDate, budgetEstimateItemCategory);
		this.budgetEstimate = budgetEstimate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		CalculationBudgetEstimateItemEntity that = (CalculationBudgetEstimateItemEntity) o;
		return getBudgetItemId() != null && Objects.equals(getBudgetItemId(), that.getBudgetItemId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
