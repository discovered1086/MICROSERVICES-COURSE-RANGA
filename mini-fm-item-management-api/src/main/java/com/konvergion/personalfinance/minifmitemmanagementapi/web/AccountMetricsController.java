package com.konvergion.personalfinance.minifmitemmanagementapi.web;

import com.konvergion.personalfinance.minifmitemmanagementapi.model.dto.AccountMetricsResponseDTO;
import com.konvergion.personalfinance.minifmitemmanagementapi.model.dto.NetWorthMetricsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/metrics")
public class AccountMetricsController {

    @Autowired
    private IAccountMetricsRequestProcessor requestProcessor;

    @GetMapping
    public ResponseEntity<AccountMetricsResponseDTO> getAccountBalances(
            @RequestHeader(name = "customer_id") String customerId) {
        return ResponseEntity.ok(requestProcessor.getAccountBalances(customerId));
    }

    @GetMapping("/net-worth")
    public ResponseEntity<NetWorthMetricsDTO> getNetWorth(
            @RequestHeader(name = "customer_id") String customerId) {
        return ResponseEntity.ok(requestProcessor.getNetWorth(customerId));
    }
}
