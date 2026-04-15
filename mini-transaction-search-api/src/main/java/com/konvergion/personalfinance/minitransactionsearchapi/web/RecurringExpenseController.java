package com.konvergion.personalfinance.minitransactionsearchapi.web;

import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.RecurringExpenseDto;
import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.RecurringExpenseResponseDTO;
import com.konvergion.personalfinance.minitransactionsearchapi.service.IRecurringExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recurring-expenses")
public class RecurringExpenseController {

    @Autowired
    private IRecurringExpenseService service;

    @PostMapping
    public ResponseEntity<String> addRecurringExpense(@RequestBody RecurringExpenseDto recurringExpenseDto,
                                                      @RequestHeader(name = "customer_id") String customerId){
        service.addRecurringExpense(recurringExpenseDto,customerId);
        return ResponseEntity.ok("Recurring expense added successfully");
    }


    @GetMapping
    public ResponseEntity<RecurringExpenseResponseDTO> getRecurringExpenses(@RequestHeader(name = "customer_id") String customerId){
        return ResponseEntity.ok(service.getRecurringExpenses(customerId));
    }
}
