package com.nts.moonsong.common.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Naver 송주용
 *
 */
public class JSONUtil {
	/**
	 * 
	 */
	private JSONUtil() {}

	public static <T> T parseJSONString(String jsonString, Class<T> type)
		throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		return mapper.readValue(jsonString, type);
	}

	public static Map<String, Object> parseJSONStringToMap(String jsonString)
		throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(jsonString, new TypeReference<HashMap<String, Object>>() {});
	}
}
