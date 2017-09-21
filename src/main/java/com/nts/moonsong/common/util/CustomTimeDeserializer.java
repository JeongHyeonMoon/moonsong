package com.nts.moonsong.common.util;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * 
 * @author Naver 송주용
 *
 */
public class CustomTimeDeserializer extends JsonDeserializer<Time> {
	private static final String LENGTH_4_FORMAT = "HHmm";
	private static final String LENGTH_5_FORMAT = "HH:mm";
	private static final String LENGTH_6_FORMAT = "HHmmss";
	private static final String LENGTH_8_FORMAT = "HH:mm:ss";

	@Override
	public Time deserialize(JsonParser parser, DeserializationContext deserializer)
		throws IOException, JsonProcessingException {
		String target = parser.getText();
		SimpleDateFormat formatter;

		if (target == null || "".equals(target)) {
			throw new RuntimeException();
		}

		try {
			switch (target.length()) {
				case 4:
					formatter = new SimpleDateFormat(LENGTH_4_FORMAT);
					return new Time(formatter.parse(target).getTime());
				case 5:
					formatter = new SimpleDateFormat(LENGTH_5_FORMAT);
					return new Time(formatter.parse(target).getTime());
				case 6:
					formatter = new SimpleDateFormat(LENGTH_6_FORMAT);
					return new Time(formatter.parse(target).getTime());
				case 8:
					formatter = new SimpleDateFormat(LENGTH_8_FORMAT);
					return new Time(formatter.parse(target).getTime());
				default:
					throw new IOException();
			}
		} catch (ParseException e) {
			throw new IOException();
		}

	}

}
