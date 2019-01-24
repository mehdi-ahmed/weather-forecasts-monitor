package com.cloudator.interview.util;

import com.cloudator.interview.domain.Location;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.cloudator.interview.util.CsvReaderConst.*;

@Component
public class CsvReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvReader.class);

    public List<Location> readCsv(String filePath, String[] header) throws IOException {

        LOGGER.info("Reading locations from CSV File...");
        try (
                Reader reader = Files.newBufferedReader(Paths.get(filePath));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withDelimiter('\t')
                        .withHeader(header)
                        .withIgnoreHeaderCase()
                        .withFirstRecordAsHeader())
        ) {
            List<Location> locations = new ArrayList<>();

            for (CSVRecord csvRecord : csvParser) {
                Location city = new Location(csvRecord.get(CITY)
                        , csvRecord.get(COUNTRY)
                        , Long.valueOf(csvRecord.get(CODE))
                        , Float.valueOf(csvRecord.get(LIMIT)));

                locations.add(city);

            }
            return locations;
        }
    }
}
