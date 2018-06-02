package io.egen.trucker.controller;

import io.egen.trucker.entity.Vehicle;
import io.egen.trucker.repository.VehicleRepository;
import org.hamcrest.Matchers;
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

import java.sql.Date;
import java.sql.Timestamp;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@AutoConfigureMockMvc
@ActiveProfiles("integrationtest")
public class VehicleControllerTest {

    @Autowired
    private MockMvc mvc;

    @Before
    public void setup() {

    }

    @After
    public void cleanup() {

    }

    @Test
    public void updateVehicles() {
        try {
            mvc.perform(MockMvcRequestBuilders.get("/vehicles").content("[\n" +
                    " {\n" +
                    "    \"vin\": \"1HGCR2F3XFA027534\",\n" +
                    "    \"make\": \"HONDA\",\n" +
                    "    \"model\": \"ACCORD\",\n" +
                    "    \"year\": 2015,\n" +
                    "    \"redlineRpm\": 5500,\n" +
                    "    \"maxFuelVolume\": 15,\n" +
                    "    \"lastServiceDate\": \"2017-05-25T17:31:25.268Z\"\n" +
                    " },\n" +
                    " {\n" +
                    "    \"vin\": \"WP1AB29P63LA60179\",\n" +
                    "    \"make\": \"PORSCHE\",\n" +
                    "    \"model\": \"CAYENNE\",\n" +
                    "    \"year\": 2015,\n" +
                    "    \"redlineRpm\": 8000,\n" +
                    "    \"maxFuelVolume\": 18,\n" +
                    "    \"lastServiceDate\": \"2017-03-25T17:31:25.268Z\"\n" +
                    " }\n" +
                    "]"))
                    .andExpect(MockMvcResultMatchers.status()
                            .isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fetchVehicles() {
        try {
            mvc.perform(MockMvcRequestBuilders.get("/fetchVehicles?vin=1FMYU02143KB34432"))
                    .andExpect(MockMvcResultMatchers.status()
                            .isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fetchAllVehicles() {
        try {
            mvc.perform(MockMvcRequestBuilders.get("/fetchAllVehicles"))
                    .andExpect(MockMvcResultMatchers.status()
                            .isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fetchVehiclesByAlerts() {
        try {
            mvc.perform(MockMvcRequestBuilders.get("/fetchVehiclesByAlerts?alert=LOW&sort=ASC"))
                    .andExpect(MockMvcResultMatchers.status()
                            .isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}