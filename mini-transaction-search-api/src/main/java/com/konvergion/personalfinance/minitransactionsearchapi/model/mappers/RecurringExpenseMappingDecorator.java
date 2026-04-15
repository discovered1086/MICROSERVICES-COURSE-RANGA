package com.konvergion.personalfinance.minitransactionsearchapi.model.mappers;

import com.konvergion.personalfinance.minitransactionsearchapi.dao.CurrencyRepository;
import com.konvergion.personalfinance.minitransactionsearchapi.dao.RecurringExpenseFrequencyRepository;
import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.AccountTransactionBatchDto;
import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.AccountTransactionDto;
import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.RecurringExpenseDto;
import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.RecurringExpenseFrequencyDto;
import com.konvergion.personalfinance.minitransactionsearchapi.model.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
public class RecurringExpenseMappingDecorator implements RecurringExpenseMapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecurringExpenseMappingDecorator.class);

    @Autowired
    @Qualifier("delegate")
    private RecurringExpenseMapper mapper;

    @Autowired
    private RecurringExpenseFrequencyRepository frequencyRepository;

    @Override
    public RecurringExpenseEntity convertDtoToEntity(RecurringExpenseDto recurringExpenseDto) {
        RecurringExpenseEntity expenseEntity = mapper.convertDtoToEntity(recurringExpenseDto);
        RecurringExpenseFrequencyDto frequency = recurringExpenseDto.getFrequency();
        RecurringExpenseFrequencyEntity frequencyEntity = frequencyRepository.findByFrequencyPeriodAndFrequencyType(
                frequency.getFrequencyPeriod(), frequency.getFrequencyType()).orElseThrow();
        expenseEntity.setFrequency(frequencyEntity);
        return expenseEntity;
    }

    @Override
    public RecurringExpenseDto convertEntityToDto(RecurringExpenseEntity recurringExpenseEntity) {
        RecurringExpenseDto expenseDto = mapper.convertEntityToDto(recurringExpenseEntity);
        RecurringExpenseFrequencyEntity frequency = recurringExpenseEntity.getFrequency();
        expenseDto.setFrequency(RecurringExpenseFrequencyDto.builder()
                .frequencyPeriod(frequency.getFrequencyPeriod())
                .frequencyType(frequency.getFrequencyType())
                .build());
        return expenseDto;
    }
}
