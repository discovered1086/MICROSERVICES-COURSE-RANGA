package com.konvergion.personalfinance.budgetmanagementapi.web;

import com.konvergion.personalfinance.budgetmanagementapi.model.dto.ExpensePlanItemDTO;
import com.konvergion.personalfinance.budgetmanagementapi.service.IExpensePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ExpensePlanController {

    @Autowired
    private IExpensePlanService expensePlanService;

    @PostMapping("/expense-plan-item")
    public ResponseEntity<String> addExpensePlan(@RequestBody ExpensePlanItemDTO itemDTO,
                                                 @RequestHeader(name = "customer_id") String customerId){
        expensePlanService.addExpensePlan(itemDTO,customerId);
        return ResponseEntity.ok("An expense plan was added");
    }

    @GetMapping("/expense-plan-item/{expensePlanItemId}")
    public ResponseEntity<ExpensePlanItemDTO> getExpensePlanItem(
            @PathVariable("expensePlanItemId") String expensePlanItemId){
        return ResponseEntity.ok(expensePlanService.getSingleExpensePlanItem(expensePlanItemId));
    }

    @GetMapping("/expense-plan-item")
    public ResponseEntity<List<ExpensePlanItemDTO>> getAllExpensePlanItems(
            @RequestHeader(name = "customer_id") String customerId){
        return ResponseEntity.ok(expensePlanService.getAllExpensePlanItems(customerId));
    }


}
