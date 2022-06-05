package com.konvergion.personalfinance.minifmitemmanagementapi.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "property_account_details")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class PropertyAccountDetailsEntity implements Serializable {

    @Id
    @Column(length = 15, name = "account_id", updatable = false, nullable = false)
    private String accountId;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "account_id", nullable = false)
    @MapsId
    @ToString.Exclude
    private FinancialAccountEntity accountEntity;

    @Column(name = "property_valuation", nullable = false, columnDefinition = "NUMBER(10,2)")
    private BigDecimal propertyValuation;
}
