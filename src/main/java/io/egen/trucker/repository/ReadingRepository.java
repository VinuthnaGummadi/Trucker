package io.egen.trucker.repository;

import io.egen.trucker.entity.Alert;
import io.egen.trucker.entity.Reading;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface ReadingRepository {
    List<Reading> findVechiclesGeo(String vin);

    List<Alert>  findByVin(String vin);

    void save(Reading reading);
}
