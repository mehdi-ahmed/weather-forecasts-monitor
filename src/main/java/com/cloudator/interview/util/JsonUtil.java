package com.cloudator.interview.util;

import com.cloudator.interview.domain.Forecast;
import com.cloudator.interview.domain.Location;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonUtil {

    //Json Fields
    private static final String MAIN = "main";
    private static final String ID = "id";
    private static final String LIST = "list";
    private static final String NAME = "name";
    private static final String CITY = "city";
    private static final String DATE = "dt_txt";
    private static final String TEMP = "temp";

    public static List<Forecast> buildObjectListForJson(ResponseEntity<String> response, Location location) {

        JSONObject jsonObject = new JSONObject(response.getBody());
        JSONObject jsonCity = jsonObject.getJSONObject(CITY);
        int id = (int) jsonCity.get(ID);
        String name = String.valueOf(jsonCity.get(NAME));
        String limit = String.valueOf(location.getLimit());


        Iterator<?> keys = jsonObject.keys();
        List<Forecast> forecasts = new ArrayList<>();
        while (keys.hasNext()) {
            String key = (String) keys.next();

            if (key.equalsIgnoreCase(LIST)) {
                JSONArray jsonList = jsonObject.getJSONArray(LIST);
                for (int i = 0; i < jsonList.length(); i++) {
                    Forecast forecast = new Forecast();
                    forecast.setCity(name);
                    forecast.setCode(id);
                    forecast.setLimit(limit);

                    forecast.setDate((String) jsonList.getJSONObject(i).get(DATE));
                    JSONObject jsonMain = (JSONObject) jsonList.getJSONObject(i).get(MAIN);
                    forecast.setTemperature((String.valueOf(jsonMain.get(TEMP))));
                    forecast.setExceed((Float.valueOf(forecast.getLimit()) > Float.valueOf(forecast.getTemperature())));

                    forecasts.add(forecast);
                }
            }

        }
        return forecasts;
    }


}
