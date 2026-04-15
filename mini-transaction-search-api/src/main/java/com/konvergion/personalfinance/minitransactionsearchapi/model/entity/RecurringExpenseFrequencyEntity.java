package com.konvergion.personalfinance.minitransactionsearchapi.model.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@Table(name = "recurring_expense_frequency")
@NoArgsConstructor
@AllArgsConstructor
public class RecurringExpenseFrequencyEntity {

    @Id
    @Column(length = 15, name = "frequency_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recurringExpenseFrequencySeq")
    @GenericGenerator(name = "recurringExpenseFrequencySeq",
            strategy = "com.konvergion.personalfinance.minitransactionsearchapi.model.entity.CommonSequenceGenerator",
            parameters = {
            @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1"),
            @org.hibernate.annotations.Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "RCCFRQ")})
    private String recurringExpenseFrequencyId;

    @Enumerated(EnumType.STRING)
    @Column(name = "frequency_type")
    private RecurringExpenseFrequencyEnum frequencyType;

    @Column(name = "frequency_period")
    private Integer frequencyPeriod;

    @Column(name = "frequency_description")
    private String frequencyDescription;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RecurringExpenseFrequencyEntity that = (RecurringExpenseFrequencyEntity) o;
        return recurringExpenseFrequencyId != null && Objects.equals(recurringExpenseFrequencyId, that.recurringExpenseFrequencyId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
