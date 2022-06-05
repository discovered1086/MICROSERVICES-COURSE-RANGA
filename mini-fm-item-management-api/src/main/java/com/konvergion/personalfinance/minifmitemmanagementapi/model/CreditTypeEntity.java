package com.konvergion.personalfinance.minifmitemmanagementapi.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "credit_types")
@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CreditTypeEntity {

    @Id
    @Column(length = 15, name = "credit_type_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "creditTypeSequenceGen")
    @GenericGenerator(name = "creditTypeSequenceGen",
            strategy = "com.konvergion.personalfinance.minifmitemmanagementapi.model.CommonSequenceGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "CRDTYP")})
    private String creditTypeId;

    @Column(name = "credit_type", nullable = false)
    private String creditType;

    @Column(name = "credit_type_description", length = 2000)
    private String creditTypeDescription;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CreditTypeEntity that = (CreditTypeEntity) o;
        return creditTypeId != null && Objects.equals(creditTypeId, that.creditTypeId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
