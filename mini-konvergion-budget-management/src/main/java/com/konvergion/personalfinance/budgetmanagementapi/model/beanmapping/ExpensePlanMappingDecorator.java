package com.konvergion.personalfinance.budgetmanagementapi.model.beanmapping;

import com.konvergion.personalfinance.budgetmanagementapi.model.dto.ExpensePlanItemDTO;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.ExpensePlanItemEntity;
import com.konvergion.personalfinance.budgetmanagementapi.model.repository.CurrencyRepository;
import com.konvergion.personalfinance.budgetmanagementapi.model.repository.ExpenseStatusRepository;
import com.konvergion.personalfinance.budgetmanagementapi.model.repository.ItemPriorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class ExpensePlanMappingDecorator implements ExpensePlanBeanMapper {

    @Autowired
    @Qualifier("delegate")
    private ExpensePlanBeanMapper beanMapper;

    @Autowired
    private ExpenseStatusRepository expenseStatusRepository;

    @Autowired
    private ItemPriorityRepository itemPriorityRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public ExpensePlanItemEntity convertDtoToEntity(ExpensePlanItemDTO expensePlanItemDTO, String customerId) {
        ExpensePlanItemEntity expensePlanItemEntity = beanMapper.convertDtoToEntity(expensePlanItemDTO, customerId);
        expensePlanItemEntity.setTargetExpenseTimeFrame(expensePlanItemDTO.getTargetExpenseDate()
                .format(DateTimeFormatter.ofPattern("MMMM yyyy")));
        expensePlanItemEntity.setExpenseStatus(
                expenseStatusRepository.findById(Integer.parseInt(expensePlanItemDTO.getExpenseStatus())).orElseGet(() ->
                        expenseStatusRepository.findExpenseStatusEntityByStatusValueLike("NOT P")));
        expensePlanItemEntity.setItemPriority(itemPriorityRepository.findById(Integer.parseInt(expensePlanItemDTO.getItemPriority()))
                .orElseGet(() -> itemPriorityRepository.findItemPriorityEntityByPriorityValueIsLike("VERY L")));
        expensePlanItemEntity.setCurrencyEntity(currencyRepository.findByCurrencyCode(expensePlanItemDTO.getCurrencyCode()));
        return expensePlanItemEntity;
    }

    @Override
    public ExpensePlanItemDTO convertEntityToDto(ExpensePlanItemEntity expensePlanItemEntity) {
        ExpensePlanItemDTO expensePlanItemDTO = beanMapper.convertEntityToDto(expensePlanItemEntity);
        expensePlanItemDTO.setExpenseStatus(expensePlanItemEntity.getExpenseStatus().getStatusValue());
        expensePlanItemDTO.setItemPriority(expensePlanItemEntity.getItemPriority().getPriorityValue());
        return expensePlanItemDTO;
    }
}
