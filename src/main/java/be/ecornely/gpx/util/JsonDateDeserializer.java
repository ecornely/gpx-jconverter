package be.ecornely.gpx.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class JsonDateDeserializer extends JsonDeserializer<Date> {

	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	public Date deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
		try {
			return sdf.parse(parser.getValueAsString());
		} catch (ParseException e) {
			throw new JsonParseException("Impossible to read date with format dd/MM/yyyy", parser.getCurrentLocation(), e);
		}
	}
	
	public static Date parseDate(String date){
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

}
