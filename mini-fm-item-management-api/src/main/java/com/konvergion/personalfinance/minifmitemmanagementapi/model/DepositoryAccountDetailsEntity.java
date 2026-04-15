package com.konvergion.personalfinance.minifmitemmanagementapi.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "depository_account_details")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class DepositoryAccountDetailsEntity extends FinancialAccountDetailsEntity {

    @Builder
    public DepositoryAccountDetailsEntity(String accountId, FinancialAccountEntity accountEntity,
                                          BigDecimal availableBalance, BigDecimal currentBalance, BigDecimal interestReceived) {
        super(accountId, accountEntity, availableBalance, currentBalance);
        this.interestReceived = interestReceived;
    }

    @Column(name = "interest_received", nullable = false, columnDefinition = "NUMBER(10,2)")
    private BigDecimal interestReceived;
}
