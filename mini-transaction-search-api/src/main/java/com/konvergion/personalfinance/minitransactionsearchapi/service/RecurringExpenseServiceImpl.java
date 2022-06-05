package com.konvergion.personalfinance.minitransactionsearchapi.service;

import com.konvergion.personalfinance.minitransactionsearchapi.dao.RecurringExpenseRepository;
import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.RecurringExpenseDto;
import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.RecurringExpenseResponseDTO;
import com.konvergion.personalfinance.minitransactionsearchapi.model.entity.RecurringExpenseFrequencyEnum;
import com.konvergion.personalfinance.minitransactionsearchapi.model.mappers.RecurringExpenseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecurringExpenseServiceImpl implements IRecurringExpenseService {

    @Autowired
    private RecurringExpenseRepository expenseRepository;

    @Autowired
    private RecurringExpenseMapper mapper;

    @Override
    public RecurringExpenseResponseDTO getRecurringExpenses(String customerId) {
        List<RecurringExpenseDto> expenseDtoList = expenseRepository.findByCustomerId(customerId).stream()
                .map(entity -> mapper.convertEntityToDto(entity))
                .collect(Collectors.toList());

        double yearlyExpense = expenseDtoList.stream()
                .mapToDouble(dto -> totalPerYear(dto.getFrequency().getFrequencyType(),
                        dto.getFrequency().getFrequencyPeriod(), dto.getExpenseAmount()).doubleValue())
                .sum();

        return RecurringExpenseResponseDTO.builder()
                .recurringExpenses(expenseDtoList)
                .totalYearlyExpenses(BigDecimal.valueOf(yearlyExpense))
                .totalMonthlyExpenses(BigDecimal.valueOf(yearlyExpense).divide(BigDecimal.valueOf(12), RoundingMode.CEILING))
                .build();
    }

    @Override
    public void addRecurringExpense(RecurringExpenseDto expenseDto, String customerId) {
        expenseDto.setCustomerId(customerId);
        expenseRepository.save(mapper.convertDtoToEntity(expenseDto));
    }

    private BigDecimal totalPerYear(RecurringExpenseFrequencyEnum frequencyType, int frequencyPeriod, BigDecimal amount) {
        switch (frequencyType) {
            case YEAR:
                BigDecimal divider = BigDecimal.ONE.divide(BigDecimal.valueOf(frequencyPeriod), RoundingMode.CEILING);
                return amount.divide(divider, RoundingMode.CEILING);
            case MONTH:
                BigDecimal multiplier = BigDecimal.valueOf(12).divide(BigDecimal.valueOf(frequencyPeriod), RoundingMode.CEILING);
                return amount.multiply(multiplier);
            case WEEK:
                BigDecimal weekMultiplier = BigDecimal.valueOf(52).divide(BigDecimal.valueOf(frequencyPeriod), RoundingMode.CEILING);
                return amount.multiply(weekMultiplier);
        }

        return BigDecimal.ZERO;
    }
}
