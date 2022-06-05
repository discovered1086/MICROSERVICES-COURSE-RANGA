package com.konvergion.personalfinance.minitransactionsearchapi.model.entity;

import lombok.AllArgsConstructor;

import java.util.EnumSet;

@AllArgsConstructor
public enum RecurringExpenseFrequencyEnum {
	MONTH("month"),
	YEAR("year"),
	WEEK("week"),
	QUARTER("quarter"),
	DAY("day");

	private final String displayValue;

	public static RecurringExpenseFrequencyEnum getEnumFromString(String displayValue){
		return EnumSet.allOf(RecurringExpenseFrequencyEnum.class).stream()
				.filter(enumInstance -> displayValue.equalsIgnoreCase(enumInstance.displayValue))
				.findFirst().orElse(RecurringExpenseFrequencyEnum.MONTH);
	}

	public static String getValueFromEnum(RecurringExpenseFrequencyEnum enumValue){
		return enumValue.displayValue;
	}
}
