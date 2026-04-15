package com.konvergion.personalfinance.budgetmanagementapi.model.beanmapping;

import com.konvergion.personalfinance.budgetmanagementapi.model.dto.BudgetEstimateItemDto;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.RegularBudgetEstimateItemEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
@DecoratedWith(RegularBudgetItemMappingDecorator.class)
public interface RegularBudgetEstimateItemBeanMapper extends BudgetEstimateItemBeanMapper<RegularBudgetEstimateItemEntity, BudgetEstimateItemDto> {

    @Mapping(target = "budgetItemCurrency", ignore = true)
    @Mapping(target = "budgetEstimate", ignore = true)
    RegularBudgetEstimateItemEntity convertDtoToEntity(BudgetEstimateItemDto dto, String budgetId);

    @Mapping(target = "currencyCode", source = "entity.budgetItemCurrency.currencyCode")
    BudgetEstimateItemDto convertEntityToDto(RegularBudgetEstimateItemEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(BudgetEstimateItemDto dto,@MappingTarget RegularBudgetEstimateItemEntity entity);
}
