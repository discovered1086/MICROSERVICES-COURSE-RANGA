package com.konvergion.personalfinance.budgetmanagementapi.external;

import com.konvergion.personalfinance.budgetmanagementapi.model.dto.SearchTransactionStandardResponseDTO;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.TransactionFilterDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Repository
public class TransactionExternalRepositoryImpl implements ITransactionExternalRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionExternalRepositoryImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${transactions_url}")
    private String transactionsUrl;

    @Override
    public SearchTransactionStandardResponseDTO getTransactionForCategory(String customerId, TransactionFilterDto filterDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.add("customer_id", customerId);
        try {

            ResponseEntity<SearchTransactionStandardResponseDTO> responseEntity = restTemplate.exchange(transactionsUrl,
                    HttpMethod.PUT,
                    new HttpEntity<>(filterDto, headers),
                    SearchTransactionStandardResponseDTO.class);

            if (responseEntity.hasBody()) {
                return responseEntity.getBody();
            }

        } catch (HttpClientErrorException exception) {
            LOGGER.error("An error occurred while getting transactions", exception);
        }

        return null;
    }
}
