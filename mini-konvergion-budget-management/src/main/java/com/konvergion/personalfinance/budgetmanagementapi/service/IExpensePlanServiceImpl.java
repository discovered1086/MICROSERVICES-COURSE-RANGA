package com.konvergion.personalfinance.budgetmanagementapi.service;


import com.konvergion.personalfinance.budgetmanagementapi.model.beanmapping.ExpensePlanBeanMapper;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.ExpensePlanItemDTO;
import com.konvergion.personalfinance.budgetmanagementapi.model.entities.ExpensePlanItemEntity;
import com.konvergion.personalfinance.budgetmanagementapi.model.repository.ExpensePlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IExpensePlanServiceImpl implements IExpensePlanService {

    private final ExpensePlanRepository expensePlanRepository;

    private final ExpensePlanBeanMapper expensePlanBeanMapper;

    @Autowired
    public IExpensePlanServiceImpl(ExpensePlanRepository expensePlanRepository, ExpensePlanBeanMapper expensePlanBeanMapper) {
        this.expensePlanRepository = expensePlanRepository;
        this.expensePlanBeanMapper = expensePlanBeanMapper;
    }

    @Override
    public ExpensePlanItemDTO addExpensePlan(ExpensePlanItemDTO expensePlanItemDTO,String customerId) {
        final ExpensePlanItemEntity expensePlanItemEntity = expensePlanBeanMapper.convertDtoToEntity(expensePlanItemDTO, customerId);
        expensePlanRepository.save(expensePlanItemEntity);
        return expensePlanBeanMapper.convertEntityToDto(expensePlanItemEntity);
    }

    @Override
    public void deleteExpensePlanItem(String itemId) {
        expensePlanRepository.deleteById(itemId);
    }

    @Override
    public List<ExpensePlanItemDTO> getAllExpensePlanItems(String customerId) {
        final var allExpensePlanItems = expensePlanRepository.findAll();
        return allExpensePlanItems.stream().map(expensePlanBeanMapper::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ExpensePlanItemDTO getSingleExpensePlanItem(String planItemId) {
        return expensePlanRepository.findById(planItemId)
                .map(expensePlanBeanMapper::convertEntityToDto)
                .orElse(new ExpensePlanItemDTO());
    }
}
