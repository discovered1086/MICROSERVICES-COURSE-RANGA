package com.konvergion.personalfinance.minitransactionsearchapi.external;

import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.FinancialAccountDto;

import java.util.List;

public interface IAccountExternalService {

    List<FinancialAccountDto> getAllAccounts(String customerId);


}
