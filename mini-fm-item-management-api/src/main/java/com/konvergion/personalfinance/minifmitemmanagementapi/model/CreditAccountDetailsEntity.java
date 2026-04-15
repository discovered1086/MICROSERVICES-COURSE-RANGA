package com.konvergion.personalfinance.minifmitemmanagementapi.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "credit_account_details")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CreditAccountDetailsEntity extends FinancialAccountDetailsEntity {

    @Builder
    public CreditAccountDetailsEntity(String accountId, FinancialAccountEntity accountEntity,
                                      BigDecimal availableBalance, BigDecimal currentBalance, BigDecimal creditLimit) {
        super(accountId, accountEntity, availableBalance, currentBalance);
        this.creditLimit = creditLimit;
    }

    @Column(name = "credit_limit", nullable = false, columnDefinition = "NUMBER(10,2)")
    private BigDecimal creditLimit;

    @OneToOne
    @JoinColumn(name = "credit_type_id", referencedColumnName = "credit_type_id")
    private CreditTypeEntity creditType;
}
