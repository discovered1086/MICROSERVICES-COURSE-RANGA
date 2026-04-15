package com.konvergion.personalfinance.minitransactionsearchapi.model.entity;

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
@Table(name = "account_transactions")
@NoArgsConstructor
@AllArgsConstructor
public class AccountTransactionEntity {

    @Id
    @Column(length = 15, name = "transaction_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactionSequenceGen")
    @GenericGenerator(name = "transactionSequenceGen",
            strategy = "com.konvergion.personalfinance.minitransactionsearchapi.model.entity.CommonSequenceGenerator",
            parameters = {
            @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1"),
            @org.hibernate.annotations.Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "TRN")})
    private String transactionId;

    @Column(name = "transaction_date")
    private LocalDate transactionDate;

    @Column(name = "transaction_posted_date")
    private LocalDate postedDate;

    @Column(name = "transaction_summary")
    private String transactionName;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private TransactionTypeEnum transactionType;

    @Column(name = "transaction_amount")
    private BigDecimal transactionAmount;

    @Column(name = "description")
    private String transactionDescription;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.PERSIST})
    @JoinColumn(name = "currency_code", referencedColumnName = "CRRNCY_CD")
    @Fetch(FetchMode.JOIN)
    private CurrencyEntity currency;

    @Column(name = "category")
    private String transactionCategory;

    @Column(name = "account_id")
    private String accountId;
}
