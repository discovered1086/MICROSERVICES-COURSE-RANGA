package com.konvergion.personalfinance.budgetmanagementapi.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor // Required for JPA
@AllArgsConstructor
@Entity
@Table(name = "expense_status")
public class ExpenseStatusEntity {
    @Id
    @Column(name = "status_id", nullable = false)
    private int statusId;

    @Column(name = "status_value", nullable = false)
    private String statusValue;

    @Column(name = "status_description", length = 512)
    private String statusDescription;
}
