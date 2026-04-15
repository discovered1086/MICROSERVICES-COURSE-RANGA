package com.konvergion.personalfinance.budgetmanagementapi.model.dto.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class OffsetDateSerializer extends StdSerializer<OffsetDateTime> {

	/**
	 *
	 */

	private static final long serialVersionUID = -5042219452670668879L;

	@SuppressWarnings("unused")
	public OffsetDateSerializer() {
		this(null);
	}

	protected OffsetDateSerializer(Class<OffsetDateTime> t) {
		super(t);
	}

	@Override
	public void serialize(OffsetDateTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		String date = value.format(DateTimeFormatter.ISO_DATE_TIME);
		gen.writeString(date);
	}

}
