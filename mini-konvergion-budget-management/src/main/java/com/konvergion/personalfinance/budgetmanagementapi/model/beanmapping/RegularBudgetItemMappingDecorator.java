package com.konvergion.personalfinance.budgetmanagementapi.model.beanmapping;

import com.konvergion.personalfinance.budgetmanagementapi.model.dto.BudgetEstimateItemDto;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.BudgetEstimateItemEntity;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.RegularBudgetEstimateEntity;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.RegularBudgetEstimateItemEntity;
import com.konvergion.personalfinance.budgetmanagementapi.model.repository.BudgetEstimateRepository;
import com.konvergion.personalfinance.budgetmanagementapi.model.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RegularBudgetItemMappingDecorator implements RegularBudgetEstimateItemBeanMapper {

    @Autowired
    @Qualifier("delegate")
    private RegularBudgetEstimateItemBeanMapper beanMapper;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private BudgetEstimateRepository<RegularBudgetEstimateEntity> estimateRepository;


    @Override
    public RegularBudgetEstimateItemEntity convertDtoToEntity(BudgetEstimateItemDto dto, String budgetId) {
        RegularBudgetEstimateItemEntity itemEntity = beanMapper.convertDtoToEntity(dto, budgetId);
        itemEntity.setBudgetItemCurrency(currencyRepository.findByCurrencyCode(dto.getCurrencyCode()));
        itemEntity.setBudgetEstimate(estimateRepository.findById(budgetId).orElse(null));
        return itemEntity;
    }

    @Override
    public void updateEntityFromDto(BudgetEstimateItemDto dto, RegularBudgetEstimateItemEntity entity) {

    }

    @Override
    public BudgetEstimateItemDto convertEntityToDto(RegularBudgetEstimateItemEntity entity) {
        BudgetEstimateItemDto itemDto = beanMapper.convertEntityToDto(entity);
        itemDto.setCurrencyCode(entity.getBudgetItemCurrency().getCurrencyCode());
        itemDto.setBudgetEstimateItemStatus(getItemStatus(entity));
        return itemDto;
    }

    private String getItemStatus(RegularBudgetEstimateItemEntity entity) {
        BigDecimal estimatedAmount = entity.getBudgetItemEstimateAmount();
        BigDecimal actualAmount = entity.getBudgetItemActualAmount();
        boolean estimateActualComparison = estimatedAmount.compareTo(actualAmount) > 0 || estimatedAmount.compareTo(actualAmount) == 0;
        if (entity.getTypeOfTransaction().equals("Account Credit Transaction")) {
            if (estimateActualComparison) {
                return "<span class=\"green font_bold\">$" +
                        estimatedAmount.subtract(actualAmount) + " still left to earn</span>";
            } else {
                return "<span class=\"red font_bold\">$" +
                        actualAmount.subtract(estimatedAmount) + " earned more than expected...!!</span>";
            }
        } else {
            if (estimateActualComparison) {
                return "<span class=\"green font_bold\">$" +
                        estimatedAmount.subtract(actualAmount) + " left to spend</span>";
            } else {
                return "<span class=\"red font_bold\">$" +
                        actualAmount.subtract(estimatedAmount) + " over budget</span>";
            }
        }

    }
}
