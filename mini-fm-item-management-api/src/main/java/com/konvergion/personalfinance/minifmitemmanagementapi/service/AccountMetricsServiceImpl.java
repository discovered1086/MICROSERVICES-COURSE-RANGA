package com.konvergion.personalfinance.minifmitemmanagementapi.service;

import com.konvergion.personalfinance.minifmitemmanagementapi.dao.CreditAccountBalanceRepository;
import com.konvergion.personalfinance.minifmitemmanagementapi.dao.DepositoryAccountBalanceRepository;
import com.konvergion.personalfinance.minifmitemmanagementapi.dao.FinancialAccountRepository;
import com.konvergion.personalfinance.minifmitemmanagementapi.dao.PropertyAccountDetailsRepository;
import com.konvergion.personalfinance.minifmitemmanagementapi.model.dto.*;
import com.konvergion.personalfinance.minifmitemmanagementapi.model.FinancialAccountEntity;
import com.konvergion.personalfinance.minifmitemmanagementapi.model.mappers.FinancialAccountMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static org.apache.commons.collections4.MapUtils.isEmpty;

@Service
public class AccountMetricsServiceImpl implements IAccountMetricsService {

    @Autowired
    private FinancialAccountRepository accountRepository;

    @Autowired
    private FinancialAccountMapper mapper;

    @Autowired
    private CreditAccountBalanceRepository creditRepository;

    @Autowired
    private DepositoryAccountBalanceRepository depositRepository;

    @Autowired
    private PropertyAccountDetailsRepository propertyRepository;


    @Override
    public <T extends DebtMetricsDTO> DebtMetricsResponseDTO<T> getDebtMetricsInformation(String customerId) {
        Map<String, List<FinancialAccountEntity>> accounts = accountRepository.getDebtAccountDetails(customerId).stream()
                .collect(Collectors.groupingBy(account -> account.getAccountType().getAccountSubType()));
        if(!isEmpty(accounts)){
            Map<Boolean, DoubleSummaryStatistics> creditCard = accounts.get("Credit Card").stream()
                    .map(account -> creditRepository.findById(account.getAccountId()).orElse(null))
                    .filter(Objects::nonNull)
                    .collect(partitioningBy(balance -> "Open Credit".equals(balance.getCreditType().getCreditType()),
                            summarizingDouble(balance -> balance.getCreditLimit().subtract(balance.getAvailableBalance()).doubleValue())));

            double creditCardLimits = accounts.get("Credit Card").stream()
                    .map(account -> creditRepository.findById(account.getAccountId()).orElse(null))
                    .filter(Objects::nonNull)
                    .mapToDouble(balance -> balance.getCreditLimit().doubleValue())
                    .sum();


            CreditCardDebtMetricsDTO cardDebtMetricsDTO = CreditCardDebtMetricsDTO.builder()
                    .totalOpenDebt(BigDecimal.valueOf(creditCard.get(true).getSum()))
                    .totalRevolvingDebt(BigDecimal.valueOf(creditCard.get(false).getSum()))
                    .debtType("Credit Card")
                    .build();

            cardDebtMetricsDTO.setTotalDebt(cardDebtMetricsDTO.getTotalOpenDebt().add(cardDebtMetricsDTO.getTotalRevolvingDebt()));
            cardDebtMetricsDTO.setTotalCreditUtilization(cardDebtMetricsDTO.getTotalDebt()
                    .divide(BigDecimal.valueOf(creditCardLimits), RoundingMode.CEILING).multiply(BigDecimal.valueOf(100)));

            DebtMetricsResponseDTO<CreditCardDebtMetricsDTO> responseDTO = new DebtMetricsResponseDTO<>();
            responseDTO.setDebtMetrics(List.of(cardDebtMetricsDTO));
            responseDTO.setTotalDebt(cardDebtMetricsDTO.getTotalDebt());
            return (DebtMetricsResponseDTO<T>) responseDTO;
        }

        return DebtMetricsResponseDTO.<T>builder().build();
    }

    @Override
    public DepositoryAccountMetricsDTO getCashAccountMetrics(String customerId) {
        Map<String, List<FinancialAccountEntity>> cashAccounts = accountRepository.getCashAccountDetails(customerId).stream()
                .collect(Collectors.groupingBy(account -> account.getAccountType().getAccountSubType()));

        if(!isEmpty(cashAccounts)){
            Map<String, List<FinancialAccountEntity>> depository = accountRepository.getDepositoryAccountDetails(customerId).stream()
                    .collect(Collectors.groupingBy(account -> account.getAccountType().getAccountSubType()));
            if(!isEmpty(depository)){
                double checking = depository.get("Checking").stream()
                        .map(account -> depositRepository.findById(account.getAccountId()).orElse(null))
                        .filter(Objects::nonNull)
                        .mapToDouble(balance -> balance.getCurrentBalance().doubleValue())
                        .sum();

                double savings = depository.get("Savings").stream()
                        .map(account -> depositRepository.findById(account.getAccountId()).orElse(null))
                        .filter(Objects::nonNull)
                        .mapToDouble(balance -> balance.getCurrentBalance().doubleValue())
                        .sum();

                DepositoryAccountMetricsDTO accountMetricsDTO = DepositoryAccountMetricsDTO.builder()
                        .totalCashInHand(BigDecimal.valueOf(cashAccounts.get("Cash").stream()
                                .map(account -> depositRepository.findById(account.getAccountId()).orElse(null))
                                .filter(Objects::nonNull)
                                .mapToDouble(balance -> balance.getCurrentBalance().doubleValue())
                                .sum()))
                        .totalChecking(BigDecimal.valueOf(checking))
                        .totalSavings(BigDecimal.valueOf(savings))
                        .build();

                accountMetricsDTO.setTotalAssets(accountMetricsDTO.getTotalCashInHand()
                        .add(accountMetricsDTO.getTotalChecking())
                        .add(accountMetricsDTO.getTotalSavings()));
                accountMetricsDTO.setTotalCashSavings(accountMetricsDTO.getTotalAssets());
                return accountMetricsDTO;
            }

        }

        return DepositoryAccountMetricsDTO.builder().build();
    }

    @Override
    public PropertyAccountMetricsDTO getPropertyMetrics(String customerId) {
        BigDecimal totalValuation = BigDecimal.valueOf(accountRepository.getPropertyAccountDetails(customerId).stream()
                .map(account -> propertyRepository.findById(account.getAccountId()).orElse(null))
                .filter(Objects::nonNull)
                .mapToDouble(balance -> balance.getPropertyValuation().doubleValue())
                .sum());
        return PropertyAccountMetricsDTO.builder()
                .totalValuation(totalValuation)
                .totalAssets(totalValuation)
                .build();
    }

    @Override
    public void updateAccountBalance(List<FinancialAccountBalanceDto> balanceDtos) {

    }
}
