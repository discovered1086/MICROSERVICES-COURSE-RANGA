package com.konvergion.personalfinance.budgetmanagementapi.model.dto.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.BudgetType;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BudgetTypeDeSerializer extends StdDeserializer<BudgetType> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3202949066610693761L;

	protected BudgetTypeDeSerializer() {
		super(BudgetType.class);
	}

	@Override
	public BudgetType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		String type = p.getText();
		return BudgetType.getBudgetTypeEnum(type);
	}

}
