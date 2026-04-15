package com.konvergion.personalfinance.budgetmanagementapi.model.beanmapping;


import com.konvergion.personalfinance.budgetmanagementapi.model.dto.ExpensePlanItemDTO;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.ExpensePlanItemEntity;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
@DecoratedWith(ExpensePlanMappingDecorator.class)
public interface ExpensePlanBeanMapper {

    @Mapping(target="targetExpenseTimeFrame", ignore = true)
    @Mapping(target="itemPriority", ignore = true)
    @Mapping(target="expenseStatus", ignore = true)
    @Mapping(target="currencyEntity", ignore = true)
    @Mapping(target="customerId", source = "customerId")
    ExpensePlanItemEntity convertDtoToEntity(ExpensePlanItemDTO expensePlanItemDTO, String customerId);

    @Mapping(target="targetExpenseDate", ignore = true)
    @Mapping(target="itemPriority", ignore = true)
    @Mapping(target="expenseStatus", ignore = true)
    @Mapping(target="currencyCode", ignore = true)
    ExpensePlanItemDTO convertEntityToDto(ExpensePlanItemEntity expensePlanItemEntity);
}
