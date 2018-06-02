package io.egen.trucker.repository;

import io.egen.trucker.entity.Vehicle;

import java.util.List;

public interface VehicleRepository {

    boolean findByVinExists(String vin);

    boolean findByVehicleExists(Vehicle vehicle);

    Vehicle update(Vehicle vehicle);

    Vehicle create(Vehicle vehicle);

    Vehicle findByVin(String vin);

    List<Vehicle> findAllVehicles();
}
