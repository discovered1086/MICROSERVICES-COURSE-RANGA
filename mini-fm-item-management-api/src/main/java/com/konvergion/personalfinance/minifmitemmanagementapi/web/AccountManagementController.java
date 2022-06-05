package com.konvergion.personalfinance.minifmitemmanagementapi.web;

import com.konvergion.personalfinance.minifmitemmanagementapi.model.dto.AccountResponseDTO;
import com.konvergion.personalfinance.minifmitemmanagementapi.model.dto.FinancialAccountDto;
import com.konvergion.personalfinance.minifmitemmanagementapi.service.IAccountManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customerAccounts")
public class AccountManagementController {

    @Autowired
    private IAccountManagementService accountManagementService;

    @PostMapping
    public ResponseEntity<String> addCustomerAccount(
            @RequestHeader(name = "customer_id") String customerId,
            @RequestBody FinancialAccountDto accountDto) {
        accountManagementService.addAccount(accountDto, customerId);
        return ResponseEntity.ok("Account added successfully");
    }

    @GetMapping
    public ResponseEntity<AccountResponseDTO> getAllCustomerAccounts(
            @RequestHeader(name = "customer_id") String customerId) {
        return ResponseEntity.ok(accountManagementService.getAccountsForCustomer(customerId));
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<FinancialAccountDto> getCustomerAccount(
            @PathVariable("accountId") String accountId) {
        return ResponseEntity.ok(accountManagementService.getSingleAccount(accountId));
    }

}
