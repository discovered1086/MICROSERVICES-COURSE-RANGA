package com.konvergion.personalfinance.budgetmanagementapi.model.beanmapping;

import com.konvergion.personalfinance.budgetmanagementapi.model.dto.BudgetEstimateItemDto;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.CalculationBudgetEstimateItemEntity;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.RegularBudgetEstimateItemEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
@DecoratedWith(CalculatedBudgetItemMappingDecorator.class)
public interface CalculatedBudgetEstimateItemBeanMapper extends BudgetEstimateItemBeanMapper<CalculationBudgetEstimateItemEntity, BudgetEstimateItemDto> {

    @Mapping(target = "budgetItemCurrency", ignore = true)
    @Mapping(target = "budgetEstimate", ignore = true)
    CalculationBudgetEstimateItemEntity convertDtoToEntity(BudgetEstimateItemDto dto, String budgetId);

    @Mapping(target = "currencyCode", source = "entity.budgetItemCurrency.currencyCode")
    @Mapping(target = "budgetEstimateItemStatus", ignore = true)
    BudgetEstimateItemDto convertEntityToDto(CalculationBudgetEstimateItemEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(BudgetEstimateItemDto dto,@MappingTarget CalculationBudgetEstimateItemEntity entity);
}
