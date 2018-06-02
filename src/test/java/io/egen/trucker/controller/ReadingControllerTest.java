package io.egen.trucker.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@AutoConfigureMockMvc
@ActiveProfiles("integrationtest")
public class ReadingControllerTest {

    @Autowired
    private MockMvc mvc;

    @Before
    public void setup() {

    }

    @After
    public void cleanup() {

    }

    @Test
    public void injectReadings() {
        try {
            mvc.perform(MockMvcRequestBuilders.put("/readings").content("{\n" +
                    "   \"vin\": \"1HGCR2F3XFA027534\",\n" +
                    "   \"latitude\": 41.803194,\n" +
                    "   \"longitude\": -88.144406,\n" +
                    "   \"timestamp\": \"2017-05-25T17:31:25.268Z\",\n" +
                    "   \"fuelVolume\": 1.5,\n" +
                    "   \"speed\": 85,\n" +
                    "   \"engineHp\": 240,\n" +
                    "   \"checkEngineLightOn\": false,\n" +
                    "   \"engineCoolantLow\": true,\n" +
                    "   \"cruiseControlOn\": true,\n" +
                    "   \"engineRpm\": 6300,\n" +
                    "   \"tires\": {\n" +
                    "      \"frontLeft\": 34,\n" +
                    "      \"frontRight\": 36,\n" +
                    "      \"rearLeft\": 29,\n" +
                    "      \"rearRight\": 34\n" +
                    "   }\n" +
                    "}"))
                    .andExpect(MockMvcResultMatchers.status()
                            .isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fetchVehicleByGeo() {
        try {
            mvc.perform(MockMvcRequestBuilders.get("/fetchVehicleByGeo?vin=1FMYU02143KB34432&sort=ASC"))
                    .andExpect(MockMvcResultMatchers.status()
                            .isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fetchAlertsByVehicle() {
        try {
            mvc.perform(MockMvcRequestBuilders.get("/fetchAlertsByVehicle?vin=1FMYU02143KB34432"))
                    .andExpect(MockMvcResultMatchers.status()
                            .isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}