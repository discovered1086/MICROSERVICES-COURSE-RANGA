package com.konvergion.personalfinance.minitransactionsearchapi.service;

import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.SearchTransactionStandardResponseDTO;
import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.TransactionFilterDto;

import java.util.List;

public interface ISearchTransactionService {

    SearchTransactionStandardResponseDTO getAllTransactions(List<String> accountIds);

    SearchTransactionStandardResponseDTO getAllTransactionsForCondition(List<String> accountIds, TransactionFilterDto transactionFilterDto);
}
