package com.konvergion.personalfinance.budgetmanagementapi.service;

import com.konvergion.personalfinance.budgetmanagementapi.model.beanmapping.BudgetEstimateBeanMapper;
import com.konvergion.personalfinance.budgetmanagementapi.model.beanmapping.BudgetEstimateItemBeanMapper;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.BudgetEstimateDto;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.BudgetEstimateItemDto;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.BudgetListingDto;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.*;
import com.konvergion.personalfinance.budgetmanagementapi.model.repository.BudgetEstimateItemRepository;
import com.konvergion.personalfinance.budgetmanagementapi.model.repository.BudgetEstimateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalculatedBudgetServiceImpl implements IBudgetService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CalculatedBudgetServiceImpl.class);

    @Autowired
    private BudgetEstimateBeanMapper<CalculationBudgetEstimateEntity, BudgetEstimateDto> beanMapper;

    @Autowired
    private BudgetEstimateItemBeanMapper<CalculationBudgetEstimateItemEntity, BudgetEstimateItemDto> calcItemBeanMapper;

    @Autowired
    private BudgetEstimateRepository<CalculationBudgetEstimateEntity> calculatedBudgetRepository;

    @Autowired
    private BudgetEstimateItemRepository<CalculationBudgetEstimateItemEntity> estimateItemRepository;

    @Autowired
    private IBudgetSyncService<CalculationBudgetEstimateItemEntity> budgetSyncService;

    @Override
    public void addBudget(BudgetEstimateDto estimateDto, String customerId) {
        calculatedBudgetRepository.save(beanMapper.convertDtoToEntity(estimateDto, customerId));
        LOGGER.info("Budget estimate has been added successfully");
    }

    @Override
    public List<BudgetListingDto> getBudgetPeriodsForCustomer(String customerId) {
        return calculatedBudgetRepository.findByCustomerId(customerId).stream()
                .map(budgetEntity -> beanMapper.getBudgetPeriods(budgetEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<BudgetEstimateDto> getAllBudgetsForCustomer(String customerId) {
        return calculatedBudgetRepository.findByCustomerId(customerId).stream()
                .map(budgetEntity -> beanMapper.convertEntityToDto(budgetEntity))
                .collect(Collectors.toList());
    }

    @Override
    public BudgetEstimateDto getSingleBudget(String budgetId) {
        return beanMapper.convertEntityToDto(calculatedBudgetRepository.findByBudgetEstimateId(budgetId));
    }

    @Override
    public List<BudgetEstimateItemDto> getAllBudgetItemsForBudget(String budgetId) {
        return calculatedBudgetRepository.findByBudgetEstimateId(budgetId).getBudgetEstimateItems()
                .stream()
                .map(entity -> calcItemBeanMapper.convertEntityToDto(entity)).collect(Collectors.toList());
    }

    @Override
    public void addBudgetItem(BudgetEstimateItemDto estimateItemDto, String budgetId, String customerId) {
        CalculationBudgetEstimateItemEntity savedEntity = estimateItemRepository.save(calcItemBeanMapper.convertDtoToEntity(estimateItemDto, budgetId));
        budgetSyncService.syncBudgetAmounts(budgetId, customerId, savedEntity, CalculationBudgetEstimateItemEntity.class);
    }
}
