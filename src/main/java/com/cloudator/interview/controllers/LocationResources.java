package com.cloudator.interview.controllers;

import com.cloudator.interview.domain.Location;
import com.cloudator.interview.services.impl.LocationsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class LocationResources {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationResources.class);

    @Autowired
    private LocationsServiceImpl locationsService;

    @GetMapping(value = "/locations", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Location> getAllLocations() throws IOException {
        LOGGER.info("Retrieving all locations from file...");
        return locationsService.getAllLocations();
    }


}
