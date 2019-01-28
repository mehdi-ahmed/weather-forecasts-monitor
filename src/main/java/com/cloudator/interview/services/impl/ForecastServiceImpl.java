package com.cloudator.interview.services.impl;

import com.cloudator.interview.domain.Forecast;
import com.cloudator.interview.exception.ForecastNotFoundException;
import com.cloudator.interview.repository.ForecastRepository;
import com.cloudator.interview.services.ForecastService;
import com.cloudator.interview.util.JsonUtil;
import com.cloudator.interview.util.UrlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static com.cloudator.interview.util.UrlUtil.FORECAST;
import static com.cloudator.interview.util.UrlUtil.isWeatherApiAvailable;

@Service
@CrossOrigin(origins = "http://localhost:4200")
public class ForecastServiceImpl implements ForecastService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ForecastServiceImpl.class);
    private final RestTemplate restTemplate;

    @Value("${endpoint.url}")
    private String ENDPOINT_URL;

    @Value("${api.key}")
    private String API_KEY;

    @Autowired
    private LocationsServiceImpl locationsService;

    @Autowired
    private ForecastRepository forecastRepository;

    public ForecastServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public List<Forecast> getForecastByCity(String cityName) throws IOException, URISyntaxException {

        List<Forecast> forecasts;
        //Calling the API with Location Id is preferred over the name to avoid ambiguous result for your city.
        String url = UrlUtil.buildUrl(ENDPOINT_URL, API_KEY, locationsService.getLocationCode(cityName), FORECAST);

        if (!isWeatherApiAvailable(ENDPOINT_URL)) {
            LOGGER.info(" !!Weather API is unavailable - Switching to stored data!!");
            forecasts = forecastRepository.findAll();

        } else {

            LOGGER.info(url);
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            if (response == null) {
                throw new ForecastNotFoundException(String.format("No data for city with id =  %s", cityName));
            }
            forecasts = JsonUtil.buildObjectListForJson(response, locationsService.findByName(cityName));
        }
        return forecasts;
    }

}
