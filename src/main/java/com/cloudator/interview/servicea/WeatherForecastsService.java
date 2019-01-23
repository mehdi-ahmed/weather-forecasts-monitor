package com.cloudator.interview.servicea;

import com.cloudator.interview.domain.Weather;
import com.cloudator.interview.util.JsonUtil;
import com.cloudator.interview.util.UrlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


@Service
public class WeatherForecastsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherForecastsService.class);

    private RestTemplate restTemplate;

    @Value("${endpoint.url}")
    private String ENDPOINT_URL;

    @Value("${api.key}")
    private String API_KEY;

    public Weather getWeatherDetailsByCity(long cityId) throws IOException {

        restTemplate = new RestTemplate();
        String url = UrlUtil.buildUrl(ENDPOINT_URL, API_KEY, cityId);

        LOGGER.info(url);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response == null) {
            throw new RuntimeException(String.format("Empty response ! No data for city with id =  %d", cityId));
        }

        return JsonUtil.buildObjectFromJson(response);
    }


}