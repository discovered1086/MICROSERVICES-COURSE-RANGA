package com.konvergion.personalfinance.minitransactionsearchapi.service;

import com.konvergion.personalfinance.minitransactionsearchapi.dao.AccountTransactionRepository;
import com.konvergion.personalfinance.minitransactionsearchapi.external.IAccountExternalService;
import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.AccountTransactionBatchDto;
import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.AccountTransactionDto;
import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.FinancialAccountDto;
import com.konvergion.personalfinance.minitransactionsearchapi.model.exceptions.AccountNotFoundException;
import com.konvergion.personalfinance.minitransactionsearchapi.model.mappers.TransactionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionLoadServiceImpl implements ITransactionLoadService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionLoadServiceImpl.class);

    @Autowired
    private IAccountExternalService accountExternalService;

    @Autowired
    private AccountTransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper mapper;

    @Override
    public void loadTransactions(AccountTransactionBatchDto transactionBatchDto, String customerId) {
        String accountId = accountExternalService.getAllAccounts(customerId).stream()
                .filter(accountDto -> (transactionBatchDto.getAccountName().equals(accountDto.getAccountName())
                        || transactionBatchDto.getAccountName().contains(accountDto.getAccountName()))
                        || transactionBatchDto.getAccountNumber().equals(accountDto.getAccountNumber()))
                .map(FinancialAccountDto::getAccountId).findFirst()
                .orElseThrow(() -> new AccountNotFoundException(String.format("Account not found for account name %s and account number %s",
                        transactionBatchDto.getAccountName(), transactionBatchDto.getAccountNumber())));

        transactionRepository.save(mapper.convertBatchDtoToEntity(transactionBatchDto, accountId));
        //LOGGER.info("Transaction {} has been saved in the database successfully", transactionBatchDto.getTransactionName());
    }

    @Override
    public void updateTransaction(AccountTransactionDto transactionDto, String transactionId) {
        transactionDto.setTransactionId(transactionId);
        transactionRepository.save(mapper.convertDtoToEntity(transactionDto));
    }
}
