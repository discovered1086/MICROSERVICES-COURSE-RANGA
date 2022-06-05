package com.konvergion.personalfinance.minitransactionsearchapi.model.entity;

import com.konvergion.personalfinance.minitransactionsearchapi.model.entity.CommonSequenceGenerator;
import com.konvergion.personalfinance.minitransactionsearchapi.model.entity.CurrencyEntity;
import com.konvergion.personalfinance.minitransactionsearchapi.model.entity.RecurringExpenseFrequencyEntity;
import com.konvergion.personalfinance.minitransactionsearchapi.model.entity.TransactionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Builder
@Table(name = "recurring_expenses")
@NoArgsConstructor
@AllArgsConstructor
public class RecurringExpenseEntity {

    @Id
    @Column(length = 15, name = "expense_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recurringExpenseSequenceGen")
    @GenericGenerator(name = "recurringExpenseSequenceGen",
            strategy = "com.konvergion.personalfinance.minitransactionsearchapi.model.entity.CommonSequenceGenerator",
            parameters = {
            @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1"),
            @org.hibernate.annotations.Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "RCCEXP")})
    private String expenseId;

    @Column(name = "next_expense_date")
    private LocalDate nextExpenseDate;

    @Column(name = "expense_name")
    private String expenseName;

    @Column(name = "expense_amount")
    private BigDecimal expenseAmount;

    @Column(name = "expense_description")
    private String expenseDescription;

    @OneToOne(cascade = {CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.PERSIST})
    @JoinColumn(name = "frequency_id", referencedColumnName = "frequency_id")
    @Fetch(FetchMode.JOIN)
    private RecurringExpenseFrequencyEntity frequency;

    @Column(name = "expense_category")
    private String expenseCategory;

    @Column(name = "customer_id")
    private String customerId;
}
