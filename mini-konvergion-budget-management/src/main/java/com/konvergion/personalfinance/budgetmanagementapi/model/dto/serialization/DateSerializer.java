package com.konvergion.personalfinance.budgetmanagementapi.model.dto.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateSerializer extends StdSerializer<LocalDate> {

	/**
	 * 
	 */

	private static final long serialVersionUID = -5042219452670668879L;

	@SuppressWarnings("unused")
	public DateSerializer() {
		this(null);
	}

	protected DateSerializer(Class<LocalDate> t) {
		super(t);
	}

	@Override
	public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		String date = value.format(DateTimeFormatter.ISO_DATE);
		gen.writeString(date);
	}

}
