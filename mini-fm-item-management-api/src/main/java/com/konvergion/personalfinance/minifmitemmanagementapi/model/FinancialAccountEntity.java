package com.konvergion.personalfinance.minifmitemmanagementapi.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "financial_account")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinancialAccountEntity implements Serializable {
    @Id
    @Column(length = 15, name = "account_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountSequenceGen")
    @GenericGenerator(name = "accountSequenceGen",
            strategy = "com.konvergion.personalfinance.minifmitemmanagementapi.model.CommonSequenceGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "ACCT")})
    private String accountId;

    @Column(name = "account_name", nullable = false)
    private String accountName;

    @Column(name = "external_account_id", nullable = false, unique = true)
    private String externalAccountId;

    @OneToOne
    @JoinColumn(name = "ACCT_TYPE_ID", referencedColumnName = "account_type_id")
    @Fetch(FetchMode.JOIN)
    private FinancialAccountTypeEntity accountType;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "institution_name")
    private String institutionName;

    @Column(name = "account_access_token", nullable = false)
    private String accessToken;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "currency_code", referencedColumnName = "CRRNCY_CD")
    @Fetch(FetchMode.JOIN)
    private CurrencyEntity currency;

    @Column(name = "customer_id", nullable = false)
    private String customerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_status", nullable = false)
    private FinancialAccountStatus accountStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_link_status", nullable = false)
    private FinancialAccountLinkStatus accountLinkStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FinancialAccountEntity that = (FinancialAccountEntity) o;
        return accountId != null && Objects.equals(accountId, that.accountId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
