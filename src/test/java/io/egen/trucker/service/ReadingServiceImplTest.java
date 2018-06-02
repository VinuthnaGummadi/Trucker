package io.egen.trucker.service;

import io.egen.trucker.constants.Constants;
import io.egen.trucker.entity.Alert;
import io.egen.trucker.entity.Reading;
import io.egen.trucker.entity.Tyre;
import io.egen.trucker.entity.Vehicle;
import io.egen.trucker.repository.AlertRepository;
import io.egen.trucker.repository.ReadingRepository;
import io.egen.trucker.repository.VehicleRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class ReadingServiceImplTest {

    @TestConfiguration
    static class ReadingServiceImplTestConfiguration {

        @Bean
        public KieContainer kieContainer() {
            return KieServices.Factory.get().getKieClasspathContainer();
        }
        @Bean
        public ReadingService getService() {
            return new ReadingServiceImpl(kieContainer());
        }
    }

    @Autowired
    private ReadingService service;

    @MockBean
    private VehicleRepository vehicleRepository;

    @MockBean
    private AlertRepository alertRepository;

    @MockBean
    private ReadingRepository readingRepository;

    private List<Vehicle> vehicles;

    private List<Reading> readings;

    private List<Alert> alerts;

    private List<Reading> readings1;

    private List<Reading> readingCases;

    private List<Alert> alertCases;

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

        Reading reading1 = new Reading();
        reading1.setLatitude("-26.6513");
        reading1.setLongitude("-36.5606");
        reading1.setVin("1FMYU02143KB34432");

        Reading readingCase = new Reading();
        readingCase.setReadingID(154);
        readingCase.setCheckEngineLightOn(false);
        readingCase.setCruiseControlOn(true);
        readingCase.setEngineCoolantLow(true);
        readingCase.setEngineHp(186);
        readingCase.setEngineRpm(7000);
        readingCase.setFuelVolume(1.57);
        readingCase.setLatitude("-26.6513");
        readingCase.setLongitude("-36.5606");
        readingCase.setSpeed(117);
        stamp = new Timestamp(System.currentTimeMillis());
        date = new Date(stamp.getTime());
        readingCase.setTimestamp(date);
        readingCase.setVin("1FMYU02143KB34432");

        tyre = new Tyre();
        tyre.setTyreID(155);
        tyre.setFrontLeft(31);
        tyre.setFrontRight(35);
        tyre.setRearLeft(35);
        tyre.setRearRight(31);
        readingCase.setTires(tyre);

        alertCases = new ArrayList<>();
        alert = new Alert();
        alert.setPriority(Constants.ALERT_HIGH);
        alert.setTimestamp(date);
        alertCases.add(alert);
        alert = new Alert();
        alert.setPriority(Constants.ALERT_MEDIUM);
        alert.setTimestamp(date);
        alertCases.add(alert);
        alert = new Alert();
        alert.setPriority(Constants.ALERT_LOW);
        alert.setTimestamp(date);
        alertCases.add(alert);
        alert = new Alert();
        alert.setPriority(Constants.ALERT_LOW);
        alert.setTimestamp(date);
        alertCases.add(alert);

        alerts = Collections.singletonList(alert);
        readingCase.setAlerts(alertCases);


        vehicles = Collections.singletonList(vehicle);
        readings = Collections.singletonList(reading);
        readings1 = Collections.singletonList(reading1);
        readingCases = Collections.singletonList(readingCase);

        Mockito.when(vehicleRepository.findAllVehicles())
                .thenReturn(vehicles);

        Mockito.when(vehicleRepository.findByVin(vehicle.getVin())).thenReturn(vehicle);

        Mockito.when(readingRepository.findVechiclesGeo(vehicle.getVin())).thenReturn(readings1);

        Mockito.when(readingRepository.findByVin(vehicle.getVin())).thenReturn(alerts);

        Mockito.when(alertRepository.save(alert)).thenReturn(alert);

    }

    @After
    public void cleanup() {
    }


    @Test
    public void injectReading() {
        List<Alert> result = service.injectReading(readingCases.get(0));
        Assert.assertEquals("reading should match", alertCases, result);
    }

    @Test
    public void fetchVehicleByGeo() {
        List<Reading> result = service.fetchVehicleByGeo(vehicles.get(0).getVin(),Constants.ASC);
        Assert.assertEquals("reading should match", readings1, result);

        result = service.fetchVehicleByGeo(vehicles.get(0).getVin(),Constants.DESC);
        Assert.assertEquals("reading should match", readings1, result);
    }

    @Test
    public void fetchAlertsByVehicle() {
        List<Alert> result = service.fetchAlertsByVehicle(vehicles.get(0).getVin());
        Assert.assertEquals("reading should match", alerts, result);
    }
}