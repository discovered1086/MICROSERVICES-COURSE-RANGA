package com.konvergion.personalfinance.minitransactionsearchapi.model.mappers;

import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.AccountTransactionBatchDto;
import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.AccountTransactionDto;
import com.konvergion.personalfinance.minitransactionsearchapi.model.entity.AccountTransactionEntity;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
@DecoratedWith(TransactionMappingDecorator.class)
public interface TransactionMapper {


    @Mapping(target="transactionDate", ignore = true)
    @Mapping(target="postedDate", ignore = true)
    @Mapping(target="accountId", ignore = true)
    @Mapping(target="currency", ignore = true)
    @Mapping(target="transactionType", ignore = true)
    AccountTransactionEntity convertDtoToEntity(AccountTransactionDto accountTransactionDto);

    @Mapping(target = "transactionId", ignore = true)
    @Mapping(target="transactionDate", ignore = true)
    @Mapping(target="postedDate", ignore = true)
    @Mapping(target="accountId", source = "accountId")
    @Mapping(target="currency", ignore = true)
    @Mapping(target="transactionType", ignore = true)
    AccountTransactionEntity convertBatchDtoToEntity(AccountTransactionBatchDto accountTransactionDto, String accountId);

    @Mapping(target="transactionDate", ignore = true)
    @Mapping(target="postedDate", ignore = true)
    AccountTransactionDto convertEntityToDto(AccountTransactionEntity accountTransactionEntity);
}
