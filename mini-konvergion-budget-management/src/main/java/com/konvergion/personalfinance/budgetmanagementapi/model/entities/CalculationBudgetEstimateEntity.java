package com.konvergion.personalfinance.budgetmanagementapi.model.entities;

import com.konvergion.personalfinance.budgetmanagementapi.model.BudgetSummaryType;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "STANDARD_CALCULATION_BUDGET_ESTIMATE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CalculationBudgetEstimateEntity extends BudgetEstimateEntity {

	@Column(name = "BDGT_NM")
	private String budgetName;

	@OneToMany(mappedBy = "budgetEstimate",
			cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.REMOVE})
	@Fetch(FetchMode.JOIN)
	@ToString.Exclude
	private List<CalculationBudgetEstimateItemEntity> budgetEstimateItems;

	@Builder
	public CalculationBudgetEstimateEntity(String budgetEstimateId, int version,
										   @PastOrPresent(message = "Budget creation date should not be in the future") OffsetDateTime budgetCreationDate,
										   @NotBlank(message = "Customer id can't be blank") String customerId, String budgetName,
										   List<CalculationBudgetEstimateItemEntity> budgetEstimateItems,
										   Map<BudgetSummaryType, BudgetSummaryEntity> budgetSummaries) {
		super(budgetEstimateId, version, budgetCreationDate, customerId, budgetSummaries);
		this.budgetName = budgetName;
		this.budgetEstimateItems = budgetEstimateItems;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		CalculationBudgetEstimateEntity that = (CalculationBudgetEstimateEntity) o;
		return getBudgetEstimateId() != null && Objects.equals(getBudgetEstimateId(), that.getBudgetEstimateId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
