package com.konvergion.personalfinance.minitransactionsearchapi.web.requestprocessors;

import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.AccountTransactionDto;
import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.SearchTransactionStandardResponseDTO;
import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.TransactionFilterDto;

import java.util.List;

public interface ITransactionRequestProcessor {

    SearchTransactionStandardResponseDTO getAllTransactions(String customerId);

    SearchTransactionStandardResponseDTO getAllTransactionsForCondition(String customerId,
                                                                        TransactionFilterDto transactionFilterDto);

    List<String> getAllCategories(String customerId);

    void updateTransaction(AccountTransactionDto transactionDto, String transactionId);
}
