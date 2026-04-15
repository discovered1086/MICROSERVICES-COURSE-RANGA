package com.konvergion.personalfinance.minitransactionsearchapi.external;

import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.AccountResponseDTO;
import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.FinancialAccountDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class AccountExternalServiceImpl implements IAccountExternalService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountExternalServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${account.service.url}")
    private String accountUrl;

    @Override
    public List<FinancialAccountDto> getAllAccounts(String customerId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.add("customer_id", customerId);
        try {

            ResponseEntity<AccountResponseDTO> responseEntity = restTemplate.exchange(accountUrl,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    AccountResponseDTO.class);

            if (responseEntity.hasBody()) {
                return Objects.requireNonNull(responseEntity.getBody()).getAccounts();
            }

        } catch (HttpClientErrorException exception) {
            LOGGER.error("An error occurred while getting transactions", exception);
        }

        return Collections.emptyList();
    }
}
