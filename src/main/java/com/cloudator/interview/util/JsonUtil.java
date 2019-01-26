package com.cloudator.interview.util;

import com.cloudator.interview.domain.Temperature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Iterator;

public class JsonUtil {

    private static final String MAIN = "main";
    private static final String ID = "id";
    private static final String NAME = "name";

    public static Temperature buildObjectFromJson(JSONObject jsonObject) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        Temperature temp = new Temperature();
        temp.setMeasureTime(LocalDateTime.now());

        Iterator<?> keys = jsonObject.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();

            if (key.equalsIgnoreCase(MAIN)) {
                temp = objectMapper.readValue(jsonObject.get(key).toString(), Temperature.class);
                break;
            }
        }
        temp.setMeasureTime(LocalDateTime.now());
        temp.setId((Integer) jsonObject.get(ID));
        temp.setCityName((String) jsonObject.get(NAME));

        return temp;
    }
}
