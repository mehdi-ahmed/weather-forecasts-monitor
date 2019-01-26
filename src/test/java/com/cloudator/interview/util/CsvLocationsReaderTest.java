package com.cloudator.interview.util;

import com.cloudator.interview.domain.Location;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.junit.Assert.assertEquals;

public class CsvLocationsReaderTest {

    private static final String TEST_LOCATIONS_CSV = "./src/test/resources/cities-sample-test.csv";

    @Test
    public void givenCvFile_WhenReadFile_ThenAllFieldsAreSetCorrectlyIntoObject() throws IOException {
        CsvReader reader = new CsvReader();
        List<Location> locations = reader.readCsv(TEST_LOCATIONS_CSV);

        Location helsinki = locations.stream()
                .filter(x -> "Helsinki".equals(x.getName()))
                .findAny()
                .orElse(null);

        assertEquals(locations.size(), 4);
        assertEquals(locations.isEmpty(), false);

        assertThat(helsinki, hasProperty("name", equalToIgnoringCase("Helsinki")));
        assertThat(helsinki, hasProperty("country", equalToIgnoringCase("FI")));
        assertThat(helsinki, hasProperty("code", equalTo(658225)));
        assertThat(helsinki, hasProperty("limit", equalTo(-1.0F)));


    }
}
