package com.konvergion.personalfinance.minifmitemmanagementapi.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "financial_account_type")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinancialAccountTypeEntity {
    @Id
    @Column(length = 15, name = "account_type_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountSequenceGen")
    @GenericGenerator(name = "accountSequenceGen",
            strategy = "com.konvergion.personalfinance.minifmitemmanagementapi.model.CommonSequenceGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "ACCTTYP")})
    private String accountTypeId;

    @Column(name = "account_type",nullable = false)
    private String accountType;

    @Column(name = "account_sub_type",nullable = false)
    private String accountSubType;

    @Column(name = "account_type_description")
    private String accountTypeDescription;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FinancialAccountTypeEntity that = (FinancialAccountTypeEntity) o;
        return accountTypeId != null && Objects.equals(accountTypeId, that.accountTypeId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
