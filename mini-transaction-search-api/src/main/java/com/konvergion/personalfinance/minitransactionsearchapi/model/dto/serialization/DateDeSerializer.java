package com.konvergion.personalfinance.minitransactionsearchapi.model.dto.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateDeSerializer extends StdDeserializer<LocalDate> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3202949066610693761L;

	protected DateDeSerializer() {
		super(LocalDate.class);
	}

	@Override
	public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		String dateTime = p.getText();
		return LocalDate.parse(dateTime, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
	}

}
