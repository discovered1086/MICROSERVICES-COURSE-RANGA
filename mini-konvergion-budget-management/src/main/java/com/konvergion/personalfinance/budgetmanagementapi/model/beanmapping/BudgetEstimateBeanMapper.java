package com.konvergion.personalfinance.budgetmanagementapi.model.beanmapping;

import com.konvergion.personalfinance.budgetmanagementapi.model.dto.BudgetEstimateDto;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.BudgetListingDto;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.BudgetEstimateEntity;
import org.mapstruct.*;

import java.time.OffsetDateTime;


public interface BudgetEstimateBeanMapper<E extends BudgetEstimateEntity, D extends BudgetEstimateDto> {


    E convertDtoToEntity(D dto, String customerId);

    D convertEntityToDto(E entity);

    BudgetListingDto getBudgetPeriods(E entity);

    void updateEntityFromDto(D dto, E entity);
}
