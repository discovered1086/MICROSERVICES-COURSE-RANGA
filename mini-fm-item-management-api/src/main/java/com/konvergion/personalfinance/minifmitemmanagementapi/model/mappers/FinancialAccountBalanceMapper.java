package com.konvergion.personalfinance.minifmitemmanagementapi.model.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FinancialAccountBalanceMapper {


//    default FinancialAccountBalanceDto convertCreditEntityToDto(FinancialAccountEntity account, CreditAccountBalanceEntity entity){
//        FinancialAccountBalanceDto balanceDto = new FinancialAccountBalanceDto();
//        balanceDto.setCreditLimit(entity.getCreditLimit());
//        populateBalanceDto(account, entity, balanceDto);
//        return balanceDto;
//    }
//
//    default FinancialAccountBalanceDto convertDepositEntityToDto(FinancialAccountEntity account, DepositoryAccountBalanceEntity entity){
//        FinancialAccountBalanceDto balanceDto = new FinancialAccountBalanceDto();
//        populateBalanceDto(account, entity, balanceDto);
//        return balanceDto;
//    }
//
//    private <Q extends FinancialAccountDetailsEntity> void populateBalanceDto(FinancialAccountEntity account,
//                                                                     Q balanceEntity, FinancialAccountBalanceDto balanceDto) {
//        balanceDto.setAccountId(account.getAccountId());
//        balanceDto.setAccountName(account.getAccountName());
//        balanceDto.setAccountType(account.getAccountType());
//        balanceDto.setAccountSubType(account.getAccountSubType());
//        balanceDto.setExternalAccountId(account.getExternalAccountId());
//        balanceDto.setAvailableBalance(balanceEntity.getAvailableBalance());
//        balanceDto.setCurrentBalance(balanceEntity.getCurrentBalance());
//    }
}
