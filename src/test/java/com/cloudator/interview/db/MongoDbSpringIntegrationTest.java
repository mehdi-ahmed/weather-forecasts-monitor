package com.cloudator.interview.db;

import com.cloudator.interview.domain.Temperature;
import com.cloudator.interview.repository.TemperatureRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MongoDbSpringIntegrationTest {

    @Autowired
    private TemperatureRepository temperatureRepository;

    @Test
    public void whenFindAllTemperaturesFromDBThatExceedsLinit_Expect_ListWithValidData() {

        //Check data from DB
        List<Temperature> found = temperatureRepository.findAll();
        assertThat(found).extracting(Temperature::getCityName)
                .contains("Helsinki")
                .contains("Paris")
                .contains("Oslo")
                .contains("Tunis")
                .contains("Moscow")
                .contains("Moscow");
    }

    @Test
    public void givenACityName_Expect_findTemperaturesThatExceedsFromDB_ByCityName() {

        Temperature helsinkiExceeds = temperatureRepository.findByCityName("Helsinki");
        assertThat(helsinkiExceeds.isExceeds() == true);
        assertThat(helsinkiExceeds.getId() == 658225);
    }

}
