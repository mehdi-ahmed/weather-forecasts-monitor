package com.cloudator.interview.controllers;


import com.cloudator.interview.domain.Forecast;
import com.cloudator.interview.domain.Location;
import com.cloudator.interview.repository.ForecastRepository;
import com.cloudator.interview.services.ForecastService;
import com.cloudator.interview.services.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/forecast")
public class ForecastResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ForecastResource.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");

    @Autowired
    private ForecastService forecastService;

    @Autowired
    LocationService locationService;

    @Autowired
    ForecastRepository forecastRepository;


    @RequestMapping(value = "/{city}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Forecast> getForecast(@PathVariable("city") String cityName) throws IOException, URISyntaxException {

        LOGGER.info(String.format("Looking for temperature for city '%s'...", cityName));
        return forecastService.getForecastByCity(cityName);
    }


    @Scheduled(fixedDelayString = "${fixedDelay.in.milliseconds}", initialDelayString = "${initialDelay.in.milliseconds}", zone = "Europe/Helsinki")
    public void saveExceedingTemperatures() throws IOException, URISyntaxException {

        LOGGER.info("Saving locations with exceeding temperatures:: Execution Time - {}"
                , dateTimeFormatter.format(LocalDateTime.now()));

        List<Location> locations = locationService.getAllLocations();

        for (Location location : locations) {
            List<Forecast> forecastListByCity = forecastService.getForecastByCity(location.getName());

            forecastListByCity.stream()
                    .filter(Objects::nonNull)
                    .forEach(forecast -> {
                        // store the exceeding values
                        if (forecast.isExceed()) {
                            forecastRepository.save(forecast);
                        }
                    });
        }

    }
}
