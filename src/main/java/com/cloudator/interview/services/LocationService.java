package com.cloudator.interview.services;

import com.cloudator.interview.domain.Location;

import java.io.IOException;
import java.util.List;

public interface LocationService {

    List<Location> getAllLocations() throws IOException;

    long getLocationCode(String locationName) throws IOException;

    String concatLocationsId();
}
