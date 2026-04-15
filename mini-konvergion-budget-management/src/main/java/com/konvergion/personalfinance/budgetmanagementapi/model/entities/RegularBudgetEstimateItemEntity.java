package com.konvergion.personalfinance.budgetmanagementapi.model.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@Table(name = "BUDGET_ESTIMATE_ITEM")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RegularBudgetEstimateItemEntity extends BudgetEstimateItemEntity {

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "BDGT_ID", referencedColumnName = "BDGT_EST_ID")
    @ToString.Exclude
    private RegularBudgetEstimateEntity budgetEstimate;

    @Column(name = "BDGT_ITM_ACT_AMT", columnDefinition = "NUMBER(20,2)")
    @PositiveOrZero(message = "Budget actual amount must be greater than 0.00")
    private BigDecimal budgetItemActualAmount;

    @Column(name = "BDGT_ITM_STTS")
    private String budgetEstimateItemStatus;

    @Builder
    public RegularBudgetEstimateItemEntity(String budgetItemId, int version, String typeOfTransaction,
                                           @NotBlank(message = "Budget estimate description must be provided") String budgetItemDescription,
                                           @NotNull(message = "Transaction currency must have a value") CurrencyEntity budgetItemCurrency,
                                           @Positive(message = "Budget item estimate amount must be greater than 0.00") BigDecimal budgetItemEstimateAmount,
                                           @FutureOrPresent(message = "Budget estimate entry date must not be in the past") OffsetDateTime budgetEstimateItemEntryDate,
                                           @NotNull(message = "Budget item category must not be empty") String budgetEstimateItemCategory,
                                           RegularBudgetEstimateEntity budgetEstimate, BigDecimal budgetItemActualAmount, String budgetEstimateItemStatus) {
        super(budgetItemId, version, typeOfTransaction, budgetItemDescription, budgetItemCurrency, budgetItemEstimateAmount, budgetEstimateItemEntryDate, budgetEstimateItemCategory);
        this.budgetEstimate = budgetEstimate;
        this.budgetItemActualAmount = budgetItemActualAmount;
        this.budgetEstimateItemStatus = budgetEstimateItemStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RegularBudgetEstimateItemEntity that = (RegularBudgetEstimateItemEntity) o;
        return getBudgetItemId() != null && Objects.equals(getBudgetItemId(), that.getBudgetItemId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
