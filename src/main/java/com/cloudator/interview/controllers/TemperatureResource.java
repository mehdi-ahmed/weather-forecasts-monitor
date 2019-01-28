package com.cloudator.interview.controllers;

import com.cloudator.interview.domain.Temperature;
import com.cloudator.interview.services.impl.LocationsServiceImpl;
import com.cloudator.interview.services.impl.TemperatureServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/temperature")
@CrossOrigin(origins = "http://localhost:4200")
public class TemperatureResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemperatureResource.class);

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");

    @Autowired
    private TemperatureServiceImpl temperatureService;

    @Autowired
    private LocationsServiceImpl locationsService;


    /*@GetMapping(value = "/{city}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Temperature getTemperatureByCity(@PathVariable("city") String cityName) throws IOException {

        LOGGER.info(String.format("Looking for temperature for city '%s'...", cityName));
        return temperatureService.getTemperatureByCityCode(locationsService.getLocationCode(cityName));
    }*/

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Temperature> getAllTemperatures() throws IOException, URISyntaxException {

        LOGGER.info("Looking for all temperatures from the Weather API :: Execution Time");
        return temperatureService.getBulkTemperatures(locationsService.concatLocationsId());
    }

    @Scheduled(cron = "${cron.expression}", zone = "Europe/Helsinki")
    public void saveExceedingTemperatures() throws IOException, URISyntaxException {

        LOGGER.info("Saving locations with exceeding temperatures:: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));

        List<Temperature> bulkCitiesTemperatures =
                temperatureService.getBulkTemperatures(locationsService.concatLocationsId());

        // store the exceeding values
        for (Temperature temperature : bulkCitiesTemperatures) {
            if (temperature.isExceeds()) {
                temperatureService.saveLocationsWithExceedingTemperatures(temperature);
            }
        }
    }
}
