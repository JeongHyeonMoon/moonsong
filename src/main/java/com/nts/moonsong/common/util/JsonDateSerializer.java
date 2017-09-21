package com.nts.moonsong.common.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * @author Naver 송주용
 *
 */
@Component
public class JsonDateSerializer extends JsonSerializer<Date> {

	private final static SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd일 // HH시 mm분 ss초 ");

	/* (non-Javadoc)
	 * @see com.fasterxml.jackson.databind.JsonSerializer#serialize(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider)
	 */
	@Override
	public void serialize(Date date, JsonGenerator generator, SerializerProvider provider)
		throws IOException, JsonProcessingException {
		generator.writeString(formatter.format(date));
	}

}
