package com.konvergion.personalfinance.budgetmanagementapi.model.beanmapping;

import com.konvergion.personalfinance.budgetmanagementapi.model.dto.BudgetSummaryDto;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.BudgetSummaryEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface BudgetEstimateSummaryBeanMapper {
    BudgetSummaryEntity convertDtoToEntity(BudgetSummaryDto budgetEstimateSummaryDto);

    BudgetSummaryDto convertEntityToDto(BudgetSummaryEntity budgetEstimateSummaryEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(BudgetSummaryDto budgetEstimateSummaryDto, @MappingTarget BudgetSummaryEntity budgetEstimateSummaryEntity);
}
