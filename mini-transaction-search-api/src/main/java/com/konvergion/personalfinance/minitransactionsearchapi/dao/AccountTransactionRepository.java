package com.konvergion.personalfinance.minitransactionsearchapi.dao;

import com.konvergion.personalfinance.minitransactionsearchapi.model.entity.AccountTransactionEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountTransactionRepository extends PagingAndSortingRepository<AccountTransactionEntity, String>,
        JpaSpecificationExecutor<AccountTransactionEntity> {
}
