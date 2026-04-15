package com.konvergion.personalfinance.budgetmanagementapi.model.entities;


import com.konvergion.personalfinance.budgetmanagementapi.model.sequencegenerators.CommonSequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor // Required for JPA
@AllArgsConstructor
@Entity
@Table(name = "expense_plans")
public class ExpensePlanItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expensePlanSequenceGen")
    @GenericGenerator(name = "expensePlanSequenceGen", strategy = "com.konvergion.personalfinance.budgetmanagementapi.model.sequencegenerators.CommonSequenceGenerator", parameters = {
            @Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1"),
            @Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "PLNITM")})
    @Column(length = 20, name = "item_id", updatable = false, insertable = false)
    private String itemId;

    @Column(name = "item_description", length = 1000, nullable = false, unique = true)
    private String itemDescription;

    @Column(name = "item_url", length = 4000)
    private String itemLinkUrl;

    @Column(name = "item_category", length = 100)
    private String itemCategory;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "expense_priority_id", referencedColumnName = "priority_id", nullable = false)
    private ItemPriorityEntity itemPriority;

    @Column(name = "target_expense_timeframe", nullable = false)
    private String targetExpenseTimeFrame;

    @Column(name = "actual_expense_date")
    private LocalDate actualPurchaseDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_code", referencedColumnName = "CRRNCY_CD")
    private CurrencyEntity currencyEntity;

    @Column(name = "estimated_amount", nullable = false)
    private BigDecimal estimatedAmount;

    @Column(name = "actual_amount")
    private BigDecimal actualAmount;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "expense_status_id", referencedColumnName = "status_id", nullable = false)
    private ExpenseStatusEntity expenseStatus;

    @Column(name = "CUST_ID", nullable = false)
    @NotBlank(message = "Customer id can't be blank")
    private String customerId;
}
