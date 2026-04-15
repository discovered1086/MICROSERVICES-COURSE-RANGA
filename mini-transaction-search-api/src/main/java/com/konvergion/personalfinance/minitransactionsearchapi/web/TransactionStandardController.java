package com.konvergion.personalfinance.minitransactionsearchapi.web;

import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.AccountTransactionDto;
import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.SearchTransactionStandardResponseDTO;
import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.TransactionFilterDto;
import com.konvergion.personalfinance.minitransactionsearchapi.web.requestprocessors.ITransactionRequestProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionStandardController {

    @Autowired
    private ITransactionRequestProcessor requestProcessor;

    @PutMapping
    public ResponseEntity<SearchTransactionStandardResponseDTO> getTransactionWithoutPaging(
            @RequestHeader(name = "customer_id") String customerId,
            @RequestBody TransactionFilterDto requestDTO) {
        return ResponseEntity.ok(requestProcessor.getAllTransactionsForCondition(customerId, requestDTO));
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<String> updateTransaction(@PathVariable("transactionId") String transactionId,
            @RequestBody AccountTransactionDto requestDTO) {
        requestProcessor.updateTransaction(requestDTO, transactionId);
        return ResponseEntity.ok("Transaction successfully updated");
    }

    @GetMapping
    public ResponseEntity<SearchTransactionStandardResponseDTO> getAllTransactions(@RequestHeader(name = "customer_id") String customerId) {
        return ResponseEntity.ok(requestProcessor.getAllTransactions(customerId));
    }


    @GetMapping("/categories")
    public ResponseEntity<List<String>> getAllCustomerCategories(
            @RequestHeader(name = "customer_id") String customerId) {
        return ResponseEntity.ok(requestProcessor.getAllCategories(customerId));
    }



}
