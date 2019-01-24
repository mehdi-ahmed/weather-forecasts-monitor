package com.cloudator.interview.controllers;

import com.cloudator.interview.domain.Location;
import com.cloudator.interview.domain.Temperature;
import com.cloudator.interview.services.impl.LocationsServiceImpl;
import com.cloudator.interview.services.impl.WeatherForecastsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationResources {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationResources.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");

    @Autowired
    private LocationsServiceImpl locationsService;

    @Autowired
    private WeatherForecastsServiceImpl weatherForecastsService;

    @GetMapping(value = "/listdata", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Location> getAllLocations() throws IOException {
        LOGGER.info("Retrieving all locations from file...");
        return locationsService.getAllLocations();
    }

    @GetMapping(value = "/{city}/temperature", produces = MediaType.APPLICATION_JSON_VALUE)
    public Temperature getTemperatureByCity(@PathVariable("city") String cityName) throws IOException {

        LOGGER.info(String.format("Looking for temperature for city '%s'...", cityName));
        return weatherForecastsService.getTemperatureByCity(String.valueOf(locationsService.getLocationCode(cityName)));
    }

    @Scheduled(fixedDelayString = "${rates.refresh}", initialDelay = 1000 * 10)
    @GetMapping(value = "/temperature", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Temperature> getBulkTemperatures() throws IOException {

        LOGGER.info("Looking for temperatures for all cities listed in CSV :: Execution Time - {}"
                , dateTimeFormatter.format(LocalDateTime.now()));

        return weatherForecastsService.getTemperatureForBulkCities(locationsService.concatLocationsId());

    }
}
