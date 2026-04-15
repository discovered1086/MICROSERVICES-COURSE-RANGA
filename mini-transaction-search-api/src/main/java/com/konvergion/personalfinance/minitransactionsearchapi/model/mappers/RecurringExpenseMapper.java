package com.konvergion.personalfinance.minitransactionsearchapi.model.mappers;

import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.RecurringExpenseDto;
import com.konvergion.personalfinance.minitransactionsearchapi.model.entity.RecurringExpenseEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
@DecoratedWith(RecurringExpenseMappingDecorator.class)
public interface RecurringExpenseMapper {
    @Mapping(target = "frequency", ignore = true)
    RecurringExpenseEntity convertDtoToEntity(RecurringExpenseDto recurringExpenseDto);

    @Mapping(target = "frequency", ignore = true)
    RecurringExpenseDto convertEntityToDto(RecurringExpenseEntity recurringExpenseEntity);
}
