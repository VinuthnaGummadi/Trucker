package io.egen.trucker.service;

import io.egen.trucker.entity.Vehicle;

import java.util.List;
import java.util.Set;

public interface VehicleService {

    List<Vehicle> updateVechicles(List<Vehicle> vehicles);

    Vehicle fetchVehicle(String vim);

    List<Vehicle> fetchVehicleByAlerts(String alert, String sort);

    List<Vehicle> fetchAllVehicles();
}
