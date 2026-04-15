package com.konvergion.personalfinance.minitransactionsearchapi.model.dto.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.konvergion.personalfinance.minitransactionsearchapi.model.entity.RecurringExpenseFrequencyEnum;

import java.io.IOException;

public class ExpenseFrequencyTypeSerializer extends StdSerializer<RecurringExpenseFrequencyEnum> {

	/**
	 * 
	 */

	private static final long serialVersionUID = -5042219452670668879L;

	@SuppressWarnings("unused")
	public ExpenseFrequencyTypeSerializer() {
		this(null);
	}

	protected ExpenseFrequencyTypeSerializer(Class<RecurringExpenseFrequencyEnum> t) {
		super(t);
	}

	@Override
	public void serialize(RecurringExpenseFrequencyEnum value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		String date = RecurringExpenseFrequencyEnum.getValueFromEnum(value);
		gen.writeString(date);
	}

}
