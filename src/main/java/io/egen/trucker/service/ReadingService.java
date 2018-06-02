package io.egen.trucker.service;

import io.egen.trucker.entity.Alert;
import io.egen.trucker.entity.Reading;

import java.util.List;
import java.util.Set;

public interface ReadingService {
    List<Alert> injectReading(Reading reading);

    List<Reading> fetchVehicleByGeo(String vin, String sort);

    List<Alert> fetchAlertsByVehicle(String vin);
}
