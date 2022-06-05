package com.konvergion.personalfinance.budgetmanagementapi.model.repository;

import com.konvergion.personalfinance.budgetmanagementapi.model.entities.ItemPriorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPriorityRepository extends JpaRepository<ItemPriorityEntity, Integer> {

    ItemPriorityEntity findItemPriorityEntityByPriorityValueIsLike(String priorityValueText);
}
