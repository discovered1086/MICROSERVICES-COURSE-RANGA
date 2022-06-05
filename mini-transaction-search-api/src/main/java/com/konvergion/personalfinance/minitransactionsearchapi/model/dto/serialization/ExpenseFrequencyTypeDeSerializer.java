package com.konvergion.personalfinance.minitransactionsearchapi.model.dto.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.konvergion.personalfinance.minitransactionsearchapi.model.entity.RecurringExpenseFrequencyEnum;

import java.io.IOException;

public class ExpenseFrequencyTypeDeSerializer extends StdDeserializer<RecurringExpenseFrequencyEnum> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3202949066610693761L;

	protected ExpenseFrequencyTypeDeSerializer() {
		super(RecurringExpenseFrequencyEnum.class);
	}

	@Override
	public RecurringExpenseFrequencyEnum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		String type = p.getText();
		return RecurringExpenseFrequencyEnum.getEnumFromString(type);
	}

}
