package io.egen.trucker.service;

import io.egen.trucker.constants.Constants;
import io.egen.trucker.entity.Alert;
import io.egen.trucker.entity.Reading;
import io.egen.trucker.entity.Tyre;
import io.egen.trucker.entity.Vehicle;
import io.egen.trucker.repository.AlertRepository;
import io.egen.trucker.repository.VehicleRepository;
import org.h2.schema.Constant;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class VehicleServiceImplTest {

    @TestConfiguration
    static class VehicleServiceImplTestConfiguration {

        @Bean
        public VehicleService getService() {
            return new VehicleServiceImpl();
        }
    }

    @Autowired
    private VehicleService service;

    @MockBean
    private VehicleRepository vehicleRepository;

    @MockBean
    private AlertRepository alertRepository;

    private List<Vehicle> vehicles;

    private List<Reading> readings;

    private List<Alert> alerts;

    @Before
    public void setup() {
        Vehicle vehicle = new Vehicle();
        vehicle.setVin("1FMYU02143KB34432");
        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date(stamp.getTime());
        vehicle.setLastServiceDate(date);
        vehicle.setMake("FORD");
        vehicle.setMaxFuelVolume(15.7);
        vehicle.setModel("ESCAPE");
        vehicle.setRedlineRpm(6500);
        vehicle.setYear(2003);

        Reading reading = new Reading();
        reading.setReadingID(154);
        reading.setCheckEngineLightOn(false);
        reading.setCruiseControlOn(false);
        reading.setEngineCoolantLow(false);
        reading.setEngineHp(186);
        reading.setEngineRpm(4651);
        reading.setFuelVolume(3);
        reading.setLatitude("-26.6513");
        reading.setLongitude("-36.5606");
        reading.setSpeed(117);
        stamp = new Timestamp(System.currentTimeMillis());
        date = new Date(stamp.getTime());
        reading.setTimestamp(date);
        reading.setVin("1FMYU02143KB34432");

        Tyre tyre = new Tyre();
        tyre.setTyreID(155);
        tyre.setFrontLeft(37);
        tyre.setFrontRight(25);
        tyre.setRearLeft(29);
        tyre.setRearRight(40);
        reading.setTires(tyre);

        Alert alert = new Alert();
        alert.setAlertID(726);
        alert.setPriority(Constants.ALERT_LOW);
        alert.setTimestamp(date);

        alerts = Collections.singletonList(alert);
        reading.setAlerts(alerts);


        vehicles = Collections.singletonList(vehicle);
        readings = Collections.singletonList(reading);

        Mockito.when(vehicleRepository.findAllVehicles())
                .thenReturn(vehicles);

        Mockito.when(vehicleRepository.findByVin(vehicle.getVin())).thenReturn(vehicle);

        Mockito.when(vehicleRepository.findByVehicleExists(vehicle)).thenReturn(true);

        Mockito.when(vehicleRepository.findByVinExists(vehicle.getVin())).thenReturn(true);

        //Mockito.when(vehicleRepository.findByVinExists(vehicle.getVin())).thenReturn(false);

        Mockito.when(alertRepository.findVechiclesByAlert(Constants.ALERT_LOW)).thenReturn(vehicles);

    }

    @After
    public void cleanup() {
    }

    @Test
    public void updateVechicles() {
    }

    @Test
    public void fetchVehicle() {
        Vehicle result = service.fetchVehicle(vehicles.get(0).getVin());
        Assert.assertEquals("vehicle should match", vehicles.get(0), result);
    }

    @Test
    public void fetchVehicleByAlerts() {
    }

    @Test
    public void fetchAllVehicles() {
        List<Vehicle> result = service.fetchAllVehicles();
        Assert.assertEquals("vehicles list should match", vehicles, result);
    }
}