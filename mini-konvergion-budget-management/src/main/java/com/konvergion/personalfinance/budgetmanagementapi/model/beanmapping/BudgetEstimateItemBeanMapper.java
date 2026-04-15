package com.konvergion.personalfinance.budgetmanagementapi.model.beanmapping;

import com.konvergion.personalfinance.budgetmanagementapi.model.dto.BudgetEstimateItemDto;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.BudgetEstimateItemEntity;
import org.mapstruct.*;

public interface BudgetEstimateItemBeanMapper<E extends BudgetEstimateItemEntity, D extends BudgetEstimateItemDto> {

    E convertDtoToEntity(D dto, String budgetId);

    D convertEntityToDto(E entity);

    void updateEntityFromDto(D dto, E entity);
}
