package com.konvergion.personalfinance.budgetmanagementapi.model.entities;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@ToString
public abstract class BudgetEstimateItemEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3685081968601619498L;

	@Id
	@Column(length = 40, name = "BDGT_ITM_ID", updatable = false, insertable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "budgetItemSequenceGen")
	@GenericGenerator(name = "budgetItemSequenceGen", strategy = "com.konvergion.personalfinance.budgetmanagementapi.model.entities.CommonSequenceGenerator", parameters = {
			@Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "BDGTITM") })
	private String budgetItemId;

	@Version
	private int version;

	@Column(name = "TRNSCTN_CTGRY_CD")
	private String typeOfTransaction;

	@Column(length = 1000, name = "BDGT_ITM_DSCRPTN", nullable = false)
	@NotBlank(message = "Budget estimate description must be provided")
	private String budgetItemDescription;

	@ManyToOne
	@JoinColumn(name = "TRNSCTN_CRNCY", referencedColumnName = "CRRNCY_CD", nullable = false)
	@NotNull(message = "Transaction currency must have a value")
	@ToString.Exclude
	private CurrencyEntity budgetItemCurrency;

	@Column(name = "BDGT_ITM_EST_AMT", columnDefinition = "NUMBER(20,2)")
	@Positive(message = "Budget item estimate amount must be greater than 0.00")
	private BigDecimal budgetItemEstimateAmount;

	@Column(name = "BDGT_ENTRY_DT", columnDefinition = "TIMESTAMP")
	@Type(type = "org.hibernate.type.OffsetDateTimeType")
	@FutureOrPresent(message = "Budget estimate entry date must not be in the past")
	private OffsetDateTime budgetEstimateItemEntryDate;

	@Column(name = "CTGRY_ID")
	@NotNull(message = "Budget item category must not be empty")
	private String budgetEstimateItemCategory;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		BudgetEstimateItemEntity that = (BudgetEstimateItemEntity) o;
		return budgetItemId != null && Objects.equals(budgetItemId, that.budgetItemId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
