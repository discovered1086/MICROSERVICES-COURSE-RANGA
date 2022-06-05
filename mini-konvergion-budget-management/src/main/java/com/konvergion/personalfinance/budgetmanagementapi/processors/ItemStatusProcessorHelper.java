package com.konvergion.personalfinance.budgetmanagementapi.processors;

import com.konvergion.personalfinance.budgetmanagementapi.model.entities.RegularBudgetEstimateItemEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ItemStatusProcessorHelper {

    public String getItemStatus(RegularBudgetEstimateItemEntity estimateItemEntity) {
        String returnValue = null;
        BigDecimal actualAmount = estimateItemEntity.getBudgetItemActualAmount();
        BigDecimal estimateAmount = estimateItemEntity.getBudgetItemEstimateAmount();
        switch (estimateItemEntity.getTypeOfTransaction()) {
            case "Account Credit Transaction":
                if (estimateAmount.compareTo(actualAmount) >= 0) {
                    returnValue = "<span class=\"brown font_bold\">$" + estimateAmount.subtract(actualAmount) + " more income expected</span>";
                } else {
                    returnValue = "<span class=\"green font_bold\">$" + actualAmount.subtract(estimateAmount) + " more earned than expected</span>";
                }
                break;
            case "Savings Transaction":
                if (estimateAmount.compareTo(actualAmount) >= 0) {
                    returnValue = "<span class=\"brown font_bold\">$" + estimateAmount.subtract(actualAmount) + " more to save</span>";
                } else {
                    returnValue = "<span class=\"green font_bold\">$" + actualAmount.subtract(estimateAmount) + " saved more than planned</span>";
                }
                break;
            case "Account Debit Transaction":
            case "Credit Card Transaction":
                if (estimateAmount.compareTo(actualAmount) >= 0) {
                    returnValue = "<span class=\"green font_bold\">$" + estimateAmount.subtract(actualAmount) + " left to spend</span>";
                } else {
                    returnValue = "<span class=\"red font_bold\">$" + actualAmount.subtract(estimateAmount) + " over budget</span>";
                }
                break;
        }

        return returnValue;
    }
}
