package io.egen.trucker.service;

import io.egen.trucker.constants.Constants;
import io.egen.trucker.entity.Alert;
import io.egen.trucker.entity.Reading;
import io.egen.trucker.entity.Tyre;
import io.egen.trucker.entity.Vehicle;
import io.egen.trucker.repository.AlertRepository;
import io.egen.trucker.repository.ReadingRepository;
import io.egen.trucker.repository.VehicleRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class ReadingServiceImpl implements ReadingService {

    private final KieContainer kieContainer;

    @Autowired
    public ReadingServiceImpl(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    @Autowired
    ReadingRepository readingRepository;

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    AlertRepository alertRepository;

    @Override
    @Transactional
    public List<Alert> injectReading(Reading reading) {
        Vehicle vehicle = vehicleRepository.findByVin(reading.getVin());
        reading.setVehicle(vehicle);
        readingRepository.save(reading);
        //get the stateful session
        KieSession kieSession = kieContainer.newKieSession("rulesSession");
        kieSession.insert(vehicle);
        kieSession.insert(reading);
        Tyre tyre = reading.getTires();
        kieSession.insert(tyre);
        List<Alert> alerts = new ArrayList<>();
        kieSession.setGlobal("alerts",alerts);
        kieSession.fireAllRules();
        kieSession.dispose();

        for(Alert alert1:alerts){
            if(alert1.getPriority()!=null && !"".equalsIgnoreCase(alert1.getPriority())){
                alert1.setTimestamp(reading.getTimestamp());
                alert1 = alertRepository.save(alert1);
            }
        }

        return alerts;
    }

    @Override
    public List<Reading> fetchVehicleByGeo(String vin, String sort) {
        List<Reading> reading = new ArrayList<>();

        reading = readingRepository.findVechiclesGeo(vin);

        if(Constants.ASC.equalsIgnoreCase(sort))
            return reading;
        else if(Constants.DESC.equalsIgnoreCase(sort)){
            Collections.reverse(reading);
            return reading;
        }else
            return reading;
    }

    @Override
    public List<Alert> fetchAlertsByVehicle(String vin) {
        List<Alert> alerts = new ArrayList<>();

        alerts = readingRepository.findByVin(vin);

        return alerts;
    }
}
