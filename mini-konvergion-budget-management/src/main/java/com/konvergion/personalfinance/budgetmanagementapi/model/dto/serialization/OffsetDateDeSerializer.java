package com.konvergion.personalfinance.budgetmanagementapi.model.dto.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class OffsetDateDeSerializer extends StdDeserializer<OffsetDateTime> {

	/**
	 *
	 */
	private static final long serialVersionUID = -3202949066610693761L;

	protected OffsetDateDeSerializer() {
		super(OffsetDateTime.class);
	}

	@Override
	public OffsetDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		String dateTime = p.getText();
		return OffsetDateTime.parse(dateTime, DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mi:ss a"));
	}

}
