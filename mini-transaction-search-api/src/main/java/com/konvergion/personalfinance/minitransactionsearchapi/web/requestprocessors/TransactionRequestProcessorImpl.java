package com.konvergion.personalfinance.minitransactionsearchapi.web.requestprocessors;

import com.konvergion.personalfinance.minitransactionsearchapi.external.IAccountExternalService;
import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.AccountTransactionDto;
import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.FinancialAccountDto;
import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.SearchTransactionStandardResponseDTO;
import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.TransactionFilterDto;
import com.konvergion.personalfinance.minitransactionsearchapi.service.ISearchTransactionService;
import com.konvergion.personalfinance.minitransactionsearchapi.service.ITransactionLoadService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class TransactionRequestProcessorImpl implements ITransactionRequestProcessor {

    @Autowired
    private ISearchTransactionService transactionService;

    @Autowired
    private IAccountExternalService accountExternalService;

    @Autowired
    private ITransactionLoadService transactionLoadService;

    @Override
    public SearchTransactionStandardResponseDTO getAllTransactions(String customerId) {
        List<String> allAccounts = accountExternalService.getAllAccounts(customerId).stream()
                .map(FinancialAccountDto::getAccountId).collect(Collectors.toList());
        return transactionService.getAllTransactions(allAccounts);
    }

    @Override
    public SearchTransactionStandardResponseDTO getAllTransactionsForCondition(String customerId,
                                                                               TransactionFilterDto transactionFilterDto) {
        List<FinancialAccountDto> allAccounts = accountExternalService.getAllAccounts(customerId);
        if (StringUtils.isNotBlank(transactionFilterDto.getAccountName())) {
            return transactionService.getAllTransactionsForCondition(allAccounts.stream()
                    .filter(financialAccountDto -> Objects.nonNull(transactionFilterDto.getAccountName())
                            && StringUtils.upperCase(financialAccountDto.getAccountName())
                            .contains(StringUtils.upperCase(transactionFilterDto.getAccountName())))
                    .map(FinancialAccountDto::getAccountId).collect(Collectors.toList()), transactionFilterDto);
        } else {
            return transactionService.getAllTransactionsForCondition(allAccounts.stream()
                    .map(FinancialAccountDto::getAccountId).collect(Collectors.toList()), transactionFilterDto);
        }
    }

    @Override
    public List<String> getAllCategories(String customerId) {
        List<String> allAccounts = accountExternalService.getAllAccounts(customerId).stream()
                .map(FinancialAccountDto::getAccountId).collect(Collectors.toList());
        return transactionService.getAllTransactions(allAccounts).getTransactions().stream()
                .map(AccountTransactionDto::getTransactionCategory)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public void updateTransaction(AccountTransactionDto transactionDto, String transactionId) {
        transactionLoadService.updateTransaction(transactionDto, transactionId);
    }
}
