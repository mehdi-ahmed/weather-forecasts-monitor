package com.cloudator.interview.services;

import com.cloudator.interview.domain.Temperature;

import java.io.IOException;
import java.util.List;

public interface TemperatureService {

    Temperature getTemperatureByCityCode(Integer cityId) throws IOException;

    List<Temperature> getTemperatureForBulkCities(String citiesId) throws IOException;

    void saveLocationsWithExceedingTemperatures(Temperature temperature);

    Temperature findByName(String name);
}
