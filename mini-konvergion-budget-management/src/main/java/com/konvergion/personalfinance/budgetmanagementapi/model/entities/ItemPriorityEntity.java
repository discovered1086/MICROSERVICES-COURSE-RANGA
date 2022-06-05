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
@Table(name = "expense_plans_priority")
public class ItemPriorityEntity {

    @Id
    @Column(name = "priority_id", nullable = false)
    private int priorityId;

    @Column(name = "priority_value", nullable = false)
    private String priorityValue;

    @Column(name = "priority_description", length = 512)
    private String priorityDescription;
}
