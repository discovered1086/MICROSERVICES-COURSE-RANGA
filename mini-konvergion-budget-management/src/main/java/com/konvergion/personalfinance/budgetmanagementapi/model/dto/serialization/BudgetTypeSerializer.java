package com.konvergion.personalfinance.budgetmanagementapi.model.dto.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.konvergion.personalfinance.budgetmanagementapi.model.dto.BudgetType;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BudgetTypeSerializer extends StdSerializer<BudgetType> {

	/**
	 * 
	 */

	private static final long serialVersionUID = -5042219452670668879L;

	@SuppressWarnings("unused")
	public BudgetTypeSerializer() {
		this(null);
	}

	protected BudgetTypeSerializer(Class<BudgetType> t) {
		super(t);
	}

	@Override
	public void serialize(BudgetType value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		String date = BudgetType.getBudgetTypeValue(value);
		gen.writeString(date);
	}

}
