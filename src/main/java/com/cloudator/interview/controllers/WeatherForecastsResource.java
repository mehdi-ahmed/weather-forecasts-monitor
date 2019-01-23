package com.cloudator.interview.controllers;

import com.cloudator.interview.domain.City;
import com.cloudator.interview.domain.Weather;
import com.cloudator.interview.servicea.WeatherForecastsService;
import com.cloudator.interview.util.CsvReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static com.cloudator.interview.util.CsvReaderConst.CITY_SAMPLE_CSV;
import static com.cloudator.interview.util.CsvReaderConst.CSV_HEADER;

@RestController
@RequestMapping("/weather")
public class WeatherForecastsResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherForecastsResource.class);

    @Autowired
    private WeatherForecastsService weatherForecastsService;

    @GetMapping(value = "/temperature/{city}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Weather getTemperatureByCity(@PathVariable("city") String cityName) throws IOException {

        LOGGER.info(String.format("Looking for temperature for city '%s'", cityName));

        City city = findCityCode(cityName);
        return weatherForecastsService.getWeatherDetailsByCity(city.getCode());

    }


    public City findCityCode(String cityName) throws IOException {

        CsvReader reader = new CsvReader();
        List<City> cities = reader.readCsv(CITY_SAMPLE_CSV, CSV_HEADER);

        return cities
                .stream()
                .filter(city -> cityName.equalsIgnoreCase(city.getName()))
                .findFirst()
                .orElse(null);
    }
}
