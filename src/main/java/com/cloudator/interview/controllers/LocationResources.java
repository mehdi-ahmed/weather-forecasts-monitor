package com.cloudator.interview.controllers;

import com.cloudator.interview.domain.Location;
import com.cloudator.interview.services.impl.LocationsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/locations")
public class LocationResources {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationResources.class);

    @Autowired
    private LocationsServiceImpl locationsService;

    @RequestMapping(value = "/listdata", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Location>> getAllLocations() throws IOException {
        LOGGER.info("Retrieving all locations from file...");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=ISO-8859-1");
        return new ResponseEntity<>(locationsService.getAllLocations(), headers, HttpStatus.OK);
    }


}
