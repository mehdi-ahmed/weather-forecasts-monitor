package com.cloudator.interview.controllers;

import com.cloudator.interview.domain.Temperature;
import com.cloudator.interview.services.impl.LocationsServiceImpl;
import com.cloudator.interview.services.impl.TemperatureServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
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
@RequestMapping("/temperature")
public class TemperatureResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemperatureResource.class);

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");

    @Autowired
    private TemperatureServiceImpl temperatureService;

    @Autowired
    private LocationsServiceImpl locationsService;


    @GetMapping(value = "/{city}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Temperature getTemperatureByCity(@PathVariable("city") String cityName) throws IOException {

        LOGGER.info(String.format("Looking for temperature for city '%s'...", cityName));
        return temperatureService.getTemperatureByCityCode(locationsService.getLocationCode(cityName));
    }


    @GetMapping(value = "/bulk", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Temperature> getBulkTemperatures() throws IOException {
        return temperatureService.getTemperatureForBulkCities(locationsService.concatLocationsId());
    }

    @Async
    //@Scheduled(fixedDelayString = "${rates.refresh}", initialDelay = 1000 * 10)
    //@Scheduled(cron = "${cron.expression}", zone = "Europe/Helsinki")
    @Scheduled(cron = "${cron.expression}", zone = "Europe/Helsinki")
    // @Scheduled(cron = "1 * * * * *", zone="Europe/Helsinki")
    @GetMapping(value = "/bulk/exceeds", produces = MediaType.APPLICATION_JSON_VALUE)
    public void saveExceedingTemperatures() throws IOException {

        LOGGER.info("Looking for temperatures for all cities listed in CSV :: Execution Time - {}"
                , dateTimeFormatter.format(LocalDateTime.now()));

        List<Temperature> bulkCitiesTemperatures =
                temperatureService.getTemperatureForBulkCities(locationsService.concatLocationsId());

        // store the exceeding values
        for (Temperature temperature : bulkCitiesTemperatures) {
            if (temperature.isExceeds()) {
                temperatureService.saveLocationsWithExceedingTemperatures(temperature);
            }
        }
    }

    @GetMapping(value = "/bulk/exceeds/stored", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Temperature> findAllExceedingTemperatureFromDB() {
        return temperatureService.getAllLocationsWithExceedingTemperatures();
    }


}
