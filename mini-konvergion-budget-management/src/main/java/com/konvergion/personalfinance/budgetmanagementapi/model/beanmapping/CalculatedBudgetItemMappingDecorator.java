package com.konvergion.personalfinance.budgetmanagementapi.model.beanmapping;

import com.konvergion.personalfinance.budgetmanagementapi.model.dto.BudgetEstimateItemDto;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.CalculationBudgetEstimateEntity;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.CalculationBudgetEstimateItemEntity;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.RegularBudgetEstimateEntity;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.RegularBudgetEstimateItemEntity;
import com.konvergion.personalfinance.budgetmanagementapi.model.repository.BudgetEstimateRepository;
import com.konvergion.personalfinance.budgetmanagementapi.model.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CalculatedBudgetItemMappingDecorator implements CalculatedBudgetEstimateItemBeanMapper {

    @Autowired
    @Qualifier("delegate")
    private CalculatedBudgetEstimateItemBeanMapper beanMapper;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private BudgetEstimateRepository<CalculationBudgetEstimateEntity> estimateRepository;


    @Override
    public CalculationBudgetEstimateItemEntity convertDtoToEntity(BudgetEstimateItemDto dto, String budgetId) {
        CalculationBudgetEstimateItemEntity itemEntity = beanMapper.convertDtoToEntity(dto, budgetId);
        itemEntity.setBudgetItemCurrency(currencyRepository.findByCurrencyCode(dto.getCurrencyCode()));
        itemEntity.setBudgetEstimate(estimateRepository.findById(budgetId).orElse(null));
        return itemEntity;
    }

    @Override
    public void updateEntityFromDto(BudgetEstimateItemDto dto, CalculationBudgetEstimateItemEntity entity) {

    }

    @Override
    public BudgetEstimateItemDto convertEntityToDto(CalculationBudgetEstimateItemEntity entity) {
        BudgetEstimateItemDto itemDto = beanMapper.convertEntityToDto(entity);
        itemDto.setCurrencyCode(entity.getBudgetItemCurrency().getCurrencyCode());
        return itemDto;

    }
}
