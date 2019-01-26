package com.cloudator.interview.service;

import com.cloudator.interview.domain.Location;
import com.cloudator.interview.exception.LocationNotFoundException;
import com.cloudator.interview.services.impl.LocationsServiceImpl;
import com.cloudator.interview.util.CsvReader;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class LocationsServiceImplTest {

    public static final String FAKE_CITY = "FakeCity";
    public static final String HELSINKI = "Helsinki";
    public static final String DUMMY_CITY_FROM_FILE = "dummyCity1";
    public static final String CITIES_SAMPLES_TEST = "./src/test/resources/cities-sample-test.csv";

    private static LocationsServiceImpl locationsService;

    @BeforeClass
    public static void init() throws IOException {
        CsvReader reader = new CsvReader();
        List<Location> locations = reader.readCsv(CITIES_SAMPLES_TEST);
        locationsService = new LocationsServiceImpl();
        locationsService.setLocations(locations);
    }

    @Test
    public void whenLocationDoesNotExist_Expect_LocationNotFoundException() {

        Assertions.assertThrows(LocationNotFoundException.class, () -> {
            locationsService.getLocationCode(FAKE_CITY);
        });
    }

    @Test
    public void whenLocationExist_Expect_CorrectLocationCode() {
        Integer codeHelsinki = locationsService.getLocationCode(HELSINKI);
        assertEquals(codeHelsinki, Integer.valueOf(658225));

        Integer codeDummyCity = locationsService.getLocationCode(DUMMY_CITY_FROM_FILE);
        assertEquals(codeDummyCity, Integer.valueOf(123456));
    }

    @Test
    public void whenConcatLocationsId_Expect_IdsConcatenated() {
        assertEquals(locationsService.concatLocationsId(), "658225,123456,999999,777777");
    }

}
