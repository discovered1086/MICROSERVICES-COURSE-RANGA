package com.konvergion.personalfinance.budgetmanagementapi.model.beanmapping;

import com.konvergion.personalfinance.budgetmanagementapi.model.dto.BudgetEstimateDto;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.BudgetListingDto;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.BudgetType;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.BudgetEstimateEntity;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.CalculationBudgetEstimateEntity;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.RegularBudgetEstimateEntity;
import org.mapstruct.*;

import java.time.OffsetDateTime;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = BudgetEstimateItemBeanMapper.class,
        imports = {OffsetDateTime.class, BudgetType.class},
        mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG)
public interface RegularBudgetEstimateBeanMapper extends BudgetEstimateBeanMapper<RegularBudgetEstimateEntity, BudgetEstimateDto> {


    @Override
    @InheritConfiguration
    @Mapping(target = "customerId", source = "customerId")
    @Mapping(target = "budgetCreationDate", expression = "java(OffsetDateTime.now())")
    @Mapping(target="budgetSummaries", ignore = true)
    RegularBudgetEstimateEntity convertDtoToEntity(BudgetEstimateDto dto, String customerId);

    @Override
    @InheritConfiguration
    BudgetListingDto getBudgetPeriods(RegularBudgetEstimateEntity entity);

    @Override
    @Mapping(target = "budgetType", expression = "java(BudgetType.REGULAR)")
    BudgetEstimateDto convertEntityToDto(RegularBudgetEstimateEntity entity);

    @Override
    @InheritConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "budgetSummaries", ignore = true)
    void updateEntityFromDto(BudgetEstimateDto dto, @MappingTarget RegularBudgetEstimateEntity entity);
}
