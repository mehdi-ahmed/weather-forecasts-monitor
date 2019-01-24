package com.cloudator.interview.util;

import com.cloudator.interview.domain.Temperature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;

public class JsonUtil {

    private static final String MAIN = "main";
    private static final String ID = "id";
    private static final String NAME = "name";

    /**
     * Fetches Keys and values from a JSON String
     *
     * @param jsonObject
     * @return object containing weather data such as temperature, etc.
     * @throws IOException
     */
    public static Temperature buildObjectFromJson(JSONObject jsonObject) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        Temperature temperature = new Temperature();

        Iterator<?> keys = jsonObject.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();

            if (key.equalsIgnoreCase(MAIN)) {
                temperature = objectMapper.readValue(jsonObject.get(key).toString(), Temperature.class);
                break;
            }
        }
        temperature.setId((Integer) jsonObject.get(ID));
        temperature.setCityName((String) jsonObject.get(NAME));


        return temperature;
    }
}
