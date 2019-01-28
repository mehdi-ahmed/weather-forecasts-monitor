package com.cloudator.interview.controllers;


import com.cloudator.interview.domain.Forecast;
import com.cloudator.interview.services.ForecastService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/forecast")
public class ForecastResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemperatureResource.class);

    @Autowired
    private ForecastService forecastService;


    @RequestMapping(value = "/{city}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Forecast> getForecast(@PathVariable("city") String cityName) throws IOException {

        LOGGER.info(String.format("Looking for temperature for city '%s'...", cityName));
        return forecastService.getForecastByCity(cityName);
    }
}
