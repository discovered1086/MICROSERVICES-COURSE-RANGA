package com.konvergion.personalfinance.budgetmanagementapi.processors;

import com.konvergion.personalfinance.budgetmanagementapi.external.ITransactionExternalRepository;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.CustomDatePickerDto;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.SearchTransactionStandardResponseDTO;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.TransactionFilterDto;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.BudgetEstimateEntity;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.BudgetEstimateItemEntity;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.RegularBudgetEstimateEntity;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.RegularBudgetEstimateItemEntity;
import com.konvergion.personalfinance.budgetmanagementapi.model.repository.RegularBudgetEstimateItemRepository;
import com.konvergion.personalfinance.budgetmanagementapi.model.repository.RegularBudgetEstimateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

@Component
public class RegularBudgetSummaryProcessorImpl implements IBudgetEstimateSummaryProcessor<RegularBudgetEstimateItemEntity, RegularBudgetEstimateEntity>,
        IBudgetActualSummaryProcessor<RegularBudgetEstimateItemEntity, RegularBudgetEstimateEntity> {

    @Autowired
    private ITransactionExternalRepository externalRepository;

    @Autowired
    private RegularBudgetEstimateRepository estimateRepository;

    @Autowired
    private RegularBudgetEstimateItemRepository estimateItemRepository;

    @Autowired
    private ItemStatusProcessorHelper itemStatusProcessorHelper;

    @Override
    public void updateBudgetEstimateSummary(RegularBudgetEstimateItemEntity budgetItem, String budgetId, String customerId) {
        RegularBudgetEstimateEntity budgetEstimate = estimateRepository.findByBudgetEstimateId(budgetId);
        SummaryCalculator<RegularBudgetEstimateEntity> summaryCalculator = new EstimateSummaryCalculatorImpl<>();
        summaryCalculator.setSummaryCalculatorImpl(getImplementationClass(budgetItem.getTypeOfTransaction()));
        summaryCalculator.updateBudgetSummary(budgetEstimate, budgetItem.getBudgetItemEstimateAmount());
        estimateRepository.save(budgetEstimate);
    }

    @Override
    public void updateBudgetActualSummary(RegularBudgetEstimateItemEntity budgetItem, String budgetId, String customerId) {
        RegularBudgetEstimateEntity budgetEstimate = estimateRepository.findByBudgetEstimateId(budgetId);
        TransactionFilterDto filterDto = TransactionFilterDto.builder()
                .category(budgetItem.getBudgetEstimateItemCategory())
                .datePicker(CustomDatePickerDto.builder()
                        .beginDate(budgetEstimate.getBudgetPeriodStartDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")))
                        .endDate(budgetEstimate.getBudgetPeriodEndDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")))
                        .build())
                .build();
        SearchTransactionStandardResponseDTO responseDTO = externalRepository.getTransactionForCategory(customerId, filterDto);

        SummaryCalculator<RegularBudgetEstimateEntity> summaryCalculator = new ActualSummaryCalculatorImpl<>();
        summaryCalculator.setSummaryCalculatorImpl(getImplementationClass(budgetItem.getTypeOfTransaction()));
        BigDecimal amount = BigDecimal.valueOf("Account Credit Transaction".equals(budgetItem.getTypeOfTransaction())
                ? responseDTO.getTotalCreditAmount()
                : responseDTO.getTotalDebitAmount());
        summaryCalculator.updateBudgetSummary(budgetEstimate, amount);
        estimateRepository.save(budgetEstimate);
        budgetItem.setBudgetItemActualAmount(amount);
        budgetItem.setBudgetEstimateItemStatus(itemStatusProcessorHelper.getItemStatus(budgetItem));
        estimateItemRepository.save(budgetItem);
    }
}
