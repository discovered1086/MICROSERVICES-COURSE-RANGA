package com.konvergion.personalfinance.minitransactionsearchapi.web;

import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.AccountTransactionBatchDto;
import com.konvergion.personalfinance.minitransactionsearchapi.service.ITransactionLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transactions")
public class TransactionLoadController {

    @Autowired
    private ITransactionLoadService transactionLoadService;

    @PostMapping
    public ResponseEntity<String> addTransactions(@RequestBody AccountTransactionBatchDto batchDto,
                                                  @RequestHeader(name = "customer_id") String customerId){
        transactionLoadService.loadTransactions(batchDto, customerId);
        return ResponseEntity.ok("Transaction added successfully");
    }
}
