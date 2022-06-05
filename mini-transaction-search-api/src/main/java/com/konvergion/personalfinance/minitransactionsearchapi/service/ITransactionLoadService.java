package com.konvergion.personalfinance.minitransactionsearchapi.service;

import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.AccountTransactionBatchDto;
import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.AccountTransactionDto;

public interface ITransactionLoadService {

    void loadTransactions(AccountTransactionBatchDto transactionBatchDto, String customerId);

    void updateTransaction(AccountTransactionDto transactionDto, String transactionId);
}
