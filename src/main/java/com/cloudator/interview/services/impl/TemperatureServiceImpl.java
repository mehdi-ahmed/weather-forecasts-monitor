package com.cloudator.interview.services.impl;

import com.cloudator.interview.domain.Temperature;
import com.cloudator.interview.repository.TemperatureRepository;
import com.cloudator.interview.services.TemperatureService;
import com.cloudator.interview.util.JsonUtil;
import com.cloudator.interview.util.UrlUtil;
import org.json.JSONArray;
import org.json.JSONObject;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.cloudator.interview.util.UrlUtil.*;


@Service
@CrossOrigin(origins = "http://localhost:4200")
public class TemperatureServiceImpl implements TemperatureService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemperatureService.class);

    private final RestTemplate restTemplate;

    @Autowired
    private LocationsServiceImpl locationsService;

    @Autowired
    private TemperatureRepository temperatureRepository;

    @Value("${endpoint.url}")
    private String ENDPOINT_URL;

    @Value("${api.key}")
    private String API_KEY;

    public TemperatureServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Temperature getTemperatureByCityCode(Integer cityStringId) throws IOException {

        String url = UrlUtil.buildUrl(ENDPOINT_URL, API_KEY, String.valueOf(cityStringId), WEATHER);

        LOGGER.info(url);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getBody().isEmpty()) {
            throw new RuntimeException(String.format("Empty response ! No data for city with id =  %d", cityStringId));
        }

        JSONObject jsonObject = new JSONObject(Objects.requireNonNull(response.getBody()));
        Temperature temperature = JsonUtil.buildObjectFromJson(jsonObject);

        setTemperatureLimit(temperature);
        return temperature;
    }

    @Override
    public List<Temperature> getTemperatureForBulkCities(String citiesStringIds) throws IOException, URISyntaxException {

        List<Temperature> temperatures;
        String url = UrlUtil.buildUrl(ENDPOINT_URL, API_KEY, citiesStringIds, GROUP);

        if (!isWeatherApiAvailable(ENDPOINT_URL)) {
            LOGGER.info("Weather API is unavailable - Switching to stored data");
            temperatures = temperatureRepository.findAll();

        } else {
            LOGGER.info(url);
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            if (Objects.requireNonNull(response.getBody()).isEmpty()) {
                throw new RuntimeException("Empty response ! ");
            }

            JSONObject jsonObject = new JSONObject(Objects.requireNonNull(response.getBody()));
            JSONArray arrayLocationsJson = jsonObject.getJSONArray(LIST);

            temperatures = new ArrayList<>();
            for (int i = 0; i < arrayLocationsJson.length(); i++) {
                Temperature temperature = JsonUtil.buildObjectFromJson(arrayLocationsJson.getJSONObject(i));
                setTemperatureLimit(temperature);
                temperatures.add(temperature);
            }
        }
        return temperatures;
    }

    private void setTemperatureLimit(Temperature temperature) {
        locationsService.getLocations().stream()
                .filter(location -> location.getCode().equals(temperature.getId()))
                .peek(location -> temperature.setTempLimit(location.getLimit()))
                .peek(location -> temperature.setExceeds(location.getLimit() > temperature.getTemp()))
                .findAny()
                .orElse(null);
    }

    public void saveLocationsWithExceedingTemperatures(Temperature temperature) {
        temperatureRepository.save(temperature);
    }

    public List<Temperature> getAllLocationsWithExceedingTemperatures() {
        return temperatureRepository.findAll();
    }

    public Temperature findByName(String name) {
        return temperatureRepository.findByCityName(name);
    }
}