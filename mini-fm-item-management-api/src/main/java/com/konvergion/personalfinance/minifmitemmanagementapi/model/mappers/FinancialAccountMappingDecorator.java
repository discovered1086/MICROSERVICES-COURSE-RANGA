package com.konvergion.personalfinance.minifmitemmanagementapi.model.mappers;

import com.konvergion.personalfinance.minifmitemmanagementapi.dao.FinancialAccountTypeRepository;
import com.konvergion.personalfinance.minifmitemmanagementapi.model.FinancialAccountEntity;
import com.konvergion.personalfinance.minifmitemmanagementapi.model.FinancialAccountLinkStatus;
import com.konvergion.personalfinance.minifmitemmanagementapi.model.FinancialAccountStatus;
import com.konvergion.personalfinance.minifmitemmanagementapi.model.FinancialAccountTypeEntity;
import com.konvergion.personalfinance.minifmitemmanagementapi.model.dto.FinancialAccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class FinancialAccountMappingDecorator implements FinancialAccountMapper {

    @Autowired
    @Qualifier("delegate")
    private FinancialAccountMapper accountMapper;

    @Autowired
    private FinancialAccountTypeRepository typeRepository;

    @Override
    public FinancialAccountEntity convertDtoToEntity(FinancialAccountDto accountDto,
                                                     String customerId,
                                                     FinancialAccountStatus accountStatus,
                                                     FinancialAccountLinkStatus accountLinkStatus) {
        FinancialAccountEntity entity = accountMapper.convertDtoToEntity(accountDto, customerId, accountStatus, accountLinkStatus);
        entity.setAccountType(typeRepository.findFinancialAccountTypeEntityByAccountTypeAndAccountSubType
                (accountDto.getAccountType(), accountDto.getAccountSubType()).orElse(null));
        return entity;
    }

    @Override
    public FinancialAccountDto convertEntityToDto(FinancialAccountEntity financialAccountEntity) {
        FinancialAccountDto accountDto = accountMapper.convertEntityToDto(financialAccountEntity);
        accountDto.setAccountStatus(FinancialAccountStatus.getValue(financialAccountEntity.getAccountStatus()));
        accountDto.setAccountLinkStatus(FinancialAccountLinkStatus.getValue(financialAccountEntity.getAccountLinkStatus()));
//        FinancialAccountTypeEntity accountType = financialAccountEntity.getAccountType();
//        accountDto.setAccountType(accountType.getAccountType());
//        accountDto.setAccountSubType(accountType.getAccountSubType());
        return accountDto;
    }
}
