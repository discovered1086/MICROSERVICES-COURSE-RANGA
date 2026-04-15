package com.konvergion.personalfinance.budgetmanagementapi.model.entities;

import com.konvergion.personalfinance.budgetmanagementapi.model.BudgetSummaryType;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@ToString
public abstract class BudgetEstimateEntity implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 3099999947097457703L;

	@Id
	@Column(length = 20, name = "BDGT_EST_ID", updatable = false, insertable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "budgetSequenceGen")
	@GenericGenerator(name = "budgetSequenceGen", strategy = "com.konvergion.personalfinance.budgetmanagementapi.model.entities.CommonSequenceGenerator", parameters = {
			@Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "BDGT") })
	private String budgetEstimateId;

	@Version
	private int version;
	
	@Column(name = "BDGT_CRTN_DT")
	@Type(type = "org.hibernate.type.OffsetDateTimeType")
	@PastOrPresent(message = "Budget creation date should not be in the future")
	private OffsetDateTime budgetCreationDate;

	@Column(name = "CUST_ID", nullable = false)
	@NotBlank(message = "Customer id can't be blank")
	private String customerId;

	@ElementCollection
	@CollectionTable(name = "BUDGET_SUMMARIES",
			joinColumns = @JoinColumn(name = "BDGT_EST_ID"
					, referencedColumnName = "BDGT_EST_ID"))
	@MapKeyEnumerated(EnumType.STRING)
	@MapKeyColumn(name = "BDGT_SMMRY_TYPE")
	private Map<BudgetSummaryType, BudgetSummaryEntity> budgetSummaries;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		BudgetEstimateEntity that = (BudgetEstimateEntity) o;
		return budgetEstimateId != null && Objects.equals(budgetEstimateId, that.budgetEstimateId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
