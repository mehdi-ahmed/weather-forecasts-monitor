package com.cloudator.interview.services.impl;

import com.cloudator.interview.domain.Temperature;
import com.cloudator.interview.services.WeatherForecastsService;
import com.cloudator.interview.util.JsonUtil;
import com.cloudator.interview.util.UrlUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.cloudator.interview.util.UrlUtil.*;


@Service
public class WeatherForecastsServiceImpl implements WeatherForecastsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherForecastsService.class);

    private final RestTemplate restTemplate;

    @Value("${endpoint.url}")
    private String ENDPOINT_URL;
    @Value("${api.key}")
    private String API_KEY;

    public WeatherForecastsServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Temperature getTemperatureByCity(String cityStringId) throws IOException {

        String url = UrlUtil.buildUrl(ENDPOINT_URL, API_KEY, cityStringId, WEATHER);

        LOGGER.info(url);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getBody().isEmpty()) {
            throw new RuntimeException(String.format("Empty response ! No data for city with id =  %d", cityStringId));
        }

        JSONObject jsonObject = new JSONObject(Objects.requireNonNull(response.getBody()));
        return JsonUtil.buildObjectFromJson(jsonObject);
    }

    @Override
    public List<Temperature> getTemperatureForBulkCities(String citiesStringIds) throws IOException {
        String url = UrlUtil.buildUrl(ENDPOINT_URL, API_KEY, citiesStringIds, GROUP);

        LOGGER.info(url);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (Objects.requireNonNull(response.getBody()).isEmpty()) {
            throw new RuntimeException("Empty response ! ");
        }


        JSONObject jsonObject = new JSONObject(Objects.requireNonNull(response.getBody()));
        JSONArray arrayLocationsJson = jsonObject.getJSONArray(LIST);

        List<Temperature> temperatures = new ArrayList<>();
        for (int i = 0; i < arrayLocationsJson.length(); i++) {
            temperatures.add(JsonUtil.buildObjectFromJson(arrayLocationsJson.getJSONObject(i)));
        }

        return temperatures;
    }


}