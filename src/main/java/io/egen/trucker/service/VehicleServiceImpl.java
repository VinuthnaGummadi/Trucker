package io.egen.trucker.service;

import io.egen.trucker.constants.Constants;
import io.egen.trucker.entity.Alert;
import io.egen.trucker.entity.Vehicle;
import io.egen.trucker.repository.AlertRepository;
import io.egen.trucker.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    AlertRepository alertRepository;

    @Override
    public List<Vehicle> updateVechicles(List<Vehicle> vehicles) {
        for (Vehicle vehicle:vehicles){
            if(!vehicleRepository.findByVehicleExists(vehicle)){
                if(vehicleRepository.findByVinExists(vehicle.getVin())){
                    vehicleRepository.update(vehicle);
                }else{
                    vehicleRepository.create(vehicle);
                }
            }
        }
        return vehicles;
    }

    @Override
    public Vehicle fetchVehicle(String vim) {
        Vehicle vehicle = vehicleRepository.findByVin(vim);
        if(vehicle!=null)
            return vehicle;
        else
            return new Vehicle();
    }

    @Override
    public List<Vehicle> fetchVehicleByAlerts(String alert, String sort) {
        List<Vehicle> vehicles = new ArrayList<>();

        vehicles = alertRepository.findVechiclesByAlert(alert);

        if(Constants.ASC.equalsIgnoreCase(sort))
         return vehicles;
        else if(Constants.DESC.equalsIgnoreCase(sort)){
            Collections.reverse(vehicles);
            return vehicles;
        }
        else
            return vehicles;
    }

    @Override
    public List<Vehicle> fetchAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles = vehicleRepository.findAllVehicles();
        return vehicles;
    }
}
