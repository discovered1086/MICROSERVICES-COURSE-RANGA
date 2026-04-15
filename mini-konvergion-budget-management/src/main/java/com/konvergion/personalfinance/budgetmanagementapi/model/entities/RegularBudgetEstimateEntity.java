package com.konvergion.personalfinance.budgetmanagementapi.model.entities;

import com.konvergion.personalfinance.budgetmanagementapi.model.BudgetSummaryType;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "BUDGET_ESTIMATE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RegularBudgetEstimateEntity extends BudgetEstimateEntity {
	/**
	* 
	*/

	@Column(name = "BDGT_STRT_DT")
	private LocalDate budgetPeriodStartDate;

	@Column(name = "BDGT_END_DT")
	@Future(message = "Budget estimate end date must be a future date")
	private LocalDate budgetPeriodEndDate;

	@OneToMany(mappedBy = "budgetEstimate",
			cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.REMOVE})
	@Fetch(FetchMode.JOIN)
	@ToString.Exclude
	private List<RegularBudgetEstimateItemEntity> budgetEstimateItems;

	@Column(name = "BDGT_EDT_LCKDWN_DT")
	@FutureOrPresent(message = "Budget lockdown date should not be in the past")
	private LocalDate budgetEditLockDownDate;

	@Column(name = "BDGT_ENTRY_LCKDWN_DT")
	@FutureOrPresent(message = "Budget lockdown date should not be in the past")
	private LocalDate budgetEntryLockDownDate;

	@Builder
	public RegularBudgetEstimateEntity(String budgetEstimateId, int version, @PastOrPresent(message = "Budget creation date should not be in the future") OffsetDateTime budgetCreationDate,
									   @NotBlank(message = "Customer id can't be blank") String customerId,
									   LocalDate budgetPeriodStartDate, LocalDate budgetPeriodEndDate,
									   List<RegularBudgetEstimateItemEntity> budgetEstimateItems, LocalDate budgetEditLockDownDate,
									   LocalDate budgetEntryLockDownDate, Map<BudgetSummaryType, BudgetSummaryEntity> budgetSummaries) {
		super(budgetEstimateId, version, budgetCreationDate, customerId, budgetSummaries);
		this.budgetPeriodStartDate = budgetPeriodStartDate;
		this.budgetPeriodEndDate = budgetPeriodEndDate;
		this.budgetEstimateItems = budgetEstimateItems;
		this.budgetEditLockDownDate = budgetEditLockDownDate;
		this.budgetEntryLockDownDate = budgetEntryLockDownDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		RegularBudgetEstimateEntity that = (RegularBudgetEstimateEntity) o;
		return getBudgetEstimateId() != null && Objects.equals(getBudgetEstimateId(), that.getBudgetEstimateId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
