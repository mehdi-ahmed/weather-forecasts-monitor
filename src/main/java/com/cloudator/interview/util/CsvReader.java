package com.cloudator.interview.util;

import com.cloudator.interview.domain.City;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.cloudator.interview.util.CsvReaderConst.*;

public class CsvReader {

    public List<City> readCsv(String filePath, String[] header) throws IOException {

        try (
                Reader reader = Files.newBufferedReader(Paths.get(filePath));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withDelimiter('\t')
                        .withHeader(header)
                        .withIgnoreHeaderCase()
                        .withFirstRecordAsHeader());
        ) {
            List<City> cities = new ArrayList<>();

            for (CSVRecord csvRecord : csvParser) {
                City city = new City(csvRecord.get(CITY)
                        , csvRecord.get(COUNTRY)
                        , Long.valueOf(csvRecord.get(CODE))
                        , Float.valueOf(csvRecord.get(LIMIT)));

                cities.add(city);

            }
            return cities;
        }
    }
}
