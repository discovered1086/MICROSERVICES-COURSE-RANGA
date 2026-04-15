package com.konvergion.personalfinance.minifmitemmanagementapi.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "account_details")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class FinancialAccountDetailsEntity {
    @Id
    @Column(length = 15, name = "account_id", updatable = false, nullable = false)
    private String accountId;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "account_id", nullable = false)
    @MapsId
    @ToString.Exclude
    private FinancialAccountEntity accountEntity;

    @Column(name = "available_balance", nullable = false, columnDefinition = "NUMBER(10,2)")
    private BigDecimal availableBalance;

    @Column(name = "current_balance", nullable = false, columnDefinition = "NUMBER(10,2)")
    private BigDecimal currentBalance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FinancialAccountDetailsEntity that = (FinancialAccountDetailsEntity) o;
        return accountId != null && Objects.equals(accountId, that.accountId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
