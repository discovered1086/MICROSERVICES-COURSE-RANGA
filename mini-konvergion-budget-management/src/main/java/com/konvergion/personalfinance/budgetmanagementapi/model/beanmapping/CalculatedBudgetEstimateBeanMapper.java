package com.konvergion.personalfinance.budgetmanagementapi.model.beanmapping;

import com.konvergion.personalfinance.budgetmanagementapi.model.dto.BudgetEstimateDto;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.BudgetListingDto;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.BudgetType;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.BudgetEstimateEntity;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.CalculationBudgetEstimateEntity;
import org.mapstruct.*;

import java.time.OffsetDateTime;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = BudgetEstimateItemBeanMapper.class,
        imports = {OffsetDateTime.class, BudgetType.class},
        mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG)
public interface CalculatedBudgetEstimateBeanMapper extends BudgetEstimateBeanMapper<CalculationBudgetEstimateEntity, BudgetEstimateDto> {


    @Override
    @InheritConfiguration
    @Mapping(target = "customerId", source = "customerId")
    @Mapping(target = "budgetCreationDate", expression = "java(OffsetDateTime.now())")
    @Mapping(target="budgetSummaries", ignore = true)
    CalculationBudgetEstimateEntity convertDtoToEntity(BudgetEstimateDto dto, String customerId);

    @Override
    @InheritConfiguration
    BudgetListingDto getBudgetPeriods(CalculationBudgetEstimateEntity entity);

    @Override
    @Mapping(target = "budgetType", expression = "java(BudgetType.CALCULATED)")
    BudgetEstimateDto convertEntityToDto(CalculationBudgetEstimateEntity entity);

    @Override
    @InheritConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "budgetSummaries", ignore = true)
    void updateEntityFromDto(BudgetEstimateDto dto, @MappingTarget CalculationBudgetEstimateEntity entity);
}
