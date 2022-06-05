package com.konvergion.personalfinance.budgetmanagementapi.service;

import com.konvergion.personalfinance.budgetmanagementapi.model.beanmapping.BudgetEstimateBeanMapper;
import com.konvergion.personalfinance.budgetmanagementapi.model.beanmapping.BudgetEstimateItemBeanMapper;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.BudgetEstimateDto;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.BudgetEstimateItemDto;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.BudgetListingDto;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.RegularBudgetEstimateEntity;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.RegularBudgetEstimateItemEntity;
import com.konvergion.personalfinance.budgetmanagementapi.model.repository.BudgetEstimateItemRepository;
import com.konvergion.personalfinance.budgetmanagementapi.model.repository.BudgetEstimateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegularBudgetServiceImpl implements IBudgetService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegularBudgetServiceImpl.class);

    @Autowired
    private BudgetEstimateBeanMapper<RegularBudgetEstimateEntity, BudgetEstimateDto> regularBeanMapper;

    @Autowired
    private BudgetEstimateItemBeanMapper<RegularBudgetEstimateItemEntity, BudgetEstimateItemDto> itemBeanMapper;

    @Autowired
    private BudgetEstimateRepository<RegularBudgetEstimateEntity> regularBudgetRepository;

    @Autowired
    private BudgetEstimateItemRepository<RegularBudgetEstimateItemEntity> estimateItemRepository;

    @Autowired
    private IBudgetSyncService<RegularBudgetEstimateItemEntity> budgetSyncService;

    @Override
    public void addBudget(BudgetEstimateDto estimateDto, String customerId) {
        regularBudgetRepository.save(regularBeanMapper.convertDtoToEntity(estimateDto, customerId));
        LOGGER.info("Budget estimate has been added successfully");
    }

    @Override
    public List<BudgetListingDto> getBudgetPeriodsForCustomer(String customerId) {
        return regularBudgetRepository.findByCustomerId(customerId).stream()
                .map(budgetEntity -> regularBeanMapper.getBudgetPeriods(budgetEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<BudgetEstimateDto> getAllBudgetsForCustomer(String customerId) {
        return regularBudgetRepository.findByCustomerId(customerId).stream()
                .map(budgetEntity -> regularBeanMapper.convertEntityToDto(budgetEntity))
                .collect(Collectors.toList());
    }

    @Override
    public BudgetEstimateDto getSingleBudget(String budgetId) {
        return regularBeanMapper.convertEntityToDto(regularBudgetRepository.findByBudgetEstimateId(budgetId));
    }

    @Override
    public List<BudgetEstimateItemDto> getAllBudgetItemsForBudget(String budgetId) {
        return regularBudgetRepository.findByBudgetEstimateId(budgetId).getBudgetEstimateItems()
                .stream()
                .map(entity -> itemBeanMapper.convertEntityToDto(entity)).collect(Collectors.toList());
    }

    @Override
    public void addBudgetItem(BudgetEstimateItemDto estimateItemDto, String budgetId, String customerId) {
        RegularBudgetEstimateItemEntity savedEntity = estimateItemRepository.save(itemBeanMapper.convertDtoToEntity(estimateItemDto, budgetId));
        budgetSyncService.syncBudgetAmounts(budgetId, customerId, savedEntity, RegularBudgetEstimateItemEntity.class);
    }
}
