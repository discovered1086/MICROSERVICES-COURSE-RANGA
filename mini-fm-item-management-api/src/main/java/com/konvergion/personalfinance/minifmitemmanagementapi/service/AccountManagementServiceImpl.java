package com.konvergion.personalfinance.minifmitemmanagementapi.service;


import com.konvergion.personalfinance.minifmitemmanagementapi.dao.CurrencyRepository;
import com.konvergion.personalfinance.minifmitemmanagementapi.dao.FinancialAccountRepository;
import com.konvergion.personalfinance.minifmitemmanagementapi.dao.FinancialAccountTypeRepository;
import com.konvergion.personalfinance.minifmitemmanagementapi.model.FinancialAccountEntity;
import com.konvergion.personalfinance.minifmitemmanagementapi.model.FinancialAccountLinkStatus;
import com.konvergion.personalfinance.minifmitemmanagementapi.model.FinancialAccountStatus;
import com.konvergion.personalfinance.minifmitemmanagementapi.model.dto.AccountResponseDTO;
import com.konvergion.personalfinance.minifmitemmanagementapi.model.dto.FinancialAccountDto;
import com.konvergion.personalfinance.minifmitemmanagementapi.model.mappers.FinancialAccountMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AccountManagementServiceImpl implements IAccountManagementService {

    @Autowired
    private FinancialAccountRepository accountRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private FinancialAccountTypeRepository accountTypeRepository;

    @Autowired
    private FinancialAccountMapper accountMapper;

    @Override
    public void addAccount(FinancialAccountDto accountDto, String customerId) {
        FinancialAccountEntity accountEntity = accountRepository.findByExternalAccountId(accountDto.getExternalAccountId())
                .orElse(accountMapper.convertDtoToEntity(accountDto, customerId,
                        FinancialAccountStatus.OPEN, FinancialAccountLinkStatus.CONNECTED));
        accountEntity.setAccessToken(accountDto.getAccessToken());
        accountEntity.setAccountLinkStatus(FinancialAccountLinkStatus.CONNECTED);
        accountEntity.setCurrency(currencyRepository.findById("USD").orElse(null));
        accountEntity.setAccountType(accountTypeRepository.findFinancialAccountTypeEntityByAccountTypeAndAccountSubType(
                StringUtils.capitalize(accountDto.getAccountType().toLowerCase()),
                StringUtils.capitalize(accountDto.getAccountSubType().toLowerCase())
        ).orElse(null));
        accountRepository.save(accountEntity);
    }

    @Override
    public AccountResponseDTO getAccountsForCustomer(String customerId) {
        return AccountResponseDTO.builder()
                .accounts(accountRepository.findByCustomerId(customerId).stream()
                        .map(accountEntity -> accountMapper.convertEntityToDto(accountEntity))
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public FinancialAccountDto getSingleAccount(String accountId) {
        return accountMapper.convertEntityToDto(accountRepository.findById(accountId).orElseThrow());
    }
}
