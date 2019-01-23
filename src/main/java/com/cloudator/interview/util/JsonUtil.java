package com.cloudator.interview.util;

import com.cloudator.interview.domain.Temperature;
import com.cloudator.interview.domain.Weather;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Iterator;

public class JsonUtil {

    public static final String MAIN = "main";
    public static final String ID = "id";
    public static final String NAME = "name";

    public static Weather buildObjectFromJson(ResponseEntity<String> response) throws IOException {
        JSONObject jsonObject = new JSONObject(response.getBody());
        ObjectMapper objectMapper = new ObjectMapper();

        Weather weather = new Weather();

        Iterator<?> keys = jsonObject.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();

            switch (key) {
                case MAIN:
                    Temperature temperature = objectMapper.readValue(jsonObject.get(key).toString(), Temperature.class);
                    weather.setTemperature(temperature);
                    break;

                case ID:
                    weather.setId((Integer) jsonObject.get(ID));
                    break;

                case NAME:
                    weather.setCityName((String) jsonObject.get(NAME));
                    break;

                default:
                    //do nothing
                    break;
            }

        }

        return weather;
    }
}
