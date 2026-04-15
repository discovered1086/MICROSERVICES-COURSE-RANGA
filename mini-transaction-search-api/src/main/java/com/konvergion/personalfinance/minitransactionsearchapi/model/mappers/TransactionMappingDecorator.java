package com.konvergion.personalfinance.minitransactionsearchapi.model.mappers;

import com.konvergion.personalfinance.minitransactionsearchapi.dao.CurrencyRepository;
import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.AccountTransactionBatchDto;
import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.AccountTransactionDto;
import com.konvergion.personalfinance.minitransactionsearchapi.model.entity.AccountTransactionEntity;
import com.konvergion.personalfinance.minitransactionsearchapi.model.entity.CurrencyEntity;
import com.konvergion.personalfinance.minitransactionsearchapi.model.entity.TransactionTypeEnum;
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
public class TransactionMappingDecorator implements TransactionMapper {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionMappingDecorator.class);

    @Autowired
    @Qualifier("delegate")
    private TransactionMapper transactionMapper;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public AccountTransactionEntity convertDtoToEntity(AccountTransactionDto accountTransactionDto) {
        AccountTransactionEntity entity = transactionMapper.convertDtoToEntity(accountTransactionDto);
        updateTransactionEntity(accountTransactionDto, entity);
        return entity;
    }


    @Override
    public AccountTransactionEntity convertBatchDtoToEntity(AccountTransactionBatchDto accountTransactionDto, String accountId) {
        AccountTransactionEntity entity = transactionMapper.convertBatchDtoToEntity(accountTransactionDto, accountId);
        entity.setPostedDate(getConvertedDate(accountTransactionDto.getPostedDate()));
        entity.setTransactionDate(getConvertedDate(accountTransactionDto.getTransactionDate()));
        entity.setCurrency(currencyRepository.findById("USD").orElse(CurrencyEntity.builder()
                .currencyCode("USD")
                .description("US Dollar")
                .numericCode(840)
                .build()));
        entity.setTransactionType(accountTransactionDto.getTransactionAmount().intValue() > 0 ? TransactionTypeEnum.DEBIT : TransactionTypeEnum.CREDIT);
        return entity;
    }

    @Override
    public AccountTransactionDto convertEntityToDto(AccountTransactionEntity accountTransactionEntity) {
        AccountTransactionDto accountTransactionDto = transactionMapper.convertEntityToDto(accountTransactionEntity);
        accountTransactionDto.setTransactionDate(getConvertedStringDate(accountTransactionEntity.getTransactionDate()));
        accountTransactionDto.setPostedDate(getConvertedStringDate(accountTransactionEntity.getPostedDate()));
        return accountTransactionDto;
    }

    private LocalDate getConvertedDate(String date) {
        try {
            return LocalDate.parse(date, FORMATTER);
        } catch (DateTimeException ex) {
            LOGGER.error("An error occurred while parsing the date", ex);
        }

        return LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
    }

    private String getConvertedStringDate(LocalDate date) {
        try {
            return date.format(FORMATTER);
        } catch (DateTimeException ex) {
            LOGGER.error("An error occurred while parsing the date", ex);
        }

        return LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()).format(FORMATTER);
    }


    private void updateTransactionEntity(AccountTransactionDto accountTransactionDto, AccountTransactionEntity entity) {
        entity.setPostedDate(getConvertedDate(accountTransactionDto.getPostedDate()));
        entity.setTransactionDate(getConvertedDate(accountTransactionDto.getTransactionDate()));
        entity.setCurrency(currencyRepository.findById("USD").orElse(CurrencyEntity.builder()
                .currencyCode("USD")
                .description("US Dollar")
                .numericCode(840)
                .build()));
        entity.setTransactionType(accountTransactionDto.getTransactionAmount().intValue() > 0 ?
                TransactionTypeEnum.DEBIT : TransactionTypeEnum.CREDIT);
    }

}
