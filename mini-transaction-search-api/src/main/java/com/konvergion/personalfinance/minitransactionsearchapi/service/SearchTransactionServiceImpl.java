package com.konvergion.personalfinance.minitransactionsearchapi.service;

import com.konvergion.personalfinance.minitransactionsearchapi.dao.AccountTransactionRepository;
import com.konvergion.personalfinance.minitransactionsearchapi.helpers.QuerySpecificationHelper;
import com.konvergion.personalfinance.minitransactionsearchapi.model.GenericSpecification;
import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.*;
import com.konvergion.personalfinance.minitransactionsearchapi.model.entity.AccountTransactionEntity;
import com.konvergion.personalfinance.minitransactionsearchapi.model.mappers.TransactionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SearchTransactionServiceImpl implements ISearchTransactionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchTransactionServiceImpl.class);

    @Autowired
    private AccountTransactionRepository repository;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private QuerySpecificationHelper queryHelper;

    @Override
    public SearchTransactionStandardResponseDTO getAllTransactions(List<String> accountIds) {
        return getAllTransactionsForCustomer(accountIds);
    }

    private SearchTransactionStandardResponseDTO getAllTransactionsForCustomer(List<String> accountIds) {
        GenericSpecification<AccountTransactionEntity> specification = queryHelper.generateSpecification(TransactionFilterDto.builder()
                .build(), accountIds);
        return generateResponse(repository.findAll(specification).stream()
                .map(entity -> transactionMapper.convertEntityToDto(entity))
                .collect(Collectors.toList()));
    }

    private SearchTransactionStandardResponseDTO generateResponse(List<AccountTransactionDto> transactionDtos) {
        Map<Boolean, Double> transactionAmountsMap = transactionDtos.stream()
                .collect(Collectors.partitioningBy(transaction ->
                                transaction.getTransactionAmount().compareTo(BigDecimal.ZERO) > 0,
                        Collectors.mapping(accountTransactionDto -> accountTransactionDto.getTransactionAmount().abs(),
                                Collectors.summingDouble(BigDecimal::doubleValue))));

        return SearchTransactionStandardResponseDTO.builder()
                .transactions(transactionDtos)
                .totalDebitAmount(BigDecimal.valueOf(transactionAmountsMap.get(true)).setScale(2, RoundingMode.CEILING).doubleValue())
                .totalCreditAmount(BigDecimal.valueOf(transactionAmountsMap.get(false)).setScale(2, RoundingMode.CEILING).doubleValue())
                .transactionCount(transactionDtos.size())
                .build();
    }


    @Override
    public SearchTransactionStandardResponseDTO getAllTransactionsForCondition(List<String> accountIds, TransactionFilterDto transactionFilterDto) {
        GenericSpecification<AccountTransactionEntity> specification = queryHelper.generateSpecification(transactionFilterDto, accountIds);
        return generateResponse(repository.findAll(specification).stream()
                .map(entity -> transactionMapper.convertEntityToDto(entity))
                .collect(Collectors.toList()));
    }
}
