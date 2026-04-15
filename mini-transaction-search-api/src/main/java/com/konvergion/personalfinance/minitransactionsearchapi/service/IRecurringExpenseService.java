package com.konvergion.personalfinance.minitransactionsearchapi.service;

import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.RecurringExpenseDto;
import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.RecurringExpenseResponseDTO;

public interface IRecurringExpenseService {

    RecurringExpenseResponseDTO getRecurringExpenses(String customerId);

    void addRecurringExpense(RecurringExpenseDto expenseDto, String customerId);
}
