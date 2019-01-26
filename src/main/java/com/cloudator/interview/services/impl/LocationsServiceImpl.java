package com.cloudator.interview.services.impl;

import com.cloudator.interview.domain.Location;
import com.cloudator.interview.exception.LocationNotFoundException;
import com.cloudator.interview.services.LocationService;
import com.cloudator.interview.util.CsvReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.cloudator.interview.util.CsvReaderConst.CITY_SAMPLE_CSV_PATH;

@Service
public class LocationsServiceImpl implements LocationService {

    @Autowired
    private CsvReader csvReader;
    private List<Location> locations;

    @Override
    public List<Location> getAllLocations() throws IOException {
        return csvReader.readCsv(CITY_SAMPLE_CSV_PATH);
    }

    @Override
    public Integer getLocationCode(String cityName) {

        Location locationFound = getLocations()
                .stream()
                .filter(city -> cityName.equalsIgnoreCase(city.getName()))
                .findFirst()
                .orElse(null);

        if (locationFound == null) {
            throw new LocationNotFoundException(String.format("Location %s not found", cityName));
        }

        return locationFound.getCode();
    }

    @Override
    public String concatLocationsId() {

        String locationIds = getLocations()
                .stream().map(location -> String.valueOf(location.getCode()))
                .collect(Collectors.joining(","));


        return locationIds;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
}
