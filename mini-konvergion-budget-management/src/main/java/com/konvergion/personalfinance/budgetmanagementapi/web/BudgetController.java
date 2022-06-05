package com.konvergion.personalfinance.budgetmanagementapi.web;

import com.konvergion.personalfinance.budgetmanagementapi.model.dto.BudgetEstimateDto;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.BudgetEstimateItemDto;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.BudgetListingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/budgets")
@Validated
public class BudgetController {

    @Autowired
    private IBudgetRequestProcessor budgetRequestProcessor;

    @PostMapping
    public ResponseEntity<String> addBudget(@RequestBody @Valid BudgetEstimateDto estimateDto,
                                            @RequestHeader(name = "customer_id") String customerId) {
        budgetRequestProcessor.addBudget(estimateDto, customerId);
        return ResponseEntity.ok("Budget added successfully");
    }

    @PostMapping(path = "/{budgetId}/budgetItems")
    public ResponseEntity<String> addBudgetItem(@RequestBody @Valid BudgetEstimateItemDto estimateDto,
                                                @RequestHeader(name = "customer_id") String customerId,
                                                @PathVariable("budgetId") String budgetId) {
        budgetRequestProcessor.addBudgetItem(estimateDto, budgetId, customerId);
        return ResponseEntity.ok("Budget added successfully");
    }

    @GetMapping(path = "/{budgetId}/budgetItems")
    public ResponseEntity<List<BudgetEstimateItemDto>> getAllBudgetItems(@RequestHeader(name = "customer_id") String customerId,
                                                                         @PathVariable("budgetId") String budgetId) {
        return ResponseEntity.ok(budgetRequestProcessor.getAllBudgetItemsForBudget(budgetId));
    }

    @GetMapping(path = "/{budgetId}")
    public ResponseEntity<BudgetEstimateDto> getSingleBudget(@RequestHeader(name = "customer_id") String customerId,
                                                             @PathVariable("budgetId") String budgetId) {
        return ResponseEntity.ok(budgetRequestProcessor.getSingleBudget(budgetId));
    }

    @GetMapping(path = "/listings")
    public ResponseEntity<List<BudgetListingDto>> getBudgetListingDetails(
            @RequestHeader(name = "customer_id") String customerId) {
        return ResponseEntity.ok(budgetRequestProcessor.getBudgetListing(customerId));
    }

    @GetMapping
    public ResponseEntity<List<BudgetEstimateDto>> getAllBudgetsForCustomer(
            @RequestHeader(name = "customer_id") String customerId) {
        return ResponseEntity.ok(budgetRequestProcessor.getAllBudgetsForCustomer(customerId));
    }
}
