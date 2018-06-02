package io.egen.trucker.repository;

import io.egen.trucker.entity.Alert;
import io.egen.trucker.entity.Vehicle;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface AlertRepository {

    List<Vehicle> findVechiclesByAlert(String alert);

    Alert save(Alert alert);
}
